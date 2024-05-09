import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.util.Random;

public class OnePlayer extends Computer{
    //declare 
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

    public OnePlayer(){  //constructor
        //initalize objects

        //set up frame
        frame.setLayout(null);
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
        

        
        //frame.add(lblOpponents, BorderLayout.AFTER_LAST_LINE);

        btnReset.setText("Reset");
        btnReset.setHorizontalAlignment(JButton.CENTER);
        //TODO add action listener to reset the game
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
        System.out.println(cwd);
        rocket = new ImageIcon(cwd + "/rocket.jpg");
        dragon = new ImageIcon(cwd + "/dragon.jpg");
        
       
        buttonPanel.setSize(500, 500);
        buttonPanel.setLocation(50, 150);
        buttonPanel.setLayout(new GridLayout(3, 3));  // also from youtube video
        frame.add(buttonPanel);

        // set up button array
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                JButton button = new JButton();
                btnBoard[i][j] = button;
                buttonPanel.add(button);

                button.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        JButton tile = (JButton) e.getSource();  // don't understand this part.  took from youtube video
                        if (winCheck == false){
                        if (tile.getIcon() != rocket && tile.getIcon() != dragon){
                            // user's turn
                            if (gameCount % 2 == 0){
                                tile.setIcon(rocket);
                                gameCount++;
                                boardManager();
                                winCheck = winCheck(intBoard, player1);
                                }
        
                            // computer's turn
                                if (player2 == "Computer - Easy"){
                                    compMove = computerMoveEasy(intBoard);
                                }
                                else if (player2 == "Computer - Hard"){
                                    compMove = computerMoveHard(intBoard);
                                }
                                intBoard[compMove[0]][compMove[1]] = -1;
                                btnBoard[compMove[0]][compMove[1]].setIcon(dragon);
                                gameCount++;
                                System.out.println("Game count: " + gameCount);
                                winCheck = winCheck(intBoard, player2);
                                
                        }}
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

        if (diag1Sum == 3 || diag1Sum == -3 || diag2Sum == 3 || diag2Sum == -3){
            System.out.println(player + " is the winner!");
            return true;
        }
        for(int i = 0; i < 3; i++){
            horSum = board[i][0] + board[i][1] + board[i][2];
            verSum = board[0][i] + board[1][i] + board[2][i];

            if (horSum == 3 || verSum == 3 || horSum == -3 || verSum == -3){
                System.out.println(player + " is the winner!");
                return true;
            }
        }
        if (gameCount == 9){
            System.out.println("It's a tie!");
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

    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            System.out.println("Can't pause");
        }
    }
}

   


