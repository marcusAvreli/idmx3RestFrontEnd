package idmx3RestFrontEnd.swing.builder.component;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idmx3RestFrontEnd.swing.DynamicTable;
import idmx3RestFrontEnd.swing.builder.SwingBuilder;
import idmx3RestFrontEnd.swing.table.DynamicTableModel;
import idmx3RestFrontEnd.swing.rowColumn.ColumnDefinition;
import idmx3RestFrontEnd.swing.rowColumn.Report;

public class DynamicTableBuilder extends ComponentBuilderAbstract<DynamicTable>{

	private DynamicTable target = new DynamicTable();
	private static final Logger logger = LoggerFactory.getLogger(DynamicTableBuilder.class);
	/**
	 * @param swBuilder
	 */
	public DynamicTableBuilder(SwingBuilder swBuilder,Report report){
		super(swBuilder);
		//logger.info("dynamic_table_builder");
		List<ColumnDefinition> columns = report.getInstance().getColumns();
		//logger.info("dynamic_1");
		DynamicTableModel model = new DynamicTableModel();
		
		target = new DynamicTable(model);
		//logger.info("dynamic_table_builder_finished");
	}
	
	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.widget.swing.builder.Builder#getTarget()
	 */
	public DynamicTable getTarget() {
		return this.target;
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.widget.swing.builder.Builder#getType()
	 */
	@SuppressWarnings("unchecked")
	public Class<DynamicTable> getType() {
		return (Class<DynamicTable>) this.target.getClass();
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.widget.swing.builder.component.ComponentBuilder#setEnabled(boolean)
	 */
	public DynamicTableBuilder setEnabled(boolean enabled) {
		this.target.setEnabled(enabled);
		return this;
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.widget.swing.builder.component.ComponentBuilder#setFont(java.awt.Font)
	 */
	public DynamicTableBuilder setFont(Font font) {
		this.getTarget().setFont(font);
		return this;
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.widget.swing.builder.component.ComponentBuilder#setName(java.lang.String)
	 */
	public DynamicTableBuilder setName(String name) {
		this.getTarget().setName(name);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public DynamicTableBuilder rows(List<Report> beans){
		DynamicTableModel model =  (DynamicTableModel)this.getTarget().getModel();
		for (Report bean : beans){
			//model.addRow(bean);
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.widget.swing.builder.component.ComponentBuilder#setPreferredSize(java.awt.Dimension)
	 */
	public DynamicTableBuilder setPreferredSize(
			Dimension dimension) {
		this.target.setPreferredSize(dimension);
		return this;
	}

	/* (non-Javadoc)
	 * @see idmx3RestFrontEnd.widget.swing.builder.Builder#setTarget(java.lang.Object)
	 */
	public void setTarget(DynamicTable target) {
		this.target = target;
	}
}
