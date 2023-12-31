package idmx3RestFrontEnd.core;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.swing.JFrame;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idmx3RestFrontEnd.annotation.processor.AnnotationProcessor;
import idmx3RestFrontEnd.annotation.processor.ViewsPerspectiveProcessor;
import idmx3RestFrontEnd.annotation.processor.ViewsProcessor;
import idmx3RestFrontEnd.annotation.processor.ViewsProcessorWrapper;
import idmx3RestFrontEnd.controller.DefaultViewControllerDispatcher;
import idmx3RestFrontEnd.controller.DefaultWindowController;
import idmx3RestFrontEnd.controller.ViewControllerDispatcher;
import idmx3RestFrontEnd.controller.ViewControllerDispatcherException;
import idmx3RestFrontEnd.model.AbstractViewModelManager;
import idmx3RestFrontEnd.model.ViewModelManager;
import idmx3RestFrontEnd.view.DefaultViewManager;
import idmx3RestFrontEnd.view.ViewContainer;
import idmx3RestFrontEnd.view.ViewContainerFrame;
import idmx3RestFrontEnd.view.ViewException;
import idmx3RestFrontEnd.view.ViewManager;
import idmx3RestFrontEnd.view.ViewManagerException;
import idmx3RestFrontEnd.view.perspective.DefaultPerspective;
import idmx3RestFrontEnd.view.perspective.Perspective;

/**
 * This is a default implementation of an Application which has
 * a <code>ViewControllerDispatcher</code> as well as a
 * <code>ViewManager</code>.
 * 
 * @author Mario Garcia
 * @since 1.0
 *
 */
public abstract class AbstractApplication implements Application
{
	private ApplicationContext			applicationContext;
	private List<ApplicationListener>	applicationListeners;
	private ViewControllerDispatcher 	dispatcher;
	private Locale						locale;
	//private Log logger = LogFactory.getLog(AbstractApplication.class);
	private static final Logger logger = LoggerFactory.getLogger(AbstractApplication.class);
	private String 						name;
	private ViewContainerFrame			rootView;
	private ViewManager 				viewManager;
	private ViewModelManager			viewModelManager;
	private List<ViewsProcessorWrapper> initViews;
	
