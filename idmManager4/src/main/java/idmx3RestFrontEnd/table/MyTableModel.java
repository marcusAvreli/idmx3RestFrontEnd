package idmx3RestFrontEnd.table;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idmx3RestFrontEnd.collection.EventList;
import idmx3RestFrontEnd.rowColumn.ColumnDefinition;
import idmx3RestFrontEnd.rowColumn.RowDefinition;
import idmx3RestFrontEnd.rowColumn.RowItem;



public class MyTableModel extends AbstractTableModel  {
	/**
	 * @author Mario Garcia
	 *
	 */
	private class ListAdapterTableModelListener implements ListDataListener{
		private MyTableModel model;
		private JTable table;
		/**
		 * @param model
		 */
		public ListAdapterTableModelListener(JTable table,MyTableModel model){
			this.model = model;
			this.table = table;
		}
		/* (non-Javadoc)
		 * @see javax.swing.event.ListDataListener#contentsChanged(javax.swing.event.ListDataEvent)
		 */
		public void contentsChanged(ListDataEvent e) {
			logger.info("check_post_1");
			Integer actual = this.table.getSelectedRow();
			model.fireTableRowsUpdated(e.getIndex0(),e.getIndex1());
			model.fireTableDataChanged();
			this.maintainSelection(actual);
		}
		
		/* (non-Javadoc)
		 * @see javax.swing.event.ListDataListener#intervalAdded(javax.swing.event.ListDataEvent)
		 */
		public void intervalAdded(ListDataEvent e) {
			logger.info("check_post_2");
			Integer actual = this.table.getSelectedRow();
			model.fireTableRowsInserted(e.getIndex0(),e.getIndex1());
			model.fireTableDataChanged();
			this.maintainSelection(actual);
		}
		
		/* (non-Javadoc)
		 * @see javax.swing.event.ListDataListener#intervalRemoved(javax.swing.event.ListDataEvent)
		 */
		public void intervalRemoved(ListDataEvent e) {
			Integer actual = this.table.getSelectedRow();
			model.fireTableRowsDeleted(e.getIndex0(),e.getIndex1());
			model.fireTableDataChanged();
			this.maintainSelection(actual);
		}
		/**
		 * @param actual
		 */
		public void maintainSelection(Integer actual){
			logger.info("check_post_3");
			Integer selectedRow = actual != -1 ? 
					actual : this.table.getModel().getRowCount() > 0 ? 
							0 : null;
			if (selectedRow != null){
				this.table.setRowSelectionInterval(selectedRow,selectedRow);
			}
		}
		
	}
	/**
	 * @author Mario Garcia
	 *
	 */
	private class SelectionListener implements ListSelectionListener{
		private JTable table;
		/**
		 * @param table
		 */
		public SelectionListener(JTable table){
			this.table = table;
		}
		/* (non-Javadoc)
		 * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
		 */
		public void valueChanged(ListSelectionEvent e) {
			
			if (!e.getValueIsAdjusting()){				
				Integer selectionIndex = table.getSelectionModel().getMinSelectionIndex();
				if (logger.isInfoEnabled()){
					logger.info("Selected index is:"+selectionIndex);
				}
				try {
					if (selectionIndex >= 0){
						int realRowIndex = table.convertRowIndexToModel(selectionIndex); 						
						Object o = MyTableModel.this.getRow(realRowIndex);
						if (logger.isInfoEnabled()){
							logger.info("Realindex: "+realRowIndex);
							logger.info("The selected object is: "+o);
							logger.info("[M:"+realRowIndex+" | V:"+selectionIndex+"] "+
									MyTableModel.this.getRow(realRowIndex));
						}
						//MyTableModel.this.setSelectedItem(o);
					}
				} catch (Exception e1) {
					logger.error(e1.getMessage());
				} 
			}
		}	
	}
	
	private static final Logger logger = LoggerFactory.getLogger(MyTableModel.class); 
	public static final String SELECTED_ITEM = "selectedItem";
	private static final long serialVersionUID = 2327107603858041801L;
	private List<ColumnDefinition> columns;
	private EventList<RowDefinition> modelList;
	private PropertyChangeSupport propertyChangeSupport;
	private RowDefinition selectedElement;
	private JTable table;
	
	//
	ArrayList<String> columnNames = new ArrayList<String>();
	ArrayList<RowItem> m_rowData = new ArrayList<RowItem>();

	ArrayList<Integer> m_visibleRowMap = new ArrayList<Integer>();
	ArrayList<String> Map_Column_IDForm = new ArrayList<String>();
	ArrayList<Integer> m_visibleDataColMap = new ArrayList<Integer>();
	/**
	 * @param elementList
	 * @param visibleProperties
	 */
	public MyTableModel(JTable table,EventList<RowDefinition> elementList,List<ColumnDefinition> columns) {
		super();
		this.table = table;
		this.modelList = elementList;
		this.columns = columns;
		this.modelList.addListDataListener(new ListAdapterTableModelListener(this.table,this));
		this.propertyChangeSupport = new PropertyChangeSupport(this);
		table.getSelectionModel().addListSelectionListener(new SelectionListener(table));
		buildTabla(this.columns);
	}
	
	//////////
	/////////		GTABLE START
	private void buildTabla(List colDefs){
		Iterator iCol = colDefs.iterator();
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
		buildRows(modelList,false);
	}
	
