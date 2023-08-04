import java.io.*;
import java.util.*;

class Solver {
    public static void main(String[] args) throws IOException {
        boolean generate = true;
        if(args.length > 0 && args[0].equals("custom"))
        {
            generate = false; // grabs board from "Board.txt"
        }

        int[][] grid  = new int[9][9];

        if(generate)
        {
            System.out.println("Generating...");
            // TODO: generate 9x9 sudoku board (easy)
        }
        else {
            String line;
            BufferedReader input;
            int height = 0;
            try {
                input = new BufferedReader( new FileReader("Board.txt") );
                while((line = input.readLine()) != null) {
                    for(int i=0; i<line.length(); i++) {
                        grid[height][i] = Integer.parseInt(line.substring(i,i+1));
                    }
                    height++;
                }
                input.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        printBoard(grid); // show unsolved sudoku board
        backtrackingSolveSudoku(grid); // solve the sudoku board
        System.out.println(" -- NEW FINISHED BOARD BELOW -- ");
        printBoard(grid); // show solved sudoku board

    } // end of main()

    static void backtrackingSolveSudoku(int[][] grid) {
        // TODO
        Stack<Integer> numsH = new Stack<>();
        Stack<Integer> numsL = new Stack<>();
        boolean searching = true;
        boolean findingNextH = true;
        boolean findingNextL = true;
        boolean works = false;
        int height = 0;
        int length = 0;
        // find first empty 
        for(int i=0; i<grid.length && findingNextH; i++) {
            for(int j=0; j<grid[0].length && findingNextL; j++) {
                if(grid[i][j] == 0) {
                    // DEBUG: System.out.println("first batch: " + Integer.toString(i) + Integer.toString(j));
                    numsH.push(i);
                    numsL.push(j);
                    findingNextH = false;
                    findingNextL = false;
                }
            }
        }
        findingNextH = true;
        findingNextL = true;

        // begin backtracking
        while(searching && !numsH.empty()) {
            // peeks at top then checks for number that satisfies the coordinates
            works = false;
            findingNextH = true;
            findingNextL = true;
            height = numsH.peek();
            length = numsL.peek();

            // try to find number > current_number (instead of 0 because of backtracking case)
            for(int i=grid[height][length]+1; i<grid.length+1; i++) {
                if(doChecks(grid, height, length, i)){
                    // DEBUG: System.out.println(Integer.toString(height) + Integer.toString(length) + Integer.toString(i));
                    grid[height][length] = i; 
                    works = true; // stop checking and get the next empty
                    // find next empty 1 2 9 8
                    for(int h=0; h<grid.length && findingNextH; h++) {
                        for(int l=0; l<grid[0].length && findingNextL; l++) {
                            if(grid[h][l] == 0) {
                                numsH.push(h);
                                numsL.push(l);
                                findingNextH = false;
                                findingNextL = false;
                            }
                        }
                    }
                    break;
                }
            }
            // condition for impossible to insert number with current configuration
            if(!works) {
                // reset current to 0 then pop (backtrack to previous) and try a different number
                // DEBUG: System.out.println("backtrack!");
                grid[height][length] = 0;
                numsH.pop();
                numsL.pop();
            }
            // end condition
            if(works && height == grid.length-1 && length == grid[0].length-1) {
                // DEBUG: System.out.println("finished searching!");
                searching = false;
            }
        }
    }

    static boolean doChecks(final int[][] grid, final int height, final int length, int number) {
        return (checkBox(grid, height, length, number) && checkHorizontal(grid, height, number) && checkVertical(grid, length, number));
    }

    static boolean checkBox(final int[][] grid, final int height, final int length, int number) {
        boolean works = true;
        int h;
        int l;
        for(int h_incremental=0;h_incremental<3;h_incremental++) {
            for(int l_incremental=0;l_incremental<3;l_incremental++) {
                h = 3*(height/3) + h_incremental; // floor coordinates to top-left of box
                l = 3*(length/3) + l_incremental; // increment from there
                if(h!=height && l!=length && grid[h][l] == number) {
                    // DEBUG: System.out.println("doesn't work in box");
                    works = false;
                    break;
                }
            }
        } 

        return works;
    }

    static boolean checkHorizontal(final int[][] grid, final int height, final int number) {
        boolean works = true;
        for(int i=0; i<grid[0].length; i++) {
            if(grid[height][i] == number) { // don't check current number (since it will obviously resolve to true)
                // DEBUG: System.out.println("doesn't work horizontally");
                works = false;
                break;
            }
        }

        return works;
    }

    static boolean checkVertical(final int[][] grid, final int length, final int number) {
        boolean works = true;
        for(int i=0; i<grid.length; i++) {
            if(grid[i][length] == number) { // don't check current number (since it will obviously resolve to true)
                // DEBUG: System.out.println("doesn't work vertically");
                works = false;
                break;
            }
        }

        return works;
    }

    static void printBoard(final int[][] grid) {
        for(int height = 0; height<grid.length; height++) {
            for(int length=0; length<(grid[0].length); length++) {
                if(length % 3 == 0 && length!=0) {
                    System.out.print(" | ");
                }
                System.out.print(" " + Integer.toString(grid[height][length]) + " ");
            }
            if((height+1) % 3 == 0)
            {
                System.out.print("\n- - - - - | - - - - - | - - - - -\n");
            }
            else
            {
                System.out.print('\n');
            }
        }
    } // end of printBoard()
}