	/**
	 * 
	 */
	public AbstractApplication(){
		this.dispatcher 			= new DefaultViewControllerDispatcher();
		this.applicationListeners 	= new ArrayList<ApplicationListener>();
		this.viewManager 			= new DefaultViewManager(this,new DefaultPerspective());
	}
	/**
	 * @param name
	 * @param mainView
	 */
	public AbstractApplication(String name,ViewContainerFrame mainView){
		this();
		this.setName(name);
		this.setRootView(mainView);
	}
	/**
	 * This constructor sets the default root view
	 * 
	 * @param mainView
	 */
	public AbstractApplication(ViewContainerFrame mainView){
		this();
		this.setRootView(mainView);
	}
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.core.ApplicationListenerAware#addApplicationListener(idmx3RestFrontEnd.core.ApplicationListener)
	 */
	public void addApplicationListener(ApplicationListener listener) {
		this.applicationListeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.core.Application#close()
	 */
	public void close() {
		logger.debug("Application closing!");
		this.fireClose(new ApplicationEvent());
	}
	
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.core.ApplicationListenerAware#fireClose(idmx3RestFrontEnd.core.ApplicationEvent)
	 */
	public void fireClose(ApplicationEvent event) {
	 /* First we execute all related listeners */
		for (ApplicationListener applicationListener : this.applicationListeners){
			applicationListener.onClose(event);
			/* Some kind of vetoableCloseException should being created. If any view
			 * throws an exception like that closing process should be aborted otherwise
			 * the application finishes even if there's other type of exceptions.*/
		}
	 /* Finally we close all children views */
		Collection<ViewContainer> views = this.getViewManager().getViews().values();
	 /* Except the root view */
		ViewContainerFrame viewContainerFrame = this.getViewManager().getRootView();
	 /* Closing all children views */
		for (ViewContainer view:views){
			if (view != viewContainerFrame)
				try {
					/* This should be view.viewSave(); or persist just the view collection from the
					 * application. Next time application starts the collection will be retrieved
					 * again and the collections could be added to the application. */
					view.viewClose();
				} catch (ViewException e) {
					throw new RuntimeException(e);
				}
		}		
	 /* And finally we close the root view */
		viewContainerFrame.getFrame().dispose();
	}
	
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.core.ApplicationListenerAware#fireinitUI(idmx3RestFrontEnd.core.ApplicationEvent)
	 */
	public void fireinitUI(ApplicationEvent event) {
		for (ApplicationListener l : this.applicationListeners){
			l.onInitUI(event);
		}
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.core.ApplicationListenerAware#firePrepare(idmx3RestFrontEnd.core.ApplicationEvent)
	 */
	public void firePrepare(ApplicationEvent event) {
		for (ApplicationListener l : this.applicationListeners){
			l.onPrepare(event);
		}
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.core.ApplicationListenerAware#firePrepareUI(idmx3RestFrontEnd.core.ApplicationEvent)
	 */
	public void firePrepareUI(ApplicationEvent event) {
		for (ApplicationListener l : this.applicationListeners){
			l.onPrepareUI(event);
		}
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.core.ApplicationContextAware#getApplicationContext()
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.core.ApplicationListenerAware#getApplicationListeners()
	 */
	public List<ApplicationListener> getApplicationListeners() {
		return applicationListeners;
	}

	/* (non-Javadoc)
	 * @see org.viewa.core.ApplicationBase#getControllerDispatcher()
	 */
	public ViewControllerDispatcher getControllerDispatcher() {
		return this.dispatcher;
	}

	public Locale getLocale() {
		if (this.locale == null){
			this.locale = Locale.ROOT;
		}
		return locale;
	}

	/* (non-Javadoc)
	 * @see org.viewa.core.ApplicationBase#getName()
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return
	 */
	public ViewContainerFrame getRootView() {
		return rootView;
	}

	/* (non-Javadoc)
	 * @see org.viewa.core.Application#getViewManager()
	 */
	public ViewManager getViewManager() {
		return viewManager;
	}
	
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.model.ViewModelManagerAware#getViewModelManager()
	 */
	public ViewModelManager getViewModelManager() {
		if (this.viewModelManager == null){
			this.viewModelManager = new AbstractViewModelManager();
		}
		return this.viewModelManager;
	}
	

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.core.Application#hideOrRestore()
	 */
	public boolean hideOrRestore(){
		boolean shouldBeVisible = !this.isVisible();
		this.setVisible(shouldBeVisible);
		return shouldBeVisible;
	}

	/* (non-Javadoc)
	 * @see org.viewa.core.Application#initUI()
	 */
	public void initUI() throws ApplicationException{
		try{
			logger.debug("Application UI initializing!");
			ViewManager viewManager = this.getViewManager();
			Component 	view 		= viewManager.arrangeViews();

			view.setVisible(true);
		} catch (ViewManagerException vme){
		  /* since 1.0.2 */
			throw new ApplicationException(vme);
		}
	}
	
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.core.Application#isVisible()
	 */
	public boolean isVisible(){
		JFrame frame = this.getRootView().getFrame();
		boolean result = frame!=null && frame.isVisible();
		return result;
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.core.Application#prepare()
	 */
	@SuppressWarnings("unchecked")
	public void prepare() throws ApplicationException{
		try {
			logger.debug("Application preparing!");
			/* If there's no custom applicationContext then a default implementation is provided (v1.0.2) */
			if (this.applicationContext == null){
				this.applicationContext = new DefaultApplicationContext();
			}
		 /* Now any application annotation is going to be processed (v1.0.2) */
			AnnotationProcessor viewsProcessor = new ViewsProcessor(this);
			AnnotationProcessor viewsPerspectiveProcessor = new ViewsPerspectiveProcessor(this);
		 /* Executing processors */
			viewsProcessor.process();
			viewsPerspectiveProcessor.process();
		 /* Getting process results */
			Perspective annotationPerspective  = Perspective.class.cast(viewsPerspectiveProcessor.getResult());
		 /* If there's a valid annotation perspective it's set as default */
			if (annotationPerspective != null){
				this.getViewManager().setPerspective(annotationPerspective);
			}
			initViews = List.class.cast(viewsProcessor.getResult());
		} catch (Exception e) {		 
			throw new ApplicationException(e);
		}
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.core.Application#prepareUI()
	 */
	public void prepareUI() throws ApplicationException{
		logger.debug("Application preparing UI!");
		if (initViews!=null){
			for (ViewsProcessorWrapper w : initViews){
				try {
					if (w.isRootView()){
						this.setRootView(ViewContainerFrame.class.cast(w.getView()));
					} else {
						this.getViewManager().addView(w.getView(),w.getConstraint());
					}
				} catch (ViewException e) {
					throw new ApplicationException(e);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.core.ApplicationListenerAware#removeApplicationListener(idmx3RestFrontEnd.core.ApplicationListener)
	 */
	public void removeApplicationListener(ApplicationListener listener) {
		this.applicationListeners.remove(listener);
	}
	
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.core.ApplicationContextAware#setApplicationContext(idmx3RestFrontEnd.core.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws ApplicationContextException {
		if (this.applicationContext != null) {
			throw new ApplicationContextException();
		}
		this.applicationContext = applicationContext;
	}
	
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.core.ApplicationListenerAware#setApplicationListeners(java.util.List)
	 */
	public void setApplicationListeners(List<ApplicationListener> applicationListeners) {
		this.applicationListeners = applicationListeners;
	}

	/* (non-Javadoc)
	 * @see org.viewa.core.ApplicationBase#setControllerDispatcher(org.viewa.controller.ViewControllerDispatcher)
	 */
	public void setControllerDispatcher(ViewControllerDispatcher dispatcher) throws ViewControllerDispatcherException {
		this.dispatcher = dispatcher;
	}


	public void setLocale(Locale locale) {
		this.locale = locale;
		Locale.setDefault(this.locale);
	}

	/* (non-Javadoc)
	 * @see org.viewa.core.ApplicationBase#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @param rootView
	 */
	public void setRootView(ViewContainerFrame rootView) {
		logger.debug("Setting Root View");
		try {
			if (this.viewManager!=null){
				this.viewManager.setRootView(rootView);
				this.rootView = rootView;
			}
		} catch (ViewException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.viewa.core.Application#setViewManager(org.viewa.view.ViewManager)
	 */
	public void setViewManager(ViewManager viewManager) throws ViewManagerException {
		this.viewManager = viewManager;
	}

	
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.model.ViewModelManagerAware#setViewModelManager(idmx3RestFrontEnd.model.ViewModelManager)
	 */
	public void setViewModelManager(ViewModelManager viewModelManager) {
			this.viewModelManager = viewModelManager;
	}
	
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.core.Application#setVisible(boolean)
	 */
	public void setVisible(boolean visible){
		JFrame frame = this.getRootView().getFrame();
		if (frame != null){
			if (visible){
				frame.setVisible(visible);
				frame.repaint();
			} else {
				frame.setVisible(visible);
			}
		}
	}
}
