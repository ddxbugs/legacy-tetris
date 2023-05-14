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
	private static final int ONE_HUNDRETH_MS = 100;
	private BoardModel myBoardModel;
	private Timer myTimer;
	private int myLevel;
	
	
	public BoardView() {
		// TODO Auto-generated constructor stub
		super();
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
	
	protected void newGame() {
		myTimer.start();
	}
	protected void endGame() {
		myLevel = 0;
		myTimer.stop();
	}
	protected void setDelay(final int theLevel) {
		myTimer.setDelay(theLevel * ONE_HUNDRETH_MS);
	}
	protected void setLevel(final int theLevel) {
		myLevel = theLevel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		myBoardModel.down();
	}
	
}
