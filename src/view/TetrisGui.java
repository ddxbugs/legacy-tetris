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

	private static final int DEFAULT_BOARD_WIDTH = 300;
	private static final int DEFAULT_BOARD_HEIGHT = 600;
	private static final int DEFAULT_PREVIEW_PANEL_WIDTH = 0;
	private static final int DEFAULT_PREVIEW_PANEL_HEIGHT = 0;
	private static final int DEFAULT_SCORE_PANEL_WIDTH = 0;
	private static final int DEFAULT_SCORE_PANEL_HEIGHT = 0;
	
	private BoardView myBoardView;
	private PreviewPanel myPreviewPanel;
	private ScorePanel myScorePanel;
	
	/**
	 * @param theTitle
	 * @throws HeadlessException
	 */
	public TetrisGui(String theTitle) throws HeadlessException {
		super(theTitle);
		// TODO Auto-generated constructor stub
		JLayeredPane layeredPane;
		JPanel panel;
		layeredPane = new JLayeredPane();
		panel = new JPanel();
		
		layeredPane.setLayout(new BoxLayout(layeredPane, BoxLayout.Y_AXIS));
		
		myBoardView = new BoardView();
		myPreviewPanel = new PreviewPanel();
		myScorePanel = new ScorePanel();
		
		setUpComponents();
		
		panel.add(myScorePanel);
		panel.add(myBoardView);
		panel.add(myPreviewPanel);
		
		layeredPane.add(panel);
		
		add(layeredPane);
	}
	
	private void setUpComponents() {
		myBoardView.setSize(DEFAULT_BOARD_WIDTH, DEFAULT_BOARD_HEIGHT);
		myPreviewPanel.setSize(DEFAULT_PREVIEW_PANEL_WIDTH, DEFAULT_PREVIEW_PANEL_HEIGHT);
		myScorePanel.setSize(DEFAULT_SCORE_PANEL_WIDTH, DEFAULT_SCORE_PANEL_HEIGHT);
	}
	
	/**
	 * 
	 */
	@Deprecated
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof Boolean && ((Boolean) arg).booleanValue()) {
			// end game
			myBoardView.endGame();
		}
	}

}
