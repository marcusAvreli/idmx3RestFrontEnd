package idmx3RestFrontEnd.view;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JRootPane;

import idmx3RestFrontEnd.util.ViewTrayIcon;
import idmx3RestFrontEnd.view.delegator.DefaultViewResourceDelegator;
import idmx3RestFrontEnd.view.delegator.Delegator;
import idmx3RestFrontEnd.view.delegator.NamedComponentsDelegator;
import idmx3RestFrontEnd.view.delegator.TrayActionDescriptorDelegator;
import idmx3RestFrontEnd.view.delegator.ViewContainerControllerDelegator;

/**
 * This class creates a tray icon in the system tray. It's based in the JDK 1.6 TrayIcon object.
 * 
 * @author Mario Garcia
 * @since 1.0.4
 *
 */
public abstract class AbstractViewContainerTray extends DefaultViewContainer{

	private List<Delegator> delegators;
	private ViewTrayIcon trayIcon;
	
	/**
	 * Constructor
	 * 
	 * @param Id
	 */
	public AbstractViewContainerTray(String Id){
		super();
		this.setId(Id);
		this.setContentPane(new JRootPane());
	}
	
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.view.AbstractViewContainer#getDelegators()
	 */
	@Override
	public List<Delegator> getDelegators() {
		if (this.delegators == null){
			this.delegators = new ArrayList<Delegator>(Arrays.asList(
			 /* This delegator is responsible for creating the contextual menus*/
				new TrayActionDescriptorDelegator(),
				new NamedComponentsDelegator(),
				new ViewContainerControllerDelegator(),
				new DefaultViewResourceDelegator()));
		}
		return delegators;
	}
	
	/**
	 * Returns the current tray icon's image
	 * 
	 * @return
	 */
	public Image getImage(){
		return this.trayIcon.getImage();
	}
	
	/**
	 * Returns the current tray icon's toolTip
	 * 
	 * @return
	 */
	public String getToolTip(){
		return this.trayIcon.getToolTip();
	}
	
	/**
	 * Returns the tray icon object
	 * 
	 * @return
	 */
	public ViewTrayIcon getTrayIcon() {
		return trayIcon;
	}
	
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.view.AbstractViewContainer#setDelegators(java.util.List)
	 */
	@Override
	public void setDelegators(List<Delegator> delegators) {
		this.delegators = delegators;
	}

	/**
	 * Exposing the setImage method from TrayIcon
	 * 
	 * @param icon
	 */
	public void setImage(Image icon){
		this.trayIcon.setImage(icon);
	}

	/**
	 * Exposing the setToolTip method from TrayIcon
	 * 
	 * @param toolTip
	 */
	public void setToolTip(String toolTip){
		this.trayIcon.setToolTip(toolTip);
	}
	
	/**
	 * Establishing the TrayIcon
	 * 
	 * @param trayIcon
	 */
	public void setTrayIcon(ViewTrayIcon trayIcon) {
		this.trayIcon = trayIcon;
	}
}
