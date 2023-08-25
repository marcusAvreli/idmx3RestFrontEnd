package idmx3RestFrontEnd;

import java.awt.Component;
import java.awt.Container;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

import idmx3RestFrontEnd.view.ViewContainer;
import idmx3RestFrontEnd.view.ViewManagerException;
import idmx3RestFrontEnd.view.perspective.DefaultPerspective;
import idmx3RestFrontEnd.view.perspective.Perspective;
import idmx3RestFrontEnd.view.perspective.PerspectiveConstraint;

public class MyPerspective implements Perspective{
	public static final String DEFAULT_PERSPECTIVE_ID = "MyPepspective";
	private String id;
	private JSplitPane topBottom = new JSplitPane();
	private javax.swing.JPanel leftRootView;
    private javax.swing.JPanel rightRootView;
    private javax.swing.JSplitPane splitPane;
   
    private Map<Object,ViewContainer> views;	
   
	@Override
	public void addView(ViewContainer view) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		this.addView(view,null);		
	}

	@Override
	public void addView(ViewContainer view, PerspectiveConstraint arg1) {
		// TODO Auto-generated method stub
		this.getViews().put(view.getId(),view);
		Component 	component 		= view.getRootPane();		
		
		  splitPane = new javax.swing.JSplitPane();
	        leftRootView = new javax.swing.JPanel();
	        rightRootView = new javax.swing.JPanel();

	     

	        splitPane.setDividerLocation(200);

	        leftRootView.setLayout(new java.awt.BorderLayout());
	        splitPane.setLeftComponent(leftRootView);

	        rightRootView.setLayout(new java.awt.BorderLayout());
	        splitPane.setRightComponent(rightRootView);
		
	        rightRootView.add(component);
	}

	@Override
	public Map<Object, ViewContainer> getViews() {
		// TODO Auto-generated method stub
		if (this.views == null){
			this.views = new HashMap<Object, ViewContainer>();
		}
		return this.views;
	}

	@Override
	public void removeView(ViewContainer view) {
		// TODO Auto-generated method stub
		ViewContainer 	viewContainer 	= ((ViewContainer)this.getViews().get(view.getId()));
		Component 		component 		= viewContainer!=null ? viewContainer.getRootPane() : null;
		if (component!=null){
			try {	
				JComponent panel = JComponent.class.cast(this.arrange());						
				//List<Component> navigationElements = Arrays.asList(this.navigationPanel.getComponents());
				//List<Component> editorElements = Arrays.asList(this.editionPanel.getComponents());
				//List<Component> auxElements = Arrays.asList(this.auxiliaryPanel.getComponents());
				JComponent component2Delete = view.getRootPane();
				//if (navigationElements.contains(component2Delete)){
				//	navigationPanel.remove(component2Delete);
					//if (navigationPanel.getTabCount() == 0){
						//this.rightToLeft.resetToPreferredSizes();
						this.topBottom.resetToPreferredSizes();
						//this.rightToLeft.getLeftComponent().setVisible(true);
						//this.rightToLeft.getRightComponent().setVisible(true);
						this.topBottom.getTopComponent().setVisible(true);
						this.topBottom.getBottomComponent().setVisible(true);
					//}
				//} else if (editorElements.contains(component2Delete)){
					//editionPanel.remove(component2Delete);
					//if (editionPanel.getTabCount() == 0){
						//this.rightToLeft.resetToPreferredSizes();
						this.topBottom.resetToPreferredSizes();
						//this.rightToLeft.getLeftComponent().setVisible(true);
						this.topBottom.getTopComponent().setVisible(true);
						this.topBottom.getBottomComponent().setVisible(true);
					//}
				//} else if (auxElements.contains(component2Delete)){
					//auxiliaryPanel.remove(component2Delete);
					//if (auxiliaryPanel.getTabCount() == 0){
						//this.rightToLeft.resetToPreferredSizes();
						this.topBottom.resetToPreferredSizes();
						//this.rightToLeft.getLeftComponent().setVisible(true);
						this.topBottom.getTopComponent().setVisible(true);						
						this.topBottom.getBottomComponent().setVisible(false);
					//}
				//}			
				getViews().remove(view.getId());		
				panel.validate();
				panel.repaint();
			} catch (ViewManagerException e) {
				e.printStackTrace();
			}			
		}
	}

	@Override
	public void setViews(Map<Object, ViewContainer> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Container arrange() throws ViewManagerException {
		// TODO Auto-generated method stub
		return splitPane;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getId() {
		if (this.id == null){
			this.id = DefaultPerspective.DEFAULT_PERSPECTIVE_ID;
		}
		return this.id;
	}

	@Override
	public void setId(String arg0) {
		// TODO Auto-generated method stub
		this.id = id;
	}

}
