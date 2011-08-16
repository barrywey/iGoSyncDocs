/**
 * 
 * @(#)UploadFilesAction.java Feb 17, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import barrywey.igosyncdocs2011.biz.IGoSyncDocsBiz;
import barrywey.igosyncdocs2011.biz.IGoSyncDocsException;
import barrywey.igosyncdocs2011.gui.MainFrame;
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
public class UploadFilesAction implements ActionListener , Runnable{

	private MainFrame frMain;
	private File[] selectedFiles;
	
	public UploadFilesAction(MainFrame frMain) {
		this.frMain = frMain;
	}

	public void actionPerformed(ActionEvent e) {
		if(!frMain.getProgressBar().isIndeterminate()) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setMultiSelectionEnabled(true);
			int result = fileChooser.showDialog(null, LanguageResource.getStringValue("main.message.upload_label"));
			if(result == JFileChooser.APPROVE_OPTION) {
				this.selectedFiles = fileChooser.getSelectedFiles();
				new Thread(this).start();//start to upload
			}
		}else 
			FaceUtils.showErrorMessage(null, LanguageResource.getStringValue("main.message.another_process_running"));
	}
	
	public void run() {
		try {
			frMain.getTabbedPane().setEnabled(false);
			frMain.getProgressBar().setIndeterminate(true);
			for(File file : selectedFiles) {
				frMain.getProcessMessageLabel().setText(LanguageResource.getStringValue("main.dialog.upload.upload_process").replace("{1}", file.getName()));
				IGoSyncDocsBiz.upload(file);
			}
			IGoSyncDocsBiz.cacheAllItem();
			frMain.refreshAllTableData();
		} catch (IGoSyncDocsException e) {
			FaceUtils.showErrorMessage(null, LanguageResource.getStringValue("main.dialog.upload.upload_error").replace("{1}",e.getMessage()));
		} finally {
			frMain.getProgressBar().setIndeterminate(false);
			frMain.getProcessMessageLabel().setText("");
			frMain.getTabbedPane().setEnabled(true);
		}//end of try-catch-finally		
	}
}
