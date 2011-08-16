/**
 * 
 * @(#)SearchItemAction.java Apr 6, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import barrywey.igosyncdocs2011.bean.MyDocumentListEntry;
import barrywey.igosyncdocs2011.biz.IGoSyncDocsBiz;
import barrywey.igosyncdocs2011.gui.MainFrame;
import barrywey.igosyncdocs2011.gui.model.EntryTableModel;
import barrywey.igosyncdocs2011.resource.LanguageResource;

/**
 * 
 * 
 * 
 * @author Barry Wey
 * @version 1.0, Apr 6, 2011
 * @since JDK1.6
 */
public class SearchItemAction implements ActionListener , Runnable{

	private MainFrame frMain;

	public SearchItemAction(MainFrame frMain) {
		this.frMain = frMain;
	}

	public void actionPerformed(ActionEvent e) {
		if(!frMain.getSearchField().getText().trim().equals("")) {
			new Thread(this).start();
		}
	}// end of method
	
	public void run() {
		if(frMain.getSearchButton().getActionCommand().equals("search")) {
			frMain.getProgressBar().setIndeterminate(true);
			frMain.getTabbedPane().setEnabled(false);
			frMain.getSearchButton().setText(LanguageResource.getStringValue("main.btn.restore"));
			frMain.getSearchButton().setActionCommand("restore");
			List<MyDocumentListEntry> foundEntries = IGoSyncDocsBiz.search(frMain.getSearchField().getText().trim());
			frMain.getAllItemPanel().getDataTable().setModel(new EntryTableModel(foundEntries));
			frMain.getAllItemPanel().initTableSettings(frMain.getAllItemPanel().getDataTable());
			frMain.getTabbedPane().setEnabled(true);
			frMain.getProgressBar().setIndeterminate(false);			
		} else {
			frMain.getSearchButton().setText(LanguageResource.getStringValue("main.btn.search"));
			frMain.getSearchButton().setActionCommand("search");
			frMain.getAllItemPanel().getDataTable().setModel(new EntryTableModel("all"));
			frMain.getAllItemPanel().initTableSettings(frMain.getAllItemPanel().getDataTable());	
			frMain.getSearchField().setText("");
		}
	}
}// end of class
