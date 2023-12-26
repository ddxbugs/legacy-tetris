/*
 * TetrisBoard.java
 */
package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import model.Board;
/**
 * This class constructs the view of pieces in the game.
 * @author ddxbugs
 * @version 1.0.0-alpha.1+001
 */
final class TetrisBoard extends JPanel implements ActionListener,
                                                  Observer {
    /** A default serial version UID. **/
    private static final long serialVersionUID = 1L;
    /** A magic number. **/
    private static final int FIVE = 5;
    /** A constant that represents one second. **/
    private static final int DELAY = 1000;
    /** A constant that deducts time from the timer. **/
    private static final int ONE_HUNDRETH_MS = 100;
    /** A constant scale factor for tetris blocks. **/
    private static final int SCALE = 30;
    
    /** The current tetris board game. **/
    private final Board myBoard;
    
    /** The current level in the game. **/
    private int myLevel;
    /** The string representation of the current board game. **/
    private String myBoardAsAString;    
    /** A timer for the current tetris piece. **/
    private Timer myTimer;
    
    
    /**
     * A default class constructor that displays the game.
     */
    TetrisBoard() {
        super();
        myLevel = 1;
        myBoard = new Board();
        myBoardAsAString = "";
        myTimer = new Timer(DELAY, this);
        setMaximumSize(new Dimension(myBoard.getWidth() * SCALE,
                                       myBoard.getHeight() * SCALE));
        setup();
        
        setFocusable(true);
        setVisible(true);
    }
    /**
     * A method to set up the board's components and layout.
     */
    private void setup() {
        myBoard.addObserver(this);
        addKeyListener(new KeyboardAdapter(this)); 
        
        final Border border = BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, 
                                                                  ColorPalette.TRON_CYAN, 
                                                                  ColorPalette.PANE, 
                                                                  ColorPalette.BASESTAR, 
                                                                  ColorPalette.TRON_CYAN);
        setBorder(border);
    }
    /**
     * A method that increments the current level in the game.
     */
    protected void levelUp() {
        myLevel++;
        myTimer.stop();
        myTimer = new Timer(DELAY - (myLevel * ONE_HUNDRETH_MS), this);
        myTimer.start();
    }
    /**
     * A method that returns the current level. 
     * @return Returns the current level. 
     */
    protected int getLevel() {
        return myLevel;
    }
    /**
     * Ends the current game.
     */
    protected void endGame() {
        
        myLevel = 0;
        myTimer.stop();
        final int result = JOptionPane.showConfirmDialog(this, 
                                      "Would you like to play again?", 
                                      "GAME OVER", JOptionPane.YES_NO_CANCEL_OPTION);
        if (result == 0) {
            myBoard.newGame();
            myTimer = new Timer(DELAY - (myLevel * ONE_HUNDRETH_MS), this);
            myTimer.start();
        }
    }
    /**
     * Returns the timer.
     * @return The timer.
     */
    protected Timer getTimer() {
        return myTimer;
    }
    /**
     * Returns the current game board.
     * @return The board.
     */
    protected Board getBoard() {
        return myBoard;
    }
    /**
     * A method that fires the timer action.
     */
    @Override
    public void actionPerformed(final ActionEvent theActionEvent) {
        myBoard.down();
    }
    /**
     * Updates the observer for this class and calls methods to update the board.
     * @param theObservable The originator of this observer pattern.
     * @param theObject The object passed by the Board as a string.
     */
    @Override
    public void update(final Observable theObservable, 
                       final Object theObject) {
        if (theObject instanceof String) {
            myBoardAsAString = (String) theObject;
            repaint();
        }
    }
    /**
     * The graphical paint component method to repaint the current game pieces.
     * @param theGraphics The graphics component.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        drawBlocks(g2d);
    }
    /**
     * A helper method to draw the board.
     * @param theGraphics The graphics component for this class.
     */
    private void drawBlocks(final Graphics2D theGraphics) {
        
        final String[] board = myBoardAsAString.split("\n");
        
        int rectX = 0;
        int rectY = 0;
        
        // The width and height factors
        final int width = getWidth() / myBoard.getWidth();
        final int height = getHeight() / myBoard.getHeight();
        
        //Iterate over each line starting at the fifth index of the board array
        for (int i = FIVE; i < board.length - 1; i++) {
            final String s = board[i];
            // Color chooser for blocks
            for (int index = 1; index <= myBoard.getWidth(); index++) {
                if (s.charAt(index) == 'L' || s.charAt(index) == 'J') {
                    theGraphics.setColor(ColorPalette.TRON_CYAN);
                } else if (s.charAt(index) == 'S' || s.charAt(index) == 'Z') {
                    theGraphics.setColor(ColorPalette.TRON_ORANGE);
                } else if (s.charAt(index) == 'T' || s.charAt(index) == 'I') {
                    theGraphics.setColor(ColorPalette.SWEET_YELLOW);
                } else if (s.charAt(index) == 'O') {
                    theGraphics.setColor(ColorPalette.PANE);
                } else {
                    theGraphics.setColor(ColorPalette.BASESTAR);
                }
                final Rectangle2D rect = new Rectangle(rectX, rectY, width, height);
                theGraphics.fill(rect);
                theGraphics.setColor(ColorPalette.PANE);
                theGraphics.draw(rect);        
                rectX += width; //increment width factor for rectX
            }
            //index = 0; //reset x-coordinate
            rectX = 0; //reset rectX coordinate
            rectY += height; //increment height factor for rectY
        }
    }
}
