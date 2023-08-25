package idmx3RestFrontEnd.swing.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idmx3RestFrontEnd.swing.rowColumn.ColumnDefinition;
import idmx3RestFrontEnd.swing.rowColumn.RowDefinition;
import idmx3RestFrontEnd.swing.rowColumn.RowItem;




/**
 * @author mgg
 *
 * @param <T>
 */
public class DynamicTableModel  extends AbstractTableModel 
	implements ListSelectionListener{

	private static final Logger logger = LoggerFactory.getLogger(DynamicTableModel.class);
	private static final long serialVersionUID = 1L;
	
	List<RowDefinition> listaFilas ;
	
	ArrayList<String> columnNames = new ArrayList<String>();
	ArrayList<RowItem> m_rowData = new ArrayList<RowItem>();

	ArrayList<Integer> m_visibleRowMap = new ArrayList<Integer>();
	ArrayList<String> Map_Column_IDForm = new ArrayList<String>();
	ArrayList<Integer> m_visibleDataColMap = new ArrayList<Integer>();

	//private T selectedObject;
	//private List<T> selectedObjects;
	//private List<T> sourceList;
	private JTable table;
	

	
	public DynamicTableModel(List<ColumnDefinition> listaColumnas,List<RowDefinition> listaFilas) {
		super();
		//this.sourceList = new ArrayList<T>();
	
		this.listaFilas=listaFilas;
		buildTabla(listaColumnas);
		//this.selectedObjects = new ArrayList<T>();
	}
	/**
	 * @param visibleProperties
	 */
	private void buildTabla(List listaColumnas){
		logger.info("build_tabla_updated");
		Iterator iCol = listaColumnas.iterator();
		int size = 0;
		
		
		
		
		int col = 0;
		while (iCol.hasNext()) {
			ColumnDefinition item = (ColumnDefinition) iCol.next();
			String label = item.getHeaderName();
			columnNames.add(label);
			String ID = item.getId();
			m_visibleDataColMap.add(new Integer(col));
			/* String ID= item.getAttributeValue("ID"); */
			col++;
		//Map_IDForm_Column.put(ID, new Integer(col));
			Map_Column_IDForm.add(ID);
		}
		buildRows(listaFilas,false);
	}
	///ROWS ROWS ROWS STARTED
	///ROWS ROWS ROWS STARTED
	///ROWS ROWS ROWS STARTED
	public void buildRows(List<?> rows,boolean replace) {
		logger.info("build_rows:"+rows);
		Iterator<?> itr=rows.iterator();
		while(itr.hasNext()){
			RowDefinition tableRow=(RowDefinition)itr.next();
			setTableRow(tableRow, replace);
		
		}
		
		//updateGUI(true);
	//	fireTableRowsInserted(0, 2);
	}
public boolean setTableRow(RowDefinition tableRow, boolean replace) {
		
		Iterator<String> itrIdColumns=Map_Column_IDForm.iterator();
		HashMap<String,Object> columnValues=new HashMap<String, Object>();
		while(itrIdColumns.hasNext()){
			String idColumn=itrIdColumns.next();
			
			Object value=tableRow.getDataColumn(idColumn);
		
			columnValues.put(idColumn, value);
		}
		ArrayList<Object> columnData = new ArrayList<Object>();
		int columns=getColumnCount();
		for(int i=0;i<columns;i++){
			String id=getFieldIDFromColumn(i);
			Object value=columnValues.get(id);
			logger.info("set_table_row_id:"+id);
			logger.info("set_table_row_value:"+value);
			columnData.add(value);
		}
		boolean permanent=tableRow.isPermanent();
		int row = !permanent?getRowCount():0;

		RowItem ritem = buildRowItem(row,columnData);
		
		return setRowItem(ritem, replace);
	}
	private boolean setRowItem(RowItem ritem, boolean replace) {
		logger.info("set_add_row");
		boolean rowAdded=true;
		
		
			int rowToReplace = 1;
			subAddRow(ritem.getIndex(), ritem);
				//	fireTableRowsUpdated(0, 2);
				
				
		

	
			//updateGUI(true);
					fireTableDataChanged();
		// if(row>0) addNullRow();
		
		return rowAdded;
	}
	/*void updateGUI(boolean reagrupar) {
		if (reagrupar)
			agrupate();
		m_visibleRowMap.clear();
		for (int r = 0; r < m_rowData.size(); r++) {
			RowItem row = (RowItem) m_rowData.get(r);
			if (row.isGroup()) {
//				row.getColumnPar().clear();
//				row.getColumnPar().add(new Integer(r));
//				row.getColumnPar().add(new Integer(GTable.virtualTypeForAgregation));
			}
			if (row.isFiltered() && m_filteredState)
				continue;
			m_visibleRowMap.add(new Integer(r));
			if (row.isGroup() && !row.isGroupExpand())
				r += row.getGroupSize();
		}
		fireTableDataChanged();
		// printRows();
	}*/
	private void subAddRow(int row, RowItem ritem)  {
		logger.info("sub_add_row_updated");
		
			
			
			m_rowData.add(ritem);
		//	m_rowData.add(row,ritem);
			//Si la insercion no es al final modificamos el index de los siguientes rowItem
			/*for(int i=row+1;i<m_rowData.size();i++){
				m_rowData.get(i).setIndex(i);
			}*/
			m_visibleRowMap.add(/*new Integer(row)*/m_rowData.size()-1);
		
	}
	
	
	public String getFieldIDFromColumn(int column) {
		logger.info("get_field_id_from_column:"+Map_Column_IDForm);
		return (String) Map_Column_IDForm.get(column);
	}
	
	private RowItem buildRowItem(int row,ArrayList columnData) {
		//logger.info("column data:"+columnData);
		RowItem ritem = new RowItem(row /*getColumnCount()
				- (m_cuantitativo ? 1 : 0),*/);
		//RowItem ritem = new RowItem(1);
		ritem.setColumnData(columnData);
		return ritem;
	}
	
	
	///ROWS ROWS ROWS FINISHED
	///ROWS ROWS ROWS FINISHED
	///ROWS ROWS ROWS FINISHED
	
	
	
	
	
	
	
	
	
	
	public DynamicTableModel(){
		super();
		//this.sourceList = new ArrayList<T>();
		//this.columns = new ArrayList<DynamicTableColumn>();
		//this.selectedObjects = new ArrayList<T>();
	}
	
	/**
	 * @param column
	 */
	/*
	public void addColumn(ColumnDefinition column){
		this.columns.add(column);
		//buildTabla(this.columns);
	}
	*/

	/**
	 * @param srcList
	 */
	public synchronized void addAll(Collection<RowDefinition> srcList){
		logger.info("add_all");
		/*this.sourceList.clear();
		this.sourceList.addAll(srcList);
		this.fireTableDataChanged();
		if (this.getRowCount() > 0){
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					table.setRowSelectionInterval(0,0);					
				}
			});
		}*/
	}

	/**
	 * @param e
	 */
	public synchronized void addRow(RowDefinition e){
		//this.sourceList.add(e);
		logger.info("add_row");
		this.fireTableDataChanged();
		//int modelIndex = this.sourceList.indexOf(e);
		//int visualIndex = this.table.convertRowIndexToView(modelIndex);		
		//this.table.setRowSelectionInterval(visualIndex,visualIndex);
	}
	/**
	 * 
	 */
	public synchronized void clear(){
	//	this.sourceList.clear();
		this.fireTableDataChanged();
	}

	/**
	 * @param e
	 * @return
	 */
	public boolean contains(RowDefinition e){
		//return this.sourceList.contains(e);
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return Comparable.class;
	}	
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return columnNames.size();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		//return (String)this.columns.get(column);
		logger.info("get_column_name:"+column);
		logger.info("column_names:"+columnNames);
		return (String) columnNames.get(column);
		//return "getColumnName";
	}
	
	/**
	 * @return
	 */
	/*
	public List<ColumnDefinition> getColumns() {
		return columns;
	}
	*/
	/**
	 * @param rowIndex
	 * @return
	 */
	/*
	public RowDefinition getRow(Integer rowIndex){
		//return this.sourceList.get(rowIndex);
		return null;
	}
	*/
		
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		//return sourceList!= null ? sourceList.size() : 0;
		logger.info("get_row_count");
		if (m_visibleRowMap != null)
			return m_visibleRowMap.size();
		return 0;
		//return 1;
	}
	
	/**
	 * @return
	 */
	public RowDefinition getSelectedObject() {
		//return selectedObject;
		return null;
	}
	
	/**
	 * @return
	 */
	public List<RowDefinition> getSelectedObjects() {
		//return selectedObjects;
		return null;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int row, int col) {
		/*if (m_cuantitativo) {
			RowItem it = (RowItem) m_rowData.get(getRowIndex(row));
			if (col == 0)
				if (it.isGroup())
					return new Boolean(it.isGroupExpand());
				else {
					int grIndex = getGroupParent(row, true);
					if (grIndex == -1)
						return " ";
					else
						return "    >";
				}
			col--;
		}*/
		int dataCol = getRealDataColumn(col);
		if (dataCol == -1)
			return null;
		
		return getDataValueAt(row, getRealDataColumn(col));
	}
	public int getRealDataColumn(int visCol) {
		Integer col = (Integer) m_visibleDataColMap.get(visCol);
		if (col == null)
			return -1;
		return col.intValue();
	}
	public Object getDataValueAt(int row, int col) {
		// System.out.println("GET VALUE
		// AT:CUANT:"+m_cuantitativo+","+row+","+col+","+getRowCount());
		if (getRowCount() < row + 1) {
			System.err.println("Table Form Model:getValueAt, error, no existe el registro " + row);
			return null;
		}
		RowItem it = (RowItem) m_rowData.get(getRowIndex(row));
		if (it.getColumnSize() < col + 1) {
			System.err.println("Table Form Model:getValueAt, error, no existe la col, row " + col + "," + row);
			return null;
		}

		return it.getColumnData(col);
	}
	private int getRowIndex(int visibleRow) {
		//System.err.println("getRowIndex: visibleRow:"+visibleRow+" m_visibleRowMap:"+m_visibleRowMap);
		return ((Integer) m_visibleRowMap.get(visibleRow)).intValue();
	}
	/**
	 * @param e
	 */
	/*
	public synchronized void removeRow(T e){
		int modelIndex = this.sourceList.indexOf(e);
		this.sourceList.remove(e);
		int visualIndex = this.table.convertRowIndexToView(modelIndex);
		this.fireTableRowsDeleted(visualIndex, visualIndex);		
		this.fireTableDataChanged();
	}
	*/
	/**
	 * @param e
	 */
	public void setSelectedObject(RowDefinition e) {
		//this.selectedObject = e;
		
	}

	/**
	 * @param selectedObjects
	 */
	public void setSelectedObjects(List<RowDefinition> selectedObjects) {
		//this.selectedObjects = selectedObjects;
	}

	/**
	 * @param table
	 */
	public void setTable(JTable table) {
		this.table = table;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
	 */
	public void valueChanged(ListSelectionEvent e) {
		/*if (!e.getValueIsAdjusting()){				
			int minIndex = table.getSelectionModel().getMinSelectionIndex();
			int maxIndex = table.getSelectionModel().getMaxSelectionIndex();			
			try {
				if (minIndex >= 0){					
					this.selectedObjects.clear();
					for (int i = minIndex ; i<=maxIndex;i++){
						int realIndex = table.convertRowIndexToModel(i);
						T value = getRow(realIndex);
                        if (table.getSelectionModel().isSelectedIndex(i)) {
						    this.selectedObjects.add(value);
                         /* Always the first occurrence is the result of calling getSelectedObject() 
                            if (i == minIndex){
                                this.selectedObject = value;
                            }
                            if (logger.isDebugEnabled()){
                                logger.debug(
                                    new StringBuilder().
                                        append("[M:").append(realIndex).
                                        append(" | V:").append(minIndex ++).
                                        append("] ").append(value).toString());
                            }
                        }
					}
				}
			} catch (Exception e1) {
				logger.error(e1.getMessage());
			} 
		}*/
	}
}