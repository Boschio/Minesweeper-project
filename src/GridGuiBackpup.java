import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GridGuiBackpup extends JFrame {
	
	public GridGuiBackpup() {
		
		Grid grid = new Grid();
		
		JPanel mainPanel = new JPanel(new GridLayout(1,1,25,10));
		JPanel bombPanel = new JPanel();
		JPanel countPanel = new JPanel();
		
		bombPanel.setLayout(new GridLayout(grid.getNumRows(), grid.getNumColumns(),10,10));
		countPanel.setLayout(new GridLayout(grid.getNumRows(), grid.getNumColumns(),10,10));
		
		mainPanel.add(bombPanel);
		mainPanel.add(countPanel);
		
		JButton[][] bombButtons = new JButton[grid.getNumRows()][grid.getNumColumns()];
		JButton[][] countButtons = new JButton[grid.getNumRows()][grid.getNumColumns()];
		
		// BombGrid buttons
		for (int row = 0; row < grid.getNumRows(); ++row) {
			for (int col = 0; col < grid.getNumRows(); ++col) {

				final int ID = row; // button ID
				final int ID2 = col;

				bombButtons[ID][ID2] = new JButton();
				countButtons[ID][ID2] = new JButton();
				
				// JButton button = new JButton(String.valueOf(grid.getBombGrid()[ID][ID2]));
				bombButtons[ID][ID2].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						if (grid.isBombAtLocation(ID, ID2)) {
							bombButtons[ID][ID2].setText((grid.getBombGrid()[ID][ID2]) ? "bomb" : "no bomb");
							bombButtons[ID][ID2].setEnabled(false);

							countButtons[ID][ID2].setText(String.valueOf(((grid.getCountAtLocation(ID,ID2)))));
							
							countButtons[ID][ID2].setSelected(true);
							if (bombButtons[ID][ID2].getActionListeners() != null) {
								
							}
							for (int x = 0; x < grid.getNumRows(); ++x) {
								for (int y = 0; y < grid.getNumRows(); ++y) {
									bombButtons[x][y].setText((grid.getBombGrid()[x][y]) ? "bomb" : "no bomb");
									bombButtons[x][y].setEnabled(false);
									countButtons[x][y].setText(String.valueOf(((grid.getCountAtLocation(x,y)))));
									countButtons[x][y].setEnabled(false);
								}
							}
							
							loseScreen();

						} else if (!grid.isBombAtLocation(ID, ID2)) {
							bombButtons[ID][ID2].setText(!(grid.getBombGrid()[ID][ID2]) ? "no bomb" : "bomb");
							bombButtons[ID][ID2].setEnabled(false);
							
							countButtons[ID][ID2].setEnabled(false);
							countButtons[ID+1][ID2].setEnabled(false);
							countButtons[ID+1][ID2+1].setEnabled(false);
							countButtons[ID][ID2-1].setEnabled(false);
							countButtons[ID][ID2+1].setEnabled(false);
							countButtons[ID-1][ID2].setEnabled(false);
							countButtons[ID-1][ID2-1].setEnabled(false);
							countButtons[ID-1][ID2+1].setEnabled(false);
							countButtons[ID+1][ID2-1].setEnabled(false);
							
							countButtons[ID][ID2].setText(String.valueOf(((grid.getCountAtLocation(ID,ID2)))));
							countButtons[ID+1][ID2].setText(String.valueOf(((grid.getCountAtLocation(ID+1,ID2)))));
							countButtons[ID+1][ID2+1].setText(String.valueOf(((grid.getCountAtLocation(ID+1,ID2+1)))));
							countButtons[ID][ID2-1].setText(String.valueOf(((grid.getCountAtLocation(ID,ID2-1)))));
							countButtons[ID][ID2+1].setText(String.valueOf(((grid.getCountAtLocation(ID,ID2+1)))));
							countButtons[ID-1][ID2].setText(String.valueOf(((grid.getCountAtLocation(ID-1,ID2)))));
							countButtons[ID-1][ID2-1].setText(String.valueOf(((grid.getCountAtLocation(ID-1,ID2-1)))));
							countButtons[ID-1][ID2+1].setText(String.valueOf(((grid.getCountAtLocation(ID-1,ID2+1)))));
							countButtons[ID+1][ID2-1].setText(String.valueOf(((grid.getCountAtLocation(ID+1,ID2-1)))));
							
						}
					}
				});
				bombPanel.add(bombButtons[ID][ID2], BorderLayout.EAST);
				countPanel.add(countButtons[ID][ID2], BorderLayout.WEST);

			}
			
		}
		
		add(mainPanel);
		setTitle("Minesweeper");
		setSize(1920,1080);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void loseScreen() {
		
		int choice = JOptionPane.showConfirmDialog(null, "Boom! You lose!\nWould you like to try again?", "Minesweeper", JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.NO_OPTION){
		   System.exit(0);
		} else {
			new GridGui();
		}
	}

	public static void main(String[] args) {
		
		new GridGui();
		
		
	}

}

/*
bombButtons[ID][ID2].addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent event) {
		if (grid.isBombAtLocation(ID, ID2) && SwingUtilities.isLeftMouseButton()) {
			bombButtons[ID][ID2].setText((grid.getBombGrid()[ID][ID2]) ? "bomb" : "no bomb");
			bombButtons[ID][ID2].setEnabled(false);

			bombButtons[ID][ID2].setText(String.valueOf(((grid.getCountAtLocation(ID,ID2)))));
			
			bombButtons[ID][ID2].setSelected(true);
			if (bombButtons[ID][ID2].getActionListeners() != null) {
				
			}
			for (int x = 0; x < grid.getNumRows(); ++x) {
				for (int y = 0; y < grid.getNumRows(); ++y) {
					bombButtons[x][y].setText((grid.getBombGrid()[x][y]) ? "bomb" : String.valueOf(((grid.getCountAtLocation(x,y)))));
					bombButtons[x][y].setEnabled(false);
					
				}
			}
			
			loseScreen();

		} else if (!grid.isBombAtLocation(ID, ID2) && SwingUtilities.isLeftMouseButton(null)) {
			bombButtons[ID][ID2].setText(!(grid.getBombGrid()[ID][ID2]) ? "no bomb" : "bomb");
			bombButtons[ID][ID2].setEnabled(false);

			bombButtons[ID][ID2].setText(String.valueOf(((grid.getCountAtLocation(ID,ID2)))));
			bombButtons[ID+1][ID2].setText(String.valueOf(((grid.getCountAtLocation(ID+1,ID2)))));
			bombButtons[ID+1][ID2+1].setText(String.valueOf(((grid.getCountAtLocation(ID+1,ID2+1)))));
			bombButtons[ID][ID2-1].setText(String.valueOf(((grid.getCountAtLocation(ID,ID2-1)))));
			bombButtons[ID][ID2+1].setText(String.valueOf(((grid.getCountAtLocation(ID,ID2+1)))));
			bombButtons[ID-1][ID2].setText(String.valueOf(((grid.getCountAtLocation(ID-1,ID2)))));
			bombButtons[ID-1][ID2-1].setText(String.valueOf(((grid.getCountAtLocation(ID-1,ID2-1)))));
			bombButtons[ID-1][ID2+1].setText(String.valueOf(((grid.getCountAtLocation(ID-1,ID2+1)))));
			bombButtons[ID+1][ID2-1].setText(String.valueOf(((grid.getCountAtLocation(ID+1,ID2-1)))));
			
		}
	}
}); */
