import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class rd_board_display {
    //declare 
    int gameCount = 0;
    boolean winCheck = false;

    JFrame frame = new JFrame();
    Font titleFont = new Font("Verdana", Font.PLAIN, 22);
    JLabel lblTitle = new JLabel();
    
    JPanel bottomPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    ImageIcon rocket = new ImageIcon();
    ImageIcon dragon = new ImageIcon();

    JButton btnReset = new JButton();
    JButton btnQuit = new JButton();
    
    JButton [][] btnBoard = new JButton[3][3];
    int [][] intBoard = new int[3][3];  //  this keeps track of winners and helps the computer decide what to do
/* 
    String player1;
    String player2;
*/
    public rd_board_display(){  //constructor
        //initalize objects

        //set up frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Rockets vs. Dragons");
        frame.setSize(500, 500); 
        
        //set up title
        lblTitle.setFont(titleFont);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setText("Rockets vs. Dragons");
        frame.add(lblTitle, BorderLayout.NORTH);
         
        //set up Quit and Reset Button frame

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
        bottomPanel.add(btnReset);
        bottomPanel.add(btnQuit);

        //set up buttons, icons, and parallel array for game area and manager

        rocket = new ImageIcon("/Users/sarabellus/Desktop/Rockets_Dragons/rocket.jpg");
        dragon = new ImageIcon("/Users/sarabellus/Desktop/Rockets_Dragons/dragon.jpg");
       
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
                            if (gameCount % 2 == 0){
                                tile.setIcon(rocket);
                                gameCount++;
                                boardManager();
                                winCheck = winCheck(intBoard);
                                if (winCheck == true){
                                    System.out.println("Rockets Win!");
                                }
                            }
                            else {
                                tile.setIcon(dragon);
                                gameCount++;
                                boardManager();
                                winCheck = winCheck(intBoard);
                                if (winCheck == true){
                                    System.out.println("Dragons Win!");

                            }
                        }}
                        
                    }
            }});
            }
        }
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
                System.out.println("row: " + i + " column: " + j + " board value: "  + intBoard[i][j]);
            }
        }
        return intBoard;
    }

    public boolean winCheck(int[][] board){
        int horSum;
        int verSum;
        int diag1Sum;
        int diag2Sum;

        diag1Sum = board[0][0] + board[1][1] + board[2][2];
        diag2Sum = board[0][2] + board[1][1] + board [2][0];

        if (diag1Sum == 3 || diag1Sum == -3 || diag2Sum == 3 || diag2Sum == -3){
            return true;
        }
        for(int i = 0; i < 3; i++){
            horSum = board[i][0] + board[i][1] + board[i][2];
            verSum = board[0][i] + board[1][i] + board[2][i];

            if (horSum == 3 || verSum == 3 || horSum == -3 || verSum == -3){
                return true;
            }
        }
        return false;
    }
}

   

