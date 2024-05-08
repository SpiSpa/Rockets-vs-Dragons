import java.util.Random;

public class ComputerEasy {

    int i;
    int j;
    
    public int[] computerMove(int intBoard[][]){
        
        System.out.println("Called computer move function");
        Random rand = new Random();

        int i;
        int j;
        int[] move = new int[2];

        do {
        i = rand.nextInt(3);
        j = rand.nextInt(3);
        }
        while (intBoard[i][j] != 0);
        move[0] = i;
        move[1] = j;

        return(move);
    }}

