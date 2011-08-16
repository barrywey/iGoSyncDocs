/**
 * 
 * @(#)ShareItemDialog.java Feb 17, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.dialog;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;

import barrywey.igosyncdocs2011.action.ShareItemAction;
import barrywey.igosyncdocs2011.gui.MainFrame;
import barrywey.igosyncdocs2011.gui.model.ConfirmListModel;
import barrywey.igosyncdocs2011.gui.renderer.ConfirmListRenderer;
import barrywey.igosyncdocs2011.gui.util.FaceUtils;
import barrywey.igosyncdocs2011.resource.ImageResource;
import barrywey.igosyncdocs2011.resource.LanguageResource;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

/**
 * 
 * 
 * 
 * @author Barry Wey
 * @version 1.0, Feb 17, 2011
 * @since JDK1.6
 */
public class ShareItemDialog extends JDialog {

	private static final long serialVersionUID = 4541385579562957553L;
	private MainFrame frMain;
	private final JPanel pnlMain = new JPanel();
	private final JLabel lblLogo = new JLabel("logo");
	private final JPanel pnlShareTo = new JPanel();
	private final JButton btnYes = new JButton(LanguageResource.getStringValue("main.dialog.share.btnShare"));
	private final JButton btnCancel = new JButton(LanguageResource.getStringValue("main.dialog.share.btnCancel"));
	private final JTextField txtInput = new JTextField();
	private final JPanel pnlShare = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JList listSharedItem = new JList(new ConfirmListModel());
	private final JPanel panel = new JPanel();
	private final JRadioButton rboReader = new JRadioButton(LanguageResource.getStringValue("main.dialog.share.rboReader"));
	private final JRadioButton rboWriter = new JRadioButton(LanguageResource.getStringValue("main.dialog.share.rboWriter"));
	private final ButtonGroup btnGroup = new ButtonGroup();

	public ShareItemDialog(MainFrame frMain) {
		this.frMain = frMain;
		initComponents();
	}

	private void initComponents() {
		setIconImage(ImageResource.getImage("window-icon.png"));
		
		txtInput.setBounds(6, 21, 340, 20);
		txtInput.setColumns(10);		
		pnlMain.setLayout(null);
		lblLogo.setIcon(new ImageIcon(ShareItemDialog.class.getResource("/barrywey/igosyncdocs2011/resource/image/share-logo-120-225.png")));
		lblLogo.setBounds(6, 6, 120, 322);
		
		pnlMain.add(lblLogo);
		pnlShareTo.setBorder(new TitledBorder(null, LanguageResource.getStringValue("main.dialog.share.titleShareWith"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlShareTo.setBounds(138, 153, 352, 56);
		
		pnlMain.add(pnlShareTo);
		pnlShareTo.setLayout(null);
		
		pnlShareTo.add(txtInput);
		btnYes.setBounds(406, 306, 84, 22);
		
		pnlMain.add(btnYes);
		btnCancel.setBounds(310, 306, 84, 22);
		
		pnlMain.add(btnCancel);
		pnlShare.setBorder(new TitledBorder(null, LanguageResource.getStringValue("main.dialog.share.titleShareItems"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlShare.setBounds(138, 6, 352, 135);
		
		pnlMain.add(pnlShare);
		pnlShare.setLayout(new BorderLayout(0, 0));
		
		pnlShare.add(scrollPane, BorderLayout.CENTER);
		listSharedItem.setCellRenderer(new ConfirmListRenderer()); // cell renderer
		scrollPane.setViewportView(listSharedItem);
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		btnYes.addActionListener(new ShareItemAction(this,frMain));
		panel.setBorder(new TitledBorder(null, LanguageResource.getStringValue("main.dialog.share.titlePermission"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(138, 221, 352, 62);
		
		
		pnlMain.add(panel);
		panel.setLayout(null);
		rboReader.setBounds(32, 22, 109, 22);
		
		panel.add(rboReader);
		rboWriter.setBounds(209, 22, 109, 22);
		
		panel.add(rboWriter);
		btnGroup.add(rboReader);
		btnGroup.add(rboWriter);
		rboReader.setSelected(true);
		
		setContentPane(pnlMain);
		setModal(true);
		setResizable(false);
		setSize(new Dimension(504, 363));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle(LanguageResource.getStringValue("main.dialog.share.title"));
		
	}
	
	public boolean validateUserInput() {
		if(txtInput.getText().trim().equals("")) {
			FaceUtils.showErrorMessage(null, LanguageResource.getStringValue("main.dialog.share.message_input_email"));
			return false;
		}
		return true;
	}
	
	public String getEnterdEmails() {
		return txtInput.getText().trim();
	}
	
	public boolean isWriter() {
		return rboReader.isSelected() ? false : true;
	}
	
	private void cancel() {
		this.dispose();
	}
}
