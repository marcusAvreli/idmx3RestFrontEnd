package idmx3RestFrontEnd.swing.rowColumn;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



//import org.viewaframework.swing.table.DynamicTableModel;

public class RowDefinition {
	private static final Logger logger = LoggerFactory.getLogger(RowDefinition.class);
	private String databaseId;
	private Map<String,Object> rowMap;
	private boolean permanent;
	public RowDefinition(String databaseId) {
		this.databaseId=databaseId;
	}
	public RowDefinition() {
		rowMap=new HashMap<String,Object>();
		permanent=false;
	}
	public Object getDataColumn(String idColumn){
		
		return rowMap.get(idColumn);
	}
	
	public void setDataColumn(String idColumn,Object value){
		
		rowMap.put(idColumn, value);
	}

	public String getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(String databaseId) {
		this.databaseId = databaseId;
	}

	public boolean isPermanent() {
		return permanent;
	}

	public void setPermanent(boolean permanent) {
		this.permanent = permanent;
	}

	
}
