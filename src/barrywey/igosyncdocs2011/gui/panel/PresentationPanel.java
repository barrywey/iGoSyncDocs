/**
 * @(#)DocumentPanel.java Oct 22, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import barrywey.igosyncdocs2011.action.RefreshItemAction;
import barrywey.igosyncdocs2011.action.ShowConfirmDialogAction;
import barrywey.igosyncdocs2011.action.UploadFilesAction;
import barrywey.igosyncdocs2011.action.ViewOnlineAction;
import barrywey.igosyncdocs2011.bean.MyDocumentListEntry;
import barrywey.igosyncdocs2011.bean.SystemRuntime;
import barrywey.igosyncdocs2011.gui.MainFrame;
import barrywey.igosyncdocs2011.gui.model.EntryTableModel;
import barrywey.igosyncdocs2011.gui.renderer.EntityTableCellRenderer;
import barrywey.igosyncdocs2011.resource.ImageResource;
import barrywey.igosyncdocs2011.resource.LanguageResource;

/**
 * 
 *
 *
 * @author BarryWei
 * @version 1.0, Oct 22, 2010
 * @since JDK1.6
 */
public class PresentationPanel extends JPanel{

	private MainFrame frMain;
	
	public PresentationPanel(MainFrame frMain) {
		this.frMain = frMain;
		initComponents();
	}

	private void initComponents() {
		
		//set up pupup menu
		popup.add(miViewOnLine);
		popup.addSeparator();
		popup.add(miRefresh);
		popup.add(miUpload);
		popup.addSeparator();
		popup.add(miDownloadAsPpt);
		popup.add(miDownloadAsPdf);
		popup.add(miDownloadAsPng);
		popup.add(miDownloadAsSwf);
		popup.add(miDownloadAsTxt);
		popup.addSeparator();
		popup.add(miStar);
		popup.add(miHide);
		popup.addSeparator();
		popup.add(miDelete);
		popup.add(miTrash);
		popup.addSeparator();
		popup.add(miShare);	
		
		setLayout(new BorderLayout(5, 0));
		
		pnlCenter = new JScrollPane();
		pnlCenter.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlCenter.setName("pnlCenter");
		add(pnlCenter, BorderLayout.CENTER);
		
		tblAllItems = new JTable();
		tblAllItems.setModel(new EntryTableModel("presentation"));
		tblAllItems.setName("tblAllItems");
		initTableSettings(tblAllItems);
		pnlCenter.setViewportView(tblAllItems);
		tblAllItems.add(popup);
		
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
		
		miViewOnLine.addActionListener(new ViewOnlineAction());
		miRefresh.addActionListener(new RefreshItemAction(frMain));
		miUpload.addActionListener(new UploadFilesAction(frMain));
		miViewOnLine.setIcon(ImageResource.getIcon("view_online.png"));
		miRefresh.setIcon(ImageResource.getIcon("refresh.png"));
		miUpload.setIcon(ImageResource.getIcon("upload.png"));
		
		miStar.addActionListener(new ShowConfirmDialogAction(frMain, "star",null));
		miHide.addActionListener(new ShowConfirmDialogAction(frMain, "hide",null));
		miDelete.addActionListener(new ShowConfirmDialogAction(frMain, "delete",null));
		miTrash.addActionListener(new ShowConfirmDialogAction(frMain, "trash",null));
		miShare.addActionListener(new ShowConfirmDialogAction(frMain, "share",null));	
		
		miDownloadAsPdf.addActionListener(new ShowConfirmDialogAction(frMain, "download", "pdf"));
		miDownloadAsPng.addActionListener(new ShowConfirmDialogAction(frMain, "download", "png"));
		miDownloadAsPpt.addActionListener(new ShowConfirmDialogAction(frMain, "download", "ppt"));
		miDownloadAsSwf.addActionListener(new ShowConfirmDialogAction(frMain, "download", "swf"));
		miDownloadAsTxt.addActionListener(new ShowConfirmDialogAction(frMain, "download", "txt"));
	}
	
	private void userClickedMouse(MouseEvent e) {
		int[] selectedRows = tblAllItems.getSelectedRows();
		if(selectedRows.length > 0) {
			if(e.getButton() == MouseEvent.BUTTON1) {
				//left click get selected document			
				SystemRuntime.SelectedItem.clear();		//clear previous selected item
				for (int i = 0; i < selectedRows.length; i++) {
					MyDocumentListEntry entry = ((EntryTableModel)tblAllItems.getModel()).getEntries().get(selectedRows[i]);
					SystemRuntime.SelectedItem.add(entry);
					
					if(i == selectedRows.length -1) {
						//show last selected item's detail
						pnlDetail.shownEntryDetail(entry);
					}
				}//end of for
			}else if(e.getButton() == MouseEvent.BUTTON3) {
				// right click show popup menu
				popup.show(tblAllItems, e.getX(), e.getY());
			}			
		}//end of if(selectedRows.length > 0)
	}	
	
	public JTable getDataTable() {
		return this.tblAllItems;
	}
	
	public ItemDetailPanel getDetailPanel() {
		return this.pnlDetail;
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
	private static final long serialVersionUID = -8000318753267046783L;
	
	private JPopupMenu popup = new JPopupMenu();
	private JMenuItem miViewOnLine = new JMenuItem(LanguageResource.getStringValue("panel.all_item.pupup_viewonline"));	
	private JMenuItem miUpload = new JMenuItem(LanguageResource.getStringValue("panel.all_item.pupup_up"));
	private JMenuItem miRefresh = new JMenuItem(LanguageResource.getStringValue("panel.all_item.pupup_rf"));
	
	private JMenuItem miDownloadAsPpt = new JMenuItem(LanguageResource.getStringValue("panel.pnl_presentation.download_as_ppt"));
	private JMenuItem miDownloadAsPng = new JMenuItem(LanguageResource.getStringValue("panel.pnl_presentation.download_as_png"));
	private JMenuItem miDownloadAsPdf = new JMenuItem(LanguageResource.getStringValue("panel.pnl_presentation.download_as_pdf"));
	private JMenuItem miDownloadAsTxt = new JMenuItem(LanguageResource.getStringValue("panel.pnl_presentation.download_as_txt"));
	private JMenuItem miDownloadAsSwf = new JMenuItem(LanguageResource.getStringValue("panel.pnl_presentation.download_as_swf"));	
	
	private JMenuItem miStar = new JMenuItem(LanguageResource.getStringValue("panel.all_item.pupup_star"),ImageResource.getIcon("stared.png"));
	private JMenuItem miHide = new JMenuItem(LanguageResource.getStringValue("panel.all_item.pupup_hide"),ImageResource.getIcon("hidden.png"));	
	private JMenuItem miTrash = new JMenuItem(LanguageResource.getStringValue("panel.all_item.pupup_trash"),ImageResource.getIcon("trashed.png"));
	private JMenuItem miDelete = new JMenuItem(LanguageResource.getStringValue("panel.all_item.pupup_delete"));
	private JMenuItem miShare = new JMenuItem(LanguageResource.getStringValue("panel.all_item.pupup_share"));	

}
