/**
 * BoardView.java
 */
package view;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.BoardModel;
/**
 * 
 * @author ddxbugs
 *
 */
public class BoardView extends JPanel implements ActionListener {
	private static final int DEFAULT_DELAY_MS = 1000;
	private BoardModel myBoardModel;
	private Timer myTimer;
	
	public BoardView() {
		// TODO Auto-generated constructor stub
		myBoardModel = new BoardModel();
		myTimer = new Timer(DEFAULT_DELAY_MS, this);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		myBoardModel.down();
	}
	
}
