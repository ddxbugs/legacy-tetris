/**
 *	BoardModel.java 
 */
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @author ddxbugs
 *
 */
public class BoardModel implements ActionListener {
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
	
	/**
	 * 
	 */
	public BoardModel() {
		// TODO Auto-generated constructor stub
		myFrozenBlocks = null;
		myTetrisPieces = null;
		myNextPiece = null;
		myCurrentPiece = null;
		
		reset();	
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
//		System.out.println("Rotate");
		if (myCurrentPiece != null) {
			final MovableTetrisPiece cwPiece = myCurrentPiece.rotate();
			final Point[] offset = WallKick.getWallKicks(cwPiece.getTetrisPiece(), 
					myCurrentPiece.getRotation(), 
					cwPiece.getRotation());
			for (final Point p : offset) {
				final Point offsetPoint = cwPiece.getPosition().transform(p);
				final MovableTetrisPiece temp = cwPiece.setPosition(offsetPoint);
				if (move(temp)) break;
			}
		} 
	}
	/**
	 * 
	 */
	public void drop() {
//		System.out.println("Drop");
		boolean drop;
		drop = false;
		while (drop) {
			drop = move(myCurrentPiece.down());
		}
	}
	/**
	 * 
	 */
	public void down() {
//		System.out.println("Down");
		if (myCurrentPiece != null)
			move(myCurrentPiece.down());
	}
	/**
	 * 
	 */
	public void left() {
//		System.out.println("Left");
		if (myCurrentPiece != null)
			move(myCurrentPiece.left());
	}
	/**
	 * 
	 */
	public void right() {
//		System.out.println("Right");
		if (myCurrentPiece != null)
			move(myCurrentPiece.right());
	}
	/**
	 * 
	 * @param theCurrentPiece
	 * @return
	 */
	private boolean move(final MovableTetrisPiece theCurrentPiece) {
		boolean success;
		success = false;
		if (isMovable()) {
			myCurrentPiece = theCurrentPiece;
			success = true;
//			setChanged(); // TODO update observer
		}
		return success;
	}
	private boolean isMovable() {
		boolean isMovable = true;
		for (final Point p : myCurrentPiece.getBoardPoints()) {
			if (p.getX() < 0 || p.getX() >= DEFAULT_BOARD_MODEL_WIDTH) {
				isMovable = false;
			}
			if (p.getY() < 0) {
				isMovable = false;
			}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
