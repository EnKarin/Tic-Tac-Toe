package tictactoe;
import java.util.Scanner;
import java.util.Random;

public class Main {
    private static void printmat (char[][] mat){
        String c1 = "| " + mat[0][0] + " " + mat[0][1] + " " + mat[0][2] + " |";
        String c2 = "| " + mat[1][0] + " " + mat[1][1] + " " + mat[1][2] + " |";
        String c3 = "| " + mat[2][0] + " " + mat[2][1] + " " + mat[2][2] + " |";
        System.out.println("---------");
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println("---------");
    }

    private static void userStep (char[][] mat, char h){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the coordinates: ");
            int r = scanner.nextInt();
            int l = scanner.nextInt();
            if (r <= 3 && r > 0 && l > 0 && l <= 3 && mat[3 - l][r - 1] == ' ') {
                mat[3 - l][r - 1] = h;
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

    private static boolean printState (char[][] mat){
        boolean flag = true;
        int win1 = 0;
        int win2 = 0;
        for(int i = 0; i < 3 && flag; i++){ //по горизонтали
            if(win1 == 3){
                System.out.println("X wins");
                flag = false;
                return false;
            }
            else win1 = 0;
            if(win2 == 3){
                flag = false;
                System.out.println("O wins");
                return false;
            }
            else win2 = 0;
            for(int j = 0; j < 3; j++){
                if(mat[i][j] == 'X' && win1 < 3){
                    win1++;
                }
                else if(mat[i][j] == 'O' && win2 < 3){
                    win2++;
                }
            }
        }

        for(int i = 0; i < 3 && flag; i++){//по вертикали
            if(win1 == 3){
                flag = false;
                System.out.println("X wins");
                return false;
            }
            else win1 = 0;
            if(win2 == 3){
                flag = false;
                System.out.println("O wins");
                return false;
            }
            else win2 = 0;
            for(int j = 0; j < 3; j++){
                if(mat[j][i] == 'X' && win1 < 3){
                    win1++;
                }
                else if(mat[j][i] == 'O' && win2 < 3){
                    win2++;
                }
            }
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
                System.out.println("X wins");
                flag = false;
                return false;
            }
            else if(q == 3){
                System.out.println("O wins");
                flag = false;
                return false;
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
                System.out.println("X wins");
                flag = false;
                return false;
            }
            else if(w == 3){
                System.out.println("O wins");
                flag = false;
                return false;
            }
        }

        for(int i = 0; i < 3 && flag; i++) {
            for (int j = 0; j < 3; j++) {
                if (mat[i][j] != ' ' && i == 2 && j == 2) { //Нет выигравшего и нет пустых ячеек
                    System.out.println("Draw");
                    flag = false;
                    return false;
                }
                if (mat[i][j] == ' ') { //Имеются пустые ячейки, но нет выигравшего
                    return true;
                }
            }
        }
        return false;
    }

    private static void easyLevel(char[][] mat, char h){
        Random rand = new Random();
        int x, y;
        while(true) {
            x = rand.nextInt(3);
            y = rand.nextInt(3);
            if(mat[x][y] == ' '){
                mat[x][y] = h;
                System.out.println("Making move level \"easy\"");
                printmat(mat);
                break;
            }
        }
    }

    private static void mediumLevel(char[][] mat, char h){
        Random rand = new Random();
        int win1 = 0, win2 = 0, zero = 0;
        int x, y;
        for(int i = 0; i < 3; i++){//добавление третьего по горизонтали
            win1 = 0;
            win2 = 0;
            zero = 0;
            for(int j = 0; j < 3; j++){
                if(mat[i][j] == 'X'){
                    win1++;
                }
                else if(mat[i][j] == 'O'){
                    win2++;
                }
                else if(mat[i][j] == ' '){
                    zero = j;
                }
            }
            if(win1 == 2 || win2 == 2) {
                if (mat[i][zero] == ' ') {
                    mat[i][zero] = h;
                    System.out.println("Making move level \"medium\"");
                    printmat(mat);
                    return;
                }
            }
        }

        for(int i = 0; i < 3; i++) {//добавление третьего по вертикали
            win1 = 0;
            win2 = 0;
            zero = 0;
            for (int j = 0; j < 3; j++) {
                if (mat[j][i] == 'X') {
                    win1++;
                } else if (mat[j][i] == 'O') {
                    win2++;
                } else if (mat[j][i] == ' ') {
                    zero = j;
                }
            }
            if (win1 == 2 || win2 == 2) {
                if (mat[zero][i] == ' ') {
                    mat[zero][i] = h;
                    System.out.println("Making move level \"medium\"");
                    printmat(mat);
                    return;
                }
            }
        }

        int k = 0, q = 0;
        for(int i = 0; i < 3 ; i++){//Добавление третьего по главной диагонали
            zero = 0;
            int j = i;
            if(mat[i][j] == 'X'){
                k++;
            }
            else if(mat[i][j] == 'O'){
                q++;
            }
            else if(mat[i][j] == ' '){
                zero = i;
            }
            if((k == 2 || q == 2) && mat[zero][zero] == ' '){
                mat[zero][zero] = h;
                System.out.println("Making move level \"medium\"");
                printmat(mat);
                return;
            }
        }

        int g = 0, w = 0;
        for(int i = 0; i < 3; i++){//Добавление третьего по побочной диагонали
            zero = 0;
            int j = 3 - i - 1;
            if( mat[i][j] == 'X'){
                g++;
            }
            else if(mat[i][j] == 'O'){
                w++;
            }
            else if(mat[i][j] == ' '){
                zero = i;
            }
            if((g == 2 || w == 2) && mat[i][2 - i] == ' '){
                mat[i][2 - i] = h;
                System.out.println("Making move level \"medium\"");
                printmat(mat);
                return;
            }
        }

            while(true) {
                x = rand.nextInt(3);
                y = rand.nextInt(3);
                if(mat[x][y] == ' '){
                    mat[x][y] = h;
                    System.out.println("Making move level \"medium\"");
                    printmat(mat);
                    return;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char h;
        boolean flag = true;
        char[][] mat = new char[3][3]; //Заполнение матрицы ходов
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mat[i][j] = ' ';
            }
        }
        while (flag) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    mat[i][j] = ' ';
                }
            }
            System.out.print("Input command: ");
            String command = scan.nextLine();
            switch (command) {
                case "start easy easy":
                    printmat(mat);
                    do {
                        h = 'X';
                        easyLevel(mat, h);
                        if (!printState(mat)) {
                            break;
                        }
                        h = 'O';
                        easyLevel(mat, h);
                    } while (printState(mat));
                    break;
                case "start medium medium":
                    printmat(mat);
                    do {
                        h = 'X';
                        mediumLevel(mat, h);
                        if (!printState(mat)) {
                            break;
                        }
                        h = 'O';
                        mediumLevel(mat, h);
                    } while (printState(mat));
                    break;
                case "start easy user":
                    printmat(mat);
                    do {
                        h = 'X';
                        easyLevel(mat, h);
                        if (!printState(mat)) {
                            break;
                        }
                        h = 'O';
                        userStep(mat, h);
                    } while (printState(mat));
                    break;
                case "start medium user":
                    printmat(mat);
                    do {
                        h = 'X';
                        mediumLevel(mat, h);
                        if (!printState(mat)) {
                            break;
                        }
                        h = 'O';
                        userStep(mat, h);
                    } while (printState(mat));
                    break;
                case "start user easy":
                    printmat(mat);
                    do {
                        h = 'X';
                        userStep(mat, h);
                        if (!printState(mat)) {
                            break;
                        }
                        h = 'O';
                        easyLevel(mat, h);
                    } while (printState(mat));
                    break;
                case "start user medium":
                    printmat(mat);
                    do {
                        h = 'X';
                        userStep(mat, h);
                        if (!printState(mat)) {
                            break;
                        }
                        h = 'O';
                        mediumLevel(mat, h);
                    } while (printState(mat));
                    break;
                case "start user user":
                    printmat(mat);
                    do {
                        h = 'X';
                        userStep(mat, h);
                        if (!printState(mat)) {
                            break;
                        }
                        h = 'O';
                        userStep(mat, h);
                    } while (printState(mat));
                    break;
                case "start medium easy":
                    printmat(mat);
                    do {
                        h = 'X';
                        mediumLevel(mat, h);
                        if (!printState(mat)) {
                            break;
                        }
                        h = 'O';
                        easyLevel(mat, h);
                    } while (printState(mat));
                    break;
                case "start easy medium":
                    printmat(mat);
                    do {
                        h = 'X';
                        easyLevel(mat, h);
                        if (!printState(mat)) {
                            break;
                        }
                        h = 'O';
                        mediumLevel(mat, h);
                    } while (printState(mat));
                    break;
                case "exit":
                    flag = false;
                    break;
                default:
                    System.out.println("Bad parameters!");
            }
        }
    }
}