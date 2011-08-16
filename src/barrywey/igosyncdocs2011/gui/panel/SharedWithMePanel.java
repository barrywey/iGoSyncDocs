/**
 * @(#)SharedWithMePanel.java Oct 22, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import barrywey.igosyncdocs2011.bean.MyDocumentListEntry;
import barrywey.igosyncdocs2011.bean.SystemRuntime;
import barrywey.igosyncdocs2011.gui.model.EntryTableModel;
import barrywey.igosyncdocs2011.gui.renderer.EntityTableCellRenderer;

/**
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Oct 22, 2010
 * @since JDK1.6
 */
public class SharedWithMePanel extends JPanel{
	
	public SharedWithMePanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout(5, 0));
		
		pnlCenter = new JScrollPane();
		pnlCenter.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCenter.setName("pnlCenter");
		add(pnlCenter, BorderLayout.CENTER);
		
		tblAllItems = new JTable();
		tblAllItems.setModel(new EntryTableModel("shared"));
		tblAllItems.setName("tblAllItems");
		initTableSettings(tblAllItems);
		pnlCenter.setViewportView(tblAllItems);
		
		pnlRight = new JPanel();
		pnlRight.setPreferredSize(new Dimension(200, 20));
		pnlRight.setName("pnlRight");
		add(pnlRight, BorderLayout.EAST);
		
		pnlDetail = new ItemDetailPanel(); //item's detail panel
		pnlDetail.setName("pnlDetail");
		pnlRight.setLayout(new BorderLayout());
		pnlRight.add(pnlDetail);
		
		tblAllItems.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				userClickedMouse(e);
			}
		});			
	}
	
	private void userClickedMouse(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			//left click get selected document
			int[] selectedRows = tblAllItems.getSelectedRows();
			if(selectedRows.length > 0) {				
				SystemRuntime.SelectedItem.clear();		//clear previous selected item
				for (int i = 0; i < selectedRows.length; i++) {
					MyDocumentListEntry entry = ((EntryTableModel)tblAllItems.getModel()).getEntries().get(selectedRows[i]);
					SystemRuntime.SelectedItem.add(entry);
					
					if(i == selectedRows.length -1) {
						//show last selected item's detail
						pnlDetail.shownEntryDetail(entry);
					}
				}//end of for
			}//end of if(rows>0)
		}else if(e.getButton() == MouseEvent.BUTTON3) {
			//right click show popup menu
		}
	}	
	
	public ItemDetailPanel getDetailPanel() {
		return this.pnlDetail;
	}
	
	public JTable getDataTable() {
		return this.tblAllItems;
	}

	public void initTableSettings(JTable tbl) {
		tbl.getTableHeader().setReorderingAllowed(false);
		tbl.setRowHeight(20);
		tbl.setAutoCreateRowSorter(true);
		tbl.getColumnModel().getColumn(0).setPreferredWidth(30);
		tbl.getColumnModel().getColumn(0).setCellRenderer(new EntityTableCellRenderer());
		tbl.getColumnModel().getColumn(1).setPreferredWidth(30);
		tbl.getColumnModel().getColumn(1).setCellRenderer(new EntityTableCellRenderer());
		tbl.getColumnModel().getColumn(2).setPreferredWidth(390);
		tbl.getColumnModel().getColumn(2).setCellRenderer(new EntityTableCellRenderer());
		tbl.getColumnModel().getColumn(3).setPreferredWidth(170);
		tbl.getColumnModel().getColumn(3).setCellRenderer(new EntityTableCellRenderer());
		tbl.getColumnModel().getColumn(4).setPreferredWidth(130);
		tbl.getColumnModel().getColumn(4).setCellRenderer(new EntityTableCellRenderer());
	}
	
	private JScrollPane pnlCenter;
	private JTable tblAllItems;
	private JPanel pnlRight;
	private ItemDetailPanel pnlDetail;			
	private static final long serialVersionUID = -4297049468691141935L;

}
