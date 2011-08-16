/**
 * 
 * @(#)CreateNewAction.java Mar 25, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import barrywey.igosyncdocs2011.biz.IGoSyncDocsBiz;
import barrywey.igosyncdocs2011.gui.MainFrame;
import barrywey.igosyncdocs2011.gui.util.FaceUtils;
import barrywey.igosyncdocs2011.resource.LanguageResource;

/**
 * 
 * 
 * 
 * @author Barry Wey
 * @version 1.0, Mar 25, 2011
 * @since JDK1.6
 */
public class CreateNewAction implements ActionListener, Runnable {

	private MainFrame frMain;
	private String type;
	private String title;

	public CreateNewAction(MainFrame frMain, String type) {
		this.frMain = frMain;
		this.type = type;
	}

	public void actionPerformed(ActionEvent e) {
		this.title = FaceUtils.showInputDialog(null, LanguageResource.getStringValue("panel.all_item.input_title"));
		if (title != null && !title.trim().equals("")) {
			if (!frMain.getProgressBar().isIndeterminate())
				new Thread(this).start();
		}// end of if
	}// end of action

	public void run() {		
		try {
			frMain.getProgressBar().setIndeterminate(true);
			frMain.getTabbedPane().setEnabled(false);
			if (type.equals("document")) {
				frMain.getProcessMessageLabel().setText(LanguageResource.getStringValue("panel.all_item.creating_document").replace("{1}", this.title));
				IGoSyncDocsBiz.createNewDocument(this.title);
			} else if (type.equals("presentation")) {
				frMain.getProcessMessageLabel().setText(LanguageResource.getStringValue("panel.all_item.creating_presentation").replace("{1}", this.title));
				IGoSyncDocsBiz.createNewPresentation(this.title);
			} else if (type.equals("spreadsheet")) {
				frMain.getProcessMessageLabel().setText(LanguageResource.getStringValue("panel.all_item.creating_spreadsheet").replace("{1}", this.title));
				IGoSyncDocsBiz.createNewSpreadsheet(this.title);
			}
			IGoSyncDocsBiz.cacheAllItem();
			frMain.refreshAllTableData();
		} catch (Exception e2) {
			FaceUtils.showErrorMessage(null, e2.getMessage() == null ? " " : e2.getMessage());
		} finally {
			frMain.getProcessMessageLabel().setText("");
			frMain.getProgressBar().setIndeterminate(false);
			frMain.getTabbedPane().setEnabled(true);
		}
	}
}
