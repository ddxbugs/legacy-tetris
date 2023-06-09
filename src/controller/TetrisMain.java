/**
 * TetrisMain.java
 */
package controller;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.net.URL;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import view.TetrisGui;

/**
 * @author ddxbugs
 *
 */
public class TetrisMain {
	private static final String TITLE = "Disney's Tron: Legacy Tetris";
	private static final String ICON = "/res/icon.jpg";
	/** The default gui frame width */
	private static final int DEFAULT_GUI_WIDTH = 1000;
	/** The default gui frame height */
	private static final int DEFAULT_GUI_HEIGHT = 600;
	
	private static GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	/**
	 * Private constructor inhibits instantiation
	 */
	private TetrisMain() {
		System.err.println("error:"+IllegalStateException.class);
		throw new IllegalStateException();
	}
	/**
	 * Tetris Main 
	 * @param args the Arguments
	 */
	public static void main(String[] args) {
		
		GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				final URL url = getClass().getResource(ICON);
				final Image icon = new ImageIcon(url).getImage();
				
				final TetrisGui gui = new TetrisGui(TITLE);
				gui.setIconImage(icon);
				gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gui.setResizable(false);
				gui.pack();
				gui.setLocationRelativeTo(null);
				gui.setVisible(true);
				
				if (graphicsDevice.isFullScreenSupported()) {
					graphicsDevice.setFullScreenWindow(gui);
					gui.validate();
				} else {
					gui.setSize(new Dimension(DEFAULT_GUI_WIDTH, DEFAULT_GUI_HEIGHT));
				}
			}
		});

	}

}
