package idmx3RestFrontEnd.swing.builder.container;

import java.awt.Container;
import java.awt.LayoutManager;

import idmx3RestFrontEnd.swing.builder.component.ComponentBuilder;

/**
 * @author mgg
 *
 * @param <T>
 */
public interface ContainerBuilder<T extends Container> extends ComponentBuilder<T>{

	/**
	 * @param layout
	 * @return
	 */
	public ContainerBuilder<T> layout(LayoutManager layout);

}
