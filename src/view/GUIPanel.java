/*
 * GUIPanel.java
 */
package view;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
/**
 * This class constructs a button interface for the GUI.
 * @author ddxbugs
 * @version 1.0.0-alpha.1+001
 */
class GUIPanel extends JPanel {
    /** A default serial version UID. **/
    private static final long serialVersionUID = 1L;
    /** A default dimension constant. **/
    private static final Dimension DEFAULT_SIZE = new Dimension(200, 400);
    /** A default font for this panel. **/
    private static final Font MONO_FONT = new Font("Monospaced", Font.BOLD, 12);
    /**
     * This is the default constructor for creating a gui keyboard.
     */
    GUIPanel() {
        super();
        
        setMaximumSize(DEFAULT_SIZE);
        setup();
    }
    /**
     * This method sets up a keypad for the GUI to control the tetris piece behavior.
     */
    private void setup() {
        final JTextArea textArea = new JTextArea();
        textArea.setFont(MONO_FONT);        
        textArea.setWrapStyleWord(true);
        
        final StringBuilder sb = new StringBuilder(400);
        sb.append("Keyboard Layout: \n"
                        + "W/UP = Rotate\n"
                        + "A/LEFT = Left\n"
                        + "S/DOWN = Down\n"
                        + "D/RIGHT = Right\n"
                        + "SPACE = Drop\n"
                        + "ESCAPE = Pause\n\n"
                        + "How to Play: \n"
                        + "SCORE = Score / Next Level\n"
                        + "LINES = Lines cleared\n\n"
                        + "LEVEL = \n"
                        + "1-4 Level 1\n"
                        + "5-9 Level 2\n"
                        + "10-14 Level 3\n"
                        + "15-19 Level 4\n\n"
                        + "Scoring Algorithm: \n"
                        + "SCORE = LINES * 100\n"
                        + "LEVEL Every 500 points");
        
        textArea.append(sb.toString());
        add(textArea);
        final Border border = BorderFactory.createSoftBevelBorder(BevelBorder.RAISED, 
                                                                  ColorPalette.TRON_CYAN, 
                                                                  ColorPalette.PANE, 
                                                                  ColorPalette.BASESTAR, 
                                                                  ColorPalette.TRON_CYAN);
        setBorder(BorderFactory.createTitledBorder(border, 
                                                   "GAME CONTROLS", 
                                                   TitledBorder.LEFT, 
                                                   TitledBorder.CENTER, 
                                                   MONO_FONT, 
                                                   ColorPalette.PANE));
        
    }
}
