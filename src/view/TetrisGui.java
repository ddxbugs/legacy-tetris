/**
 * TetrisGui.java
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import model.ColorPalette;

/**
 * @author ddxbugs
 *
 */
public class TetrisGui extends JFrame implements Observer, ActionListener {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;
	/** Disney movie franchise title and credits */
	private static final String INFO = "Disney's : Tron 2010 All Rights Reserved";
	/** Legacy Tetris game title */
	private static final String TITLE = "Legacy Tetris 2018";
	/** The default board view width */
	private static final int DEFAULT_BOARD_VIEW_WIDTH = 300;
	/** The default board view height */
	private static final int DEFAULT_BOARD_VIEW_HEIGHT = 600;
	/** The default preview panel view width */
	private static final int DEFAULT_PREVIEW_PANEL_WIDTH = 200;
	/** The default preview panel view height */
	private static final int DEFAULT_PREVIEW_PANEL_HEIGHT = 200;
	/** The default score panel view width */
	private static final int DEFAULT_SCORE_PANEL_WIDTH = 300;
	/** The default score panel view height */
	private static final int DEFAULT_SCORE_PANEL_HEIGHT = 50;
	/** The in-game menu options */
	private MenuBar myMenuBar;
	/** The tetris board view */
	private BoardView myBoardView;
	/** The tetris preview view */
	private PreviewPanel myPreviewPanel;
	/** The tetris score view */
	private ScorePanel myScorePanel;
//	private SettingsPanel mySettingsPanel;
	/**
	 * Default public constructor class
	 * @param theTitle Legacy Tetris
	 * @throws HeadlessException
	 */
	public TetrisGui(final String theTitle) 
			throws HeadlessException {
		super(theTitle);
		
		myBoardView = new BoardView();
		myPreviewPanel = new PreviewPanel();
		myScorePanel = new ScorePanel();
		myMenuBar = new MenuBar();
		
		setUpComponents();
	}
	/**
	 * Set up the tetris game components
	 */
	private void setUpComponents() {
		
		JPanel tetrisGuiPanel;
		GridBagConstraints c;
		Dimension boardViewDimension, previewPanelDimension, scorePanelDimension;
		
		tetrisGuiPanel = new JPanel(new GridBagLayout());		
		tetrisGuiPanel.setBackground(ColorPalette.BASESTAR.getColor());	// DEBUG Remove me
		
		c = new GridBagConstraints();
		
		// set tetris panel subcomponent view dimensions
		boardViewDimension = new Dimension(DEFAULT_BOARD_VIEW_WIDTH, DEFAULT_BOARD_VIEW_HEIGHT);
		previewPanelDimension = new Dimension(DEFAULT_PREVIEW_PANEL_WIDTH, DEFAULT_PREVIEW_PANEL_HEIGHT);
		scorePanelDimension = new Dimension(DEFAULT_SCORE_PANEL_WIDTH, DEFAULT_SCORE_PANEL_HEIGHT);
		
		// set board dimensions
		myBoardView.setMinimumSize(boardViewDimension);
		myBoardView.setMaximumSize(boardViewDimension);
		myBoardView.setPreferredSize(boardViewDimension);
		
		// set preview panel dimensions
		myPreviewPanel.setMinimumSize(previewPanelDimension);
		myPreviewPanel.setMaximumSize(previewPanelDimension);
		myPreviewPanel.setPreferredSize(previewPanelDimension);
		
		// set score panel dimensions
		myScorePanel.setMinimumSize(scorePanelDimension);
		myScorePanel.setMaximumSize(scorePanelDimension);
		myScorePanel.setPreferredSize(scorePanelDimension);
		
		// set raised border with colors
		myBoardView.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, 
				ColorPalette.CYAN_TRON_LEGACY.getColor(), ColorPalette.PANE.getColor()));
		myPreviewPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
				ColorPalette.CYAN_TRON_LEGACY.getColor(), ColorPalette.PANE.getColor()));
		myScorePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,
				ColorPalette.CYAN_TRON_LEGACY.getColor(), ColorPalette.PANE.getColor()));
		 
		// set view subcomponent background colors
		myBoardView.setBackground(ColorPalette.BASESTAR.getColor());		// DEBUG Remove me
		myPreviewPanel.setBackground(ColorPalette.BASESTAR.getColor());	// DEBUG Remove me
		myScorePanel.setBackground(ColorPalette.BASESTAR.getColor());	// DEBUG Remove me
				
		// set grid bag constraints for score panel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 0;
		c.gridy = 0;
