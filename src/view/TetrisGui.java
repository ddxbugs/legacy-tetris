/**
 * TetrisGui.java
 */
package view;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * @author ddxbugs
 *
 */
public class TetrisGui extends JFrame implements Observer {
	
	private BoardView myBoardView;
	private PreviewPanel myPreviewPanel;
	private ScorePanel myScorePanel;
	
	/**
	 * @throws HeadlessException
	 */
	private TetrisGui() throws HeadlessException {
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
		JLayeredPane layeredPane;
		JPanel panel;
		layeredPane = new JLayeredPane();
		panel = new JPanel();
		
		layeredPane.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		myBoardView = new BoardView();
		myPreviewPanel = new PreviewPanel();
		myScorePanel = new ScorePanel();
		
		panel.add(myScorePanel);
		panel.add(myBoardView);
		panel.add(myPreviewPanel);
		
		layeredPane.add(panel);
		
		add(layeredPane);
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
		if (arg instanceof Boolean && ((Boolean) arg).booleanValue()) {
			// end game
		}
	}

}
