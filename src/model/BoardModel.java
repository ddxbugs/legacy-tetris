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
	private static final int DEFAULT_WIDTH = 10;
	private static final int DEFAULT_HEIGHT = 20;
	
	private List<Block[]> myFrozenBlocks;
	private List<TetrisPiece> myTetrisPieces;
	private TetrisPiece myNextPiece;
	private MovableTetrisPiece myCurrentPiece;
	
	
	/**
	 * 
	 */
	public BoardModel() {
		// TODO Auto-generated constructor stub'
		myFrozenBlocks = new ArrayList<Block[]>();
		myTetrisPieces = new ArrayList<TetrisPiece>();
		myNextPiece = null;
		myCurrentPiece = null;
		
	}
	public void down() {
	
	}
	
	/**
	 * 
	 * @return Returns the current board state as a String
	 */
	public String toString() {
		int height, width;
		final StringBuilder sb;
		Block[] arr;
		
		height = DEFAULT_HEIGHT;
		width = DEFAULT_WIDTH;
		
		sb = new StringBuilder();
		arr = new Block[width];
		
		// TODO new game; add initial piece to board
		
		// buffer
		for (int buffer = 0; buffer < CEILING_BUFFER; buffer++) {
			sb.append(new Block[width]);
		}
		
		// ceiling	
		sb.append(' ');
		for (int i = 0; i < width; i++)
			sb.append('-');
		sb.append('\n');
		
		// board
		for (int row = height - 1; row >= 0; row--) {
			arr = myFrozenBlocks.get(row);	
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
		sb.append('|');
		for (int w = 0; w < width; w++)
			sb.append('-');
		sb.append('|');
		
		return sb.toString();
	}

}
