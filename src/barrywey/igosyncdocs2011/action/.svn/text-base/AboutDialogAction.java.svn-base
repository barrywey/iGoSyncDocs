/**
 * 
 * @(#)AboutDialogAction.java Apr 6, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.action;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import barrywey.igosyncdocs2011.gui.dialog.AboutDialog;
import barrywey.igosyncdocs2011.gui.util.FaceUtils;

/**
 * 
 * 
 * 
 * @author Barry Wey
 * @version 1.0, Apr 6, 2011
 * @since JDK1.6
 */
public class AboutDialogAction implements ActionListener {

	private String action;

	public AboutDialogAction(String action) {
		this.action = action;
	}

	public void actionPerformed(ActionEvent e) {

		try {
			Desktop desktop = null;
			if (Desktop.isDesktopSupported()) {
				desktop = Desktop.getDesktop();
			}
			if (this.action.equals("donate")) {
				if (desktop != null)
					desktop.browse(new URI(
									"http://sourceforge.net/project/project_donations.php?group_id=342681"));
			} else if (this.action.equals("googlecode")) {
				if (desktop != null)
					desktop.browse(new URI(
							"http://code.google.com/p/igosyncdocs/"));
			} else if (this.action.equals("sourceforge")) {
				if (desktop != null)
					desktop.browse(new URI(
							"http://igosyncdocs.sourceforge.net/"));
			} else if (this.action.equals("bugs")) {
				if (desktop != null)
					desktop.browse(new URI(
									"http://code.google.com/p/igosyncdocs/issues/list"));
			} else if (this.action.equals("about")) {
				AboutDialog dialog = new AboutDialog();
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			}
		} catch (Exception ex) {
			FaceUtils.showErrorMessage(null, ex.getMessage() == null ? " " : ex.getMessage());
		}// end of try-catch
	}// end of method
}
