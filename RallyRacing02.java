import java.util.Collections;
import java.util.Scanner;

public class RallyRacing02 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        String[][] matrix = new String[n][n];

        String number=scanner.nextLine();

        int carRow=0;
        int carCol=0;

        int firstTunnelRow=0;
        int firstTunnelCol=0;

        int secondTunnelRow=0;
        int secondTunnelCol=0;

        boolean has=false;

        int kmPassed=0;

        boolean finish=false;
        boolean two=false;
        for (int i = 0; i < n; i++){
            String[] line=scanner.nextLine().split(" ");
            for(int j=0;j<n;j++){

                matrix[i][j]=line[j];

                if(matrix[i][j].equals("T") && has==false){
                    has=true;
                    firstTunnelRow=i;
                    firstTunnelCol=j;
                    two=true;
                }
                if(matrix[i][j].equals("T") && has==true){
                    secondTunnelRow=i;
                    secondTunnelCol=j;
                    two=true;
                }
            }
        }
        boolean  passed=false;

        String command=scanner.nextLine();

        boolean weFinal=false;
        while (!command.equals("End") && !matrix[carRow][carCol].equals("F")){

            int oldRow=carRow;
            int oldCol=carCol;

            switch (command){
                case "up":
                    if(carRow-1>=0){
                        carRow--;
                        passed=true;
                    }else{
                        passed=false;
                    }
                    break;
                case "down":
                    if(carRow+1<n){
                        carRow++;
                        passed=true;
                    }else{
                        passed=false;
                    }
                    break;
                case "left":
                    if(carCol-1>=0){
                        carCol--;
                        passed=true;
                    }else{
                        passed=false;
                    }
                    break;
                case "right":
                    if(carCol+1<n){
                        carCol++;
                        passed=true;
                    }else {
                        passed=false;
                    }
                    break;
            }

            if(carRow==firstTunnelRow && carCol==firstTunnelCol){
                carRow = secondTunnelRow;
                carCol = secondTunnelCol;
                matrix[oldRow][oldCol] = ".";
                matrix[firstTunnelRow][firstTunnelCol]=".";
                kmPassed+= 30;
            }else if(carRow==secondTunnelRow && carCol==secondTunnelCol){
                carRow = firstTunnelRow;
                carCol = firstTunnelCol;
                matrix[oldRow][oldCol] = ".";
                matrix[secondTunnelRow][secondTunnelCol] =".";
                kmPassed+= 30;
            }else if(matrix[carRow][carCol].equals("F")){
                matrix[oldRow][oldCol] = ".";
                weFinal=true;
                kmPassed += 10;
                break;
            }else {
                matrix[oldRow][oldCol] = ".";
               kmPassed += 10;
            }

            command=scanner.nextLine();
        }
        matrix[carRow][carCol]="C";
        if(weFinal){
            System.out.printf("Racing car %s finished the stage!%n",number);
        }else {
            System.out.printf("Racing car %s DNF.%n",number);
        }
        System.out.printf("Distance covered %d km.%n",kmPassed);
        printMatrix(matrix);

    }
    private  static void printMatrix(String [][] matrix){
        for (int row=0;row<matrix.length;row++){
            for(int col=0;col<matrix[row].length;col++){
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

}
