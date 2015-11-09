
package takuzusolver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TakuzuSolver {

    static int board[][];
    
    public static void main(String[] args) {
        try {
            init("src//takuzusolver//12-2.txt"); 
            
            int counter = 0;
            
            while (!isFinished()) {
                twoInARow();
                noThrees();
                countNums();
                similarRows();
                similarCols();
                counter++;
                if (counter > 100) {
                    System.out.println("Taking too long...");
                    break;
                }
            }
            
            printBoard();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void init(String filename) throws FileNotFoundException, IOException {
        File input = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(input));
        String line = br.readLine();
        board = new int[line.length()][line.length()];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char c = line.charAt(j);
                if (Character.isDigit(c)) {
                    board[i][j] = Integer.parseInt(Character.toString(c));
                }
            }
            line = br.readLine();
        }
    }
    
    public static void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    System.out.print('r');
                } else if (board[i][j] == 2) {
                    System.out.print('b');
                } else {
                    System.out.print('.');
                }
            }
            System.out.print("\n");
        }
    }
    
    /**
     * Checks to see if there are missing squares.
     * @return True if we're done
     */
    public static boolean isFinished() {
        int sum;
        //check rows
        for (int i = 0; i < board.length; i++) {
            sum = 0;
            for (int j = 0; j < board[i].length; j++) {
                sum += board[i][j];
            }
            if (sum != (int)(board.length * 1.5)) {
                return false;
            }
        }
        //check columns
        for (int i = 0; i < board.length; i++) {
            sum = 0;
            for (int j = 0; j < board[i].length; j++) {
                sum += board[j][i];
            }
            if (sum != (int)(board.length * 1.5)) {
                return false;
            }
        }
        return true;
    }
    
    public static void noThrees() {
        //prevent three in a row
        for (int i = 0; i < board.length; i++) {
            for (int j = 1; j < board[i].length - 1; j++) {
                if (board[i][j] == 0 && board[i][j - 1] == board[i][j + 1] && board[i][j + 1] != 0) {
                    board[i][j] = (board[i][j-1] == 1) ? 2 : 1;
                }
            }
        }
        
        //prevent three in a col
        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0 && board[i - 1][j] == board[i + 1][j] && board[i + 1][j] != 0) {
                    board[i][j] = (board[i - 1][j] == 1) ? 2 : 1;
                }
            }
        }
    }
    
    public static void twoInARow() {
        int prev;
        //check rows
        for (int i = 0; i < board.length; i++) {
            prev = board[i][0];
            for (int j = 1; j < board[i].length - 1; j++) {
                if (prev != 0 && board[i][j] == prev && board[i][j + 1] == 0) {
                    board[i][j + 1] = (board[i][j] == 1) ? 2 : 1;
                } else if (prev == 0 && board[i][j] == board[i][j + 1] && board[i][j + 1] != 0) {
                    board[i][j - 1] = (board[i][j] == 1) ? 2 : 1;
                }
                prev = board[i][j];
            }
        }
        
        //check cols
        for (int j = 0; j < board.length; j++) {
            prev = board[0][j];
            for (int i = 1; i < board[j].length - 1; i++) {
                if (prev != 0 && board[i][j] == prev && board[i + 1][j] == 0) {
                    board[i + 1][j] = (board[i][j] == 1) ? 2 : 1;
                } else if (prev == 0 && board[i][j] == board[i + 1][j] && board[i + 1][j] != 0) {
                    board[i - 1][j] = (board[i][j] == 1) ? 2 : 1;
                }
                prev = board[i][j];
            }
        }
    }
    
    public static void countNums() {
        int ones, twos;
        //rows
        for (int i = 0; i < board.length; i++) {
            ones = 0;
            twos = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) 
                    ones++;
                if (board[i][j] == 2)
                    twos++;
            }
            if (ones == board.length / 2) {
                //fill zeros with 2s
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = 2;
                    }
                }
            } else if (twos == board.length / 2) {
                //fill zeros with 1s
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == 0) {
                        board[i][j] = 1;
                    }
                }
            }
        }
        
        //cols
        for (int j = 0; j < board.length; j++) {
            ones = 0;
            twos = 0;
            for (int i = 0; i < board[j].length; i++) {
                if (board[i][j] == 1) 
                    ones++;
                if (board[i][j] == 2)
                    twos++;
            }
            if (ones == board.length / 2) {
                //fill zeros with 2s
                for (int i = 0; i < board[j].length; i++) {
                    if (board[i][j] == 0) {
                        board[i][j] = 2;
                    }
                }
            } else if (twos == board.length / 2) {
                //fill zeros with 1s
                for (int i = 0; i < board[j].length; i++) {
                    if (board[i][j] == 0) {
                        board[i][j] = 1;
                    }
                }
            }
        }
    }
    
    public static int sumRow(int i) {
        int sum = 0;
        for (int j = 0; j < board[i].length; j++) {
            sum += board[i][j];
        }
        return sum;
    }
    
    public static int sumCol(int j) {
        int sum = 0;
        for (int i = 0; i < board.length; i++) {
            sum += board[i][j];
        }
        return sum;
    }
    
    //checks if a row has only 2 zeros left in it
    public static boolean isRowAlmostDone(int i) {
        int zeroes = 0;
        for (int j = 0; j < board[i].length; j++) {
            if (board[i][j] == 0) {
                zeroes++;
            }
            if (zeroes > 2)
                return false;
        }
        if (zeroes == 2) {
            return true;
        }
        return false;
    }
    
    //checks if a column has only 2 zeros left in it
    public static boolean isColAlmostDone(int j) {
        int zeroes = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i][j] == 0) {
                zeroes++;
            }
            if (zeroes > 2)
                return false;
        }
        if (zeroes == 2) {
            return true;
        }
        return false;
    }
    
    public static void similarRows() {
        int complete = (int)(board.length * 1.5);
        for (int i = 0; i < board.length; i++) {
            if (sumRow(i) == complete) {
                //find nearly complete rows
                for (int j = 0; j <board.length; j++) {
                    if (isRowAlmostDone(j)) {
                        boolean flag = true;
                        for (int k = 0; k < board.length; k++) {
                            if (board[i][k] == board[j][k] || board[j][k] == 0) {
                                
                            } else {
                                flag = false;
                                break;
                            }
                        }
                        if (flag == true) {
                            for (int k = 0; k < board.length; k++) {
                                if (board[j][k] == 0) {
                                    board[j][k] = (board[i][k] == 1) ? 2: 1;
                                }
                            }
                        }
                    }
                }
            }
            
        }
    }
    
    public static void similarCols() {
        int complete = (int)(board.length * 1.5);
        for (int i = 0; i < board.length; i++) {
            if (sumCol(i) == complete) {
                //find nearly complete rows
                for (int j = 0; j <board.length; j++) {
                    if (isColAlmostDone(j)) {
                        boolean flag = true;
                        for (int k = 0; k < board.length; k++) {
                            if (board[k][i] == board[k][j] || board[k][j] == 0) {
                                
                            } else {
                                flag = false;
                                break;
                            }
                        }
                        if (flag == true) {
                            for (int k = 0; k < board.length; k++) {
                                if (board[k][j] == 0) {
                                    board[k][j] = (board[k][i] == 1) ? 2: 1;
                                }
                            }
                        }
                    }
                }
            }
            
        }
    }
    
}
