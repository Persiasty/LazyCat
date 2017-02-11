package pl.com.knopers.lazycat.swing.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class JNumberator extends JComponent
{
	@FunctionalInterface
	private static interface OnChange extends DocumentListener
	{
		@Override default void insertUpdate(DocumentEvent e){ changedUpdate(e); }
		@Override default void removeUpdate(DocumentEvent e){ changedUpdate(e); }
	}
	public static class ExtendedColors
	{
		public static Color SEMI_RED = Color.decode("#FF0060");
		public static Color SEMI_YELLOW = Color.decode("#DFFF00");
		public static Color SEMI_GREEN = Color.decode("#00FFA0");
		public static Color SEMI_BLUE = Color.decode("#006EFF");
	}
	
	private static final long serialVersionUID = -2162786123012669375L;
	private int width = 10;
	private JTextArea parent;
	private Map<Integer, Color> markedLines;
	private int margin = 5;
	
	public JNumberator(JTextArea parent)
	{
		this.parent = parent;
		this.markedLines = new HashMap<>();
		
		this.parent.getDocument().addDocumentListener((OnChange) e -> 
		{
			repaint();
			clearSize();
		});
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
		g.setColor(getForeground());
		g.setFont(getFont());
		
		int counter = 1, maxHeight = parent.getLineCount();
		int fontHeight = g.getFontMetrics().getHeight(), fontDescent = g.getFontMetrics().getDescent();
		int currPos = 0;
		
		do
		{
			if(markedLines.containsKey(counter))
			{
				g.setColor(markedLines.get(counter));
				g.fillRect(0, currPos + fontDescent, width, fontHeight);
				g.setColor(getForeground());
			}
			currPos = (fontHeight * counter) - fontDescent;
			g.drawString(String.valueOf(counter), margin, currPos);
		} 
		while(counter++ < maxHeight);
		
		int lastNumWidth = g.getFontMetrics().stringWidth(String.valueOf(--counter)) + 2 * margin;
		if(lastNumWidth != width)
		{
			width = lastNumWidth;
			clearSize();
		}
	}
	private void clearSize()
	{
		Dimension dim = new Dimension(width, parent.getHeight());
		setPreferredSize(dim);
		setSize(dim);
	}
	public void clearMarks()
	{
		markedLines.clear();
	}
	
	public void addMark(Integer line, Color c)
	{
		markedLines.put(line, c);
	}
	
	public void deleteMark(Integer line)
	{
		markedLines.remove(line);
	}
}
