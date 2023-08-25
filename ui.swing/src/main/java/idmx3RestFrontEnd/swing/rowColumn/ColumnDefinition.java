package idmx3RestFrontEnd.swing.rowColumn;

public class ColumnDefinition {

	private int ordering=0;
	private String id="1";
	private String fieldName="field_name";
	private String headerName="header_name";
	
	public ColumnDefinition() {
		
	}
	
	public ColumnDefinition(int ordering, String fieldName, String headerName) {
		super();
		this.ordering = ordering;
		this.id=fieldName;
		this.fieldName = fieldName;
		this.headerName = headerName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getOrdering() {
		return ordering;
	}
	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	
	
	
}
