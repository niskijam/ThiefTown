package ThiefTown;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GuiInterface extends JFrame implements ActionListener {
	private ThiefTown t;
	private PositionMarker[][] board;
	JPanel grid;
	private boolean firstDraw;	
	public GuiInterface g;
	
	/**
	 * Creates the GuiInterface
	 */
	public GuiInterface() {
		g = this;
		t = new ThiefTown(9, 1, this);
		board = t.getBoard();
		firstDraw = true;

		initUI();
	
		setVisible(true);
		setSize(800, 600);
		setTitle("Thief Town");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	/**
	 * Initialized the graphics
	 */
	public void initUI() {
		if (!this.firstDraw) remove(grid);
		
		if (t.playersAlive() != 2)
		{
			setVisible(false);
			int winner = 0;
			for (int r = 0; r < board.length; r++)
			{
				for (int c = 0; c < board[0].length; c++)
				{
					if (board[r][c].isPlayer)
					{
						winner = board[r][c].playerNum;
					}
				}
			}
			if (winner == 0)
			{
				if (t.getP1NpcsKilled() < t.getP2NpcsKilled())
				{
					winner = 1;
				}
				else if (t.getP2NpcsKilled() < t.getP1NpcsKilled())
				{
					winner = 2;
				}
				if (winner != 0)
				{
					System.out.println("PLAYER " + winner + " WINS BY KILLING LESS INNOCENTS!");
				}
				else if (t.getP1NpcsKilled() == t.getP2NpcsKilled())
				{
					System.out.println("IT'S A DRAW!");
				}
			}
			else
			{
				System.out.println("PLAYER " + winner + " WINS!");
			}
			System.exit(0);
		}
		
		grid = new JPanel();
		grid.setLayout(new GridLayout(10, 9, 5, 5));


		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[0].length; j++) {
				JLabel temp = new JLabel(board[i][j].toString(), (int)Component.CENTER_ALIGNMENT);
				temp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				grid.add(temp);
			}
		}
		
		//move up
		JButton up = new JButton("W");
		up.addActionListener(this);
		up.getInputMap(up.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "up");
		up.getActionMap().put("up", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				g.actionPerformed(e);
			}
		});
		up.setVisible(true);
		grid.add(up);
		
		//move left
		JButton left = new JButton("A");
		left.addActionListener(this);
		left.getInputMap(up.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "left");
		left.getActionMap().put("left", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				g.actionPerformed(e);
			}
		});
		left.setVisible(true);
		grid.add(left);
		
		//move down
		JButton down = new JButton("S");
		down.addActionListener(this);
		down.getInputMap(up.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "down");
		down.getActionMap().put("down", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				g.actionPerformed(e);
			}
		});
		down.setVisible(true);
		grid.add(down);
		
		//move right
		JButton right = new JButton("D");
		right.addActionListener(this);
		right.getInputMap(up.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "right");
		right.getActionMap().put("right", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				g.actionPerformed(e);
			}
		});
		right.setVisible(true);
		grid.add(right);
		
		//stay still
		JButton stay = new JButton("X");
		stay.addActionListener(this);
		stay.getInputMap(up.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0), "stay");
		stay.getActionMap().put("stay", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				g.actionPerformed(e);
			}
		});
		stay.setVisible(true);
		grid.add(stay);
		
		//attack up
		JButton attUp = new JButton("I");
		attUp.addActionListener(this);
		attUp.getInputMap(up.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0), "attUp");
		attUp.getActionMap().put("attUp", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				g.actionPerformed(e);
			}
		});
		attUp.setVisible(true);
		grid.add(attUp);
		
		//attack left
		JButton attLeft = new JButton("J");
		attLeft.addActionListener(this);
		attLeft.getInputMap(up.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_J, 0), "attLeft");
		attLeft.getActionMap().put("attLeft", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				g.actionPerformed(e);
			}
		});
		attLeft.setVisible(true);
		grid.add(attLeft);
		
		//attack down
		JButton attDown = new JButton("K");
		attDown.addActionListener(this);
		attDown.getInputMap(up.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_K, 0), "attDown");
		attDown.getActionMap().put("attDown", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				g.actionPerformed(e);
			}
		});
		attDown.setVisible(true);
		grid.add(attDown);
		
		//attack down
		JButton attRight = new JButton("L");
		attRight.addActionListener(this);
		attRight.getInputMap(up.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0), "attRight");
		attRight.getActionMap().put("attRight", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				g.actionPerformed(e);
			}
		});
		attRight.setVisible(true);
		grid.add(attRight);
		
		add(grid);

		if (firstDraw) {
			firstDraw = false;
			//System.out.println("bottom kek");
		} else {
			//System.out.println("kek");
			revalidate();
		}
	}



	public static void main(String[] args) {
		GuiInterface ex = new GuiInterface();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton source = (JButton)arg0.getSource();
		String input = source.getLabel();
		//System.out.println("button pushed " + input);
		
		if (input.equalsIgnoreCase("w"))
		{
			t.move(-1, 0);
		}
		else if (input.equalsIgnoreCase("a"))
		{
			t.move(0, -1);
		}
		else if (input.equalsIgnoreCase("s"))
		{
			t.move(1, 0);
		}
		else if (input.equalsIgnoreCase("d"))
		{
			t.move(0, 1);
		}
		else if (input.equalsIgnoreCase("x"))
		{
			t.move(0, 0);
		}
		else if (input.equalsIgnoreCase("i"))
		{
			t.attack(-1, 0);
		}
		else if (input.equalsIgnoreCase("j"))
		{
			t.attack(0, -1);
		}
		else if (input.equalsIgnoreCase("k"))
		{
			t.attack(1, 0);
		}
		else if (input.equalsIgnoreCase("l"))
		{
			t.attack(0, 1);
		}
		else if (input.equalsIgnoreCase("x"))
		{
			t.move(0, 0);
		}
	}
}
