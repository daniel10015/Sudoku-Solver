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

        // pre-print board
        printBoard(grid);
        
        

    } // end of main()

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