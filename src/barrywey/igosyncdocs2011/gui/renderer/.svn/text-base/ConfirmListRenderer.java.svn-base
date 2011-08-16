/**
 * 
 * @(#)ConfirmListRenderer.java Feb 15, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import barrywey.igosyncdocs2011.bean.MyDocumentListEntry;

import com.google.gdata.data.docs.DocumentListEntry;

/**
 *
 *
 *
 * @author Barry Wey
 * @version 1.0, Feb 15, 2011
 * @since JDK1.6
 */
public class ConfirmListRenderer extends JLabel implements ListCellRenderer{

	private static final long serialVersionUID = -2460499751287360553L;

	/* (non-Javadoc)
	 * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
	 */
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		if(value instanceof DocumentListEntry) {
			MyDocumentListEntry entry = (MyDocumentListEntry)value;
			setText(entry.getEntry().getTitle().getPlainText());
		}
		
		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}		
		setEnabled(list.isEnabled());
		setFont(list.getFont());
		setOpaque(true);
		setIconTextGap(10);			
		return this;
	}
}
