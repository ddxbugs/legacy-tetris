/*
 * TetrisMain.java
 */
package view;

//import com.sun.media.codec.audio.mp3.JavaDecoder; 
import java.awt.EventQueue;
//import javax.media.Codec;
//import javax.media.PlugInManager;
/**
 * This is the main driver for TetrisGUI.
 * @author ddxbugs
 * @version 1.0.0-alpha.1+001
 *
 */
public final class TetrisMain {
    /** A default private constructor to prevent instantiation of this class. **/
    private TetrisMain() {
        throw new IllegalStateException();
    }
    /**
     * The main method, instantiates new Tetris game and invokes runnable method.
     * @param theArgs The command line arguments.
     */
    public static void main(final String[] theArgs) {
        
//        final Codec c = new JavaDecoder();
//        PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder",
//                                c.getSupportedInputFormats(),
//                                c.getSupportedOutputFormats(null),
//                                PlugInManager.CODEC);
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final TetrisGUI gui = new TetrisGUI(); 
                gui.start();
            }
        });
    }
}
