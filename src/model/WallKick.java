/*
 * Wallkick.java
 */

package model;

/**
 * An implementation of the classic game "Tetris".
 * Enumeration of Rotation types.
 * 
 * @author acfowler
 * @version 1.0.0-alpha.1+001
 */
public final class WallKick {
    
    /**
     * The kick offsets for rotations of J, L, S, T, and Z pieces. 
     */
    private static final Point[][] JLSTZ_OFFSETS = {
        {new Point(0, 0), new Point(-1, 0), new Point(-1, +1),
            new Point(0, -2), new Point(-1, -2)},  // NONE to QUARTER
        {new Point(0, 0), new Point(+1, 0), new Point(+1, -1),
            new Point(0, +2), new Point(+1, +2) }, // QUARTER to NONE
        {new Point(0, 0), new Point(+1, 0), new Point(+1, -1),
            new Point(0, +2), new Point(+1, +2) }, // QUARTER to HALF
        {new Point(0, 0), new Point(-1, 0), new Point(-1, +1),
            new Point(0, -2), new Point(-1, -2) }, // HALF to QUARTER
        {new Point(0, 0), new Point(+1, 0), new Point(+1, +1),
            new Point(0, -2), new Point(+1, -2) }, // HALF to THREEQUARTER
        {new Point(0, 0), new Point(-1, 0), new Point(-1, -1),
            new Point(0, +2), new Point(-1, +2) }, // THREEQUARTER to HALF
        {new Point(0, 0), new Point(-1, 0), new Point(-1, -1),
            new Point(0, +2), new Point(-1, +2) }, // THREEQUARTER to NONE
        {new Point(0, 0), new Point(+1, 0), new Point(+1, +1),
            new Point(0, -2), new Point(+1, -2) }  // NONE to THREEQUARTER
    };
    
    /**
     * The kick offsets for rotations of I pieces.
     */
    private static final Point[][] I_OFFSETS = {
        {new Point(0, 0), new Point(-2, 0), new Point(+1, 0),
            new Point(-2, -1), new Point(+1, +2)},  // NONE to QUARTER
        {new Point(0, 0), new Point(+2, 0), new Point(-1, 0),
            new Point(+2, +1), new Point(-1, -2) }, // QUARTER to NONE    
        {new Point(0, 0), new Point(-1, 0), new Point(+2, 0),
            new Point(-1, +2), new Point(+2, -1) }, // QUARTER to HALF
        {new Point(0, 0), new Point(+1, 0), new Point(-2, 0),
            new Point(+1, -2) , new Point(-2, +1) }, // HALF to QUARTER
        {new Point(0, 0), new Point(+2, 0), new Point(-1, 0),
            new Point(+2, +1), new Point(-1, -2) }, // HALF to THREEQUARTER
        {new Point(0, 0), new Point(-2, 0), new Point(+1, 0),
            new Point(-2, -1), new Point(+1, +2) }, // THREEQUARTER to HALF
        {new Point(0, 0), new Point(+1, 0), new Point(-2, 0),
            new Point(+1, -2), new Point(-2, +1) }, // THREEQUARTER to NONE 
        {new Point(0, 0), new Point(-1, 0), new Point(+2, 0),
            new Point(-1, +2), new Point(+2, -1) }  // NONE to THREEQUARTER
    };
    
    /**
     * Private constructor to inhibit external instantiation.
     */
    private WallKick() {
        // do nothing
    }
    
    /**
     * Returns an array of Points representing the wall kick offsets
     * for the situation defined by the parameters.
     * 
     * @param thePiece the piece type being rotated
     * @param theOriginalRotation the rotational state before the rotation
     * @param theGoalRotation the desired rotational state
     * @return The wall kick offsets for these conditions
     */
    public static Point[] getKicks(final TetrisPiece thePiece,
                                       final Rotation theOriginalRotation,
                                       final Rotation theGoalRotation) {
        
        Point[] result = new Point[0];
        
        if (thePiece == TetrisPiece.I) {
            result = getIPieceKicks(theOriginalRotation, theGoalRotation);
        } else if (thePiece != TetrisPiece.O) {
            result = getJLSTZKicks(theOriginalRotation, theGoalRotation);
        }
        
        return result;
    }

    /**
     * Returns an array of Points representing the wall kick offsets
     * for J, L, S, T, and Z Pieces for the situation defined by the parameters.
     * 
     * @param theOriginalRotation the rotational state before the rotation
     * @param theGoalRotation the desired rotational state
     * @return The wall kick offsets for these conditions
     */
    private static Point[] getJLSTZKicks(final Rotation theOriginalRotation,
                                         final Rotation theGoalRotation) {
        Point[] result = new Point[0];
        switch (theOriginalRotation) {
            case NONE:
                if (theGoalRotation == Rotation.QUARTER) {
                    result = JLSTZ_OFFSETS[0];
                } else if (theGoalRotation == Rotation.THREEQUARTER) {
                    result = JLSTZ_OFFSETS[7];
                }
                break;
            case QUARTER:
                if (theGoalRotation == Rotation.HALF) {
                    result = JLSTZ_OFFSETS[2];
                } else if (theGoalRotation == Rotation.NONE) {
                    result = JLSTZ_OFFSETS[1];
                }
                break;
            case HALF:
                if (theGoalRotation == Rotation.THREEQUARTER) {
                    result = JLSTZ_OFFSETS[4];
                } else if (theGoalRotation == Rotation.QUARTER) {
                    result = JLSTZ_OFFSETS[3];
                }
                break;
            case THREEQUARTER:
                if (theGoalRotation == Rotation.NONE) {
                    result = JLSTZ_OFFSETS[6];
                } else if (theGoalRotation == Rotation.HALF) {
                    result = JLSTZ_OFFSETS[5];
                }
                break;
            default:
                // should never happen
                break;
        }
        return result;
    }

    /**
     * Returns an array of Points representing the wall kick offsets
     * for IPieces for the situation defined by the parameters.
     * 
     * @param theOriginalRotation the rotational state before the rotation
     * @param theGoalRotation the desired rotational state
     * @return The wall kick offsets for these conditions
     */
    private static Point[] getIPieceKicks(final Rotation theOriginalRotation,
                                          final Rotation theGoalRotation) {
        Point[] result = new Point[0];
        switch (theOriginalRotation) {
            case NONE:
                if (theGoalRotation == Rotation.QUARTER) {
                    result = I_OFFSETS[0];
                } else if (theGoalRotation == Rotation.THREEQUARTER) {
                    result = I_OFFSETS[7];
                }
                break;
            case QUARTER:
                if (theGoalRotation == Rotation.HALF) {
                    result = I_OFFSETS[2];
                } else if (theGoalRotation == Rotation.NONE) {
                    result = I_OFFSETS[1];
                }
                break;
            case HALF:
                if (theGoalRotation == Rotation.THREEQUARTER) {
                    result = I_OFFSETS[4];
                } else if (theGoalRotation == Rotation.QUARTER) {
                    result = I_OFFSETS[3];
                }
                break;
            case THREEQUARTER:
                if (theGoalRotation == Rotation.NONE) {
                    result = I_OFFSETS[6];
                } else if (theGoalRotation == Rotation.HALF) {
                    result = I_OFFSETS[5];
                }
                break;
            default:
                // should never happen
                break;
        }
        return result;
    }

}
