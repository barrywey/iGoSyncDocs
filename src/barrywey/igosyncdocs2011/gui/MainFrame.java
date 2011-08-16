/**
 * @(#)MainFrame.java Oct 12, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenu;

import barrywey.igosyncdocs2011.action.AboutDialogAction;
import barrywey.igosyncdocs2011.action.AppExitAction;
import barrywey.igosyncdocs2011.action.ChangeLanguageAction;
import barrywey.igosyncdocs2011.action.CreateNewAction;
import barrywey.igosyncdocs2011.action.RefreshItemAction;
import barrywey.igosyncdocs2011.action.SearchItemAction;
import barrywey.igosyncdocs2011.action.SystemTrayAction;
import barrywey.igosyncdocs2011.action.ShowConfirmDialogAction;
import barrywey.igosyncdocs2011.action.UploadFilesAction;
import barrywey.igosyncdocs2011.bean.SystemRuntime;
import barrywey.igosyncdocs2011.gui.model.EntryTableModel;
import barrywey.igosyncdocs2011.gui.panel.AllItemPanel;
import barrywey.igosyncdocs2011.gui.panel.DocumentPanel;
import barrywey.igosyncdocs2011.gui.panel.HiddenObjectsPanel;
import barrywey.igosyncdocs2011.gui.panel.OtherFilesPanel;
import barrywey.igosyncdocs2011.gui.panel.PresentationPanel;
import barrywey.igosyncdocs2011.gui.panel.SharedWithMePanel;
import barrywey.igosyncdocs2011.gui.panel.SpreadsheetPanel;
import barrywey.igosyncdocs2011.gui.panel.StaredObjectsPanel;
import barrywey.igosyncdocs2011.gui.panel.TrashedObjectsPanel;
import barrywey.igosyncdocs2011.resource.ImageResource;
import barrywey.igosyncdocs2011.resource.LanguageResource;

import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Oct 12, 2010
 * @since JDK1.6
 */
public class MainFrame extends JFrame {
	
	public MainFrame() {
		initComponents();
	}

	private void initComponents() {
		setTitle(LanguageResource.getStringValue("app.title"));
		setMinimumSize(new Dimension(990, 660));
		setSize(new Dimension(990, 660));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(ImageResource.getImage("window-icon.png"));
		pnlMain = new JPanel();
		pnlMain.setBorder(new EmptyBorder(0, 0, 5, 0));
		pnlMain.setLayout(new BorderLayout());
		setContentPane(pnlMain);
		
		menuBar = new JMenuBar();
		menuBar.setName("menuBar");
		setJMenuBar(menuBar);
		
		mnFile = new JMenu(LanguageResource.getStringValue("main.menu.file"));
		mnFile.setMnemonic('F');
		mnFile.setName("mnFile");
		menuBar.add(mnFile);
		
		miNewDocument = new JMenuItem(LanguageResource.getStringValue("main.menuitem.new_doc"));
		miNewDocument.setMnemonic('D');
		miNewDocument.setIcon(ImageResource.getIcon("doc.png"));
		miNewDocument.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		miNewDocument.setName("miNewDocument");
		mnFile.add(miNewDocument);
		
		miSpreadsheet = new JMenuItem(LanguageResource.getStringValue("main.menuitem.new_spre"));
		miSpreadsheet.setMnemonic('S');
		miSpreadsheet.setIcon(ImageResource.getIcon("spreadsheet.png"));
		miSpreadsheet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		miSpreadsheet.setName("miSpreadsheet");
		mnFile.add(miSpreadsheet);
		
		miPresentation = new JMenuItem(LanguageResource.getStringValue("main.menuitem.new_pres"));
		miPresentation.setMnemonic('P');
		miPresentation.setIcon(ImageResource.getIcon("presentation.png"));
		miPresentation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		miPresentation.setName("miPresentation");
		mnFile.add(miPresentation);
		
		miRefresh = new JMenuItem(LanguageResource.getStringValue("main.menuitem.refresh"));
		miRefresh.setMnemonic('R');
		miRefresh.setIcon(ImageResource.getIcon("refresh.png"));
		miRefresh.setName("miRefresh");
		miRefresh.setAccelerator(KeyStroke.getKeyStroke("F5"));
		mnFile.addSeparator();
		mnFile.add(miRefresh);
		
		miUpload = new JMenuItem(LanguageResource.getStringValue("main.menuitem.upload"));
		miUpload.setMnemonic('U');
		miUpload.setIcon(ImageResource.getIcon("upload.png"));
		miUpload.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,InputEvent.CTRL_MASK));
		miUpload.setName("miUpload");
		mnFile.add(miUpload);

		miExit = new JMenuItem(LanguageResource.getStringValue("main.menuitem.exit"));
		miExit.setMnemonic('x');
		miExit.setName("miExit");
		miExit.setIcon(ImageResource.getIcon("exit.png"));
		mnFile.addSeparator();
		mnFile.add(miExit);
		
