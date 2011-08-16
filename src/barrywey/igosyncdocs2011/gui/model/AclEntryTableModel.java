/**
 * 
 * @(#)AclEntryTableModel.java Mar 3, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import barrywey.igosyncdocs2011.bean.MyDocumentListEntry;
import barrywey.igosyncdocs2011.resource.LanguageResource;

import com.google.gdata.data.acl.AclEntry;

/**
 *
 *
 *
 * @author Barry Wey
 * @version 1.0, Mar 3, 2011
 * @since JDK1.6
 */
public class AclEntryTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 6687932160855910444L;
	private MyDocumentListEntry entry;
	private Vector<AclEntry> data;
	private Vector<String> columns;
	
	public AclEntryTableModel(MyDocumentListEntry entry) {
		if(entry != null) {
			this.entry = entry;
			initData();
		}
	}
	
	private void initData() {
		columns = new Vector<String>();
		columns.add(LanguageResource.getStringValue("panel.item_detail_th_role"));
		columns.add(LanguageResource.getStringValue("panel.item_detail_th_scope"));
		data = new Vector<AclEntry>();
		List<AclEntry> entries = this.entry.getAclFeeds().getEntries();
		for(AclEntry e : entries) {
			data.add(e);
		}
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		if(columns == null)
			return null;
		else
			return columns.get(column);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		if(columns == null)
			return 0;
		else
			return columns.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		if(data == null)
			return 0;
		else
			return data.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(data == null)
			return null;
		else
			return data.get(rowIndex);
	}

	
}
