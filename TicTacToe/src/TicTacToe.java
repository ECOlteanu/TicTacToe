import java.awt.*;															//imports the set of instruments for frames (Abstract Window Toolkit) platform dependent
import java.awt.event.*;													//imports that provide interfaces and classes for dealing with different types of events received from AWT components
import java.util.*;															//imports that are needed for the random class to be used
import javax.swing.*;														//imports the set of instruments for JPane 

public class TicTacToe implements ActionListener{							//implements the interface of ActionListner must have awt.event imported in order to call upon this component
	
//----------initialization of the elements that are used further on--------
	
	Random  random = new Random();											//instance of the random class will be used to determinate the player that goes first
	JFrame frame = new JFrame();											//creates a frame
	JPanel title_panel = new JPanel();										//creates a panel to hold the title
	JPanel button_panel = new JPanel();										//creates a panel to hold the buttons
	JLabel textfield = new JLabel();										//creates a text field to display messages
	JButton[] buttons = new JButton[9];										//creates an array of 9 buttons
	boolean player1_turn;													//declaration of the boolean variable used to determinate players turn
	
//---------------------- constructor for the class  ----------------------
	TicTacToe(){
//---------------------- frame configuration -----------------------------		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				//sets the action that will be performed at the press of the exit button "X"
		frame.setSize(800, 800);											//sets the dimensions of the frame 
		frame.getContentPane().setBackground(new Color(50, 50, 50));		//sets the background color of the frame
		frame.setLayout(new BorderLayout());								//sets a border to the frame
		frame.setVisible(true);												//sets the visibility of the frame
	
//----------------------- text field configuration -----------------------		
		textfield.setBackground(new Color(25, 25, 25));						//sets the background color of the text field
		textfield.setForeground(new Color(25, 255, 0));						//sets the text color
		textfield.setFont(new Font("Ink Free", Font.BOLD, 75));				//sets the text font ,stile and size 
		textfield.setHorizontalAlignment(JLabel.CENTER);					//sets the horizontal alignment of the text field
		textfield.setText("Tic-Tac-Toe");									//sets the text 
		textfield.setOpaque(true);											//sets the text field visible
		
//-----------------------title panel configuration -----------------------		
		title_panel.setLayout(new BorderLayout());							//sets the border layout of the title panel
		title_panel.setBounds(0, 0, 800, 100);								//sets the position of the title panel on the frame
		
//----------------------button panel configuration -----------------------		
		button_panel.setLayout(new GridLayout(3, 3));						//sets the layout of the button panel
		button_panel.setBackground(new Color(150, 150, 150));				//sets the background of the button panel
		
//--------------configuration of the buttons using a for loop-------------		
		for(int i = 0; i < 9; i++) {
			buttons[i] = new JButton();										//for each index of the array a new button is created
			button_panel.add(buttons[i]);									//adding each button on the button panel
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));		//sets the font, style and size of the font of the buttons
			buttons[i].setFocusable(false);									//sets the focusability of the button 
			buttons[i].addActionListener(this);								//register the button on index i of the array with the ActionListener
			
			}

		title_panel.add(textfield);											//adding the text field to the title panel
		frame.add(title_panel, BorderLayout.NORTH);							//adding the title panel to the frame,setting the position 
		frame.add(button_panel);											//adding the button panel to the frame
		
		firstTurn();														//calling the firstTurn method to determinate who's the first player
		}
	@Override
	public void actionPerformed(ActionEvent e) {							//override of the actionPerforme method
		for(int i = 0; i < 9; i++) {										//for loop in order to check if one of the 9 buttons is clicked
			if(e.getSource() == buttons[i]) {								//condition that one of the buttons is pressed 
				if(player1_turn == true) {									//if statement that check if is X turn
					if(buttons[i].getText() == "") {						//if statement that checks if the button was click before, does that by using a getter
						buttons[i].setForeground(new Color(255, 0, 0));		//setter that will change the text color to red when the button is click 
						buttons[i].setText("X");							//setter that will change the text of the button from "" to "X"
						player1_turn = false;								//changing the boolean variable to false
						textfield.setText("O turn");						//changing the text of the textfield
						check(); 											//calling the check function
					
						}
					}
			else {															//else statement for O turn
						if(buttons[i].getText() == "") {					//if statement that checks if the button was click before, does that by using a getter
							buttons[i].setForeground(new Color(0, 0, 255));	//condition that one of the buttons is pressed 
							buttons[i].setText("O");						//setter that will change the text color to red when the button is click 
							player1_turn = true;							//changing the boolean variable to true
							textfield.setText("X turn");					//changing the text of the textfield
							check(); 										//calling the check function
						}					
			}
			}
		}
}

