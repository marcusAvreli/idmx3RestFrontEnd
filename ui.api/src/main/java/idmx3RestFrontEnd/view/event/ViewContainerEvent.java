package idmx3RestFrontEnd.view.event;

import idmx3RestFrontEnd.view.ViewContainer;

/**
 * @author Mario García
 * @since 1.0
 *
 */
public class ViewContainerEvent {
	
	private ViewContainer source;

	/**
	 * @param view
	 */
	public ViewContainerEvent(ViewContainer view){
		this.source = view;;
	}
	
	/**
	 * @return
	 */
	public ViewContainer getSource() {
		return source;
	}

	/**
	 * @param source
	 */
	public void setSource(ViewContainer source) {
		this.source = source;
	}

}
