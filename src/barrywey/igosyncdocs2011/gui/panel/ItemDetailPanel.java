/**
 * 
 * @(#)ItemDetailPanel.java Feb 24, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.panel;

import javax.swing.JPanel;

import barrywey.igosyncdocs2011.bean.MyDocumentListEntry;
import barrywey.igosyncdocs2011.gui.JLinkedLabel;
import barrywey.igosyncdocs2011.gui.model.AclEntryTableModel;
import barrywey.igosyncdocs2011.gui.renderer.AclTableCellRenderer;
import barrywey.igosyncdocs2011.resource.LanguageResource;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 * 
 * 
 * 
 * @author Barry Wey
 * @version 1.0, Feb 24, 2011
 * @since JDK1.6
 */
public class ItemDetailPanel extends JSplitPane {

	private static final long serialVersionUID = -1573751409066337643L;
	private final JScrollPane pnlDown = new JScrollPane();
	private final JPanel pnlUp = new JPanel();
	private final JTable table = new JTable();
	private final JLinkedLabel lblViewOnLine = new JLinkedLabel(LanguageResource.getStringValue("panel.item_detail_lbllink"), "");
	private final JLabel lblTitle = new JLabel("");

	public ItemDetailPanel() {
		super(JSplitPane.VERTICAL_SPLIT);
		initComponents();
	}
	
	public JTable getAclTable() {
		return this.table;
	}
	
	public void clearDetail() {
		table.setModel(new AclEntryTableModel(null));
		lblTitle.setText("");
		lblViewOnLine.setHref("");
	}
	
	public void shownEntryDetail(MyDocumentListEntry entry) {
		if(entry != null) {
			table.setModel(new AclEntryTableModel(entry));
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getColumnModel().getColumn(0).setPreferredWidth(50);
			table.getColumnModel().getColumn(0).setCellRenderer(new AclTableCellRenderer());
			table.getColumnModel().getColumn(1).setPreferredWidth(200);
			table.getColumnModel().getColumn(1).setCellRenderer(new AclTableCellRenderer());			

			lblTitle.setText("<html>"+entry.getEntry().getTitle().getPlainText()+"</html>");
			lblViewOnLine.setHref(entry.getEntry().getHtmlLink().getHref());
		}//end of if
	}//end of method shownEntryDetail

	private void initComponents() {
		setOneTouchExpandable(false);
		setBorder(null);
		setResizeWeight(0.5);
		pnlDown.setBorder(BorderFactory.createTitledBorder(LanguageResource.getStringValue("panel.title_people_shared_with")));
		table.setBackground(getBackground());
		pnlDown.setViewportView(table);
		setBottomComponent(pnlDown);
		pnlUp.setBorder(BorderFactory.createTitledBorder(LanguageResource.getStringValue("panel.title_item_detail")));
		setTopComponent(pnlUp);
		pnlUp.setLayout(null);
		lblViewOnLine.setBounds(18, 55, 125, 25);
		
		pnlUp.add(lblViewOnLine);
		lblTitle.setHorizontalTextPosition(SwingConstants.LEFT);
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setVerticalTextPosition(SwingConstants.TOP);
		lblTitle.setVerticalAlignment(SwingConstants.TOP);
		lblTitle.setBounds(29, 19, 150, 36);
		
		pnlUp.add(lblTitle);
		setDividerLocation(0.5);
		setDividerLocation(100);
	}
}
