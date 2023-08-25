package idmx3RestFrontEnd.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import org.jdesktop.swingx.decorator.CompoundHighlighter;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.decorator.HighlighterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idmx3RestFrontEnd.swing.builder.SwingBuilder;
import idmx3RestFrontEnd.swing.rowColumn.ColumnDefinition;
import idmx3RestFrontEnd.swing.table.DynamicTableColumn;
import idmx3RestFrontEnd.swing.table.DynamicTableModel;

/**
 * @author mgg
 *
 */
public class DynamicTable extends JXTable{

	private static final long serialVersionUID = -8747703761099581354L;
	private static final Logger logger = LoggerFactory.getLogger(DynamicTable.class);
	/**
	 * @param dynamicTableModel
	 */
	public DynamicTable(DynamicTableModel dynamicTableModel) {
		super(dynamicTableModel);
		
		dynamicTableModel.setTable(this);
		
		
		this.setHighlighters(HighlighterFactory.createAlternateStriping());
		
	//	this.initDynamicTableColumns(dynamicTableModel);
		
		this.setShowGrid(false, false);
		Dimension dimension = this.getSize();
		
		
		this.getSelectionModel().addListSelectionListener(dynamicTableModel);
		
	}
	
	/**
	 * This method initializes column settings set to the model
	 * 
	 * @param dynamicTableModel
	 */
	private void initDynamicTableColumns(DynamicTableModel dynamicTableModel) {
		logger.info("init_columns_started");
		TableColumnModel columnModel = this.getColumnModel();
		logger.info("checkPost_1");
		/*
		for (ColumnDefinition column : dynamicTableModel.getColumns()){
			logger.info("checkPost_2");
			Integer columnIndex = columnModel.getColumnIndex(0);
			logger.info("columnIndex:"+columnIndex);
			/*TableColumn tableColumn = columnModel.getColumn(columnIndex);
			String headerValue = column.getTitle() != null ? column.getTitle() : column.getPropertyName();
			tableColumn.setHeaderValue(headerValue);
			tableColumn.setPreferredWidth(column.getWidth());
			if (column.getRenderer() != null){
				tableColumn.setCellRenderer(column.getRenderer());
			}
			
		}
		*/
		logger.info("init_columns_finished");
	}

	/**
	 * Use this constructor if you want to use this kind of table just because of the 
	 * row highlights
	 */
	public DynamicTable(){
		super();
		this.setHighlighters(HighlighterFactory.createAlternateStriping());
		this.setShowGrid(false, false);
	}

	/**
     * Paints empty rows too, after letting the UI delegate do
     * its painting.
     */
	
    public void paint(Graphics g) {
        super.paint(g);
        paintEmptyRows(g);
    }

    /**
     * Paints the backgrounds of the implied empty rows when the
     * table model is insufficient to fill all the visible area
     * available to us. We don't involve cell renderers, because
     * we have no data.
     */
	
    protected void paintEmptyRows(Graphics g) {
    	Highlighter[] highlighters = this.getCompoundHighlighter().getHighlighters();
    	if (highlighters.length == 1){
    		final int rowCount = getRowCount();
    		final Rectangle clip = g.getClipBounds();
    		if (rowCount * rowHeight < clip.height) {
    			for (int i = rowCount; i <= clip.height/rowHeight; ++i) {
    				g.setColor(colorForRow(i));
    				g.fillRect(clip.x, i * rowHeight, clip.width, rowHeight);
    			}
    		}
    	}
    }
    
    /**
     * Returns the appropriate background color for the given row.
     */
    protected Color colorForRow(int row) {
    	CompoundHighlighter ch = null;
    	int rowx = 1;
        if(row % 2 == 0){
        	rowx = 0;
        }      
        ch = CompoundHighlighter.class.cast(this.getCompoundHighlighter().getHighlighters()[0]);
        return ColorHighlighter.class.cast(ch.getHighlighters()[rowx]).getBackground();
    }

	/* (non-Javadoc)
	 * @see org.jdesktop.swingx.JXTable#setModel(javax.swing.table.TableModel)
	 */
	/*@Override
	@SuppressWarnings("rawtypes")
	
	public void setModel(TableModel dataModel) {
	  If we're using a DynamicTableModel, the model needs to know about the
	  * JTable internally 
		if (dataModel instanceof DynamicTableModel){
			((DynamicTableModel) dataModel).setTable(this);
		}
	 /* Finally the model is set 
		super.setModel(dataModel);
	}
	*/
}
