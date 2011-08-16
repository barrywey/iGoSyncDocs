/**
 * 
 * @(#)UploadFileListCellRenderer.java Feb 18, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.renderer;

import java.awt.Component;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 *
 *
 * @author Barry Wey
 * @version 1.0, Feb 18, 2011
 * @since JDK1.6
 */
public class UploadFileListCellRenderer extends JLabel implements ListCellRenderer{

	private static final long serialVersionUID = 5816173193583478106L;

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		if(value instanceof File) {
			File file = (File)value;
			setText("    "+file.getName()+"    ");
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
