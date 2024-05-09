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
        int[] move = new int[2];  //two element array to return computer's move
        int sum;

        i = rand.nextInt(10);  //random # for computer move.  0-7, computer makes a good move.  8 and 9, computer makes a random move
        System.out.println("my random number is: " + i);
        if (i < 8){
        // computer checks to see if it can win. 
            for (i = 0; i < 3; i++){  // check each row
                sum = intBoard[i][0] + intBoard[i][1] + intBoard[i][2];
                if (sum == - 2){
                    for (j = 0; j < 3; j ++){
                        if (intBoard[i][j] == 0){
                            move[0] = i;
                            move[1] = j;
                            System.out.println("Chose my smart move" + move[0] + move[1]);
                            return move;
                        }
                    }
                }
            }
            for (j = 0; j < 3; j++){  // check each column
                sum = intBoard[0][j] + intBoard[1][j] + intBoard[2][j];
                if (sum == - 2){
                    for (i = 0; i < 3; i++){
                    if (intBoard[i][j] == 0){
                        move[0] = i;
                        move[1] = j;
                        System.out.println("Chose my smart move" + move[0] + move[1]);
                        return move;
                    }
                }
            }
        }
            // check diagonal 1 for win 
            sum = intBoard[0][0] + intBoard[1][1] + intBoard[2][2];
            if (sum == -2){
                for (i = 0; i < 3; i++){
                    if (intBoard[i][i] == 0){
                        move[0] = i;
                        move[1] = i;
                        System.out.println("Chose my smart move" + move[0] + move[1]);
                        return move;
                    }
                }
            }
            // check diagonal 2 for win
            sum = intBoard[0][2] + intBoard[1][1] + intBoard[2][0];
            if (sum == -2){
                for (i = 0; i < 3; i++){
                    if (intBoard[i][2-i] == 0){
                        move[0] = i;
                        move[1] = 2-i;
                        System.out.println("Chose my smart move" + move[0] + move[1]);
                        return move;
                    }
                }
            }

            
        }
        // just choose a random number if the computer randomly chose 8 or 9. 
        do {
            i = rand.nextInt(3);
            j = rand.nextInt(3);
             }
            while (intBoard[i][j] != 0);
            move[0] = i;
            move[1] = j;
        
            return(move);
        }


    }

    
