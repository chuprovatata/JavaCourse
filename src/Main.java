//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Примеры использования класса ComplexNumber
        ComplexNumber z1 = new ComplexNumber(4.55, 6.66);
        ComplexNumber z2 = new ComplexNumber(-1.6, 4.44);
        ComplexNumber sum = new ComplexNumber();
        sum = z1.add(z2);
        // Сумма z1 и z2
        System.out.println("z1+z2: " + sum);
        ComplexNumber z3 = new ComplexNumber(-33, 99.09);
        ComplexNumber z4 = new ComplexNumber(-1.2, 91.44);
        ComplexNumber substraction = new ComplexNumber();
        substraction = z3.subtract(z4);
        // Разность z3 и z4
        System.out.println("z3-z4: " + substraction);
        ComplexNumber z5 = new ComplexNumber();
        // Получаем случайное комплексное число из дапазона (-10, 22)
        z5.randomNumber(-10, 22);
        ComplexNumber z6 = new ComplexNumber();
        // Получаем число z6 сопряженное с z1
        z6 = z1.getConjugate();
        ComplexNumber multiplication = new ComplexNumber();
        multiplication = z5.multiply(z6);
        // Умножение случайного числа z5 на  z6 (число сопряженное, z1)
        System.out.println("z5*z6: " + multiplication);
        ComplexNumber z7 = new ComplexNumber(-1.2, 91.44);
        ComplexNumber z8 = new ComplexNumber(7, 4);
        ComplexNumber divison = new ComplexNumber();
        divison = z7.divide(z8);
        // Деление числа z7 на число z8
        System.out.println("z7/z8: " + divison);


        // Примеры использования класса Matrix
        // Создание матрицы 2 на 3 и установка для неё каждого элемента по индексам
        Matrix matrix1 = new Matrix(2, 3);
        matrix1.setElement(new ComplexNumber(3,22.3),0,0);
        matrix1.setElement(new ComplexNumber(-22,97),0,1);
        matrix1.setElement(new ComplexNumber(87,2.91),0,2);
        matrix1.setElement(new ComplexNumber(88,-42),1,0);
        matrix1.setElement(new ComplexNumber(-22,97),1,1);
        matrix1.setElement(new ComplexNumber(0.856,-11.91),1,2);
        System.out.println("Matrix1:");
        matrix1.print();
        // Транспонирование матрицы
        Matrix matrixTr = new Matrix(3, 2);
        matrixTr = matrix1.transpose();
        System.out.println("Matrix1 transpose:");
        matrixTr.print();

        // Создание 2-x квадратных матриц размерностью 3 и заполнение их рандомными числами
        Matrix matrix2 = new Matrix(3, 3);
        Matrix matrix3 = new Matrix(3, 3);
        matrix2.randomMatrix(-100,100);
        matrix3.randomMatrix(-100,100);
        // Вывод на экран
        System.out.println("Matrix2:");
        matrix2.print();
        System.out.println("Matrix3:");
        matrix3.print();
        // Сложение 2-х матриц matrix2 и matrix3
        Matrix matrixSum = new Matrix(3, 3);
        matrixSum = matrix2.add(matrix3);
        System.out.println("Matrix2 + Matrix3:");
        matrixSum.print();
        // Вычитание из matrix2 matrix3
        Matrix matrixSub = new Matrix(3, 3);
        matrixSub = matrix2.subtract(matrix3);
        System.out.println("Matrix2 - Matrix3:");
        matrixSub.print();
        // Умножение 2-х матриц matrix2 и matrix3
        Matrix matrixMult = new Matrix(3, 3);
        matrixMult = matrix2.multiply(matrix3);
        System.out.println("Matrix2 * Matrix3:");
        matrixMult.print();
        // Деление matrix2 на matrix3
        Matrix matrixDiv = new Matrix(3, 3);
        matrixDiv = matrix2.devide(matrix3);
        System.out.println("Matrix2 * Matrix3:");
        matrixDiv.print();
        // Нахождение детерминанта для matrix2
        ComplexNumber determinant= new ComplexNumber();
        determinant=matrix2.getDeterminant();
        System.out.println("Matrix2 determinant:" + determinant);
        // Нахождение обратной матрицы для matrix3
        Matrix matrixInv = new Matrix(3,3);
        matrixInv = matrix3.getInverse();
        System.out.println("Inverse matrix3:");
        matrixInv.print();

    }
}