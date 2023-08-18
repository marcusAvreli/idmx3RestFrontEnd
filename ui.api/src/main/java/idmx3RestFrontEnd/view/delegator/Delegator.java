package idmx3RestFrontEnd.view.delegator;

import idmx3RestFrontEnd.view.ViewContainer;
import idmx3RestFrontEnd.view.ViewException;

public interface Delegator {
	
	public void inject(ViewContainer viewContainer) throws ViewException;
	public void clean(ViewContainer viewContainer)throws ViewException;

}
