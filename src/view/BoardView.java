/**
 * BoardView.java
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.BoardModel;
import model.ColorPalette;
/**
 * 
 * @author ddxbugs
 *
 */
public class BoardView extends JPanel implements ActionListener, ChangeListener {
	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;
	/** Default timer start delay */
	private static final int DEFAULT_DELAY_MS = 1000;
	/** Default board model scale */
	private static final int DEFAULT_BOARD_MODEL_SCALE = 10;
	/** Magic number 100 millisecond */
	private static final int ONE_HUNDRETH_MS = 100;
	/** Magic number Zero */
	private static final int ZERO = 0;
	/** Magic number Two */
	private static final int TWO = 2;
	/** The tetris board model */
	private BoardModel myBoardModel;
	/** The tetris action timer */
	private Timer myTimer;
	/**
	 * Default class constructor instantiates tetris board view
	 */
	public BoardView(/*final int theWidth, final int theHeight*/) {
		// TODO Auto-generated constructor stub
		super();
		myBoardModel = new BoardModel();
		myTimer = new Timer(DEFAULT_DELAY_MS, this);
		
		myBoardModel.setChangeListener(this);
		
		addKeyListener(new Player());
		
		setFocusable(true);
	}
	/**
	 * Manually set the width and height of the board to the scale
	 */
	public void setDimension(final int theWidth, final int theHeight, final int theScale) {
		setSize(theWidth * theScale, theHeight * theScale);
	}
	/**
	 * 
	 */
	protected void newGame() {
		myBoardModel.reset();
		myTimer.start();
	}
	/**
	 * 
	 */
	protected void endGame() {
		myTimer.stop();
	}
	/**
	 * 
	 * @param theLevel
	 */
	protected void setDelay(final int theLevel) {
		myTimer.setDelay(theLevel * ONE_HUNDRETH_MS);
	}
	/**
	 * Java Swing Timer listener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		myBoardModel.down();
		repaint();
	}
	/**
	 * BoardModel change listener
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		repaint();
	}
	/**
	 * 
	 */
	public void paintComponent(final Graphics theGraphics) {
		super.paintComponent(theGraphics);
		final Graphics2D g = (Graphics2D) theGraphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		drawBlocks(g);
	}
	/**
	 * 
	 * @param theGraphics
	 */
	private void drawBlocks(final Graphics2D theGraphics) {
		int rectX, rectY, width, height;
		String row;
		char block;
		Rectangle2D rect;
		
		rectX = 0;
		rectY = 0;
		
		width = getWidth() / DEFAULT_BOARD_MODEL_SCALE;
		height = getHeight() / DEFAULT_BOARD_MODEL_SCALE / TWO;
		
		String[] board = myBoardModel.toString().split("\n");
		for (int i = 0; i < board.length; i++) {
			row = board[i];
			
			for (int col = 0; col < row.length(); col++) {
				
				block = row.charAt(col);
				
				switch (block) {
				case 'I' : theGraphics.setColor(ColorPalette.ORANGE_TRON_LEGACY.getColor());;
				case 'J' : theGraphics.setColor(ColorPalette.CYAN_TRON_LEGACY.getColor());
				case 'L' : theGraphics.setColor(ColorPalette.CYAN_TRON_LEGACY.getColor());				
				case 'S' : theGraphics.setColor(ColorPalette.SWEET_YELLOW.getColor());
				case 'T' : theGraphics.setColor(ColorPalette.SWEET_YELLOW.getColor());
				case 'Z' : theGraphics.setColor(ColorPalette.ORANGE_TRON_LEGACY.getColor());;
				case 'O' : theGraphics.setColor(ColorPalette.PANE.getColor());
				case ' ' : theGraphics.setColor(ColorPalette.BASESTAR.getColor());
				case '-' : break;
				}
				
				rect = new Rectangle(rectX, rectY, width, height);
				theGraphics.fill(rect);
				theGraphics.setColor(ColorPalette.PANE.getColor());
				theGraphics.draw(rect);
				rectX += width;
				
			}
			
			rectX = ZERO;
			rectY += height;
		}
	}
	
	/**
	 * Player listener class registers key pressed events
	 * @author ddxbugs
	 *
	 */
	class Player implements KeyListener {
		private static final int UP = KeyEvent.VK_UP;
		private static final int DOWN = KeyEvent.VK_DOWN;
		private static final int LEFT = KeyEvent.VK_LEFT;
		private static final int RIGHT = KeyEvent.VK_RIGHT;
		private static final int SPACE = KeyEvent.VK_SPACE;
		private static final int ESC = KeyEvent.VK_ESCAPE;
		
		public void keyPressed(final KeyEvent theKeyEvent) {
			int key;
			
			key = theKeyEvent.getKeyCode();
			
			switch(key) {
			
			case UP : myBoardModel.rotate(); break;
			case DOWN : myBoardModel.down(); break;
			case LEFT : myBoardModel.left(); break;
			case RIGHT : myBoardModel.right(); break;
			case SPACE : myBoardModel.drop(); break;
			case ESC : myTimer.stop(); break;
			default : break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
}
