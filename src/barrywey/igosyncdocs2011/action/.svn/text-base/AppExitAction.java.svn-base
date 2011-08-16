/**
 * @(#)AppExitAction.java Oct 24, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywey.igosyncdocs2011.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import barrywey.igosyncdocs2011.gui.util.FaceUtils;
import barrywey.igosyncdocs2011.resource.LanguageResource;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Oct 24, 2010
 * @since JDK1.6
 */
public class AppExitAction extends WindowAdapter implements ActionListener {

	public void windowClosing(WindowEvent e) {
		String message = LanguageResource.getStringValue("app.confirm_exit");
		int result = FaceUtils.showConfirmMessage(null, message);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(1);
		}
	}

	public void actionPerformed(ActionEvent e) {
		String message = LanguageResource.getStringValue("app.confirm_exit");
		int result = FaceUtils.showConfirmMessage(null, message);
		if (result == JOptionPane.YES_OPTION) {
			System.exit(1);
		}
	}
}
