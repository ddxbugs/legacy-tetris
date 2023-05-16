/**
 * BoardView.java
 */
package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.BoardModel;
import model.ColorPalette;
/**
 * 
 * @author ddxbugs
 *
 */
public class BoardView extends JPanel implements ActionListener {
	private static final int DEFAULT_DELAY_MS = 1000;
	private static final int DEFAULT_BOARD_MODEL_SCALE = 30;
	private static final int ONE_HUNDRETH_MS = 100;
	/** Magic number Zero */
	private static final int ZERO = 0;
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
//		myBoardModel.down();
	}
	
	public void paintComponent(final Graphics theGraphics) {
		super.paintComponent(theGraphics);
		final Graphics2D g = (Graphics2D) theGraphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		drawBlocks(g);
	}
	
	private void drawBlocks(final Graphics2D theGraphics) {
		int rectX, rectY, w, h;
		String row;
		char block;
		Rectangle2D r;
		
		rectX = 0;
		rectY = 0;
		w = DEFAULT_BOARD_MODEL_SCALE;
		h = DEFAULT_BOARD_MODEL_SCALE;
		
		String[] board = myBoardModel.toString().split("\n");
		
		for (int i = 5; i < board.length; i++) {
			row = board[i];
			for (int col = 0; col < row.length(); col++) {
				block = row.charAt(i);
				switch (block) {
				case 'I' : theGraphics.setColor(ColorPalette.ORANGE_TRON_LEGACY.getColor());;
				case 'J' : theGraphics.setColor(ColorPalette.CYAN_TRON_LEGACY.getColor());
				case 'L' : theGraphics.setColor(ColorPalette.CYAN_TRON_LEGACY.getColor());				case 'S' : break;
				case 'T' : theGraphics.setColor(ColorPalette.SWEET_YELLOW.getColor());
				case 'Z' : theGraphics.setColor(ColorPalette.ORANGE_TRON_LEGACY.getColor());;
				case 'O' : theGraphics.setColor(ColorPalette.PANE.getColor());
				default : theGraphics.setColor(ColorPalette.BASESTAR.getColor());
				}
				r = new Rectangle(rectX, rectY, w, h);
				theGraphics.fill(r);
				theGraphics.setColor(ColorPalette.PANE.getColor());
				theGraphics.draw(r);
				rectX += w;
			}
			rectX = ZERO;
			rectY += h;
		}
	}
	
}
