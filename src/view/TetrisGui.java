/**
 * TetrisGui.java
 */
package view;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author ddxbugs
 *
 */
public class TetrisGui extends JFrame implements Observer {

	private static final String INFO = "Disney's : Tron 2010 All Rights Reserved";
	private static final String TITLE = "Legacy Tetris 2018";
	
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
	/**
	 * 
	 */
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
	
	/**
	 * @author ddxbugs
	 *
	 */
	class MenuBar extends JMenuBar implements ActionListener {
		
		private static final String ABOUT = "About";
		private static final String NEW_GAME = "New Game";
		private static final String END_GAME = "End Game";
		private static final String SETTINGS = "Settings";
		private static final String HELP = "Help";
		private static final String EXIT = "Exit";
		private static final String CONTROLS = "Controls";
		MenuBar() {
			super();
			setUpComponents();
			setVisible(true);
			
		}
		/**
		 * 
		 */
		private void setUpComponents() {
			JMenu newGame = new JMenu(NEW_GAME);
			JMenu settings = new JMenu(SETTINGS);
			JMenu endGame = new JMenu(END_GAME);
			
			JMenuItem exit = new JMenuItem(EXIT);
			JMenuItem about = new JMenuItem(ABOUT);
			JMenuItem help = new JMenuItem(HELP);
			JMenuItem controls = new JMenuItem(CONTROLS);
			
			newGame.addActionListener(this);
			endGame.addActionListener(this);
			settings.addActionListener(this);
			exit.addActionListener(this);
			about.addActionListener(this);
			help.addActionListener(this);
			controls.addActionListener(this);
			
			settings.add(about);
			settings.add(help);
			settings.add(controls);
			
			add(newGame);
			add(endGame);
			add(settings);
		}
		/**
		 * 
		 */
		@Override
		public void actionPerformed(ActionEvent theActionEvent) {
			// TODO Auto-generated method stub
			final String cmd = theActionEvent.getActionCommand();
			
			switch(cmd) {
			case NEW_GAME : break;
			case END_GAME : break;
			case SETTINGS : break;
			case ABOUT : JOptionPane.showMessageDialog(myBoardView, INFO, TITLE, JOptionPane.OK_OPTION);
			case HELP : break;
			case EXIT : break;
			case CONTROLS : break;
			default : break;
			}
		}
	}
}
