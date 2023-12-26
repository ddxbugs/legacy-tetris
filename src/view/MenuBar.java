/*
 * MenuBar.java
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
/**
 * This class constructs a menu bar for the GUI.
 * @author ddxbugs
 * @version 1.0.0-alpha.1+001
 */
class MenuBar extends JMenuBar implements ActionListener {
    /**A default serial version UID. **/
    private static final long serialVersionUID = 1L;
    /**The string value of About. **/
    private static final String ABOUT = "About";
    /**The string value of New Game. **/
    private static final String NEW_GAME = "New Game";
    /**The string value of End Game. **/
    private static final String END_GAME = "End Game";
    /**The string value of Key Configuration. **/
    private static final String KEY_CONFIG = "Key Configuration";
    /**The string value of End Game. **/
    private static final String GAME_MENU = "Game Menu";
    /**The string value of File. **/
    private static final String FILE = "File";
    /**The string value of How to Play. **/
    private static final String HOW_TO_PLAY = "How to Play";
    /**The string value of Help. **/
    private static final String HELP = "Help";
    
    /** The current tetris game board. **/
    private final TetrisBoard myTetrisBoard;
    /**
     * A default constructor that creates the menu bar for the game.
     * @param theBoard The current tetris board game.
     */
    MenuBar(final TetrisBoard theBoard) {
        super();
        myTetrisBoard = theBoard;
        setup();
        setVisible(true);
    }
    /**
     * A method to add menu item components to the menu bar.
     */
    private void setup() {
        
        final Border border = BorderFactory.createBevelBorder(BevelBorder.LOWERED, 
                                                              ColorPalette.PANE, 
                                                              ColorPalette.TRON_CYAN, 
                                                              ColorPalette.TRON_CYAN, 
                                                              ColorPalette.PANE);
        final Border title = BorderFactory.createTitledBorder(border, 
                                                              GAME_MENU, 
                                                              TitledBorder.CENTER, 
                                                              TitledBorder.ABOVE_TOP);
        setBorder(title);
        createFileMenu();
        createHelpMenu();
    }
    /**
     * A helper method to create the file menu.
     */
    private void createFileMenu() {
        
        final JMenu file = new JMenu(FILE);
        
        final JMenuItem newGame = new JMenuItem(NEW_GAME);
        newGame.addActionListener(this);
        
        final JMenuItem endGame = new JMenuItem(END_GAME);
        endGame.addActionListener(this);
        
        file.add(newGame);
        file.addSeparator();
        file.add(endGame);
        
        add(file);
    }
    /**
     * A helper method to create the help menu.
     */
    private void createHelpMenu() {

        final JMenu help = new JMenu(HELP);
        help.addActionListener(this);
        
        final JMenuItem about = new JMenuItem(ABOUT);
        about.addActionListener(this);
        
        help.add(about);
        
        final JMenuItem controls = new JMenuItem(KEY_CONFIG);
        controls.addActionListener(this);
        
        help.add(controls);
        
        final JMenuItem rules = new JMenuItem(HOW_TO_PLAY);
        rules.addActionListener(this);
        
        help.add(rules);
        
        add(help);
    }
    /**
     * A method that carries out the specified action command.
     * @param theActionEvent The source of the action event.
     */
    @Override
    public void actionPerformed(final ActionEvent theActionEvent) {
       
        final String action = theActionEvent.getActionCommand();
        
        if (NEW_GAME.equals(action) && !myTetrisBoard.getTimer().isRunning()) {
            myTetrisBoard.endGame();
        }
        if (END_GAME.equals(action) && myTetrisBoard.getTimer().isRunning()) {
            myTetrisBoard.endGame();
        }
        if (ABOUT.equals(action)) {
            JOptionPane.showMessageDialog(null, 
                                          "Color palette courtesy by www.colourlovers.com\n"
                                          + "Disney's Tron: Legacy 2010 All Rights Reserved.", 
                                          "Disney's Tron: Legacy Super Tetris 2018", 
                                          JOptionPane.OK_OPTION, 
                                          new ImageIcon("resources/icon.jpg"));
        }
        if (KEY_CONFIG.equals(action)) {
            JOptionPane.showMessageDialog(null, 
                                          "W/UP = Rotate\n"
                                                  + "A/LEFT = Left\n"
                                                  + "S/DOWN = Down\n"
                                                  + "D/RIGHT = Right\n"
                                                  + "SPACE = Drop\n"
                                                  + "ESCAPE = Pause\n\n", 
                                          KEY_CONFIG, 
                                          JOptionPane.INFORMATION_MESSAGE);
        }
        if (HOW_TO_PLAY.equals(action)) {
            JOptionPane.showMessageDialog(null, 
                                          "Rules: \n"
                                          + "1 x Line = 100 pts\n"
                                          + "2 x Line = 200 pts\n"
                                          + "3 x Line = 300 pts\n"
                                          + "4 x Line = 400 pts\n"
                                          + "5 x Line = 500 pts\n\n"
                                          + "Timer delay 1000 ms\n "
                                          + "Delay - (Level * 100 ms)", 
                                          HOW_TO_PLAY, 
                                          JOptionPane.QUESTION_MESSAGE);
        }
    }
}
