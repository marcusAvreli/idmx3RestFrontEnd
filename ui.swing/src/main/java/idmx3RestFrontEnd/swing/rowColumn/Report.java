package idmx3RestFrontEnd.swing.rowColumn;

import java.util.ArrayList;
import java.util.List;

public class Report {
	private RowDefinition rowDefinition;
	private ColumnDefinition columnDefinition;
	List<ColumnDefinition> columns;
	public List<ColumnDefinition> getColumns() {
		if(null == columns || columns.isEmpty()) {
			columns = new ArrayList<ColumnDefinition>();
			columns.add(new ColumnDefinition());
		}
		return columns;
	}
	public void setColumns(List<ColumnDefinition> columns) {
		this.columns = columns;
	}
	public RowDefinition getRowDefinition() {
		return rowDefinition;
	}
	public void setRowDefinition(RowDefinition rowDefinition) {
		this.rowDefinition = rowDefinition;
	}
	public ColumnDefinition getColumnDefinition() {
		return columnDefinition;
	}
	public void setColumnDefinition(ColumnDefinition columnDefinition) {
		this.columnDefinition = columnDefinition;
	}
	private Report() {

	}
	public static Report getInstance() {
		return ReportSingleton.INSTANCE;
	}
	private static class ReportSingleton {
		private static final Report INSTANCE = new Report();
	}
}
