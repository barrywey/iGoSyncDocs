/**
 * @(#)FaceUtils.java Oct 14, 2010
 * Copyright 2010 BarryWei. All rights reserved.
 */
package barrywey.igosyncdocs2011.gui.util;

import java.awt.Component;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import barrywey.igosyncdocs2011.resource.LanguageResource;

/**
 * 
 * 
 * 
 * @author BarryWei
 * @version 1.0, Oct 14, 2010
 * @since JDK1.6
 */
public class FaceUtils {
	
	public static void expandTree(JTree tree) {
		if (tree != null) {
			TreeNode root = (TreeNode) tree.getModel().getRoot();
			expandAllNode(tree, new TreePath(root));
		}
	}// end of method

	private static void expandAllNode(JTree tree, TreePath parent) {
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration<?> e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandAllNode(tree, path);
			}
		}// end of if
		tree.expandPath(parent);
	}// end of method
	
	public static void showErrorMessage(Component parent,String message) {
		JOptionPane.showMessageDialog(parent, message,LanguageResource.getStringValue("app.title"),JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showWarningMessage(Component parent,String message) {
		JOptionPane.showMessageDialog(parent, message,LanguageResource.getStringValue("app.title"),JOptionPane.WARNING_MESSAGE);
	}
	
	public static void showInformationMessage(Component parent,String message) {
		JOptionPane.showMessageDialog(parent, message,LanguageResource.getStringValue("app.title"),JOptionPane.INFORMATION_MESSAGE);	
	}
	
	public static int showConfirmMessage(Component parent,String message) {
		return JOptionPane.showConfirmDialog(parent, message,LanguageResource.getStringValue("app.title"),JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
	}
	
	public static String showInputDialog(Component parent,String message) {
		return JOptionPane.showInputDialog(null,message,LanguageResource.getStringValue("app.title"),JOptionPane.QUESTION_MESSAGE);
	}
}
