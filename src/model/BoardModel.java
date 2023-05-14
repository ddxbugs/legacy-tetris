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

}
