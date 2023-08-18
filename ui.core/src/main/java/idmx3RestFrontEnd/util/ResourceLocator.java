package idmx3RestFrontEnd.util;

import javax.swing.ImageIcon;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ResourceLocator {
	private static final Logger logger = LoggerFactory.getLogger(ResourceLocator.class);
	//private static final Log logger = LogFactory.getLog(ResourceLocator.class);
	
	/**
	 * @param clazz
	 * @param classPath
	 * @return
	 */
	public static ImageIcon getImageIcon(Class<? extends Object> clazz,String classPath) {
		ImageIcon image = null;
		try{
			image = new ImageIcon(clazz.getClassLoader().getResource(classPath));
		} catch (RuntimeException ex){
			logger.error("Unable to get ImageIcon from "+classPath);
		}
		return image;
	}
	
}
