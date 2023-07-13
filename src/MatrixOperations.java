import java.util.Scanner;

public class MatrixOperations {

    public static double[][] enterMatrix(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter a number of columns:");
        int columns = scanner.nextInt();
        double[][] matrix = new double[rows][columns];
        System.out.println("Enter the elements of the matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    public static void printMatrix(double[][] matrix){
        int rows = matrix.length;
        int columns = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.println(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static double[][] addMatrices(double[][] matrix1, double[][] matrix2){
        int rows = matrix1.length;
        int columns = matrix2.length;
        double[][] result = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    public static double[][] subtractMatrices(double[][] matrix1, double[][] matrix2){
        int rows = matrix1.length;
        int columns = matrix2.length;
        double[][] result = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return result;
    }

    public static double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2){
        int rows1 = matrix1.length;
        int columns1 = matrix1[0].length;
        int columns2 = matrix2[0].length;
        double[][] result = new double[rows1][columns2];
        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                for (int k = 0; k < columns1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    public static double calculateDeterminant(double[][] matrix) {
        int n = matrix.length;
        if (n != matrix[0].length) {
            throw new IllegalArgumentException("The matrix is not quadratic.");
        }
        if (n == 1) {
            return matrix[0][0];
        }
        double determinant = 0;
        for (int j = 0; j < n; j++) {
            determinant += matrix[0][j] * calculateMinor(matrix, 0, j);
        }
        return determinant;
    }

    private static double calculateMinor(double[][] matrix, int row, int column) {
        int n = matrix.length;
        double[][] minor = new double[n - 1][n - 1];
        int minorRow = 0;
        int minorColumn = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != row && j != column) {
                    minor[minorRow][minorColumn++] = matrix[i][j];

                    if (minorColumn == n - 1) {
                        minorRow++;
                        minorColumn = 0;
                    }
                }
            }
        }
        return Math.pow(-1, row + column) * calculateDeterminant(minor);
    }

    public static double[][] calculateInverseMatrix(double[][] matrix) {
        double determinant = calculateDeterminant(matrix);
        if (determinant == 0) {
            throw new IllegalArgumentException("The matrix doesn't have an inverse.");
        }
        int n = matrix.length;
        double[][] inverseMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double cofactor = calculateMinor(matrix, i, j);
                inverseMatrix[j][i] = cofactor / determinant;
            }
        }
        return inverseMatrix;
    }

    public static boolean isIdentityMatrix(double[][] matrix) {
        int n = matrix.length;
        if (n != matrix[0].length) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    if (matrix[i][j] != 1) {
                        return false;
                    }
                } else {
                    if (matrix[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first matrix:");
        double[][] matrix1 = enterMatrix();
        System.out.println("Enter second matrix:");
        double[][] matrix2 = enterMatrix();

        System.out.println("Matrix 1:");
        printMatrix(matrix1);
        System.out.println("Matrix 2:");
        printMatrix(matrix2);

        double[][] sum = addMatrices(matrix1, matrix2);
        System.out.println("The sum of the matrices is:");
        printMatrix(sum);
        double[][] difference = subtractMatrices(matrix1, matrix2);
        System.out.println("The difference between matrices is:");
        printMatrix(difference);

        double[][] product = multiplyMatrices(matrix1, matrix2);
        System.out.println("The multiplication of the matrices is:");
        printMatrix(product);

        double determinant = calculateDeterminant(matrix1);
        System.out.println("Determinant of matrix 1 is: " + determinant);

        try {
            double[][] inverseMatrix = calculateInverseMatrix(matrix1);
            System.out.println("Inverse matrix 1:");
            printMatrix(inverseMatrix);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        boolean isIdentity = isIdentityMatrix(matrix1);
        if (isIdentity) {
            System.out.println("Matrix 1 is an identity.");
        } else {
            System.out.println("Matrix 1 is not identity.");
        }
    }

}
