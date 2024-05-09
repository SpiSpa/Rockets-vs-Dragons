/*This class is in charge of the computer's move.  Either the computer chooses an empty
 * spot randomly (easy) or checks the board and moves according to whether or not the user
 * or the comptuter is about to win.  
 */

import java.util.Random;

public class Computer {

    int i;  // row value in 3x3 grid
    int j;  // column value in 3x3 grid
    
    public int[] computerMoveEasy(int intBoard[][]){  // computer chooses a random empty space on the board
        
        Random rand = new Random();

        int i;
        int j;
        int[] move = new int[2];  // move variable returns a pair of numbers representing the location of the computer's move, {i, j}

        do {
        i = rand.nextInt(3);
        j = rand.nextInt(3);
        }
        while (intBoard[i][j] != 0); // check to see if the chosen space on the board is already occupied. if so, randomly choose another spot.
        move[0] = i;   // set up move array to return row and column value
        move[1] = j;

        return(move);
    }
    public int[] computerMoveHard(int intBoard[][]){
        
        Random rand = new Random();
        

        int i;
        int[] move = new int[2];  //two element array to return computer's move
        int sum;

        i = rand.nextInt(10);  //random # for computer move.  0-7, computer makes a good move.  8 and 9, computer makes a random move
        System.out.println("My random number is: " + i);
        if (i < 8){

        // computer checks to see if it can win. 
            for (i = 0; i < 3; i++){  
                sum = intBoard[i][0] + intBoard[i][1] + intBoard[i][2]; // add the values in each row
                if (sum == - 2){  // if the sum is equal to -2, the computer can win by placing their dragon in this row.  
                    for (j = 0; j < 3; j ++){
                        if (intBoard[i][j] == 0){  // find the empty space in the row
                            move[0] = i;
                            move[1] = j;
                            return move;
                        }
                    }
                }
            }
            for (j = 0; j < 3; j++){  // check each column
                sum = intBoard[0][j] + intBoard[1][j] + intBoard[2][j];
                if (sum == - 2){  // if the sum is equal to -2, the computer can win by placing their dragon in this column
                    for (i = 0; i < 3; i++){
                    if (intBoard[i][j] == 0){   // find the empty space in the column
                        move[0] = i;
                        move[1] = j;
                        System.out.println("Chose my smart move " + move[0] + move[1]);
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
                        System.out.println("Chose my smart move " + move[0] + move[1]);
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
                        System.out.println("Chose my smart move " + move[0] + move[1]);
                        return move;
                    }
                }
            }
// computer checks if user is about to win. 
            for (i = 0; i < 3; i++){  // check each row
                sum = intBoard[i][0] + intBoard[i][1] + intBoard[i][2];
                if (sum == 2){  // if sum of a row is equal to 2, the player will win with their next move, so we tell the computer to block.  
                    for (j = 0; j < 3; j ++){
                        if (intBoard[i][j] == 0){
                            move[0] = i;
                            move[1] = j;
                            System.out.println("Chose my smart move " + move[0] + move[1]);
                            return move;
                        }
                    }
                }
            }
            for (j = 0; j < 3; j++){  // check each column
                sum = intBoard[0][j] + intBoard[1][j] + intBoard[2][j];
                if (sum == 2){
                    for (i = 0; i < 3; i++){
                    if (intBoard[i][j] == 0){
                        move[0] = i;
                        move[1] = j;
                        System.out.println("Chose my smart move " + move[0] + move[1]);
                        return move;
                    }
                }
            }
        }
            // check diagonal 1 for win 
            sum = intBoard[0][0] + intBoard[1][1] + intBoard[2][2];
            if (sum == 2){
                for (i = 0; i < 3; i++){
                    if (intBoard[i][i] == 0){
                        move[0] = i;
                        move[1] = i;
                        System.out.println("Chose my smart move " + move[0] + move[1]);
                        return move;
                    }
                }
            }
            // check diagonal 2 for win
            sum = intBoard[0][2] + intBoard[1][1] + intBoard[2][0];
            if (sum == 2){
                for (i = 0; i < 3; i++){
                    if (intBoard[i][2-i] == 0){
                        move[0] = i;
                        move[1] = 2-i;
                        System.out.println("Chose my smart move " + move[0] + move[1]);
                        return move;
                    }
                }
            } 
        }
        // if the middle space is open, choose the middle
        if (intBoard[1][1] == 0){
            move[0] = 1;
            move[1] = 1;
            System.out.println("Chose my smart move " + move[0] + move[1]);
            return move;
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

    
