/*
 * PreviewPane.java
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import model.MovableTetrisPiece;
/**
 * This class constructs a preview pane displaying the next tetris piece in the game.
 * @author ddxbugs
 * @version 1.0.0-alpha.1+001
 */
final class PreviewPane extends JPanel implements Observer {
    /** A default serial version UID. **/
    private static final long serialVersionUID = 1L;
    /** The default fixed dimension. **/
    private static final Dimension PANE_SIZE = new Dimension(200, 200);
    /** A default font for this panel. **/
    private static final Font MONO_FONT = new Font("Monospaced", Font.BOLD, 12);
    /** Magic number three. **/
    private static final int THREE = 3;
    /** Magic number four. **/
    private static final int FOUR = 4;
    /** Magic number twenty. **/
    private static final int TWENTY = 20;
    /** Magic number fifty-five. **/
    private static final int FIFTY_FIVE = 55;
    /** Magic number sixty. **/
    private static final int SIXTY = 60;
    /** Magic number eighty. **/
    private static final int EIGHTY = 80;
    /** Magic number seventy. **/
    private static final int SEVENTY = 70;
    
    /** The current tetris board game. **/
    private final TetrisBoard myBoard;
    
    /** The next tetris piece in the queue. **/
    private MovableTetrisPiece myNextPiece;
    
    /** 
     * A default constructor method that creates a preview of the next tetris piece.
     * @param theBoard The current tetris board game. 
     */
    PreviewPane(final TetrisBoard theBoard) {
        super();
        myBoard = theBoard;
        
        setMaximumSize(PANE_SIZE);
        setup();
        
        setVisible(true);
    }
    /**
     * A method to set up the score board panel components.
     */
    private void setup() {
        myBoard.getBoard().addObserver(this);
        final Border border = BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, 
                                                                  ColorPalette.TRON_CYAN, 
                                                                  ColorPalette.PANE, 
                                                                  ColorPalette.BASESTAR, 
                                                                  ColorPalette.TRON_CYAN);
        setBorder(BorderFactory.createTitledBorder(border, 
                                                   "NEXT PIECE", 
                                                   TitledBorder.LEFT, 
                                                   TitledBorder.CENTER, 
                                                   MONO_FONT, 
                                                   ColorPalette.PANE));
    }
    /**
     * 
     */
    @Override
    public void update(final Observable theObservable, final Object theObject) {
       
        if (theObject instanceof MovableTetrisPiece) {
            
            myNextPiece = (MovableTetrisPiece) theObject;
            
            repaint();
        }
    }
    /**
     * A method for drawing the next tetris piece in the preview pane.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                              RenderingHints.VALUE_ANTIALIAS_ON);
        
        final String blockType = myNextPiece.toString().trim().substring(0, 1);
        
        if ("J".equals(blockType) || "L".equals(blockType)) {
            theGraphics.setColor(ColorPalette.TRON_CYAN);
        } else if ("S".equals(blockType) || "Z".equals(blockType)) {
            theGraphics.setColor(ColorPalette.TRON_ORANGE);
        } else if ("T".equals(blockType) || "I".equals(blockType)) {
            theGraphics.setColor(ColorPalette.SWEET_YELLOW);
        } else if ("O".equals(blockType)) {
            theGraphics.setColor(ColorPalette.PANE);
        } else {
            theGraphics.setColor(ColorPalette.BASESTAR);
        }
        
        drawBlocks(g2d, blockType);        
    }
    /**
     * A helper method to paint the next tetris piece in a small window pane.
     * @param theGraphics The graphics component for this panel.
     * @param theBlockType The tetramino piece used to identify the color correctly.
     */
    private void drawBlocks(final Graphics2D theGraphics, final String theBlockType) {
        
        int rectX = 0;  // variable scaled to fit this panel's width
        int rectY = 0;  // variable scaled to fit this panel's height
        
        final Color fillColor = theGraphics.getColor(); // used to remember fill color 
        
        final String[] split = myNextPiece.toString().split("\n"); // split into array
        
        // Determine if the piece is odd or even width or height
        if (myNextPiece.getWidth() == THREE) { // T, L, J, S or Z 
            rectX = SIXTY;
            rectY = SEVENTY;
        } else if (myNextPiece.getWidth() == FOUR) { // I
            rectX = FIFTY_FIVE;
            rectY = EIGHTY;
        } else {    // O 
            rectX = SIXTY;
            rectY = SIXTY;
        }
        
        // Iterate split array and find index location for each block type in the index
        for (int row = 0; row < split.length; row++) {
            int tempX = rectX; //Variable to store temp start x
            for (int col = 0; col < split[row].length(); col++) {
                
                final Rectangle2D rect = new Rectangle(); 
                
                if (split[row].charAt(col) == theBlockType.charAt(0)) {
                    rect.setRect(tempX, rectY, TWENTY, TWENTY);
                    
                    theGraphics.fill(rect);
                    theGraphics.setColor(ColorPalette.PANE);
                    theGraphics.draw(rect);
                    theGraphics.setColor(fillColor);
                    
                }
                tempX += TWENTY;
            }
            tempX = rectX;
            rectY += TWENTY;
        }
    } 
}
