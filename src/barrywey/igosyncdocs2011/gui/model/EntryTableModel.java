/**
 * @(#)AllItemTableModel.java Oct 19, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import barrywey.igosyncdocs2011.bean.MyDocumentListEntry;
import barrywey.igosyncdocs2011.biz.IGoSyncDocsBiz;
import barrywey.igosyncdocs2011.resource.LanguageResource;

/**
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Oct 19, 2010
 * @since JDK1.6
 */
public class EntryTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 3857728294426996858L;
	private List<MyDocumentListEntry> entries;
	private Vector<String> columnName;
	
	public List<MyDocumentListEntry> getEntries() {
		return this.entries;
	}
	
	public EntryTableModel(List<MyDocumentListEntry> list) {
		super();
		this.entries = list;
		columnName = new Vector<String>();
		columnName.add(LanguageResource.getStringValue("main.frame.th_star"));
		columnName.add(LanguageResource.getStringValue("main.frame.th_type"));
		columnName.add(LanguageResource.getStringValue("main.frame.th_name"));
		columnName.add(LanguageResource.getStringValue("main.frame.th_owner"));
		columnName.add(LanguageResource.getStringValue("main.frame.th_lastupdate"));		
	}

	public EntryTableModel(String type) {
		super();
		if(type.trim().equalsIgnoreCase("all"))
			entries = IGoSyncDocsBiz.getAllItems();
		else if(type.trim().equalsIgnoreCase("document"))
			entries = IGoSyncDocsBiz.getAllDocuments();
		else if(type.trim().equalsIgnoreCase("spreadsheet"))
			entries = IGoSyncDocsBiz.getAllSpreadsheets();
		else if(type.trim().equalsIgnoreCase("presentation"))
			entries = IGoSyncDocsBiz.getAllPresentations();
		else if(type.trim().equalsIgnoreCase("other"))
			entries = IGoSyncDocsBiz.getAllOthers();
		else if(type.trim().equalsIgnoreCase("hidden")) 
			entries = IGoSyncDocsBiz.getHiddenObjects();
		else if(type.trim().equalsIgnoreCase("stared"))
			entries = IGoSyncDocsBiz.getStaredObjects();
		else if(type.trim().equalsIgnoreCase("trashed"))
			entries = IGoSyncDocsBiz.getTrashedObjects();
		else if(type.trim().equalsIgnoreCase("shared"))
			entries = IGoSyncDocsBiz.getSharedWithMeObjects();
		columnName = new Vector<String>();
		columnName.add(LanguageResource.getStringValue("main.frame.th_star"));
		columnName.add(LanguageResource.getStringValue("main.frame.th_type"));
		columnName.add(LanguageResource.getStringValue("main.frame.th_name"));
		columnName.add(LanguageResource.getStringValue("main.frame.th_owner"));
		columnName.add(LanguageResource.getStringValue("main.frame.th_lastupdate"));
	}

	public int getColumnCount() {
		if(columnName == null)
			return 0;
		else
			return columnName.size();
	}

	public int getRowCount() {
		if(entries == null)
			return 0;
		else
			return entries.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if(entries == null)
			return null;
		else
			return entries.get(rowIndex);
	}
	
	public String getColumnName(int column) {
		if(columnName == null)
			return null;
		else
			return columnName.get(column);
	}
}
 