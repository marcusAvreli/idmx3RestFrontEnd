package idmx3RestFrontEnd;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Component;
import java.util.ArrayList;
import idmx3RestFrontEnd.swing.DynamicTable;
import idmx3RestFrontEnd.swing.rowColumn.ColumnDefinition;
import idmx3RestFrontEnd.swing.rowColumn.RowDefinition;
import idmx3RestFrontEnd.swing.table.DynamicTableModel;
import idmx3RestFrontEnd.view.ViewContainer;
import idmx3RestFrontEnd.view.event.ViewContainerEvent;
import idmx3RestFrontEnd.view.event.ViewContainerEventController;


public class MyDoggyListener implements ViewContainerEventController {
	private static final Logger logger = LoggerFactory.getLogger(MyDoggyListener.class);

	@Override
	public void onViewClose(ViewContainerEvent arg0) {
		// TODO Auto-generated method stub

		logger.info("listener_on_view_close");
	}

	@Override
	public void onViewFinalUIState(ViewContainerEvent view) {
		// TODO Auto-generated method stub
		if(null !=view) {
			logger.info("view_is_not_null");
		}else {
			logger.info("view_is_null");
		}
		logger.info("listener_on_view_final_ui_state");
		
	}

	@Override
	public void onViewInit(ViewContainerEvent view) {
		// TODO Auto-generated method stub
		logger.info("listener_on_view_started");
		logger.info("listener_on_view_init_finished");
	}

	@Override
	public void onViewInitBackActions(ViewContainerEvent view) {
		// TODO Auto-generated method stub
		if(null !=view) {
			logger.info("view_is_not_null");
		}else {
			logger.info("view_is_null");
		}
		logger.info("listener_on_view_init_back_actions");
	}

	@Override
	public void onViewInitUIState(ViewContainerEvent view) {
		// TODO Auto-generated method stub
		//logger.info("listener_on_view_init_ui_state_started");
		if(null !=view) {
			
			ViewContainer viewContainer = view.getSource();
			if (null != viewContainer) {			
				Map<String,List<Component>> componentsMap = viewContainer.getNamedComponents();
				List<Component> components = componentsMap.get("report");
				if(null != components) {
			//		logger.info("components_is_not_null");
					 ColumnDefinition colDef = new ColumnDefinition(0, "name", "Name");
					 List<ColumnDefinition> colDefs = new ArrayList<ColumnDefinition>();
					 colDefs.add(colDef);
					 colDef = new ColumnDefinition(2, "displayName", "Display Name");
					 colDefs.add(colDef);
					 colDef = new ColumnDefinition(1, "description", "Description");
					 colDefs.add(colDef);
					DynamicTable reportTable = (DynamicTable)components.get(0);
					 CustomApplication ca = new CustomApplication(1, "1", "business object", "description1");
					 List<CustomApplication> cas = new ArrayList<CustomApplication>();
					 cas.add(ca);
					 ca = new CustomApplication(2, "2", "atlas", "description2");
					 cas.add(ca);
					 List<RowDefinition> rows = buildRows(cas);
					// DynamicTableModel model = (DynamicTableModel) reportTable.getModel();
					 DynamicTableModel model = new DynamicTableModel(colDefs,rows);
					 reportTable.setModel(model);
					// model.addColumn(colDefs.get(0));
					// model.getColumns().add(colDefs.get(0));
					//model.fireTableStructureChanged();
					// model.buildRows(rows, false);
				}
				
				
			}
		}else {
			
		}
		//logger.info("listener_on_view_init_ui_state_finished");
	}
	public List<RowDefinition> buildRows(List<CustomApplication> members) {
		List<RowDefinition> rows = new ArrayList<RowDefinition>();
		RowDefinition row = null;
		int counter=0;
		if(null != members) {
			for(CustomApplication ca : members) {
				counter++;
				int databaseId = counter;
	    		String appName = ca.getName();
	    		String displayName = ca.getDisplayName();
	    		String description = ca.getDescription();
	    		row=new RowDefinition();	
	    		row.setDatabaseId("id");
	    		row.setDataColumn("name", appName);
	    		row.setDataColumn("displayName", displayName);
	    		row.setDataColumn("description", description);
	    		rows.add(row);    		
	    	}	
		}
		return rows;
	}

}
