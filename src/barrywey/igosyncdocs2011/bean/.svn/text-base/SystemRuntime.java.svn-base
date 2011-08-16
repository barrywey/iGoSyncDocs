/**
 * @(#)Runtime.java Oct 18, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywey.igosyncdocs2011.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Oct 18, 2010
 * @since JDK1.6
 */
public class SystemRuntime implements Serializable {

	private static final long serialVersionUID = 3636286982031235354L;
	public static UserSettings Settings = new UserSettings();
	public static List<MyDocumentListEntry> CachedEntries = Collections.synchronizedList(new ArrayList<MyDocumentListEntry>());
	public static List<MyDocumentListEntry> SelectedItem = Collections.synchronizedList(new ArrayList<MyDocumentListEntry>());
	//public static List<MyDocumentListEntry> CachedEntries = new ArrayList<MyDocumentListEntry>();
	//public static List<MyDocumentListEntry> SelectedItem = new ArrayList<MyDocumentListEntry>();

}

