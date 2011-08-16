/**
 * 
 * @(#)TrashItemAction.java Feb 15, 2011
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
 * @version 1.0, Feb 15, 2011
 * @since JDK1.6
 */
public class DeleteItemAction implements Runnable {

	private MainFrame frMain;
	private ConfirmActionDialog dialog;
	
	public DeleteItemAction(ConfirmActionDialog dialog, MainFrame frMain) {
		this.frMain = frMain;
		this.dialog = dialog;
	}

	public void run() {
		try {
			dialog.setVisible(false);
			frMain.getTabbedPane().setEnabled(false);
			frMain.getProgressBar().setIndeterminate(true);
			for(MyDocumentListEntry entry : SystemRuntime.SelectedItem) {
				frMain.getProcessMessageLabel().setText(LanguageResource.getStringValue("main.message.del_running").replace("{1}", entry.getEntry().getTitle().getPlainText()));
				IGoSyncDocsBiz.delete(entry);
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
