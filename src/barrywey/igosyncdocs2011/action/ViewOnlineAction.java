/**
 * 
 * @(#)ViewOnlineAction.java Mar 25, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.action;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import barrywey.igosyncdocs2011.bean.MyDocumentListEntry;
import barrywey.igosyncdocs2011.bean.SystemRuntime;
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
public class ViewOnlineAction implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		List<MyDocumentListEntry> selectedEntries = SystemRuntime.SelectedItem;
		if (selectedEntries != null && selectedEntries.size() > 0) {
			MyDocumentListEntry entry = selectedEntries.get(0);//only open first selected item
			if(Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(
							new URI(entry.getEntry().getHtmlLink().getHref()));
				} catch (IOException e1) {
					FaceUtils.showErrorMessage(null, LanguageResource
							.getStringValue("main.message.error").replace(
									"{1}",
									e1.getMessage() == null ? " " : e1
											.getMessage()));
				} catch (URISyntaxException e1) {
					FaceUtils.showErrorMessage(null, LanguageResource
							.getStringValue("main.message.error").replace(
									"{1}",
									e1.getMessage() == null ? " " : e1
											.getMessage()));
				} catch (Exception e1) {
					FaceUtils.showErrorMessage(null, LanguageResource
							.getStringValue("main.message.error").replace(
									"{1}",
									e1.getMessage() == null ? " " : e1
											.getMessage()));
				}
			}//end of if(Desktop supported)
		}//end of if
	}//end of method
}
