import javax.swing.JOptionPane;

public class RDBoard {

    
    public static void main(String[] args) {

        int gameOption;

        String[] buttons = {"One Player - Easy", "One Player - Hard", "Two Player"};
        gameOption = JOptionPane.showOptionDialog(null, "What type of game do you want?", "Game Choice", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[0]);
        System.out.println("game option: " + gameOption);

        if (gameOption == 2){
            rd_board_display board = new rd_board_display();
            
        }
        else if (gameOption == 1){
            System.out.println("Here's where we put the hard computer");
            System.exit(0);
        }
        else if (gameOption == 0){
            System.out.println("Here's where we put the easy computer");
            System.exit(0);
        }

        
        
    }
}
