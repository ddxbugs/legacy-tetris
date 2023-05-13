/**
 * TetrisMain.java
 */
package controller;

import java.awt.EventQueue;

import view.TetrisGui;

/**
 * @author ddxbugs
 *
 */
public class TetrisMain {
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
				final TetrisGui gui = new TetrisGui();
				gui.start();
			}
		});

	}

}
