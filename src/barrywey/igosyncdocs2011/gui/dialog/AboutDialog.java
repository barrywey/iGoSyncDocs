/**
 * 
 * @(#)AboutDialog.java Apr 6, 2011
 * Copyright 2011 Barry Wey. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.dialog;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JPanel;

import barrywey.igosyncdocs2011.resource.ImageResource;
import barrywey.igosyncdocs2011.resource.LanguageResource;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 * 
 * 
 * 
 * @author Barry Wey
 * @version 1.0, Apr 6, 2011
 * @since JDK1.6
 */
public class AboutDialog extends JDialog {

	private static final long serialVersionUID = -1707501001667819812L;
	private final JPanel pnlMain = new JPanel();
	private final JButton btnOk = new JButton(LanguageResource.getStringValue("main.dialog.about.btn_ok"));
	private final JLabel lblLogo = new JLabel();
	private final JLabel lblAbout = new JLabel(LanguageResource.getStringValue("main.dialog.about.lbl_about"));

	public AboutDialog() {
		initComponets();
	}

	private void initComponets() {
		setContentPane(pnlMain);
		setIconImage(ImageResource.getImage("window-icon.png"));
		pnlMain.setLayout(null);
		btnOk.setBounds(337, 222, 93, 23);

		pnlMain.add(btnOk);
		lblLogo.setIcon(new ImageIcon(AboutDialog.class.getResource("/barrywey/igosyncdocs2011/resource/image/about-dialog-132-234.png")));
		lblLogo.setBackground(Color.MAGENTA);
		lblLogo.setBounds(10, 11, 132, 234);

		pnlMain.add(lblLogo);
		lblAbout.setVerticalAlignment(SwingConstants.TOP);
		lblAbout.setHorizontalTextPosition(SwingConstants.LEFT);
		lblAbout.setHorizontalAlignment(SwingConstants.LEFT);
		lblAbout.setBounds(152, 11, 276, 200);

		pnlMain.add(lblAbout);
		setSize(new Dimension(446, 285));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		setTitle(LanguageResource.getStringValue("main.dialog.about.title"));

		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeDialog();
			}
		});
	}

	private void closeDialog() {
		this.setVisible(false);
		this.dispose();
	}
}
