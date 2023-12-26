/*
 * Backgrounds.java 
 */
package view;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
/**
 * This class constructs a layered pane for the GUI background.
 * @author ddxbugs
 * @version 1.0.0-alpha.1+001
 */
final class Background extends JLayeredPane {
    /** A defaul serial version UID. **/
    private static final long serialVersionUID = 1L;
    /**
     * This is the default constructor method for constructing a pane.
     * @param theWidth The width of the GUI frame.
     * @param theHeight The height of the GUI frame.
     */
    Background(final int theWidth, final int theHeight) {
        super();
        
        setMinimumSize(new Dimension(theWidth, theHeight));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
     
    }
}
