/*
 * MovableTetrisPiece.java
 */

package model;

/**
 * Represents a TetrisPiece with a position and a rotation.
 * 
 * A MovableTetrisPiece is immutable.
 * 
 * @author acfowler
 * @version  1.0.0-alpha.1+001
 */
public final class MovableTetrisPiece {
    
    /**
     * The number of Points in a TetrisPiece.
     */
    private static final int BLOCKS = 4;
    
    /**
     * The TetrisPiece.
     */
    private final TetrisPiece myTetrisPiece;
    
    /**
     * The board position of this TetrisPiece.
     */
    private final Point myPosition;

    /**
     * The rotation value of this TetrisPiece.
     */
    private final Rotation myRotation;
    
    // This constructor allows creation of pieces in the zero rotational state
    /**
     * Constructs a MovablTetrisPiece using the specified type and position;
     * the initial rotation is set to the default zero (NONE) Rotation.
     * 
     * @param theTetrisPiece the type of TetrisPiece.
     * @param thePosition the position on the Board.
     */
    public MovableTetrisPiece(final TetrisPiece theTetrisPiece,
                              final Point thePosition) {
        
        this(theTetrisPiece, thePosition, Rotation.START);
    }

    // This constructor allows creation of pieces in any rotation
    /**
     * Constructs a MovablTetrisPiece using the specified type, position, and initial rotation.
     * 
     * @param theTetrisPiece the type of TetrisPiece.
     * @param thePosition the position on the Board.
     * @param theRotation the initial angle of the TetrisPiece.
     */
    public MovableTetrisPiece(final TetrisPiece theTetrisPiece,
                              final Point thePosition, 
                              final Rotation theRotation) {
        
        myTetrisPiece = theTetrisPiece;
        myPosition = thePosition;
        myRotation = theRotation;
    }
    
    
    
    
    
    
    /**
     * Return the width of the MovableTetrisPiece.
     * 
     * @return the width of the MovableTetrisPiece.
     */
    public int getWidth() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (final Point block : getLocalPoints()) {
            min = Math.min(min, block.getX());
            max = Math.max(max, block.getX());
        }
        return max - min + 1;
    }

    /**
     * Return the height of the MovableTetrisPiece.
     * 
     * @return the height of the MovableTetrisPiece.
     */
    public int getHeight() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (final Point block : getLocalPoints()) {
            min = Math.min(min, block.getY());
            max = Math.max(max, block.getY());
        }
        return max - min + 1;
    }
    
    /**
     * Return the Block type of the TetrisPiece.
     * 
     * @return The Block type of the TetrisPiece.
     */
    public Block getBlock() {
        return myTetrisPiece.getBlock();
    }
    
    
    // methods overridden from class Object
    
    @Override
    public String toString() {
        
        final StringBuilder sb = new StringBuilder();
        final String[][] blocks = new String[BLOCKS][BLOCKS];
        for (int h = 0; h < BLOCKS; h++) {
            for (int w = 0; w < BLOCKS; w++) {
                blocks[w][h] = " ";
            }
        }       
        for (final Point block : getLocalPoints()) {
            blocks[block.getY()][block.getX()] =
                myTetrisPiece.getBlock().toString();
        }

        for (int h = BLOCKS - 1; h >= 0; h--) {
            for (int w = 0; w < BLOCKS; w++) {
                if (blocks[h][w] != null) {
                    sb.append(blocks[h][w]);
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
    
    
    
    
    
    // protected getters - used by the Board class
    /**
     * Get the TetrisPiece type of this movable TetrisPiece.
     * 
     * @return The TetrisPiece describing this piece.
     */
    protected TetrisPiece getTetrisPiece() {
        return myTetrisPiece;
    }
    
    /**
     * The current board position of the TetrisPiece.
     * 
     * @return the board position.
     */
    protected Point getPosition() {
        return myPosition;
    }

    /**
     * Get the current rotation value of the movable TetrisPiece.
     * 
     * @return current rotation value.
     */
    protected Rotation getRotation() {
        return myRotation;
    }

    /**
     * Gets the TetrisPiece points rotated and translated to board coordinates.
     * 
     * @return the board points for the TetrisPiece blocks.
     */
    protected Point[] getBoardPoints() {
        return getPoints(myPosition);
    }
    
    
    
    // protected movement methods - used by the Board class
    /**
     * Rotates the TetrisPiece clockwise.
     * 
     * @return A new rotated movable TetrisPiece
     */
    protected MovableTetrisPiece rotate() {
        return new MovableTetrisPiece(myTetrisPiece,
                                      myPosition,
                                      myRotation.clockwise());
    }

    /**
     * Moves the TetrisPiece to the left on the game board.
     * 
     * @return A new left moved movable TetrisPiece
     */
    protected MovableTetrisPiece left() {
        return new MovableTetrisPiece(myTetrisPiece,
                                      myPosition.transform(-1, 0),
                                      myRotation);
    }

    /**
     * Moves the TetrisPiece to the right on the game board.
     * 
     * @return A new right moved movable TetrisPiece
     */
    protected MovableTetrisPiece right() {
        return new MovableTetrisPiece(myTetrisPiece,
                                      myPosition.transform(1, 0),
                                      myRotation);
    }

    /**
     * Moves the TetrisPiece down on the game board.
     * 
     * @return A new movable TetrisPiece moved down.
     */
    protected MovableTetrisPiece down() {
        return new MovableTetrisPiece(myTetrisPiece,
                                      myPosition.transform(0, -1),
                                      myRotation);
    }
    
    

    
    // This protected method is used by the Board class rotation methods
    // in order to support wall kicks during rotations
    /**
     * Returns a new MovableTetrisPiece of the current piece type and same Rotation
     * at the specified location.
     * 
     * @param thePosition the location for the returned MovableTetrisPiece
     * @return A new movable TetrisPiece at the specified location
     */
    protected MovableTetrisPiece setPosition(final Point thePosition) {
        return new MovableTetrisPiece(myTetrisPiece, thePosition, myRotation);
    }


    
    // private methods

    /**
     * Get the block points of the TetrisPiece transformed by x and y.
     * 
     * @param thePoint the point to transform the points around.
     * @return array of TetrisPiece block points.
     */
    private Point[] getPoints(final Point thePoint) {

        final Point[] blocks = myTetrisPiece.getPoints();
        
        for (int i = 0; i < blocks.length; i++) {
            final Point block = blocks[i];
            if (myTetrisPiece != TetrisPiece.O) {
                switch (myRotation) {
                    case QUARTER:
                        blocks[i] = new Point(block.getY(),
                                              myTetrisPiece.getWidth() - block.getX() - 1);
                        
                        break;
                    case HALF:
                        blocks[i] = new Point(myTetrisPiece.getWidth() - block.getX() - 1,
                                              myTetrisPiece.getWidth() - block.getY() - 1);
                        
                        break;
                    case THREEQUARTER:                 
                        blocks[i] = new Point(myTetrisPiece.getWidth() - block.getY() - 1,
                                              block.getX());
                        
                        
                        break;
                    default:
                }
            }
            if (thePoint != null) {
                blocks[i] = blocks[i].transform(thePoint);
            }
        }

        return blocks;
    }
    
    /**
     * Gets the local points of the TetrisPiece rotated.
     * 
     * @return array of TetrisPiece block points.
     */
    private Point[] getLocalPoints() {
        return getPoints(null);
    }


}
