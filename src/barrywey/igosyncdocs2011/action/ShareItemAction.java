/**
 * 
 * @(#)ShareItemAction.java Feb 17, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import barrywey.igosyncdocs2011.bean.MyDocumentListEntry;
import barrywey.igosyncdocs2011.bean.SystemRuntime;
import barrywey.igosyncdocs2011.biz.IGoSyncDocsBiz;
import barrywey.igosyncdocs2011.gui.MainFrame;
import barrywey.igosyncdocs2011.gui.dialog.ShareItemDialog;
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
public class ShareItemAction implements ActionListener , Runnable{

	private ShareItemDialog dialog;
	private MainFrame frMain;
	
	public ShareItemAction(ShareItemDialog dialog, MainFrame frMain) {
		this.dialog = dialog;
		this.frMain = frMain;
	}

	public void actionPerformed(ActionEvent e) {
		if(dialog.validateUserInput())
			new Thread(this).start();
	}
	
	public void run() {
		try {
			frMain.getTabbedPane().setEnabled(false);
			String[] emails = dialog.getEnterdEmails().split(",");			
			dialog.setVisible(false);
			frMain.getProgressBar().setIndeterminate(true);
			for(MyDocumentListEntry entry : SystemRuntime.SelectedItem) {
				frMain.getProcessMessageLabel().setText(LanguageResource.getStringValue("main.message.share_running").replace("{1}", entry.getEntry().getTitle().getPlainText()));
				for(String email : emails) {
					if(!email.trim().equals(""))
						IGoSyncDocsBiz.shareItem(email, dialog.isWriter(), entry);
				}
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
