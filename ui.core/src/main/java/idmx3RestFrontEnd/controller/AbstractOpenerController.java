package idmx3RestFrontEnd.controller;

import java.util.EventListener;
import java.util.EventObject;

import idmx3RestFrontEnd.view.ViewContainer;
import idmx3RestFrontEnd.view.ViewException;

/**
 * This class is a helper for opening new views with the less coding possible
 * 
 * @author Mario Garcia
 *
 * @param <EL> EventListener
 * @param <EO> EventObject
 */
public abstract class AbstractOpenerController<EL extends EventListener,EO extends EventObject>
	extends AbstractViewController<EL,EO>{

	private ViewContainer viewToOpen;
	
	/**
	 * The constructor has one argument which is the view the user
	 * wants to interact with.
	 * 
	 * @param view2Open
	 */
	public AbstractOpenerController(ViewContainer view2Open){
		this.viewToOpen = view2Open;
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.controller.AbstractViewController#postHandlingView(idmx3RestFrontEnd.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	public void postHandlingView(ViewContainer view, EO eventObject) throws ViewException{
		getViewManager().addView(this.viewToOpen);
	}
}
