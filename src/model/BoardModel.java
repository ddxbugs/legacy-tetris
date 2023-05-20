/**
 *	BoardModel.java 
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @author ddxbugs
 *
 */
public class BoardModel extends Observable {
	private static final int CEILING_BUFFER = 4;
	private static final int DEFAULT_MODEL_WIDTH = 10;
	private static final int DEFAULT_MODEL_HEIGHT = 20;
	
	private List<Block[]> myFrozenBlocks;
	private List<TetrisPiece> myTetrisPieces;
	private TetrisPiece myNextPiece;
	private MovableTetrisPiece myCurrentPiece;
	
	
	/**
	 * 
	 */
	public BoardModel() {
		// TODO Auto-generated constructor stub
		myFrozenBlocks = new ArrayList<Block[]>();
		myTetrisPieces = new ArrayList<TetrisPiece>();
		myNextPiece = null;
		myCurrentPiece = null;
		
		reset();
		
	}
	
	public void reset() {
		myFrozenBlocks.clear();
		for (int h = 0; h < DEFAULT_MODEL_HEIGHT; h++) {
			myFrozenBlocks.add(new Block[DEFAULT_MODEL_WIDTH]);
		}
		
		myNextPiece = TetrisPiece.getRandomPiece();
		
		myCurrentPiece = new MovableTetrisPiece(myNextPiece,
				new Point((DEFAULT_MODEL_WIDTH - myNextPiece.getWidth()) / 2,
						DEFAULT_MODEL_HEIGHT - 1));

		// TODO fire action listener OR notify observers
		
	}
	public void rotate() {
		System.out.println("Rotate");
	}
	public void drop() {
		System.out.println("Drop");
	}
	public void down() {
		System.out.println("Down");
	}
	public void left() {
		System.out.println("Left");
	}
	public void right() {
		System.out.println("Right");
	}
	/**
	 * 
	 * @return Returns the current board state as a String
	 */
	public String toString() {
		int height, width;
		final StringBuilder sb;
		Block[] arr;
		
		height = DEFAULT_MODEL_HEIGHT;
		width = DEFAULT_MODEL_WIDTH;
		
		sb = new StringBuilder();
		
		// ceiling	
		sb.append(' ');
		for (int i = 0; i < width; i++)
			sb.append('-');
		sb.append(" \n");
		
		// board
		for (int row = height - 1; row >= 0 
				&& myFrozenBlocks.size() > 0 
				&& myCurrentPiece != null; row--) {
			
			arr = myFrozenBlocks.get(row);	// TODO FIX ME!!!
			sb.append('|'); // left wall
			for (final Block b : arr) {
				if (b == null)
					sb.append(' ');	// EMPTY
				else
					sb.append(b);	// IJLOSTZ
			}
			sb.append("|\n"); // right wall
		}
		
		// floor
		sb.append(' ');
		for (int w = 0; w < width; w++)
			sb.append('-');
		sb.append(' ');
		
		return sb.toString();
	}

}
