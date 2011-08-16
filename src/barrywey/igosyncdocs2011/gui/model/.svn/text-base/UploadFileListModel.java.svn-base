/**
 * 
 * @(#)UploadFileTableModel.java Feb 18, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.model;

import java.io.File;

import javax.swing.DefaultListModel;

/**
 *
 *
 *
 * @author Barry Wey
 * @version 1.0, Feb 18, 2011
 * @since JDK1.6
 */
public class UploadFileListModel extends DefaultListModel {

	private static final long serialVersionUID = -1246727134151209620L;
	private File[] files;

	public UploadFileListModel(File[] files) {
		this.files = files;
		initData();
	}
	
	private void initData() {
		for(File file : files) {
			addElement(file);
		}
	}
}

