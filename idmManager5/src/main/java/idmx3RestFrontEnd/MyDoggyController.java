package idmx3RestFrontEnd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;
import java.util.EventObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idmx3RestFrontEnd.controller.AbstractViewController;
import idmx3RestFrontEnd.controller.BackgroundException;
import idmx3RestFrontEnd.view.ViewContainer;
import idmx3RestFrontEnd.view.ViewException;
import idmx3RestFrontEnd.view.event.ViewContainerEvent;



public class MyDoggyController  extends AbstractViewController<MouseListener, MouseEvent> {
	private static final Logger logger = LoggerFactory.getLogger(MyDoggyController.class);
	public MyDoggyController() {
		debugJustInCase("certification_constructor_called");
		
		
		
		
	}
	
	private void debugJustInCase(String message) {
		if (logger.isInfoEnabled()) {
			logger.info(message);
		}
	}
	@Override
	public Class<MouseListener> getSupportedClass() {
		// TODO Auto-generated method stub
		return MouseListener.class;
	}
	public void handleView(ViewContainer view, ActionEvent eventObject) throws BackgroundException{
		debugJustInCase("certification report_handle view");
	
		
	}
	public void preHandlingView(ViewContainer view, ActionEvent eventObject) throws ViewException { 
		debugJustInCase("certification report_handle view");
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.viewaframework.controller.AbstractViewController#postHandlingView
	 * (org.viewaframework.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	public void postHandlingView(ViewContainer view, MouseEvent eventObject) throws ViewException {
		debugJustInCase("certification report_post_handling_view");
		/*String actionCommand = eventObject.getActionCommand();
		debugJustInCase("actionCommand:"+actionCommand);
		if(actionCommand.equals("Refresh")) {
			debugJustInCase("actionCommand:"+actionCommand);	
			ViewContainerEvent event=new ViewContainerEvent(view);
			view.fireViewInit(event);
		}
		*/
	}
}
