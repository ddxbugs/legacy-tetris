/**
 * BoardView.java
 */
package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
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
	private static final int DEFAULT_BOARD_MODEL_SCALE = 30;
	private static final int ONE_HUNDRETH_MS = 100;
	private BoardModel myBoardModel;
	private Timer myTimer;
	
	public BoardView() {
		// TODO Auto-generated constructor stub
		super();
		myBoardModel = new BoardModel();
		myTimer = new Timer(DEFAULT_DELAY_MS, this);
	}
	
	public void setDimension(final int theWidth, final int theHeight, final int theScale) {
		setSize(theWidth * theScale, theHeight * theScale);
	}
	protected void newGame() {
		myTimer.start();
//		myBoardModel.reset();
	}
	protected void endGame() {
		myTimer.stop();
	}
	protected void setDelay(final int theLevel) {
		myTimer.setDelay(theLevel * ONE_HUNDRETH_MS);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		myBoardModel.down();
	}
	
	public void paintComponent(final Graphics theGraphics) {
		super.paintComponent(theGraphics);
		final Graphics2D g = (Graphics2D) theGraphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		drawBlocks(g);
	}
	
	private void drawBlocks(final Graphics2D theGraphics) {
		int rectX, rectY;
		
	}
	
}
