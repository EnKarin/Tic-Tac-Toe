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
        int win1 = 0;
        int win2 = 0;
        for(int i = 0; i < 3; i++){ //по горизонтали
            if(win1 == 3){
                System.out.println("X wins");
                return false;
            }
            else win1 = 0;
            if(win2 == 3){
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
        win1 = 0;
        win2 = 0;
        for(int i = 0; i < 3; i++){//по вертикали
            for(int j = 0; j < 3; j++){
                if(mat[j][i] == 'X' && win1 < 3){
                    win1++;
                }
                else if(mat[j][i] == 'O' && win2 < 3){
                    win2++;
                }
            }
            if(win1 == 3){
                System.out.println("X wins");
                return false;
            }
            else win1 = 0;
            if(win2 == 3){
                System.out.println("O wins");
                return false;
            }
            else win2 = 0;
        }

        int k = 0, q = 0;
        for(int i = 0; i < 3; i++){//Победа по главной диагонали
            int j = i;
            if(mat[i][j] == 'X'){
                k++;
            }
            else if(mat[i][j] == 'O'){
                q++;
            }
            if(k == 3){
                System.out.println("X wins");
                return false;
            }
            else if(q == 3){
                System.out.println("O wins");
                return false;
            }
        }

        int g = 0, w = 0;
        for(int i = 0; i < 3; i++){//Победа по побочной диагонали
            int j = 3 - i - 1;
            if( mat[i][j] == 'X'){
                g++;
            }
            else if(mat[i][j] == 'O'){
                w++;
            }
            if(g == 3){
                System.out.println("X wins");
                return false;
            }
            else if(w == 3){
                System.out.println("O wins");
                return false;
            }
        }

        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (mat[i][j] != ' ' && i == 2 && j == 2) { //Нет выигравшего и нет пустых ячеек
                    System.out.println("Draw");
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
            if(mat[i][i] == 'X'){
                k++;
            }
            else if(mat[i][i] == 'O'){
                q++;
            }
            else if(mat[i][i] == ' '){
                zero = i;
            }
        }
        if((k == 2 || q == 2) && mat[zero][zero] == ' ') {
            mat[zero][zero] = h;
            System.out.println("Making move level \"medium\"");
            printmat(mat);
            return;
        }

        int g = 0, w = 0;
        for(int i = 0; i < 3; i++){//Добавление третьего по побочной диагонали
            if( mat[i][2 - i] == 'X'){
                g++;
            }
            else if(mat[i][2 - i] == 'O'){
                w++;
            }
            else if(mat[i][2 - i] == ' '){
                zero = i;
            }
        }
        if((g == 2 || w == 2) && mat[zero][2 - zero] == ' '){
            mat[zero][2 - zero] = h;
            System.out.println("Making move level \"medium\"");
            printmat(mat);
            return;
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

   /* private static void hardLevel(char[][] mat, char h) {
        Random rand = new Random();
        int win1 = 0, win2 = 0, zero = 0;
        int x, y;
        char pl2;
        if(h == 'X'){
            pl2 = 'O';
        }
        else pl2 = 'X';
        for(int i = 0; i < 3; i++){//добавление третьего по горизонтали
            win1 = 0;
            win2 = 0;
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
                    System.out.println("Making move level \"hard\"");
                    printmat(mat);
                    return;
                }
            }
        }

        for(int i = 0; i < 3; i++) {//добавление третьего по вертикали
            win1 = 0;
            win2 = 0;
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
                    System.out.println("Making move level \"hard\"");
                    printmat(mat);
                    return;
                }
            }
        }

        int k = 0, q = 0;
        for(int i = 0; i < 3 ; i++){//Добавление третьего по главной диагонали
            if(mat[i][i] == 'X'){
                k++;
            }
            else if(mat[i][i] == 'O'){
                q++;
            }
            else if(mat[i][i] == ' '){
                zero = i;
            }
        }
        if((k == 2 || q == 2) && mat[zero][zero] == ' ') {
            mat[zero][zero] = h;
            System.out.println("Making move level \"hard\"");
            printmat(mat);
            return;
        }

        int g = 0, w = 0;
        for(int i = 0; i < 3; i++){//Добавление третьего по побочной диагонали
            if( mat[i][2 - i] == 'X'){
                g++;
            }
            else if(mat[i][2 - i] == 'O'){
                w++;
            }
            else if(mat[i][2 - i] == ' '){
                zero = i;
            }
        }
        if((g == 2 || w == 2) && mat[zero][2 - zero] == ' '){
            mat[zero][2 - zero] = h;
            System.out.println("Making move level \"hard\"");
            printmat(mat);
            return;
        }
        if(mat[2][2] == ' '){
            mat[2][2] = h;
            System.out.println("Making move level \"hard\"");
            printmat(mat);
            return;
        }
        if(mat[0][0] == ' ' && mat[2][2] != pl2){
            mat[0][0] = h;
            System.out.println("Making move level \"hard\"");
            printmat(mat);
            return;
        }
        if(mat[2][2] == ' ' && mat[0][0] != pl2){
            mat[2][2] = h;
            System.out.println("Making move level \"hard\"");
            printmat(mat);
            return;
        }
        if(mat[0][2] == ' ' && mat[2][0] != pl2){
            mat[0][2] = h;
            System.out.println("Making move level \"hard\"");
            printmat(mat);
            return;
        }
        if(mat[2][0] == ' ' && mat[0][2] != pl2){
            mat[2][0] = h;
            System.out.println("Making move level \"hard\"");
            printmat(mat);
            return;
        }
        while(true) {
            x = rand.nextInt(3);
            y = rand.nextInt(3);
            if(mat[x][y] == ' '){
                mat[x][y] = h;
                System.out.println("Making move level \"hard\"");
                printmat(mat);
                return;
            }
        }
    } */

   public static int hardLevel(char[][] pmat, char h, int koef, boolean orig){
       Random rand = new Random();
       char[][] mat;
       if(orig){
           mat = pmat;
           System.out.println("Making move level \"hard\"");

       }
       else {
           mat = new char[3][3];
           for(int i = 0; i < 3; i++){
               mat[i] = pmat[i].clone();
           }
       }
       int win1 = 0, win2 = 0, zero = 0;
       int x, y;
       for(int i = 0; i < 3; i++){//добавление третьего по горизонтали
           win1 = 0;
           for(int j = 0; j < 3; j++){
               if(mat[i][j] == h) {
                   win1++;
               }
               else if(mat[i][j] == ' '){
                   zero = j;
               }
           }
           if(win1 == 2) {
               if (mat[i][zero] == ' ') {
                   mat[i][zero] = h;
                   return koef;
               }
           }
       }

       for(int i = 0; i < 3; i++) {//добавление третьего по вертикали
           win1 = 0;
           for (int j = 0; j < 3; j++) {
               if (mat[j][i] == h) {
                   win1++;
               } else if (mat[j][i] == ' ') {
                   zero = j;
               }
           }
           if (win1 == 2) {
               if (mat[zero][i] == ' ') {
                   mat[zero][i] = h;
                   return koef;
               }
           }
       }

       int k = 0;
       for(int i = 0; i < 3 ; i++){//Добавление третьего по главной диагонали
           if(mat[i][i] == h){
               k++;
           }
           else if(mat[i][i] == ' '){
               zero = i;
           }
       }
       if(k == 2 && mat[zero][zero] == ' ') {
           mat[zero][zero] = h;
           return koef;
       }

       int g = 0;
       for(int i = 0; i < 3; i++){//Добавление третьего по побочной диагонали
           if( mat[i][2 - i] == h){
               g++;
           }
           else if(mat[i][2 - i] == ' '){
               zero = i;
           }
       }
       if(g == 2 && mat[zero][2 - zero] == ' '){
           mat[zero][2 - zero] = h;
           return koef;
       }
       for(int i = 0; i < 3; i++){//добавление третьего по горизонтали
           win2 = 0;
           for(int j = 0; j < 3; j++){
               if(mat[i][j] == (h == 'X'? 'O': 'X')){
                   win2++;
               }
               else if(mat[i][j] == ' '){
                   zero = j;
               }
           }
           if(win2 == 2) {
               if (mat[i][zero] == ' ') {
                   mat[i][zero] = h;
                   return hardLevel(mat, (h == 'X'? 'O': 'X'), -koef, false);
               }
           }
       }

       for(int i = 0; i < 3; i++) {//добавление третьего по вертикали
           win2 = 0;
           for (int j = 0; j < 3; j++) {
               if (mat[j][i] == (h == 'X'? 'O': 'X')) {
                   win2++;
               } else if (mat[j][i] == ' ') {
                   zero = j;
               }
           }
           if (win2 == 2) {
               if (mat[zero][i] == ' ') {
                   mat[zero][i] = h;
                   return hardLevel(mat, (h == 'X'? 'O': 'X'), -koef, false);
               }
           }
       }
       k = 0;
       for(int i = 0; i < 3 ; i++){//Добавление третьего по главной диагонали
           if(mat[i][i] == (h == 'X'? 'O': 'X')){
               k++;
           }
           else if(mat[i][i] == ' '){
               zero = i;
           }
       }
       if(k == 2 && mat[zero][zero] == ' ') {
           mat[zero][zero] = h;
           return hardLevel(mat, (h == 'X'? 'O': 'X'), -koef, false);
       }

       g = 0;
       for(int i = 0; i < 3; i++){//Добавление третьего по побочной диагонали
           if( mat[i][2 - i] == (h == 'X'? 'O': 'X')){
               g++;
           }
           else if(mat[i][2 - i] == ' '){
               zero = i;
           }
       }
       if(g == 2 && mat[zero][2 - zero] == ' '){
           mat[zero][2 - zero] = h;
           return hardLevel(mat, (h == 'X'? 'O': 'X'), -koef, false);
       }
       for(int p = 1; p >= 0; p--){
           for(int i = 0; i < 3; i++){
               for(int j = 0; j < 3; j++){
                   if(mat[i][j] == ' '){
                       mat[i][j] = h;
                       if(hardLevel(mat, (h == 'X'? 'O': 'X'), -koef, false) == p * koef){
                           return p * koef;
                       }
                       else{
                           mat[i][j] = ' ';
                       }
                   }
               }
           }
       }
       boolean flag = true;
       for(int i = 0; i < 3 && flag; i++){
           for(int j = 0; j < 3; j++){
               if(mat[i][j] == ' '){
                   flag = false;
                   break;
               }
           }
       }
       if(flag == true){
           return 0;
       }
       else{
           while(true) {
               x = rand.nextInt(3);
               y = rand.nextInt(3);
               if(mat[x][y] == ' '){
                   mat[x][y] = h;
                   break;
               }
           }
       }
       return -1;
   }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char hum, ai;
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
                        ai = 'X';
                        easyLevel(mat, ai);
                        if (!printState(mat)) {
                            break;
                        }
                        ai = 'O';
                        easyLevel(mat, ai);
                    } while (printState(mat));
                    break;
                case "start medium medium":
                    printmat(mat);
                    do {
                        ai = 'X';
                        mediumLevel(mat, ai);
                        if (!printState(mat)) {
                            break;
                        }
                        ai = 'O';
                        mediumLevel(mat, ai);
                    } while (printState(mat));
                    break;
                case "start hard hard":
                    printmat(mat);
                    do {
                        ai = 'X';
                        hardLevel(mat, ai, 1, true);
                        printmat(mat);
                        if (!printState(mat)) {
                            break;
                        }
                        ai = 'O';
                        hardLevel(mat, ai, 1, true);
                        printmat(mat);
                    } while (printState(mat));
                    break;
                case "start easy user":
                    printmat(mat);
                    ai = 'X';
                    hum = 'O';
                    do {
                        easyLevel(mat, ai);
                        if (!printState(mat)) {
                            break;
                        }
                        userStep(mat, hum);
                    } while (printState(mat));
                    break;
                case "start hard user":
                    printmat(mat);
                    ai = 'X';
                    hum = 'O';
                    do {
                        hardLevel(mat, ai, 1, true);
                        printmat(mat);
                        if (!printState(mat)) {
                            break;
                        }
                        userStep(mat, hum);
                    } while (printState(mat));
                    break;
                case "start medium user":
                    printmat(mat);
                    ai = 'X';
                    hum = 'O';
                    do {
                        mediumLevel(mat, ai);
                        if (!printState(mat)) {
                            break;
                        }
                        userStep(mat, hum);
                    } while (printState(mat));
                    break;
                case "start user hard":
                    printmat(mat);
                    hum = 'X';
                    ai = 'O';
                    do {
                        hum = 'X';
                        userStep(mat, hum);
                        if (!printState(mat)) {
                            break;
                        }
                        hardLevel(mat, ai, 1, true);
                        printmat(mat);
                    } while (printState(mat));
                    break;
                case "start user easy":
                    printmat(mat);
                    ai = 'O';
                    hum = 'X';
                    do {
                        userStep(mat, hum);
                        if (!printState(mat)) {
                            break;
                        }
                        easyLevel(mat, ai);
                    } while (printState(mat));
                    break;
                case "start user medium":
                    printmat(mat);
                    hum = 'X';
                    ai = 'O';
                    do {
                        userStep(mat, hum);
                        if (!printState(mat)) {
                            break;
                        }
                        mediumLevel(mat, ai);
                    } while (printState(mat));
                    break;
                case "start user user":
                    printmat(mat);
                    do {
                        hum = 'X';
                        userStep(mat, hum);
                        if (!printState(mat)) {
                            break;
                        }
                        hum = 'O';
                        userStep(mat, hum);
                    } while (printState(mat));
                    break;
                case "start medium easy":
                    printmat(mat);
                    do {
                        ai = 'X';
                        mediumLevel(mat, ai);
                        if (!printState(mat)) {
                            break;
                        }
                        ai = 'O';
                        easyLevel(mat, ai);
                    } while (printState(mat));
                    break;
                case "start easy medium":
                    printmat(mat);
                    do {
                        ai = 'X';
                        easyLevel(mat, ai);
                        if (!printState(mat)) {
                            break;
                        }
                        ai = 'O';
                        mediumLevel(mat, ai);
                    } while (printState(mat));
                    break;
                case "start easy hard":
                    printmat(mat);
                    do {
                        ai = 'X';
                        easyLevel(mat, ai);
                        if (!printState(mat)) {
                            break;
                        }
                        ai = 'O';
                        hardLevel(mat, ai, 1, true);
                        printmat(mat);
                    } while (printState(mat));
                    break;
                case "start hard easy":
                    printmat(mat);
                    do {
                        ai = 'X';
                        hardLevel(mat, ai, 1, true);
                        printmat(mat);
                        if (!printState(mat)) {
                            break;
                        }
                        ai = 'O';
                        easyLevel(mat, ai);
                    } while (printState(mat));
                    break;
                case "start medium hard":
                    printmat(mat);
                    do {
                        ai = 'X';
                        mediumLevel(mat, ai);
                        if (!printState(mat)) {
                            break;
                        }
                        ai = 'O';
                        hardLevel(mat, ai, 1, true);
                        printmat(mat);
                    } while (printState(mat));
                    break;
                case "start hard medium":
                    printmat(mat);
                    do {
                        ai = 'X';
                        hardLevel(mat, ai, 1, true);
                        printmat(mat);
                        if (!printState(mat)) {
                            break;
                        }
                        ai = 'O';
                        mediumLevel(mat, ai);
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