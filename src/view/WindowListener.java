/*
 * WindowListener.java
 */
package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * This class constructs a listener for GUI window state change events.
 * @author ddxbugs
 * @version 1.0.0-alpha.1+001
 */
final class WindowListener extends WindowAdapter {
    /** The current tetris game board. **/
    private final TetrisBoard myBoard;
    /**
     * A default constructor method that creates a windows adapter.
     * @param theBoard The current tetris game board.
     */
    WindowListener(final TetrisBoard theBoard) {
        super();
        myBoard = theBoard;
    }
    /** A method that fires an action when the GUI frame is resized. **/
    @Override
    public void windowStateChanged(final WindowEvent theWindowEvent) {
        //set the scale of the board, pieces and components
    }
    /** Fired action turns the volume down when the frame is deactivated. **/
    @Override
    public void windowLostFocus(final WindowEvent theWindowEvent) {
        // lower the music volume
    }
}
