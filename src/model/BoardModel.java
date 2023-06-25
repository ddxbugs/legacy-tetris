/**
 *	BoardModel.java 
 */
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author ddxbugs
 *
 */
public class BoardModel {
	private static final int CEILING_BUFFER = 4;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int DEFAULT_BOARD_MODEL_WIDTH = 10;
	private static final int DEFAULT_BOARD_MODEL_HEIGHT = 20;
	private static final Point DEFAULT_START_POINT = new Point((DEFAULT_BOARD_MODEL_WIDTH / TWO),
			DEFAULT_BOARD_MODEL_HEIGHT - CEILING_BUFFER - ONE);
	private List<Block[]> myFrozenBlocks;
	private List<TetrisPiece> myTetrisPieces;
	private TetrisPiece myNextPiece;
	private MovableTetrisPiece myCurrentPiece;
	private ChangeListener myChangeListener;
	
	/**
	 * 
	 */
	public BoardModel() {
		// TODO Auto-generated constructor stub
		myFrozenBlocks = null;
		myTetrisPieces = null;
		myNextPiece = null;
		myCurrentPiece = null;
		myChangeListener = null;
	}
	
	public void setChangeListener(final ChangeListener theChangeListener) {
		myChangeListener = theChangeListener;
	}
	
	public void reset() {
		myFrozenBlocks = new ArrayList<Block[]>();
		myTetrisPieces = new ArrayList<TetrisPiece>();
		
		for (int h = 0; h < DEFAULT_BOARD_MODEL_HEIGHT; h++) {
			myFrozenBlocks.add(new Block[DEFAULT_BOARD_MODEL_WIDTH]);
		}
		
		myNextPiece = TetrisPiece.getRandomPiece();
		
		myCurrentPiece = new MovableTetrisPiece(TetrisPiece.getRandomPiece(),
				DEFAULT_START_POINT);
		
		myTetrisPieces.add(myNextPiece);		
	}
	/**
	 * 
	 */
	public void rotate() {
//		if (myCurrentPiece != null) {
//			final MovableTetrisPiece cwPiece = myCurrentPiece.rotate();
//			final Point[] offset = WallKick.getWallKicks(cwPiece.getTetrisPiece(), 
//					myCurrentPiece.getRotation(), 
//					cwPiece.getRotation());
//			for (final Point p : offset) {
//				final Point offsetPoint = cwPiece.getPosition().transform(p);
//				final MovableTetrisPiece temp = cwPiece.setPosition(offsetPoint);
//				if (move(temp)) break;
//			}
//		} 
	}
	/**
	 * 
	 */
	public void drop() {
		while (myCurrentPiece != null && isMovable(myCurrentPiece.down())) {
			move(myCurrentPiece.down());
		}
	}
	/**
	 * 
	 */
	public void down() {
		if (myCurrentPiece != null && isMovable(myCurrentPiece.down()))
			move(myCurrentPiece.down());
	}
	/**
	 * 
	 */
	public void left() {
		if (myCurrentPiece != null && isMovable(myCurrentPiece.left()))
			move(myCurrentPiece.left());
	}
	/**
	 * 
	 */
	public void right() {
		if (myCurrentPiece != null && isMovable(myCurrentPiece.right()))
			move(myCurrentPiece.right());
	}
	/**
	 * 
	 * @param theCurrentPiece
	 * @return
	 */
	private void move(final MovableTetrisPiece theMovedPiece) {
		myCurrentPiece = theMovedPiece;
		myChangeListener.stateChanged(new ChangeEvent(myCurrentPiece));	
	}
	/**
	 * Helper function
	 * @param theMovedPiece
	 * @return
	 */
	private boolean isMovable(final MovableTetrisPiece theMovedPiece) {
		boolean isMovable = true;
		for (final Point p : theMovedPiece.getBoardPoints()) {
			if (p.getX() < 0 || p.getX() >= DEFAULT_BOARD_MODEL_WIDTH || p.getY() < 0) {
				isMovable = false;
			}
			if (!isMovable) break;	// exit loop false case
		}
		return isMovable;
	}
	/**
	 * 
	 * @return Returns the current board state as a String
	 */
	public String toString() {
		int height, width;
		final StringBuilder sb;
		Block[] arr;
		
		height = DEFAULT_BOARD_MODEL_HEIGHT;
		width = DEFAULT_BOARD_MODEL_WIDTH;
		
		sb = new StringBuilder();
		
		// ceiling	
//		sb.append(' ');
		for (int i = 0; i < width; i++)
			sb.append('-');
		sb.append("\n");
		
		// board
		for (int row = height - 1; row >= 0 
				&& myFrozenBlocks != null
				&& myFrozenBlocks.size() > 0 
				&& myCurrentPiece != null; row--) {
			
			arr = myFrozenBlocks.get(row);	// TODO FIX ME!!!
//			sb.append('|'); // left wall
			for (final Block b : arr) {
				if (b == null)
					sb.append(' ');	// EMPTY
				else
					sb.append(b);	// IJLOSTZ
			}
//			sb.append("|\n"); // right wall
			sb.append("\n");
		}
		
		// floor
//		sb.append(' ');
		for (int w = 0; w < width; w++)
			sb.append('-');
//		sb.append(' ');
		
		return sb.toString();
	}

}
