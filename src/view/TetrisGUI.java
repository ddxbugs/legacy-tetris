/*
 * TetrisGUI.java
 */
package view;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * This is the main class that constructs a GUI for the tetris game.
 * @author ddxbugs
 * @version 1.0.0-alpha.1+001
 *
 */
public class TetrisGUI extends JFrame implements Observer {
    
    /** A default serial version UID. **/
    private static final long serialVersionUID = 1L;
    /** A default constant frame width value. **/
    private static final int DEFAULT_WIDTH = 600;
    /** A default constant frame height value. **/
    private static final int DEFAULT_HEIGHT = 700;
    /** A default constant time delay value. **/
    private static final int DELAY = 1000;
    
    /** The current tetris board panel. **/
    private TetrisBoard myCurrentBoard;
    /** The current score board panel. **/
    private ScoreBoard myScoreBoard;
    /** The current tetris piece preview panel. **/
    private PreviewPane myPreviewPane;
    /**
     * A default constructor for displaying the GUI components.
     */
    public TetrisGUI() {
        super("Disney's Tron: Legacy Super Tetris");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        setResizable(false);
        
        pack();
        
        setLocationRelativeTo(null);        
        setVisible(true);
    }
    /**
     * Invokes the runnable.
     */
    public void start() {
        initialize();
        setup();
        load();
    }
    /**
     * Initializes the game's variables.
     */
    private void initialize() {
        myCurrentBoard = new TetrisBoard();
        myScoreBoard = new ScoreBoard(myCurrentBoard);
        myPreviewPane = new PreviewPane(myCurrentBoard);
    }
    /**
     * Sets the game's interface.
     */
    private void setup() {
        myCurrentBoard.getBoard().addObserver(this);
        
        final URL url = getClass().getResource("/resources/icon.jpg");
        final Image img = new ImageIcon(url).getImage();
        setIconImage(img);  
        
        final Background background = new Background(DEFAULT_WIDTH, DEFAULT_HEIGHT);
                
        final JPanel leftPanel = new JPanel();
        final JPanel rightPanel = new JPanel();
        
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        
        leftPanel.add(myScoreBoard);
        leftPanel.add(myCurrentBoard);
        rightPanel.add(myPreviewPane);
        rightPanel.add(new GUIPanel());
        
        background.add(leftPanel);
        background.add(rightPanel);
        
        setJMenuBar(new MenuBar(myCurrentBoard));
        
        add(background);
    }
    /**
     * Loads a new game.
     */
    private void load() {
        myCurrentBoard.getBoard().newGame();
        myCurrentBoard.getTimer().setInitialDelay(DELAY);
        myCurrentBoard.getTimer().start();
    }
    /**
     * An observer pattern method that notifies this GUI that the game has ended.
     * @param theObservable The observable class Board.
     * @param theObject The observable object's boolean value.
     */
    @Override
    public void update(final Observable theObservable, final Object theObject) {
        if (theObject instanceof Boolean) {
            final boolean gameOver = ((Boolean) theObject).booleanValue();
            if (gameOver) {
                myCurrentBoard.endGame();
            }
        }
    }
}
