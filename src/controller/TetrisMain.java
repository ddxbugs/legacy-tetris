/**
 * TetrisMain.java
 */
package controller;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.net.URL;

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
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 700;
	/**
	 * Private constructor inhibits instantiation
	 */
	private TetrisMain() {
		throw new IllegalStateException();
	}
	/**
	 * Tetris Main 
	 * @param args the Arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				final URL url = getClass().getResource(ICON);
				final Image icon = new ImageIcon(url).getImage();
				
				final TetrisGui gui = new TetrisGui(TITLE);
				gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gui.setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
				gui.setResizable(false);
				gui.pack();
				gui.setLocationRelativeTo(null);
				gui.setVisible(true);
			}
		});

	}

}