//		c.insets = new Insets(10, 10, 0, 0);
		tetrisGuiPanel.add(myScorePanel, c);
		
		// set grid bag constraints for board view
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 1;
		tetrisGuiPanel.add(myBoardView, c);
		
		// set grid bag constraints for preview panel
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		tetrisGuiPanel.add(myPreviewPanel, c);
		
		add(tetrisGuiPanel);
		
		setJMenuBar(myMenuBar);
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
		private static final String FILE = "File";
		private static final String NEW_GAME = "New Game";
		private static final String END_GAME = "End Game";
		private static final String SETTINGS = "Settings";
		private static final String HELP = "Help";
		private static final String EXIT = "Exit";
		private static final String CONTROLS = "Controls";
		private static final String HINT = "Hint";
		/**
		 * 
		 */
		MenuBar() {
			super();
			setUpComponents();
			setVisible(true);
			
		}
		/**
		 * 
		 */
		private void setUpComponents() {
			// File JMenu
			JMenu file = new JMenu(FILE);
			JMenu settings = new JMenu(SETTINGS);
			
			// File JMenuItems
			JMenuItem newGame = new JMenuItem(NEW_GAME);
			JMenuItem endGame = new JMenuItem(END_GAME);
			JMenuItem exit = new JMenuItem(EXIT);
			JMenuItem about = new JMenuItem(ABOUT);
			JMenuItem help = new JMenuItem(HELP);
			JMenuItem controls = new JMenuItem(CONTROLS);
			
			// Add mouse 
			newGame.addActionListener(this);
			endGame.addActionListener(this);
			settings.addActionListener(this);
			exit.addActionListener(this);
			about.addActionListener(this);
			help.addActionListener(this);
			controls.addActionListener(this);
			
			// Add JMenuItems to File
			file.add(newGame);
			file.add(endGame);
			file.add(exit);
			
			// Add JMenuItems to Settings 
			settings.add(about);
			settings.add(help);
			settings.add(controls);
			
			// Add JMenus to Menu Bar
			add(file);
			add(settings);
		}
		/**
		 * 
		 */
		@Override
		public void actionPerformed(ActionEvent theActionEvent) {
			// TODO Auto-generated method stub
			final String cmd = theActionEvent.getActionCommand();
			System.out.println(cmd); // TODO remove me
			switch(cmd) {
			case NEW_GAME : myBoardView.newGame(); break;
			case END_GAME : myBoardView.endGame(); break;
//			case SETTINGS : mySettingsPanel.show(); break;
			case ABOUT : JOptionPane.showMessageDialog(myBoardView, INFO, TITLE, JOptionPane.OK_OPTION); break;
			case HELP : JOptionPane.showMessageDialog(myBoardView, HINT, TITLE, JOptionPane.OK_OPTION); break;
			case EXIT : System.exit(0); break;
//			case CONTROLS : myControlPanel.show(); break;
			default : break;
			}
		}
	}
	/**
	 * Display the settings configuration menu panel
	 * TODO 
	 * @author ddxbugs
	 *
	 */
	class SettingsPanel extends JPanel {
		SettingsPanel() {
			super();
		}
	}
	/**
	 * Display the keyboard controller configuration menu panel
	 * @author ddxbugs
	 *
	 */
	class ControlPanel extends JPanel {
		ControlPanel() {
			super();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
