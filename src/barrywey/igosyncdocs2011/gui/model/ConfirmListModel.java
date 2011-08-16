/**
 * 
 * @(#)ConfirmListModel.java Feb 15, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.model;

import javax.swing.DefaultListModel;

import barrywey.igosyncdocs2011.bean.MyDocumentListEntry;
import barrywey.igosyncdocs2011.bean.SystemRuntime;


/**
 *
 *
 *
 * @author Barry Wey
 * @version 1.0, Feb 15, 2011
 * @since JDK1.6
 */
public class ConfirmListModel extends DefaultListModel {

	private static final long serialVersionUID = 5763656183727411756L;

	public ConfirmListModel() {
		for(MyDocumentListEntry entry : SystemRuntime.SelectedItem) {
			addElement(entry);
		}
	}//end of method
}
