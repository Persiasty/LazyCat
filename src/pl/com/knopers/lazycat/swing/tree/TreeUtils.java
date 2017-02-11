package pl.com.knopers.lazycat.swing.tree;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class TreeUtils
{
	public static void expandFullTree(JTree tree)
	{
		for (int i = 0; i < tree.getRowCount(); i++)
			tree.expandRow(i);
	}

	public static void expandTreeLevel(JTree tree, int deep)
	{
		DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)tree.getModel().getRoot();
		while((currentNode = currentNode.getNextNode()) != null)
		{
			if(currentNode.getLevel() <= deep)
				tree.expandPath(new TreePath(currentNode.getPath()));
		}
	}
}
