package pl.com.knopers.lazycat.swing.interfaces;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

@FunctionalInterface
public interface OnWidowClosed extends WindowListener
{
	@Override
	default void windowActivated(WindowEvent e){}
	@Override
	default void windowClosing(WindowEvent e){}
	@Override
	default void windowDeactivated(WindowEvent e){}
	@Override
	default void windowDeiconified(WindowEvent e){}
	@Override
	default void windowIconified(WindowEvent e){}
	@Override
	default void windowOpened(WindowEvent e){}
}
