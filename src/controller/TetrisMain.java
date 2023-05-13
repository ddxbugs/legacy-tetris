/**
 * TetrisMain.java
 */
package controller;

import java.awt.Dimension;
import java.awt.EventQueue;

import view.TetrisGui;

/**
 * @author ddxbugs
 *
 */
public class TetrisMain {
	private static final String TITLE = "Disney's Tron: Legacy Tetris";
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 700;
	/**
	 * 
	 */
	private TetrisMain() {
		throw new IllegalStateException();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				final TetrisGui gui = new TetrisGui(TITLE);
				gui.setDefaultCloseOperation(0);
				gui.setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
				gui.setResizable(false);
				gui.pack();
			}
		});

	}

}
