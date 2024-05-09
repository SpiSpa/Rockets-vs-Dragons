import javax.swing.JOptionPane;


public class RDBoard {

    public static String playerNames(String playerNum){
        String player;
        int playerInt;
        int zeroCheck;

            do {   
                player = JOptionPane.showInputDialog("Enter " + playerNum + " name.\n Name has a 10 character max.");
                playerInt = player.length();
                try{
                    zeroCheck = 10 / playerInt;
                }
                catch (ArithmeticException e){
                    JOptionPane.showMessageDialog(null, "Name is empty, please enter a name");
                    playerInt = 0;
                }}
                while(playerInt == 0 || playerInt > 10);  
           
        return player;
    }

    public static void main(String[] args) {

        String player1;
        String player2;

        int gameOption;

        String[] buttons = {"One Player - Easy", "One Player - Hard", "Two Player"};
        gameOption = JOptionPane.showOptionDialog(null, "What type of game do you want?", "Game Choice", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
        System.out.println("game option: " + gameOption);

        if (gameOption == 2){
            rd_board_display board = new rd_board_display();
            player1 = playerNames("Player 1");
            player2 = playerNames("Player 2");
            board.setPlayer1(player1);
            board.setPlayer2(player2);
            System.out.println("Player 1: " + player1 + "Player 2: " + player2);
        }

        else if (gameOption == 1){
            rd_board_display board = new rd_board_display();
            player1 = playerNames("Player 1");
            player2 = "Computer - Hard";
            board.setPlayer1(player1);
            board.setPlayer2(player2);
            System.out.println("Player 1: " + player1 + "Player 2: " + player2);
        }
        else if (gameOption == 0){
            rd_board_display board = new rd_board_display();
            player1 = playerNames("Player 1");
            player2 = "Computer - Easy";
            board.setPlayer1(player1);
            board.setPlayer2(player2);
            System.out.println("Player 1: " + player1 + "Player 2: " + player2);
        } 
    }
}
