/**
 * BoardView.java
 */
package view;

import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * 
 * @author ddxbugs
 *
 */
public class BoardView extends JPanel {

	private Timer myTimer;
	
	public BoardView() {
		// TODO Auto-generated constructor stub
	}

	public BoardView(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public BoardView(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public BoardView(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	
	protected void endGame() {
		myTimer.stop();
	}
	
}
