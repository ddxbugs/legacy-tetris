/*
 * Block.java
 */

package model;

/**
 * The different types of blocks that can be stored in a Board's grid.
 * 
 * @author acfowler
 * @version  1.0.0-alpha.1+001
 */
public enum Block {
    
    /** AN empty space in the grid. */
    EMPTY,
    /** A Block from an IPiece. */
    I,
    /** A Block from a JPiece. */
    J,
    /** A Block from an LPiece. */
    L,
    /** A Block from an OPiece. */
    O,
    /** A Block from an SPiece. */
    S,
    /** A Block from a TPiece. */
    T,
    /** A Block from a ZPiece. */
    Z;
    
}