//		mnSetting = new JMenu(LanguageResource.getStringValue("main.menu.setting"));
//		mnSetting.setMnemonic('S');
//		mnSetting.setName("mnSetting");
//		menuBar.add(mnSetting);
		
		mnLanguage = new JMenu(LanguageResource.getStringValue("main.menu.language"));
		mnLanguage.setMnemonic('L');
		mnLanguage.setName("mnLanguage");
		//mnSetting.add(mnLanguage);
		menuBar.add(mnLanguage);
		
		miEnglish = new JMenuItem(LanguageResource.getStringValue("main.menu.language.en_US"));
		miEnglish.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		miEnglish.setIcon(ImageResource.getIcon("language_usa.png"));
		
		miSimpleChinese = new JMenuItem(LanguageResource.getStringValue("main.menu.language.zh_CN"));
		miSimpleChinese.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		miSimpleChinese.setIcon(ImageResource.getIcon("language_china.png"));
		
		miTraditionalChinese = new JMenuItem(LanguageResource.getStringValue("main.menu.language.zh_TW"));
		miTraditionalChinese.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		miTraditionalChinese.setIcon(ImageResource.getIcon("language_taiwan.png"));
		
		miJapanese = new JMenuItem(LanguageResource.getStringValue("main.menu.language.ja_JP"));
		miJapanese.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		miJapanese.setIcon(ImageResource.getIcon("language_japan.png"));		
		
		mnLanguage.add(miEnglish);
		mnLanguage.add(miSimpleChinese);
		mnLanguage.add(miTraditionalChinese);
		mnLanguage.add(miJapanese);		
		
		mnHelp = new JMenu(LanguageResource.getStringValue("main.menu.help"));
		mnHelp.setMnemonic('H');
		mnHelp.setName("mnHelp");
		menuBar.add(mnHelp);
		
		miAboutiGoSyncDocs = new JMenuItem(LanguageResource.getStringValue("main.menuitem.about"));
		miAboutiGoSyncDocs.setIcon(ImageResource.getIcon("about.png"));
		
		miDonate = new JMenuItem(LanguageResource.getStringValue("main.menuitem.donate"));
		miDonate.setIcon(ImageResource.getIcon("donate.png"));
		
		miIgoSyncDocsOnGC = new JMenuItem(LanguageResource.getStringValue("main.menuitem.visit_gc"));
		miIgoSyncDocsOnSF = new JMenuItem(LanguageResource.getStringValue("main.menuitem.visit_sf"));
		miOnelineHelp = new JMenuItem(LanguageResource.getStringValue("main.menuitem.ol_help"));
		miOnelineHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnHelp.add(miAboutiGoSyncDocs);
		mnHelp.addSeparator();
		mnHelp.add(miIgoSyncDocsOnGC);
		mnHelp.add(miIgoSyncDocsOnSF);
		mnHelp.add(miOnelineHelp);
		mnHelp.addSeparator();
		mnHelp.add(miDonate);
		
		pnlStatusbar = new JPanel();
		pnlStatusbar.setBorder(new EmptyBorder(0, 5, 0, 5));
		pnlStatusbar.setPreferredSize(new Dimension(800, 20));
		pnlStatusbar.setName("pnlStatusbar");
		pnlMain.add(pnlStatusbar, BorderLayout.SOUTH);
		pnlStatusbar.setLayout(new BorderLayout(5, 3));
		
		String strConnectedTo = LanguageResource.getStringValue("main.connected_to");
		lblConnectMessage = new JLabel(strConnectedTo.replace("{1}",SystemRuntime.Settings.UserName));
		lblConnectMessage.setName("lblConnectMessage");
		pnlStatusbar.add(lblConnectMessage, BorderLayout.WEST);
		
		pnlStatusbarRight = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) pnlStatusbarRight.getLayout();
		flowLayout_2.setVgap(3);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		pnlStatusbarRight.setName("pnlStatusbarRight");
		pnlStatusbar.add(pnlStatusbarRight, BorderLayout.EAST);
		
		progressBar = new JProgressBar();
		progressBar.setPreferredSize(new Dimension(150, 15));
		pnlStatusbarRight.add(progressBar);
		progressBar.setName("progressBar");
		
		lblProcessMessage = new JLabel("");
		lblProcessMessage.setBorder(new EmptyBorder(0, 15, 0, 0));
		pnlStatusbar.add(lblProcessMessage, BorderLayout.CENTER);
		lblProcessMessage.setPreferredSize(new Dimension(180, 15));
		lblProcessMessage.setName("lblProcessMessage");
		
		pnlToolbar = new JPanel();
		pnlToolbar.setPreferredSize(new Dimension(10, 50));
		pnlToolbar.setName("pnlToolbar");
		pnlMain.add(pnlToolbar, BorderLayout.NORTH);
		pnlToolbar.setLayout(new BorderLayout(0, 0));
		
		pnlButtons = new JPanel();
		FlowLayout fl_pnlButtons = (FlowLayout) pnlButtons.getLayout();
		fl_pnlButtons.setAlignment(FlowLayout.LEFT);
		pnlButtons.setName("pnlButtons");
		pnlToolbar.add(pnlButtons, BorderLayout.WEST);
		
		btnRefresh = new JButton("<html>"+LanguageResource.getStringValue("main.btn.refresh")+"</html>");
		pnlButtons.add(btnRefresh);
		btnRefresh.setPreferredSize(new Dimension(70, 40));
		btnRefresh.setName("btnRefresh");
		
		btnStar = new JButton("<html>"+LanguageResource.getStringValue("main.btn.star")+"</html>");
		btnStar.setPreferredSize(new Dimension(70, 40));
		btnStar.setName("btnStar");
		pnlButtons.add(btnStar);
		btnStar.addActionListener(new ShowConfirmDialogAction(this,"star",null));
		
		btnShare = new JButton("<html>"+LanguageResource.getStringValue("main.btn.share")+"</html>");
		btnShare.setPreferredSize(new Dimension(70, 40));
		btnShare.setName("btnShare");
		pnlButtons.add(btnShare);
		btnShare.addActionListener(new ShowConfirmDialogAction(this,"share",null));
		
		btnHide = new JButton("<html>"+LanguageResource.getStringValue("main.btn.hide")+"</html>");
		btnHide.setPreferredSize(new Dimension(70, 40));
		btnHide.setName("btnHide");
		pnlButtons.add(btnHide);
		btnHide.addActionListener(new ShowConfirmDialogAction(this,"hide",null));
		
		btnTrash = new JButton("<html>"+LanguageResource.getStringValue("main.btn.trash")+"</html>");
		btnTrash.setPreferredSize(new Dimension(70, 40));
		btnTrash.setName("btnTrash");
		pnlButtons.add(btnTrash);
		
		btnDelete = new JButton("<html>"+LanguageResource.getStringValue("main.btn.delete")+"</html>");
		btnDelete.setPreferredSize(new Dimension(70, 40));
		btnDelete.setName("btnDelete");
		pnlButtons.add(btnDelete);
		
		btnDownload = new JButton("<html>"+LanguageResource.getStringValue("main.btn.download")+"</html>");
		btnDownload.setPreferredSize(new Dimension(80, 40));
		btnDownload.setName("btnDownload");
		pnlButtons.add(btnDownload);
		
		btnUpload = new JButton("<html>"+LanguageResource.getStringValue("main.btn.upload")+"</html>");
		btnUpload.setPreferredSize(new Dimension(70, 40));
		btnUpload.setName("btnUpload");
		pnlButtons.add(btnUpload);
		
		pnlSearch = new JPanel();
		FlowLayout fl_pnlSearch = (FlowLayout) pnlSearch.getLayout();
		fl_pnlSearch.setAlignment(FlowLayout.RIGHT);
		pnlSearch.setName("pnlSearch");
		pnlToolbar.add(pnlSearch);
		
		txtSearch = new JTextField();
		txtSearch.setName("txtSearch");
		pnlSearch.add(txtSearch);
		txtSearch.setColumns(23);
		
		btnSearch = new JButton(LanguageResource.getStringValue("main.btn.search"));
		btnSearch.setName("btnSearch");
		btnSearch.setActionCommand("search");
		pnlSearch.add(btnSearch);
		
		pnlTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		pnlTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		pnlTabbedPane.setName("pnlTabbedPane");
		pnlMain.add(pnlTabbedPane, BorderLayout.CENTER);
		
		pnlAllItem = new AllItemPanel(this);
		pnlAllItem.setName("pnlAllItem");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_allitem"), new ImageIcon(MainFrame.class.getResource("/barrywey/igosyncdocs2011/resource/image/all.png")), pnlAllItem, null);
		
		pnlDocument = new DocumentPanel(this);
		pnlDocument.setName("pnlDocument");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_document"), new ImageIcon(MainFrame.class.getResource("/barrywey/igosyncdocs2011/resource/image/doc.png")), pnlDocument, null);
		
		pnlPresentation = new PresentationPanel(this);
		pnlPresentation.setName("pnlPresentation");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_presentation"), new ImageIcon(MainFrame.class.getResource("/barrywey/igosyncdocs2011/resource/image/presentation.png")), pnlPresentation, null);
		
		pnlSpreadsheet = new SpreadsheetPanel(this);
		pnlSpreadsheet.setName("pnlSpreadsheet");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_spreadsheet"), new ImageIcon(MainFrame.class.getResource("/barrywey/igosyncdocs2011/resource/image/spreadsheet.png")), pnlSpreadsheet, null);
		
		pnlOtherfiles = new OtherFilesPanel(this);
		pnlOtherfiles.setName("pnlOtherfiles");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_otherfiles"), new ImageIcon(MainFrame.class.getResource("/barrywey/igosyncdocs2011/resource/image/all.png")), pnlOtherfiles, null);
		
		pnlHidden = new HiddenObjectsPanel(this);
		pnlHidden.setName("pnlHidden");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_hidden"), new ImageIcon(MainFrame.class.getResource("/barrywey/igosyncdocs2011/resource/image/hidden.png")), pnlHidden, null);
		
		pnlStared = new StaredObjectsPanel(this);
		pnlStared.setName("pnlStared");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_stared"), new ImageIcon(MainFrame.class.getResource("/barrywey/igosyncdocs2011/resource/image/stared.png")), pnlStared, null);
		
		pnlTrashed = new TrashedObjectsPanel(this);
		pnlTrashed.setName("pnlTrashed");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_trashed"), new ImageIcon(MainFrame.class.getResource("/barrywey/igosyncdocs2011/resource/image/trashed.png")), pnlTrashed, null);
		
		pnlSharedWithMe = new SharedWithMePanel();
		pnlSharedWithMe.setName("pnlSharedWithMe");
		pnlTabbedPane.addTab(LanguageResource.getStringValue("main.tab_sharedwithme"), new ImageIcon(MainFrame.class.getResource("/barrywey/igosyncdocs2011/resource/image/doc.png")), pnlSharedWithMe, null);				
		
		//event handler
		addWindowListener(new SystemTrayAction(this));
		pnlTabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				SystemRuntime.SelectedItem.clear(); //clear document selection when new tab selected
				//clear table selection
				pnlAllItem.getDataTable().clearSelection();
				pnlAllItem.getDetailPanel().clearDetail();
				
				pnlDocument.getDataTable().clearSelection();
				pnlDocument.getDetailPanel().clearDetail();
				
				pnlPresentation.getDataTable().clearSelection();
				pnlPresentation.getDetailPanel().clearDetail();
				
				pnlSpreadsheet.getDataTable().clearSelection();
				pnlSpreadsheet.getDetailPanel().clearDetail();
				
				pnlOtherfiles.getDataTable().clearSelection();
				pnlOtherfiles.getDetailPanel().clearDetail();
				
				pnlHidden.getDataTable().clearSelection();
				pnlHidden.getDetailPanel().clearDetail();
				
				pnlStared.getDataTable().clearSelection();
				pnlStared.getDetailPanel().clearDetail();
				
				pnlTrashed.getDataTable().clearSelection();
				pnlTrashed.getDetailPanel().clearDetail();
				
				pnlSharedWithMe.getDataTable().clearSelection();
				pnlSharedWithMe.getDetailPanel().clearDetail();					
			}//end of stateChanged()
		});
		btnRefresh.addActionListener(new RefreshItemAction(this));
		btnTrash.addActionListener(new ShowConfirmDialogAction(this,"trash",null));
		btnUpload.addActionListener(new UploadFilesAction(this));
		btnDelete.addActionListener(new ShowConfirmDialogAction(this,"delete",null));
		btnDownload.addActionListener(new ShowConfirmDialogAction(this,"download",null));
		btnSearch.addActionListener(new SearchItemAction(this));
		
		miNewDocument.addActionListener(new CreateNewAction(this, "document"));
		miPresentation.addActionListener(new CreateNewAction(this,"presentation"));
		miSpreadsheet.addActionListener(new CreateNewAction(this, "spreadsheet"));
		miRefresh.addActionListener(new RefreshItemAction(this));
		miUpload.addActionListener(new UploadFilesAction(this));
		miExit.addActionListener(new AppExitAction());
		miEnglish.addActionListener(new ChangeLanguageAction("en_US"));
		miSimpleChinese.addActionListener(new ChangeLanguageAction("zh_CN"));
		miTraditionalChinese.addActionListener(new ChangeLanguageAction("zh_TW"));
		miJapanese.addActionListener(new ChangeLanguageAction("ja_JP"));
		
		miAboutiGoSyncDocs.addActionListener(new AboutDialogAction("about"));
		miDonate.addActionListener(new AboutDialogAction("donate"));
		miIgoSyncDocsOnGC.addActionListener(new AboutDialogAction("googlecode"));
		miIgoSyncDocsOnSF.addActionListener(new AboutDialogAction("sourceforge"));
		miOnelineHelp.addActionListener(new AboutDialogAction("bugs"));
		
		
	}
	
	public JButton getSearchButton() {
		return this.btnSearch;
	}
	
	public AllItemPanel getAllItemPanel() {
		return this.pnlAllItem;
	}
	
	public JTextField getSearchField() {
		return this.txtSearch;
	}
	
	public JProgressBar getProgressBar() {
		return this.progressBar;
	}
	
	public JLabel getProcessMessageLabel() {
		return this.lblProcessMessage;
	}
	
	public JTabbedPane getTabbedPane() {
		return this.pnlTabbedPane;
	}

	public void refreshAllTableData() {
		this.pnlAllItem.getDataTable().setModel(new EntryTableModel("all"));
		this.pnlAllItem.initTableSettings(this.pnlAllItem.getDataTable());
		
		this.pnlDocument.getDataTable().setModel(new EntryTableModel("document"));
		this.pnlDocument.initTableSettings(this.pnlDocument.getDataTable());
		
		this.pnlPresentation.getDataTable().setModel(new EntryTableModel("presentation"));
		this.pnlPresentation.initTableSettings(this.pnlPresentation.getDataTable());
		
		this.pnlSpreadsheet.getDataTable().setModel(new EntryTableModel("spreadsheet"));
		this.pnlSpreadsheet.initTableSettings(this.pnlSpreadsheet.getDataTable());
		
		this.pnlOtherfiles.getDataTable().setModel(new EntryTableModel("other"));
		this.pnlOtherfiles.initTableSettings(this.pnlOtherfiles.getDataTable());
		
		this.pnlHidden.getDataTable().setModel(new EntryTableModel("hidden"));
		this.pnlHidden.initTableSettings(this.pnlHidden.getDataTable());
		
		this.pnlStared.getDataTable().setModel(new EntryTableModel("stared"));
		this.pnlStared.initTableSettings(this.pnlStared.getDataTable());
		
		this.pnlTrashed.getDataTable().setModel(new EntryTableModel("trashed"));
		this.pnlTrashed.initTableSettings(this.pnlTrashed.getDataTable());	
		
		this.pnlSharedWithMe.getDataTable().setModel(new EntryTableModel("shared"));
		this.pnlSharedWithMe.initTableSettings(this.pnlSharedWithMe.getDataTable());			
	}//end of method

	private static final long serialVersionUID = -9147881941303957656L;
	private JMenuBar menuBar;
	private JMenu mnFile;
	//private JMenu mnSetting;
	//private JMenu mnSearch;
	private JMenu mnHelp;
	private JMenuItem miAboutiGoSyncDocs;
	private JMenuItem miIgoSyncDocsOnGC;
	private JMenuItem miIgoSyncDocsOnSF;
	private JMenuItem miOnelineHelp;
	private JMenuItem miDonate;
	private JPanel pnlStatusbar;
	private JMenuItem miNewDocument;
	private JMenuItem miSpreadsheet;
	private JMenuItem miPresentation;
	private JMenuItem miRefresh;
	private JMenuItem miUpload;
	private JMenuItem miExit;
	private JMenu mnLanguage;
	private JMenuItem miEnglish;
	private JMenuItem miSimpleChinese;
	private JMenuItem miTraditionalChinese;
	private JMenuItem miJapanese;
	private JPanel pnlMain;
	private JPanel pnlToolbar;
	private JButton btnRefresh;
	private JTabbedPane pnlTabbedPane;
	private AllItemPanel pnlAllItem;
	private DocumentPanel pnlDocument;
	private PresentationPanel pnlPresentation;
	private SpreadsheetPanel pnlSpreadsheet;
	private OtherFilesPanel pnlOtherfiles;
	private HiddenObjectsPanel pnlHidden;
	private StaredObjectsPanel pnlStared;
	private TrashedObjectsPanel pnlTrashed;
	private JLabel lblConnectMessage;
	private JProgressBar progressBar;
	private JPanel pnlButtons;
	private JPanel pnlSearch;
	private JTextField txtSearch;
	private JButton btnSearch;
	private SharedWithMePanel pnlSharedWithMe;
	private JPanel pnlStatusbarRight;
	private JLabel lblProcessMessage;
	private JButton btnDelete;
	private JButton btnUpload;
	private JButton btnStar;
	private JButton btnShare;
	private JButton btnDownload;
	private JButton btnHide;
	private JButton btnTrash;
}
