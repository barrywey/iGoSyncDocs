/**
 * 
 * @(#)StarItemAction.java Feb 17, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.action;

import barrywey.igosyncdocs2011.bean.MyDocumentListEntry;
import barrywey.igosyncdocs2011.bean.SystemRuntime;
import barrywey.igosyncdocs2011.biz.IGoSyncDocsBiz;
import barrywey.igosyncdocs2011.gui.MainFrame;
import barrywey.igosyncdocs2011.gui.dialog.ConfirmActionDialog;
import barrywey.igosyncdocs2011.gui.util.FaceUtils;
import barrywey.igosyncdocs2011.resource.LanguageResource;

/**
 * 
 * 
 * 
 * @author Barry Wey
 * @version 1.0, Feb 17, 2011
 * @since JDK1.6
 */
public class StarItemAction implements Runnable {

	private MainFrame frMain;
	private ConfirmActionDialog dialog;

	public StarItemAction(ConfirmActionDialog dialog,
			MainFrame frMain) {
		this.frMain = frMain;
		this.dialog = dialog;
	}

	public void run() {
		try {
			frMain.getTabbedPane().setEnabled(false);
			dialog.setVisible(false);
			frMain.getProgressBar().setIndeterminate(true);
			for(MyDocumentListEntry entry : SystemRuntime.SelectedItem) {
				frMain.getProcessMessageLabel().setText(LanguageResource.getStringValue("main.message.star_running").replace("{1}", entry.getEntry().getTitle().getPlainText()));
				IGoSyncDocsBiz.starItem(entry);
			}
			IGoSyncDocsBiz.cacheAllItem();
			frMain.refreshAllTableData();
		}catch (Exception e) {
			FaceUtils.showErrorMessage(null, LanguageResource.getStringValue(
					"main.message.error").replace("{1}",
					e.getMessage() == null ? " " : e.getMessage()));
		}finally {
			frMain.getProcessMessageLabel().setText("");
			frMain.getProgressBar().setIndeterminate(false);
			dialog.dispose();
			frMain.getTabbedPane().setEnabled(true);
		}
	}

}
