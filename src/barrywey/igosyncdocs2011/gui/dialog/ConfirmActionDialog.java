/**
 * 
 * @(#)ConfirmActionDialog.java Feb 11, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;

import barrywey.igosyncdocs2011.action.DeleteItemAction;
import barrywey.igosyncdocs2011.action.DownloadFilesAction;
import barrywey.igosyncdocs2011.action.HideItemAction;
import barrywey.igosyncdocs2011.action.StarItemAction;
import barrywey.igosyncdocs2011.action.TrashItemAction;
import barrywey.igosyncdocs2011.action.UnhideItemAction;
import barrywey.igosyncdocs2011.action.UnstarItemAction;
import barrywey.igosyncdocs2011.action.UntrashItemAction;
import barrywey.igosyncdocs2011.gui.MainFrame;
import barrywey.igosyncdocs2011.gui.model.ConfirmListModel;
import barrywey.igosyncdocs2011.gui.renderer.ConfirmListRenderer;
import barrywey.igosyncdocs2011.resource.ImageResource;
import barrywey.igosyncdocs2011.resource.LanguageResource;
import javax.swing.border.TitledBorder;

/**
 * 
 * 
 * 
 * @author Barry Wey
 * @version 1.0, Feb 11, 2011
 * @since JDK1.6
 */
public class ConfirmActionDialog extends JDialog {

	private static final long serialVersionUID = -1452168472121468458L;
	private final JPanel pnlMain = new JPanel();
	private String confirmMessage;
	private String actionType;
	private MainFrame frMain;
	private String format;
	private final JLabel lblLogo = new JLabel("");
	private final JLabel lblMessage = new JLabel("");
	private final JScrollPane pnlScroll = new JScrollPane();
	private JList listItems ;
	private final JButton btnYes = new JButton(LanguageResource.getStringValue("main.dialog.confirm_dialog.btn_yes"));
	private final JButton btnNo = new JButton(LanguageResource.getStringValue("main.dialog.confirm_dialog.btn_no"));
	private final JPanel pnlRight = new JPanel();

	public ConfirmActionDialog(String confimMessage,String actionType, MainFrame frMain, String format) {
		this.confirmMessage = confimMessage;
		this.actionType = actionType;
		this.frMain = frMain;
		this.format = format;
		initComponents();
	}

	private void initComponents() {
		setIconImage(ImageResource.getImage("window-icon.png"));
		pnlMain.setLayout(null);
		lblLogo.setIcon(new ImageIcon(ConfirmActionDialog.class.getResource("/barrywey/igosyncdocs2011/resource/image/prompt-logo-130-150.png")));
		lblLogo.setBounds(6, 6, 119, 218);
		
		pnlMain.add(lblLogo);
		btnYes.setBounds(362, 202, 84, 22);
		
		pnlMain.add(btnYes);
		btnNo.setBounds(266, 202, 84, 22);
		
		pnlMain.add(btnNo);
		
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yesButtonPressed();
			}
		});
		
		setTitle(LanguageResource.getStringValue("app.title"));
		setContentPane(pnlMain);
		setSize(new Dimension(464, 259));
		setModal(true);
		setResizable(false);		
		pnlRight.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlRight.setBounds(137, 6, 309, 184);
		
		pnlMain.add(pnlRight);
		pnlRight.setLayout(null);
		pnlScroll.setBounds(6, 36, 297, 142);
		pnlRight.add(pnlScroll);
		listItems = new JList(new ConfirmListModel());
		pnlScroll.setViewportView(listItems);
		listItems.setCellRenderer(new ConfirmListRenderer()); // cell renderer
		lblMessage.setBounds(6, 0, 297, 32);
		pnlRight.add(lblMessage);
		lblMessage.setText(this.confirmMessage);
	}
	
	private void cancel() {
		this.dispose();
	}
	
	private void yesButtonPressed() {
		if(actionType.trim().equals("trash")) {
			new Thread(new TrashItemAction(this, frMain)).start();
		} else if(actionType.trim().equals("hide")) {
			new Thread(new HideItemAction(this,frMain)).start();
		} else if(actionType.trim().equals("star")) {
			new Thread(new StarItemAction(this,frMain)).start();
		} else if(actionType.trim().equals("delete")) {
			new Thread(new DeleteItemAction(this, frMain)).start();
		} else if(actionType.trim().equals("download")) {
			JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.home")));
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int result = fileChooser.showDialog(null, LanguageResource.getStringValue("dialog.download.lbl_download_dialog"));
			if(result == JFileChooser.APPROVE_OPTION) {
				new Thread(new DownloadFilesAction(this, frMain,fileChooser.getSelectedFile().getPath(),format)).start();
			}
		} else if(actionType.trim().equals("unhide")) {
			new Thread(new UnhideItemAction(this,frMain)).start();
		} else if(actionType.trim().equals("unstar")) {
			new Thread(new UnstarItemAction(this,frMain)).start();
		} else if(actionType.trim().equals("untrash")) {
			new Thread(new UntrashItemAction(this, frMain)).start();
		}//end of if
	}//end of method
}
