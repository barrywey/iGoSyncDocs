/**
 * 
 * @(#)ChangeLanguageAction.java Apr 6, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import barrywey.igosyncdocs2011.biz.IGoSyncDocsBiz;
import barrywey.igosyncdocs2011.gui.util.FaceUtils;
import barrywey.igosyncdocs2011.resource.LanguageResource;

/**
 *
 *
 *
 * @author Barry Wey
 * @version 1.0, Apr 6, 2011
 * @since JDK1.6
 */
public class ChangeLanguageAction implements ActionListener {

	private String language;
	
	public ChangeLanguageAction(String language) {
		this.language = language;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			IGoSyncDocsBiz.changeLanguage(this.language);
			FaceUtils.showInformationMessage(null, LanguageResource.getStringValue("main.menu.language.message"));
		} catch (Exception ex) {
			FaceUtils.showErrorMessage(null, ex.getMessage() == null ? " " : ex.getMessage());
		}//end of try-catch		
	}
}