/*-------------- Method to set the first player to start the game--------------
 *---------- Adding a delay of 2 second befor applaying the method ------------
 *----------- In order for the initial text of the label to be visible --------
 */
	public void firstTurn() {										
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (random.nextInt(2) == 0) {
		player1_turn = true;
		textfield.setText("X turn");
		}
		else {
		player1_turn = false;
		textfield.setText("O turn");
		}
	}
//--------------------- check draw----------------------------

	public void check() {
        int i = 0;
        while (buttons[i].getText() != "") {
            if (i == buttons.length - 1) {
                draw();
                break;
            }
            i++;
        }
/*------------------ check X win conditions -------------------
 * ---------- using the if statements will check --------------
 *---------  all the wining combinations possible -------------
 *------------- this is done by using getters -----------------
 */

		if (
				(buttons[0].getText() == "X")&&
				(buttons[1].getText() == "X")&&
				(buttons[1].getText() == "X")
			) {
			xWins(0,1,2);
		}
		if (
				(buttons[3].getText() == "X")&&
				(buttons[4].getText() == "X")&&
				(buttons[5].getText() == "X")
			) {
			xWins(3,4,5);	
		}
		if (
				(buttons[6].getText() == "X")&&
				(buttons[7].getText() == "X")&&
				(buttons[8].getText() == "X")
			) {
			xWins(6,7,8);	
		}		
		if (
				(buttons[0].getText() == "X")&&
				(buttons[3].getText() == "X")&&
				(buttons[6].getText() == "X")
			) {
			xWins(0,3,6);	
		}		
		if (
				(buttons[1].getText() == "X")&&
				(buttons[4].getText() == "X")&&
				(buttons[7].getText() == "X")
			) {
			xWins(1,4,7);	
		}	
		if (
				(buttons[2].getText() == "X")&&
				(buttons[5].getText() == "X")&&
				(buttons[8].getText() == "X")
			) {
			xWins(2,5,8);	
		}		
		if (
				(buttons[0].getText() == "X")&&
				(buttons[4].getText() == "X")&&
				(buttons[8].getText() == "X")
			) {
			xWins(0,4,8);	
		}
		if (
				(buttons[2].getText() == "X")&&
				(buttons[4].getText() == "X")&&
				(buttons[6].getText() == "X")
			) {
			xWins(2,4,6);	
		}		
	
//-----------------check O win conditions------------------------
		if (
				(buttons[0].getText() == "O")&&
				(buttons[1].getText() == "O")&&
				(buttons[2].getText() == "O")
			) {
			oWins(0,1,2);
		}
		if (
				(buttons[3].getText() == "O")&&
				(buttons[4].getText() == "O")&&
				(buttons[5].getText() == "O")
			) {
			oWins(3,4,5);	
		}
		if (
				(buttons[6].getText() == "O")&&
				(buttons[7].getText() == "O")&&
				(buttons[8].getText() == "O")
			) {
			oWins(6,7,8);	
		}		
		if (
				(buttons[0].getText() == "O")&&
				(buttons[3].getText() == "O")&&
				(buttons[6].getText() == "O")
			) {
			oWins(0,3,6);	
		}		
		if (
				(buttons[1].getText() == "O")&&
				(buttons[4].getText() == "O")&&
				(buttons[7].getText() == "O")
			) {
			oWins(1,4,7);	
		}	
		if (
				(buttons[2].getText() == "O")&&
				(buttons[5].getText() == "O")&&
				(buttons[8].getText() == "O")
			) {
			oWins(2,5,8);	
		}		
		if (
				(buttons[0].getText() == "O")&&
				(buttons[4].getText() == "O")&&
				(buttons[8].getText() == "O")
			) {
			oWins(0,4,8);	
		}
		if (
				(buttons[2].getText() == "O")&&
				(buttons[4].getText() == "O")&&
				(buttons[6].getText() == "O")
			) {
			oWins(2,4,6);	
		}				
	}
	
//-------- Method that determinate what happens if it's a draw -----------
	
	public void draw() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("Game is draw!");
    }
//-------- Method that determinate what happens if X wins ---------------- 
	public void xWins(int a, int b, int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
			}
		textfield.setText("X wins");
		}

//-------- Method that determinate what happens if X wins ---------------- 	
	public void oWins(int a, int b, int c) {
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		
		for(int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
			}
		textfield.setText("O wins");
		}
	}
	
	

