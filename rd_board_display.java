import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;

public class rd_board_display extends Computer{
    //declare variables
    int gameCount = 0;    
    boolean winCheck = false;
    int[] compMove;  

    JFrame frame = new JFrame();
    Font titleFont = new Font("Verdana", Font.PLAIN, 36);
    JLabel lblTitle = new JLabel();
    JLabel lblOpponents = new JLabel();
    
    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    String cwd;
    ImageIcon rocket = new ImageIcon();
    ImageIcon dragon = new ImageIcon();

    JButton btnReset = new JButton();
    JButton btnQuit = new JButton();
    
    JButton [][] btnBoard = new JButton[3][3];
    int [][] intBoard = new int[3][3];  //  this keeps track of winners and helps the computer decide what to do

    String player1;
    String player2;

    public rd_board_display(){  //constructor
        //initalize objects

        //set up frame
        frame.setLayout(null);   // setting this to null allows us to manually place our objects. 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Rockets vs. Dragons");
        frame.setSize(600, 800); 
        
        //set up title and opponent labels
        player1 = "Player 1";
        player2 = "Player 2";
        lblTitle.setFont(titleFont);
        lblTitle.setBounds(100, 25, 500, 50);
        lblTitle.setText("Rockets vs. Dragons");
        lblOpponents.setBounds(250, 75, 500, 20);
        lblOpponents.setText(player1 + " vs. " + player2);
        
        topPanel.setSize(600, 100);
        topPanel.setOpaque(true);
        topPanel.setBackground(Color.orange);
        topPanel.setLayout(null);
        topPanel.add(lblOpponents);
        topPanel.add(lblTitle);

        // add reset button and functionality
        btnReset.setText("Reset");
        btnReset.setHorizontalAlignment(JButton.CENTER);
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                for (int i = 0; i < 3; i++){
                    for (int j = 0; j < 3; j++){
                        intBoard[i][j] = 0;
                        btnBoard[i][j].setIcon(null);
                        lblTitle.setText("Rockets vs. Dragons");
                    }
                gameCount = 0;
                winCheck = false;
                };
            }
        });

        // add quit button and functionality
        btnQuit.setText("Quit");
        btnQuit.setHorizontalAlignment(JButton.CENTER);
        btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        bottomPanel.setSize(500, 100);
        bottomPanel.setLocation(50, 700);
        bottomPanel.add(btnReset);
        bottomPanel.add(btnQuit);

        //set up buttons, icons, and parallel array for game area and manager
        cwd = System.getProperty("user.dir");
        rocket = new ImageIcon(cwd + "/rocket.jpg");
        dragon = new ImageIcon(cwd + "/dragon.jpg");
       
        buttonPanel.setSize(500, 500);
        buttonPanel.setLocation(50, 150);
        buttonPanel.setLayout(new GridLayout(3, 3));  // also from youtube video
        frame.add(buttonPanel);

        // set up button array and functionality for the dragons and rockets (x's and o's)
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                JButton button = new JButton();
                btnBoard[i][j] = button;
                buttonPanel.add(button);  // creates and adds each button from 0,0 to 2,2

                button.addActionListener(new ActionListener(){  // adds the button functionality
                    public void actionPerformed(ActionEvent e) {
                        JButton tile = (JButton) e.getSource();  //  borrowed from here: https://www.youtube.com/watch?v=Nc77ymnm8Ss
                        if (winCheck == false){
                            if (tile.getIcon() != rocket && tile.getIcon() != dragon){  // only allow user to press the button if it's blank
                                // user's turn
                                if (gameCount % 2 == 0){  // if it's rockets turn, then gameCount is even.  
                                    tile.setIcon(rocket);  // put a rocket on the button
                                    gameCount++;
                                    boardManager(); // update the intBoard see board manager function for more info. 
                                    winCheck = winCheck(intBoard, player1);
                                    if (winCheck == true){
                                        return;
                                    }
                                    if (player2 == "Computer - Easy" || player2 == "Computer - Hard"){ // check if this is a one player or two player game
                                        if (player2 == "Computer - Easy"){ 
                                            compMove = computerMoveEasy(intBoard);
                                        }
                                        else if (player2 == "Computer - Hard"){
                                            compMove = computerMoveHard(intBoard);
                                        }

                                        intBoard[compMove[0]][compMove[1]] = -1;
                                        btnBoard[compMove[0]][compMove[1]].setIcon(dragon);
                                        gameCount++;
                                        winCheck = winCheck(intBoard, player2);
                                        return;
                                    }   
                                }   
                                    
                                else{  // this only executes if it's a two player game. 
                                    tile.setIcon(dragon);
                                    gameCount++;
                                    boardManager();
                                    winCheck = winCheck(intBoard, player2);
                                    while (winCheck == true){
                                        return;   
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    public int[][] boardManager(){
        // create array of ints to track wins.  1 for rockets, -1 for dragons, and 0 for empty space.
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (btnBoard[i][j].getIcon() == rocket){
                    intBoard[i][j] = 1;
                }
                else if (btnBoard[i][j].getIcon() == dragon){
                    intBoard[i][j] = -1;
                }
                else{
                    intBoard[i][j] = 0;
                }
            }
        }
        return intBoard;
    }

    public boolean winCheck(int[][] board, String player){
        int horSum;
        int verSum;
        int diag1Sum;
        int diag2Sum;

        diag1Sum = board[0][0] + board[1][1] + board[2][2];
        diag2Sum = board[0][2] + board[1][1] + board [2][0];

        // if the sum of a row, column or diagonal is equal to 3 or -3, there is a winner.  Current player is passed to function. 
    
        if (diag1Sum == 3 || diag1Sum == -3 || diag2Sum == 3 || diag2Sum == -3){  // checks diagonal sums
            if (diag1Sum == 3 || diag1Sum == -3){
            }
            lblTitle.setText(player + " wins!");
            writeToFile(player1, player2, player);
            return true;
        }
        for(int i = 0; i < 3; i++){ // checks horizontal and vertial sums
            horSum = board[i][0] + board[i][1] + board[i][2];
            verSum = board[0][i] + board[1][i] + board[2][i];

            if (horSum == 3 || verSum == 3 || horSum == -3 || verSum == -3){
                lblTitle.setText(player + " wins!");
                writeToFile(player1, player2, player);
                return true;
            }
        }
        if (gameCount == 9){  // checks if all the squares are filled.  game count goes up by one everytime a move is made, so game count = 9 means all squares are filled
            lblTitle.setText("It's a tie!");
            player = "Tie";
            writeToFile(player1, player2, player);
            return true;
        }
        return false;
    }

    public String getPlayer1(){
        return player1;
    }
    public void setPlayer1(String name){
        this.player1 = name;
        lblOpponents.setText(player1 + " vs. " + player2);
    }

    public String getPlayer2(){
        return player2;
    }
    public void setPlayer2(String name){
        this.player2 = name;
        lblOpponents.setText(player1 + " vs. " + player2);
    }

    public void writeToFile(String player1, String player2, String winner){
    String result;
    result = ("Player 1: " + player1 + " vs. Player 2: " + player2 + "\tWinner: " + winner );
    try {
        FileWriter fileWriter = new FileWriter("rockets_vs_dragons_results.txt\n", true);
        fileWriter.write("\n" + result);
        fileWriter.close();
        }
        catch (Exception e){
        e.getStackTrace();
        }
    }
}

   

