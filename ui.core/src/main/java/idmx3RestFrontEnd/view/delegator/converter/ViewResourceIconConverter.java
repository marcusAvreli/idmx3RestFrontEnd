package idmx3RestFrontEnd.view.delegator.converter;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idmx3RestFrontEnd.util.BeanUtils;
import idmx3RestFrontEnd.util.ClassPathURLHandler;
import idmx3RestFrontEnd.view.delegator.ViewResourceConverter;

/**
 * This converter can convert an icon path in a java.awt.Image object
 * 
 * @author Mario Garcia
 *
 */
public class ViewResourceIconConverter implements ViewResourceConverter{

	private static final Logger logger = LoggerFactory.getLogger(ViewResourceIconConverter.class);
	
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.view.delegator.ViewResourceConverter#convert(java.lang.Object, java.lang.Class)
	 */
	public Object convert(Object arg0, Class<? extends Object> arg1) {
		String pathUrl = arg0.toString();
		ImageIcon imageIcon = null;
		try {
			imageIcon = new ImageIcon(new URL(null,pathUrl,new ClassPathURLHandler()));
		} catch (MalformedURLException e) {
			logger.error(e.getMessage());
		}		
		return imageIcon;
	}

	public Class<? extends Object> getDestinationClass() {
		return Icon.class;
	}

}
