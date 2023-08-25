package idmx3RestFrontEnd.swing.builder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import idmx3RestFrontEnd.swing.builder.component.ButtonBuilder;
import idmx3RestFrontEnd.swing.builder.component.ComponentBuilderAware;

import idmx3RestFrontEnd.swing.builder.component.DynamicTableBuilder;
import idmx3RestFrontEnd.swing.builder.component.JComponentBuilder;
import idmx3RestFrontEnd.swing.builder.component.LabelBuilder;
import idmx3RestFrontEnd.swing.builder.component.SpinnerBuilder;
import idmx3RestFrontEnd.swing.builder.component.TextFieldBuilder;
import idmx3RestFrontEnd.swing.builder.container.ContainerBuilder;
import idmx3RestFrontEnd.swing.rowColumn.Report;

/**
 * @author mgg
 *
 */
public class SwingBuilder implements ContainerBuilder<JPanel>,ComponentBuilderAware{

	private JPanel mainPanel;
	private static final Logger logger = LoggerFactory.getLogger(SwingBuilder.class);
	public SwingBuilder(){
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BorderLayout());
		this.mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}
	
	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilderAware#button()
	 */
	public ButtonBuilder button() {
		ButtonBuilder builder = new ButtonBuilder(this);
		this.mainPanel.add(builder.getTarget());
		return builder;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilderAware#button(java.lang.Object)
	 */
	public ButtonBuilder button(Object constraints) {
		ButtonBuilder builder = new ButtonBuilder(this);
		this.mainPanel.add(builder.getTarget(),constraints);
		return builder;
	}

	public <T extends JComponent> JComponentBuilder<T> component(T component,Class<T> clazz,Object constraints) {
		JComponentBuilder<T> builder = 
			new JComponentBuilder<T>(this,component,clazz);
		this.mainPanel.add(builder.getTarget(),constraints);
		return builder;
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.widget.swing.builder.component.ComponentBuilderAware#datePicker()
	 */
	/*
	public DatePickerBuilder datePicker() {
		DatePickerBuilder builder = new DatePickerBuilder(this);
		this.mainPanel.add(builder.getTarget());
		return builder;
	}
*/
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.widget.swing.builder.component.ComponentBuilderAware#datePicker(java.lang.Object)
	 */
	/*
	public DatePickerBuilder datePicker(Object constraints) {
		DatePickerBuilder builder = new DatePickerBuilder(this);
		this.mainPanel.add(builder.getTarget(),constraints);
		return builder;
	}
	*/
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.widget.swing.builder.component.ComponentBuilderAware#dynamicTable(java.lang.Class)
	 */
	/*
	public <T> DynamicTableBuilder<T> dynamicTable(Class<T> clazz) {
		DynamicTableBuilder<T> builder = new DynamicTableBuilder<T>(this,clazz);
		this.mainPanel.add(new JScrollPane(builder.getTarget()),BorderLayout.CENTER);
		return builder;
	}
*/
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.widget.swing.builder.component.ComponentBuilderAware#dynamicTable(java.lang.Class, java.lang.Object)
	 */
	public  DynamicTableBuilder dynamicTable(Report report,Object constraints) {
		logger.info("dynamic_table");
		DynamicTableBuilder builder = new DynamicTableBuilder(this,report);
		logger.info("builder_target:"+builder.getTarget());
		this.mainPanel.add(new JScrollPane(builder.getTarget()),constraints);
		logger.info("dynamic_table_finished");
		return builder;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.Builder#getTarget()
	 */
	public JPanel getTarget() {		
		return this.mainPanel;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.Builder#getType()
	 */
	public Class<JPanel> getType() {
		return JPanel.class;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilderAware#label()
	 */
	public LabelBuilder label() {
		LabelBuilder labelBuilder = new LabelBuilder(this);
		this.mainPanel.add(labelBuilder.getTarget());
		return labelBuilder;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilderAware#label(java.lang.Object)
	 */
	public LabelBuilder label(Object constraints) {
		LabelBuilder labelBuilder = new LabelBuilder(this);
		this.mainPanel.add(labelBuilder.getTarget(),constraints);
		return labelBuilder;	
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.widget.swing.builder.container.ContainerBuilder#layout(java.awt.LayoutManager)
	 */
	public SwingBuilder layout(LayoutManager layout) {
		this.mainPanel.setLayout(layout);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilder#setEnabled(boolean)
	 */
	public SwingBuilder setEnabled(boolean enabled) {
		this.mainPanel.setEnabled(enabled);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilder#setFont(java.awt.Font)
	 */
	public SwingBuilder setFont(Font font) {
		this.mainPanel.setFont(font);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilder#setName(java.lang.String)
	 */
	public SwingBuilder setName(String name) {
		this.mainPanel.setName(name);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilder#setPreferredSize(java.awt.Dimension)
	 */
	public SwingBuilder setPreferredSize(Dimension dimension) {
		this.mainPanel.setPreferredSize(dimension);
		return this;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.Builder#setTarget(java.lang.Object)
	 */
	public void setTarget(JPanel target) {
		this.mainPanel = target;
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.widget.swing.builder.component.ComponentBuilderAware#spinner()
	 */
	public SpinnerBuilder spinner() {
		SpinnerBuilder spinnerBuilder = new SpinnerBuilder(this);
		this.mainPanel.add(spinnerBuilder.getTarget());
		return spinnerBuilder;
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.widget.swing.builder.component.ComponentBuilderAware#spinner(java.lang.Object)
	 */
	public SpinnerBuilder spinner(Object constraints) {
		SpinnerBuilder spinnerBuilder = new SpinnerBuilder(this);
		this.mainPanel.add(spinnerBuilder.getTarget(),constraints);
		return spinnerBuilder;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.SwingBuilderAware#swingBuilder()
	 */
	public SwingBuilder swingBuilder() {
		return this;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilderAware#textField()
	 */
	public TextFieldBuilder text() {
		TextFieldBuilder textBuilder = new TextFieldBuilder(this);
		this.mainPanel.add(textBuilder.getTarget());		
		return textBuilder;
	}

	/* (non-Javadoc)
	 * @see org.examples.viewaframework.swing.component.ComponentBuilderAware#textField()
	 */
	public TextFieldBuilder text(Object constraints) {
		TextFieldBuilder textBuilder = new TextFieldBuilder(this);
		this.mainPanel.add(textBuilder.getTarget(),constraints);		
		return textBuilder;
	}	
}
