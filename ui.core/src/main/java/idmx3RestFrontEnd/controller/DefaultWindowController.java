package idmx3RestFrontEnd.controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idmx3RestFrontEnd.view.ViewContainer;
import idmx3RestFrontEnd.view.ViewException;

/**
 * This controller just closes the view when the user push the x button of the window. It is 
 * usually injected in dialog or root views by {@idmx3RestFrontEnd.view.delegator.DialogViewClosingWindowDelegator}.
 * 
 * @author Mario Garcia
 *
 */
public class DefaultWindowController extends AbstractViewController<WindowListener,WindowEvent>{
	private static final Logger logger = LoggerFactory.getLogger(DefaultWindowController.class);
	//private static final Log logger = LogFactory.getLog(DefaultWindowController.class);
	
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.controller.ViewController#getSupportedClass()
	 */
	public Class<WindowListener> getSupportedClass() {
		return WindowListener.class;
	}
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.controller.AbstractViewController#handleView(idmx3RestFrontEnd.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	public void handleView(ViewContainer view, WindowEvent eventObject) {
		try {
			if (eventObject.getID() == WindowEvent.WINDOW_CLOSING){
				view.getApplication().getViewManager().removeView(view);
			}
		} catch (ViewException e) {
			logger.error("Exception while closing dialog: "+e.getMessage());
		}
	}		
}