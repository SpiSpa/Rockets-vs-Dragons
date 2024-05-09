import javax.swing.JOptionPane;


public class RDBoard {

    public static String playerNames(String playerNum){
        String player;
        int playerInt;
        int zeroCheck;

        //ask for players' names. 
            do {   
                player = JOptionPane.showInputDialog("Enter " + playerNum + " name.\n Name has a 10 character max.");
                playerInt = player.length();
                try{
                    zeroCheck = 10 / playerInt; // check if player enterd a name.  if not, error message pops up
                }
                catch (ArithmeticException e){
                    JOptionPane.showMessageDialog(null, "Name is empty, please enter a name");
                    playerInt = 0;
                }}
                while(playerInt == 0 || playerInt > 10);   // check that the name is not too long, if so, reprompt user
           
        return player;
    }

    public static void main(String[] args) {

        String player1;
        String player2;

        int gameOption;

        // player chooses one player with easy or hard move, or a two player game
        String[] buttons = {"One Player - Easy", "One Player - Hard", "Two Player"};
        gameOption = JOptionPane.showOptionDialog(null, "What type of game do you want?", "Game Choice", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);

        if (gameOption == 2){  // two player game
            rd_board_display board = new rd_board_display(); // instantitate class containing game board and functionality
            player1 = playerNames("Player 1");
            player2 = playerNames("Player 2");
            board.setPlayer1(player1);  // set player names on game board
            board.setPlayer2(player2);
        }

        else if (gameOption == 1){ // one player game, hard mode
            rd_board_display board = new rd_board_display();  // instantitate class containing game board and functionality
            player1 = playerNames("Player 1");
            player2 = "Computer - Hard";
            board.setPlayer1(player1); // set player names on game board
            board.setPlayer2(player2);
        }

        else if (gameOption == 0){ // one player game, easy mode
            rd_board_display board = new rd_board_display(); // instantitate class containing game board and functionality
            player1 = playerNames("Player 1");
            player2 = "Computer - Easy";
            board.setPlayer1(player1); // set player names on game board
            board.setPlayer2(player2);
        } 
    }
}
