import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Object;
import java.util.ArrayList;

public class assignment4 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please input file name: ");
        String file = scan.nextLine();
        Scanner scanner = new Scanner(new File(file));
        String line1 = scanner.nextLine();
        String[] lines = line1.split(",");
        int size = Integer.parseInt(lines[0]);
        String[][] board = new String[size][size];

        for(int i = 0; i<size;i++){
            String line = scanner.nextLine();
            for(int j =0;j<size;j++){
                String[] letters = line.split(",");
                board[i][j] = letters[j];
            }  
        }
        ArrayList<player> players = new ArrayList<player>();
       
        System.out.println("How many players?");
        int numOfPlayers = scan.nextInt();

        for(int i = 0; i < numOfPlayers; i++)
        {
            System.out.print("What is Player " + (i + 1) + " name? ");
            String name = scan.next();
            player plr = new player();
            plr.setName(name);
            players.add(plr);
        }

        //System.out.println(players.get(1).getName());

        int first = (int)(Math.random() * numOfPlayers);
        int numWords = lines.length -2;
        printBoard(size, board);
       
        while(numWords > 0){
            System.out.print(players.get(first).getName() + ", please enter a word: ");
            //System.out.println("HUH");
            //Scanner wow = new Scanner(System.in);
            String input = scan.next().toLowerCase();
            Boolean in = false;
            for(int i = 2; i<lines.length;i++){
                if(input.equals(lines[i])){
                    //System.out.println("In");
                    in = true;
                    checkUp(input, board, size);
                    checkDown(input, board, size);
                    checkLeft(input, board, size);
                    checkRight(input, board, size);
                    checkDig1(input, board, size);
                    checkDig2(input, board, size);
                    checkDig3(input, board, size);
                    checkDig4(input, board, size);
                    lines[i] = "-1";
                    players.get(first).addWord(input);
                    players.get(first).addScore();
                    //System.out.println(players.get(first).getWords());
                    numWords--;
                }
            }

            if(in){
                System.out.println("You found a word!");
            }
            else{
                System.out.println("The word is either not in the puzzle or has already been found.");
            }

            printBoard(size, board);
            System.out.println("Score:");
            for(int i =0;i< numOfPlayers;i++){
                System.out.println(players.get(i).getName()+": " + players.get(i).getScore() + " [" + players.get(i).getWords() + "]");
            }

            if(first == numOfPlayers-1){
                first = 0;
            }
            else{
                first++;
            }
            in = false;
        }

        

        System.out.println("GAME OVER!");
        

    }

    public static void printBoard(int size, String[][] grid){
        String line = "+";
        //System.out.print("+");
        for(int i = 0; i<size;i++){
            line = line + "---+";
        }
        System.out.println(line);
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j == 0)
                    System.out.print("| " + grid[i][j]+" |");
                else if (j == size - 1)
                    System.out.print(" "+grid[i][j] + " |");
                else
                    System.out.print(" " +grid[i][j]+ " |");
            }
            System.out.println();
            System.out.println(line);
        }
    }

    public static void checkUp(String word, String[][] board, int size){
        char firstL = word.charAt(0);
        for(int i =0; i<size;i++){
            for(int j = 0; j<size;j++){
                //System.out.print(board[i][j].charAt(0));
                if(firstL==board[i][j].charAt(0)){
                    //System.out.println("found");
                    if(i+1>=word.length()){
                        //System.out.println("found");
                        for(int n = 1;n<word.length();n++){
                            if(word.charAt(n) == board[i-n][j].toLowerCase().charAt(0)){
                                //System.out.println(board[i-n][j].charAt(0) + " " + word.charAt(n));
                                if(n == word.length()-1){
                                    for(int k = 0; k<word.length();k++){
                                        board[i-k][j] = board[i-k][j].toUpperCase();
                                    }
                                    //return true;
                                }
                                continue;
                            }
                            else{
                                break;
                            }
                            
                        }
                        
                    }

                }

            }
        }
        //return false;
    }

    public static void checkDown(String word, String[][] board, int size){
        char firstL = word.charAt(0);
        for(int i =0; i<size;i++){
            for(int j = 0; j<size;j++){
                //System.out.print(board[i][j].charAt(0));
                if(firstL==board[i][j].charAt(0)){
                    //System.out.println("found");
                    if(size-i>=word.length()){
                        //System.out.println("found");
                        for(int n = 1;n<word.length();n++){
                            if(word.charAt(n) == board[i+n][j].toLowerCase().charAt(0)){
                                //System.out.println(board[i+n][j].charAt(0) + " " + word.charAt(n));
                                if(n == word.length()-1){
                                    for(int k = 0; k<word.length();k++){
                                        board[i+k][j] = board[i+k][j].toUpperCase();
                                    }
                                }
                                continue;
                            }
                            else{
                                break;
                            }
                            
                        }
                        
                    }

                }

            }
        }
    }

    public static void checkRight(String word, String[][] board, int size){
        char firstL = word.charAt(0);
        for(int i =0; i<size;i++){
            for(int j = 0; j<size;j++){
                //System.out.print(board[i][j].charAt(0));
                if(firstL==board[i][j].charAt(0)){
                    //System.out.println("found");
                    if(size-j>=word.length()){
                        //System.out.println("found");
                        for(int n = 1;n<word.length();n++){
                            if(word.charAt(n) == board[i][j+n].toLowerCase().charAt(0)){
                                //System.out.println(board[i][j+n].charAt(0) + " " + word.charAt(n));
                                if(n == word.length()-1){
                                    for(int k = 0; k<word.length();k++){
                                        board[i][j+k] = board[i][j+k].toUpperCase();
                                    }
                                }
                                continue;
                            }
                            else{
                                break;
                            }
                            
                        }
                        
                    }

                }

            }
        }
        
    }

    public static void checkLeft(String word, String[][] board, int size){
        char firstL = word.charAt(0);
        for(int i =0; i<size;i++){
            for(int j = 0; j<size;j++){
                //System.out.print(board[i][j].charAt(0));
                if(firstL==board[i][j].charAt(0)){
                    //System.out.println("found");
                    if(j+1 >= word.length()){
                        //System.out.println("found");
                        for(int n = 1;n<word.length();n++){
                            if(word.charAt(n) == board[i][j-n].toLowerCase().charAt(0)){
                                //System.out.println(board[i][j+n].charAt(0) + " " + word.charAt(n));
                                if(n == word.length()-1){
                                    for(int k = 0; k<word.length();k++){
                                        board[i][j-k] = board[i][j-k].toUpperCase();
                                    }
                                }
                                continue;
                            }
                            else{
                                break;
                            }
                            
                        }
                        
                    }

                }

            }
        }
        
    }


    public static void checkDig1(String word, String[][] board, int size){
        char firstL = word.charAt(0);
        for(int i =0; i<size;i++){
            for(int j = 0; j<size;j++){
                //System.out.print(board[i][j].charAt(0));
                if(firstL==board[i][j].charAt(0)){
                    //System.out.println("found");
                    if(size-j>=word.length() && i+1>=word.length()){
                        //System.out.println("found");
                        for(int n = 1;n<word.length();n++){
                            if(word.charAt(n) == board[i-n][j+n].toLowerCase().charAt(0)){
                                //System.out.println(board[i][j+n].charAt(0) + " " + word.charAt(n));
                                if(n == word.length()-1){
                                    for(int k = 0; k<word.length();k++){
                                        board[i-k][j+k] = board[i-k][j+k].toUpperCase();
                                    }
                                }
                                continue;
                            }
                            else{
                                break;
                            }
                            
                        }
                        
                    }

                }

            }
        }
    }

    public static void checkDig2(String word, String[][] board, int size){
        char firstL = word.charAt(0);
        for(int i =0; i<size;i++){
            for(int j = 0; j<size;j++){
                //System.out.print(board[i][j].charAt(0));
                if(firstL==board[i][j].charAt(0)){
                    //System.out.println("found");
                    if(size-j>=word.length() && size-i>=word.length()){
                        //System.out.println("found");
                        for(int n = 1;n<word.length();n++){
                            if(word.charAt(n) == board[i+n][j+n].toLowerCase().charAt(0)){
                                //System.out.println(board[i][j+n].charAt(0) + " " + word.charAt(n));
                                if(n == word.length()-1){
                                    for(int k = 0; k<word.length();k++){
                                        board[i+k][j+k] = board[i+k][j+k].toUpperCase();
                                    }
                                }
                                continue;
                            }
                            else{
                                break;
                            }
                            
                        }
                        
                    }

                }

            }
        }
    }

    public static void checkDig3(String word, String[][] board, int size){
        char firstL = word.charAt(0);
        for(int i =0; i<size;i++){
            for(int j = 0; j<size;j++){
                //System.out.print(board[i][j].charAt(0));
                if(firstL==board[i][j].charAt(0)){
                    //System.out.println("found");
                    if(j+1 >= word.length() && size-i>=word.length()){
                        //System.out.println("found");
                        for(int n = 1;n<word.length();n++){
                            if(word.charAt(n) == board[i+n][j-n].toLowerCase().charAt(0)){
                                //System.out.println(board[i][j+n].charAt(0) + " " + word.charAt(n));
                                if(n == word.length()-1){
                                    for(int k = 0; k<word.length();k++){
                                        board[i+k][j-k] = board[i+k][j-k].toUpperCase();
                                    }
                                }
                                continue;
                            }
                            else{
                                break;
                            }
                            
                        }
                        
                    }

                }

            }
        }
    }

    public static void checkDig4(String word, String[][] board, int size){
        char firstL = word.charAt(0);
        for(int i =0; i<size;i++){
            for(int j = 0; j<size;j++){
                //System.out.print(board[i][j].charAt(0));
                if(firstL==board[i][j].charAt(0)){
                    //System.out.println("found");
                    if(j+1 >= word.length() && i+1>=word.length()){
                        //System.out.println("found");
                        for(int n = 1;n<word.length();n++){
                            if(word.charAt(n) == board[i-n][j-n].toLowerCase().charAt(0)){
                                //System.out.println(board[i][j+n].charAt(0) + " " + word.charAt(n));
                                if(n == word.length()-1){
                                    for(int k = 0; k<word.length();k++){
                                        board[i-k][j-k] = board[i-k][j-k].toUpperCase();
                                    }
                                }
                                continue;
                            }
                            else{
                                break;
                            }
                            
                        }
                        
                    }

                }

            }
        }
    }
}

