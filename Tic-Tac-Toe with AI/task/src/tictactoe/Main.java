package tictactoe;
import java.util.Scanner;
import java.util.Random;

public class Main {
    private static void printmat (char[][] mat){
        String c1 = "| " + mat[0][0] + " " + mat[0][1] + " " + mat[0][2] + " |";
        String c2 = "| " + mat[1][0] + " " + mat[1][1] + " " + mat[1][2] + " |";
        String c3 = "| " + mat[2][0] + " " + mat[2][1] + " " + mat[2][2] + " |";
        System.out.println();
        System.out.println("---------");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println("---------");
    }

    private static void userStep (char[][] mat){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the coordinates: ");
            int r = scanner.nextInt();
            int l = scanner.nextInt();
            if (r <= 3 && r > 0 && l > 0 && l <= 3 && mat[3 - l][r - 1] == ' ') {
                mat[3 - l][r - 1] = 'X';
                printmat(mat);
                break;
            } else if ((r < '1' || r > '9') && (l < '1' || l > '9')) {
                System.out.println("You should enter numbers!");
            } else if (r > 3 || r < 0 || l > 3 || l < 0) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (mat[3 - l][r - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
            }
        }
    }

    private static void printState (char[][] mat){
        boolean flag = true;
        int win1 = 0;
        int win2 = 0;
        int x = 0, o = 0;
        for(int i = 0; i < 3 && flag; i++){ //Если ходов одного игрока значительно больше, чем другого
            for(int j = 0; j < 3; j++){
                if(mat[i][j] == 'X'){
                    x++;
                }
                else if(mat[i][j] == 'O'){
                    o++;
                }
            }
        }
        if(x - 1 > o || o - 1 > x) {
            System.out.printf("Impossible");
            flag = false;
        }

        for(int i = 0; i < 3 && flag; i++){ //Если по горизонтали имеются 3 'х' и 3 'о'
            if(win1 != 3){
                win1 = 0;
            }
            if(win2 != 3){
                win2 = 0;
            }
            for(int j = 0; j < 3; j++){
                if(mat[i][j] == 'X' && win1 < 3){
                    win1++;
                }
                else if(mat[i][j] == 'O' && win2 < 3){
                    win2++;
                }
                if (win2 == 3 && win1 == 3){
                    flag = false;
                    System.out.printf("Impossible");
                    break;
                }
            }
        }

        for(int i = 0; i < 3 && flag; i++){//Если по вертикали имеются 3 "х" и 3 "о"
            if(win1 != 3){
                win1 = 0;
            }
            if(win2 != 3){
                win2 = 0;
            }
            for(int j = 0; j < 3; j++){
                if(mat[j][i] == 'X' && win1 < 3){
                    win1++;
                }
                else if(mat[j][i] == 'O' && win2 < 3){
                    win2++;
                }
                if (win2 == 3 && win1 == 3){
                    flag = false;
                    System.out.printf("Impossible");
                    break;
                }
            }
        }
        if(win1 == 3 && flag){ //Победа "х"
            flag = false;
            System.out.printf("X wins");
        }
        else if(win2 == 3 && flag){ //Победа "о"
            flag = false;
            System.out.printf("O wins");
        }

        int k = 0, q = 0;
        for(int i = 0; i < 3 && flag; i++){//Победа по главной диагонали
            int j = i;
            if(mat[i][j] == 'X'){
                k++;
            }
            else if(mat[i][j] == 'O'){
                q++;
            }
            if(k == 3){
                flag = false;
                System.out.printf("X wins");
            }
            else if(q == 3){
                flag = false;
                System.out.printf("O wins");
            }
        }

        int g = 0, w = 0;
        for(int i = 0; i < 3 && flag; i++){//Победа по побочной диагонали
            int j = 3 - i - 1;
            if( mat[i][j] == 'X'){
                g++;
            }
            else if(mat[i][j] == 'O'){
                w++;
            }
            if(g == 3){
                flag = false;
                System.out.printf("X wins");
            }
            else if(w == 3){
                flag = false;
                System.out.printf("O wins");
            }
        }

        for(int i = 0; i < 3 && flag; i++) {
            for (int j = 0; j < 3; j++) {
                if (mat[i][j] == ' ') { //Имеются пустые ячейки, но нет выигравшего
                    System.out.print("Game not finished");
                    flag = false;
                    break;
                }
                if (mat[i][j] != ' ' && i == 2 && j == 2) { //Нет выигравшего и нет пустых ячеек
                    System.out.print("Draw");
                    flag = false;
                    break;
                }
            }
        }
    }
    private static void easyLevel(char[][] mat){
        Random rand = new Random();
        int x, y;
        while(true) {
            x = rand.nextInt(3);
            y = rand.nextInt(3);
            if(mat[x][y] == ' '){
                mat[x][y] = 'X';
                System.out.println("Making move level \"easy\"");
                printmat(mat);
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells: ");
        String input = scanner.nextLine();
        String move = input.substring(1, 10);
        String s1 = "| " + move.charAt(0) + " " + move.charAt(1) + " " + move.charAt(2) + " |";
        String s2 = "| " + move.charAt(3) + " " + move.charAt(4) + " " + move.charAt(5) + " |";
        String s3 = "| " + move.charAt(6) + " " + move.charAt(7) + " " + move.charAt(8) + " |";
        System.out.println("---------");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println("---------");

        char[][] mat = new char[3][3]; //Заполнение матрицы ходов
        for(int i = 0, w = 0; i < 3; i++){
            for(int j = 0; j < 3; j++, w++){
                mat[i][j] = move.charAt(w);
            }
        }
        easyLevel(mat);
    }
}