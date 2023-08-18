package idmx3RestFrontEnd.annotation.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idmx3RestFrontEnd.annotation.Listener;
import idmx3RestFrontEnd.annotation.Listeners;
import idmx3RestFrontEnd.core.ApplicationContext;
import idmx3RestFrontEnd.ioc.IOCContext;
import idmx3RestFrontEnd.view.ViewContainer;
import idmx3RestFrontEnd.view.event.ViewContainerEventController;

public class ListenersProcessor implements AnnotationProcessor<List<ViewContainerEventController>>{
	private static final Logger logger = LoggerFactory.getLogger(ListenersProcessor.class);
//	private static final Log logger = LogFactory.getLog(ListenersProcessor.class);
	private ViewContainer view;
	private List<ViewContainerEventController> listeners;
	
	public ListenersProcessor(ViewContainer view){
		this.view = view;
		this.listeners = new ArrayList<ViewContainerEventController>();
	}
	
	@Override
	public void process() throws Exception {
		Listeners listeners = view.getClass().getAnnotation(Listeners.class);
		ApplicationContext ctx = this.view.getApplication().getApplicationContext();
		IOCContext ioc = (IOCContext)ctx.getAttribute(IOCContext.ID);
		for (Listener l : listeners.value()){
			ViewContainerEventController listener = 
				ViewContainerEventController.class.cast(
					ioc != null ? 
						ioc.getBean(l.id()) : 
						l.type().newInstance()
					);
			this.listeners.add(listener);
		}
	}

	@Override
	public List<ViewContainerEventController> getResult() {
		try {
			this.process();
		} catch (Exception e) {
			logger.error(
				new StringBuilder("Cant process listeners from: ").
					append(this.view.getClass().getSimpleName()).
					append(" [").append(e.getMessage()).append("]").toString());
		}
		return this.listeners;
	}

}
