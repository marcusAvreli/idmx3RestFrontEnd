package idmx3RestFrontEnd.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import idmx3RestFrontEnd.controller.AbstractViewController;
import idmx3RestFrontEnd.view.ViewContainer;

/**
 * This class is a helper mouse controller. You can dispatch the
 * actions establishing at startup the number of clicks the
 * controller is going to listen at.
 * 
 * @author Mario Garcia
 *
 */
public abstract class AbstractMouseClickController extends AbstractViewController<MouseListener,MouseEvent>{

	private int numberOfClicks;
	
	/**
	 * 
	 */
	public AbstractMouseClickController(){
		super();
		this.numberOfClicks = 1;
	}
	
	/**
	 * @param numberOfClicks
	 */
	public AbstractMouseClickController(int numberOfClicks){
		super();
		this.numberOfClicks = numberOfClicks;
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.controller.AbstractViewController#executeHandler(idmx3RestFrontEnd.view.ViewContainer, java.util.EventObject)
	 */
	@Override
	public void executeHandler(ViewContainer view,MouseEvent eventObject) {		
		if (eventObject.getID() == MouseEvent.MOUSE_CLICKED && eventObject.getClickCount() == this.numberOfClicks){
			super.executeHandler(view, eventObject);
		}
	}

	/**
	 * @return
	 */
	public int getNumberOfClicks() {
		return numberOfClicks;
	}
	
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.controller.ViewController#getSupportedClass()
	 */
	public Class<MouseListener> getSupportedClass() {
		return MouseListener.class;
	}
}
