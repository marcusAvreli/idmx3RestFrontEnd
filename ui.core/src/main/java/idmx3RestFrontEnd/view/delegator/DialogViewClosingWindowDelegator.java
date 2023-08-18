package idmx3RestFrontEnd.view.delegator;



import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;

import idmx3RestFrontEnd.controller.DefaultWindowController;
import idmx3RestFrontEnd.controller.ViewController;
import idmx3RestFrontEnd.view.ViewContainer;
import idmx3RestFrontEnd.view.ViewException;

/**
 * @author mario
 *
 */
public class DialogViewClosingWindowDelegator implements Delegator{

	private static final String POINT = ".";
	private String componentName;
	
	/**
	 * @param componentName
	 */
	public DialogViewClosingWindowDelegator(String componentName){
		this.componentName = componentName;
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.view.delegator.Delegator#clean(idmx3RestFrontEnd.view.ViewContainer)
	 */
	public void clean(ViewContainer arg0) throws ViewException {
		arg0.getViewControllerMap().get(arg0.getId() + POINT + getComponentName()).clear();		
	}

	/**
	 * @return
	 */
	public String getComponentName() {
		return componentName;
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.view.delegator.Delegator#inject(idmx3RestFrontEnd.view.ViewContainer)
	 */
	public void inject(ViewContainer arg0) throws ViewException {
		Object list = arg0.getViewControllerMap().get(arg0.getId() + POINT + getComponentName());
		if (list == null){
			List<ViewController<? extends EventListener,? extends EventObject>> listilla =
				new ArrayList<ViewController<? extends EventListener,? extends EventObject>>();
			listilla.add(new DefaultWindowController());
			arg0.getViewControllerMap().put(
					arg0.getId() + POINT + getComponentName(),
					listilla);
		}
	}
}
