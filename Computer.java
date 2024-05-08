import java.util.Random;

public class Computer {

    int i;
    int j;
    
    public int[] computerMoveEasy(int intBoard[][]){
        
        System.out.println("Called computer move  Easy function");
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
    }
    public int[] computerMoveHard(int intBoard[][]){
        
        System.out.println("Called computer move Hard function");
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

    
