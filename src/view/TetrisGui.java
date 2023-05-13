/**
 * TetrisGui.java
 */
package view;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

/**
 * @author ddxbugs
 *
 */
public class TetrisGui extends JFrame implements Observer {
	private BoardView myBoardView;
	private PreviewPanel myPreviewPanel;
	private ScorePanel myScorePanel;
	
	/**
	 * Invokes the runnable method
	 */
	public void start() {
		
	}
	
	/**
	 * @throws HeadlessException
	 */
	public TetrisGui() throws HeadlessException {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param gc
	 */
	public TetrisGui(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param title
	 * @throws HeadlessException
	 */
	public TetrisGui(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
		myBoardView = new BoardView();
		myPreviewPanel = new PreviewPanel();
		myScorePanel = new ScorePanel();
	}

	/**
	 * @param title
	 * @param gc
	 */
	public TetrisGui(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