	public void buildRows(List<?> rows,boolean replace) {
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
			//logger.info("value:"+value);
			columnData.add(value);
		}
		
		RowItem ritem = buildRowItem(columnData);
		
		return setRowItem(ritem, replace);
	}
	private boolean setRowItem(RowItem ritem, boolean replace) {
		boolean rowAdded=true;
		
		
			int rowToReplace = 1;
			subAddRow(ritem.getIndex(), ritem);
				//	fireTableRowsUpdated(0, 2);
				
				
		

	
			//updateGUI(true);
			//		fireTableDataChanged();
		// if(row>0) addNullRow();
		
		return rowAdded;
	}
	private void subAddRow(int row, RowItem ritem)  {
		
		
			
			
			//m_rowData.add(ritem);
			m_rowData.add(0,ritem);
			//Si la insercion no es al final modificamos el index de los siguientes rowItem
			for(int i=row+1;i<m_rowData.size();i++){
				m_rowData.get(0).setIndex(i);
			}
			m_visibleRowMap.add(/*new Integer(row)*/m_rowData.size()-1);
		
	}
	void updateGUI(boolean reagrupar) {
		
		
		//fireTableDataChanged();
		// printRows();
	}
	private RowItem buildRowItem(ArrayList columnData) {
		//logger.info("column data:"+columnData);
		RowItem ritem = new RowItem(1);
		ritem.setColumnData(columnData);
		return ritem;
	}
	public String getFieldIDFromColumn(int column) {
		return (String) Map_Column_IDForm.get(column);
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		 return getDataValueAt(rowIndex, getRealDataColumn(columnIndex));
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
	
	public int getRealDataColumn(int visCol) {
		Integer col = (Integer) m_visibleDataColMap.get(visCol);
		if (col == null)
			return -1;
		return col.intValue();
	}
	private int getRowIndex(int visibleRow) {
		//System.err.println("getRowIndex: visibleRow:"+visibleRow+" m_visibleRowMap:"+m_visibleRowMap);
		return ((Integer) m_visibleRowMap.get(visibleRow)).intValue();
	}
	
	///////////////
	/////////////	GTABLE FINISHED
	//////////////
	
	
	/**
	 * @param visibleProperties
	 */
	public MyTableModel(JTable table,List<ColumnDefinition> columns) {
		this(table,new EventList<RowDefinition>(),columns);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Observable#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener){
		this.propertyChangeSupport.addPropertyChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ObservableModel#contains(java.lang.Object)
	 */
	public boolean contains(RowDefinition element) {
		logger.info("contains_called");
		return this.modelList.contains(element);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Observable#firePropertyChange(java.beans.PropertyChangeEvent)
	 */
	public void firePropertyChange(PropertyChangeEvent evt) {
		this.propertyChangeSupport.firePropertyChange(evt);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class<?> clazz = super.getColumnClass(columnIndex);
		/*if (!this.modelList.isEmpty()){
			clazz = new BasicBeanAdapter<U>(this.modelList.get(0)).
				getPropertyClass(this.columns.get(columnIndex).getPropertyName());
		}*/		
		return clazz;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return this.columns.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		//return this.columns.get(column).getColumnName();
		return (String) columnNames.get(column);
	}
	
	/**
	 * @param column
	 * @return
	 */
	public String getColumnPropertyName(int column){
		//return this.columns.get(column).getPropertyName();
		return "getColumnPropertyName";
	}
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ObservableModel#getModelList()
	 */
	public EventList<RowDefinition> getModelList() {
		return modelList;
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Observable#getPropertyChangeListeners()
	 */
	public List<PropertyChangeListener> getPropertyChangeListeners() {
		return Arrays.asList(this.propertyChangeSupport.getPropertyChangeListeners());
	}
	
	/**
	 * @param rowIndex
	 * @return
	 */
	public RowDefinition getRow(int rowIndex){
		return modelList.get(rowIndex);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return this.modelList.size();
	}
	
	
	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ObservableModel#getSelectedElementAdapter()
	 */
	/*
	public BeanAdapter<U> getSelectedElementAdapter() {
		return new BasicBeanAdapter<U>(this.selectedElement);
	}

	*/
	/**
	 * @return
	 */
	public RowDefinition getSelectedItem() {
		return selectedElement;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.Observable#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener){
		this.propertyChangeSupport.removePropertyChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.viewaframework.binding.core.ObservableModel#setModelList(org.viewaframework.binding.collection.EventList)
	 */
	public void setModelList(EventList<RowDefinition> modelList) {
		this.modelList = modelList;
	}

	/**
	 * @param selectedObject
	 */
	
	@SuppressWarnings("unchecked")
	public void setSelectedItem(Object anItem) {
			logger.info("set_selected_item_called:"+anItem.getClass().getSimpleName());
			RowDefinition received = (RowDefinition)anItem;
			logger.info("received value:"+received.getDatabaseId());
		//if (this.modelList.contains(anItem)){
			RowDefinition oldValue = this.selectedElement;
			RowDefinition newValue = (RowDefinition) anItem;
			logger.info("new value:"+newValue.getDatabaseId());
			if(null != oldValue) {
				logger.info("old value:"+oldValue.getDatabaseId());
			}
			this.selectedElement = newValue;
			this.firePropertyChange(
					new PropertyChangeEvent(
							this,SELECTED_ITEM,oldValue,newValue));		
	//	}
		
	}
	
	
}