public class Matrix {
    private final ComplexNumber[][] matrix;
    private final int rows;
    private final int columns;

    // Конструктор, принимающий на вход количество строк и столбцов матрицы и инициализирующий матрицу нулями
    public Matrix(int rows, int columns) {
        this.rows=rows;
        this.columns= columns;
        matrix = new ComplexNumber[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = new ComplexNumber();
            }
        }
    }

    // Конструктор для создания матрицы из двумерного массива комплексных чисел
    public Matrix(ComplexNumber[][]matrix){
        rows= matrix.length;
        columns= matrix[0].length;
        this.matrix= matrix.clone();
    }

    // Геттер количества строк матрицы
    public int getRows(){
        return rows;
    }

    // Геттер количества столбцов матрицы
    public int getColumns(){
        return columns;
    }

    // Геттер элемента матрицы по индексам
    public ComplexNumber getElement(int row, int column){
        if (row < 0 || row >= rows || column < 0 || column >= columns){
            throw new IndexOutOfBoundsException("Индексы вне диапазона матрицы");
        }
        else{
            return matrix[row][column];
        }
    }

    // Сеттер элемента матрицы по индексам
    public void setElement(ComplexNumber element, int row, int column){
        if (row < 0 || row >= rows || column < 0 || column >= columns){
            throw new IndexOutOfBoundsException("Индексы вне диапазона матрицы");
        }
        else{
            matrix[row][column]=element;
        }
    }

    // Заполнение матрицы случайными элементами
    public void randomMatrix (double minNumber, double maxNumber){
        for (int row = 0; row < rows; row++){
            for (int column = 0; column < columns; column++){
                ComplexNumber number = new ComplexNumber();
                number.randomNumber(minNumber,maxNumber);
                matrix[row][column]= number;
            }
        }
    }

    // Сложение двух матриц
    public Matrix add(Matrix other) {
        if (this.getRows() != other.getRows() || this.getColumns() != other.getColumns()) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера");
        }
        else {
            Matrix res = new Matrix(this.getRows(), this.getColumns());
            for (int row = 0; row < this.getRows(); row++) {
                for (int column = 0; column < this.getColumns(); column++) {
                    ComplexNumber element = this.getElement(row, column).add(other.getElement(row, column));
                    res.setElement(element, row, column);
                }
            }
            return res;
        }
    }

    // Вычитание двух матриц
    public Matrix subtract(Matrix other) {
        if (this.getRows() != other.getRows() || this.getColumns() != other.getColumns()) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера");
        }
        else {
            Matrix res = new Matrix(this.getRows(), this.getColumns());
            for (int row = 0; row < this.getRows(); row++) {
                for (int column = 0; column < this.getColumns(); column++) {
                    ComplexNumber element = this.getElement(row, column).subtract(other.getElement(row, column));
                    res.setElement(element, row, column);
                }
            }
            return res;
        }
    }

    // Умножение матрицы на число
    public Matrix multiply(ComplexNumber number) {
        Matrix res = new Matrix(rows, columns);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                res.setElement(matrix[row][column].multiply(number), row, column);
            }
        }
        return res;
    }

    // Умножение двух матриц
    public Matrix multiply(Matrix other) {
        if (this.getColumns() != other.getRows()) {
            throw new IllegalArgumentException("Количество столбцов в первой матрице должно совпадать с количеством строк во второй");
        }
        Matrix res = new Matrix(this.getRows(), other.getColumns());
        for (int row = 0; row < this.getRows(); row++) {
            for (int column = 0; column < other.getColumns(); column++) {
                ComplexNumber sum = new ComplexNumber();
                for (int i = 0; i < this.getColumns(); i++) {
                    sum = sum.add(this.getElement(row, i).multiply(other.getElement(i, column)));
                }
                res.setElement(sum,row,column);
            }
        }
        return res;
    }

    // Транспонирование матрицы
    public Matrix transpose() {
        Matrix transposedMatrix = new Matrix(columns, rows);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                transposedMatrix.setElement(matrix[row][column], column, row);
            }
        }
        return transposedMatrix;
    }

    // Нахождение детерминанта квадратной матрицы
    public ComplexNumber getDeterminant(){
        if (rows != columns){
            throw new IllegalArgumentException("Матрица должна быть квадратной!");
        }
        if (rows == 1){
            return matrix[0][0];
        }
        else if (rows == 2){
            return (matrix[0][0].multiply(matrix[1][1]).subtract(matrix[0][1].multiply(matrix[1][0])));
        }
        else{
            ComplexNumber determinant = new ComplexNumber();
            for (int row = 0; row < rows; row++){
                determinant = determinant.add(getAlgebraicComplement(row,0).multiply(matrix[row][0]) );
            }
            return determinant;
        }
    }

    private ComplexNumber getAlgebraicComplement(int i, int j){
        Matrix submatrix = this.getSubmatrix(i, j);
        ComplexNumber minor = submatrix.getDeterminant();
        return minor.multiply(new ComplexNumber(Math.pow(-1, i + j), 0));
    }

    private Matrix getSubmatrix(int row, int column) {
        ComplexNumber[][] submatrix = new ComplexNumber[rows - 1][columns - 1];
        int subRow = 0;
        for (int i = 0; i < rows; i++) {
            if (i == row) continue;
            int subColumn = 0;
            for (int j = 0; j < columns; j++) {
                if (j == column) continue;
                submatrix[subRow][subColumn] = matrix[i][j];
                subColumn++;
            }
            subRow++;
        }
        return new Matrix(submatrix);
    }

    // Вычисление обратной матрицы
    public Matrix getInverse(){
        if (rows != columns) {
            throw new IllegalArgumentException("Матрица должна быть квадратной для вычисления обратной");
        }
        ComplexNumber determinant = getDeterminant();
        if (determinant.getIm() == 0 && determinant.getRe() == 0){
            throw new IllegalArgumentException("Обратная матрица не существует, так как детерминант равен 0");
        }
        return getAdjugate().multiply(new ComplexNumber(1,0).divide(determinant));
    }

    private Matrix getAdjugate() {
        Matrix adjointMatrix = new Matrix(rows, columns);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                adjointMatrix.setElement(this.getAlgebraicComplement(column, row).getConjugate(), row, column);
            }
        }
        return adjointMatrix.transpose();
    }

    // Деление матрицы на матрицу
    public Matrix devide(Matrix other){
        if (this.columns != other.rows) {
            throw new IllegalArgumentException("Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы");
        }
        else if (other.getDeterminant().equals(new ComplexNumber(0,0))){
            throw new IllegalArgumentException("Детерминант второй матрицы равен нулю");
        }
        else{
            return this.multiply(other.getInverse());
        }
    }

    // Вывод матрицы с выравниванием
    public void print() {
        int maxLen = 0;
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                String str = getElement(row, column).toString();
                if (str.length() > maxLen) {
                    maxLen = str.length();
                }
            }
        }
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                String str = getElement(row, column).toString();
                System.out.print(String.format("%" + maxLen + "s", str));
                if (column < columns - 1) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }
}

