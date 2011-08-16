/**
 * 
 * @(#)JLinkedLabel.java Feb 25, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;

import javax.swing.JLabel;

/**
 *
 *
 *
 * @author Barry Wey
 * @version 1.0, Feb 25, 2011
 * @since JDK1.6
 */
public class JLinkedLabel extends JLabel implements MouseListener{

	private static final long serialVersionUID = -7660590169545320113L;
	private String href;
	
	public JLinkedLabel(String label,String href) {
		setText("<html><b>"+label+"</b><html>");
		this.href = href;
		setForeground(Color.BLUE);
		addMouseListener(this);
	}
	
	public void setHref(String href) {
		this.href = href;
	}

	public void mouseClicked(MouseEvent e) {
		if(this.href != null && !this.href.trim().equals("")) {
			if(Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI(this.href));
				} catch(Exception e1) {
					//we have to do nothing here,right? i don't want to prompt some stupid dialog to user.
				}
			}//end of if
		}//end of if
	}

	public void mouseEntered(MouseEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

	public void mouseExited(MouseEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}
}
