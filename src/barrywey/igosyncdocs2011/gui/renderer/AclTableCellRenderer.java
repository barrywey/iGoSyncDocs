/**
 * 
 * @(#)AclTableCellRenderer.java Mar 3, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.google.gdata.data.acl.AclEntry;

/**
 *
 *
 *
 * @author Barry Wey
 * @version 1.0, Mar 3, 2011
 * @since JDK1.6
 */
public class AclTableCellRenderer extends JLabel implements TableCellRenderer{

	private static final long serialVersionUID = 6345384892754386293L;

	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if(value instanceof AclEntry) {
			AclEntry entry = (AclEntry)value;
			if(column == 0) {
				setText(entry.getRole().getValue());
			}else if(column == 1) {
				setText(entry.getScope().getValue());
			}			
		}//end of if
		
		if(isSelected) {
			setBackground(table.getSelectionBackground());
			setForeground(table.getSelectionForeground());
		}else {
			setBackground(table.getBackground());
			setForeground(table.getForeground());
		}
		setOpaque(true);
		return this;
	}
}
