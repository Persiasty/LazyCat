package pl.com.knopers.lazycat.swing.elements;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class HintTextField extends JTextField implements FocusListener
{
	private static final long serialVersionUID = -57466261437930418L;
	private String hint;
	private boolean showingHint;

	public HintTextField(String hint)
	{
		super(hint);
		this.hint = hint;
		this.showingHint = true;
		super.addFocusListener(this);
	}
	
	public String getHint()
	{
		return hint;
	}

	public void setHint(String hint)
	{
		this.hint = hint;
		if(showingHint)
			super.setText(hint);
	}

	@Override
	public void focusGained(FocusEvent e)
	{
		if(this.getText().isEmpty())
		{
			showingHint = false;
			super.setText("");
		}
		super.setForeground(Color.BLACK);
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		if(this.getText().isEmpty())
		{
			showingHint = true;
			super.setText(hint);
			super.setForeground(Color.LIGHT_GRAY);
		}
		else
			super.setForeground(Color.BLACK);
	}

	@Override
	public String getText()
	{
		return showingHint ? "" : super.getText();
	}
	
	@Override
	public void setText(String t)
	{
		if(this.getText().isEmpty())
		{
			showingHint = true;
			super.setText(hint);
			super.setForeground(Color.LIGHT_GRAY);
		}
		else
		{
			showingHint = false;
			super.setText(t);
			super.setForeground(Color.BLACK);
		}
	}
}