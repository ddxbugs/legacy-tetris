/*
 * ScoreBoard.java
 */
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
/**
 * This class constructs a score board panel for the current game.
 * @author ddxbugs
 * @version 1.0.0-alpha.1+001
 */
final class ScoreBoard extends JPanel implements Observer {
    
    /** A default serial version UID. **/
    private static final long serialVersionUID = 1L;
    /** A default score board dimension. **/
    private static final Dimension SCORE_BOARD_SIZE = new Dimension(300, 100);
    /** A default font used for the score board. **/
    private static final Font MONO_FONT = new Font("Monospaced", Font.BOLD, 12);
    /** Magic number ten. **/
    private static final int FIFTY = 50;
    /** Magic number ten. **/
    private static final int ONE_HUNDRED = 100;
    /** Magic number ten. **/
    private static final int FIVE_HUNDRED = 500;
    
    /** The current tetris board game. **/
    private final TetrisBoard myBoard;
    /** The score displayed as a text label. **/
    private final JLabel myScoreLabel;
    /** The current level in the game. **/
    private final JLabel myLevelLabel;
    /** The current number of lines cleared. **/
    private final JLabel myLinesLabel;
    /** The current score in the game. **/
    
    private int myScore; 
    /** The current number of lines cleared. **/
    private int myLines;
    
    
    /** 
     * A default constructor method creates a panel to track game score. 
     * @param theBoard The current tetris board game. 
     */
    ScoreBoard(final TetrisBoard theBoard) {
        super();
        
        myBoard = theBoard;
        myScoreLabel = new JLabel();
        myLevelLabel = new JLabel();
        myLinesLabel = new JLabel();
        
        myScore = 0; 
        myLines = 0;
        
        setLayout(new BorderLayout());
        setMaximumSize(SCORE_BOARD_SIZE);
        setup();
        
    }
    /**
     * A method to set the border and score HUD.
     */
    private void setup() {
        myBoard.getBoard().addObserver(this);
        
        myScoreLabel.setSize(ONE_HUNDRED, FIFTY);
        myScoreLabel.setFont(MONO_FONT);
        myScoreLabel.setText("Score: " + myScore
                            + " / " 
                            + (myBoard.getLevel() * FIVE_HUNDRED));
       
        myLevelLabel.setSize(ONE_HUNDRED, FIFTY);
        myLevelLabel.setFont(MONO_FONT);
        myLevelLabel.setText("Level: " + myBoard.getLevel());
        
        
        myLinesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myLinesLabel.setVerticalAlignment(SwingConstants.CENTER);
        myLinesLabel.setSize(ONE_HUNDRED, FIFTY);
        myLinesLabel.setFont(MONO_FONT);
        myLinesLabel.setText("Lines: " + myLines);
        
        add(myScoreLabel, BorderLayout.WEST);
        add(myLevelLabel, BorderLayout.EAST);
        add(myLinesLabel, BorderLayout.CENTER);
        
        final Border border = BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, 
                                                                  ColorPalette.TRON_CYAN, 
                                                                  ColorPalette.PANE, 
                                                                  ColorPalette.BASESTAR, 
                                                                  ColorPalette.TRON_CYAN);
        setBorder(BorderFactory.createTitledBorder(border, 
                                                   "SCORE: ", 
                                                   TitledBorder.LEFT, 
                                                   TitledBorder.CENTER, 
                                                   MONO_FONT, 
                                                   ColorPalette.PANE));
    }
    /** 
     * Resets the scoreboard.
     */
    protected void reset() {
        myLines = 0;
        myScore = 0;
        myScoreLabel.setText(myScore
                             + " / " 
                             + 0);
        
        myLevelLabel.setText("Level: " 
                        + myBoard.getLevel());    
        
        myLinesLabel.setText("Lines: " + myLines);
    }
    /**
     * Updates the scoreboard.
     */
    private void updateScoreBoard() {
        
        //  Level xp based on increments of FIVE_HUNDRED
        int levelCap = myBoard.getLevel() * FIVE_HUNDRED; 
        
        if (myScore >= levelCap) {
            
            myBoard.levelUp();
            
            levelCap = myBoard.getLevel() * FIVE_HUNDRED;
        }
        
        myScoreLabel.setText(myScore
                             + " / " 
                             + levelCap);
        
        myLevelLabel.setText("Level: " 
                        + myBoard.getLevel());    
        
        myLinesLabel.setText("Lines: " + myLines);
    }
    /**
     * A method that updates the JLabels to this class implementing observer.
     * @param theObservable The tetris board.
     * @param theObject The object passed as an integer array.
     */
    @Override
    public void update(final Observable theObservable, 
                       final Object theObject) {
                
        if (theObject instanceof Integer[]) {
            
            myLines += ((Integer[]) theObject).length;
            myScore += ((Integer[]) theObject).length * ONE_HUNDRED;
            
            updateScoreBoard();
           
        }
    }
}
