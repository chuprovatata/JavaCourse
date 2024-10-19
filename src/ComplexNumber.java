import java.util.Random;
public class ComplexNumber {
    private double Re;
    private double Im;

    // Параметризованный конструктор
    public ComplexNumber(double Re, double Im){
        this.Re=Re;
        this.Im=Im;
    }

    // Конструктор без аргументов
    public ComplexNumber(){
        Re= 0;
        Im= 0;
    }

    // Геттеры
    public double getRe(){
        return Re;
    }

    public double getIm(){
        return Im;
    }

    // Сеттеры
    public void setRe(double Re){
        this.Re=Re;
    }

    public void setIm(double Im){
        this.Im=Im;
    }

    // Создание случайного комплексного числа
    public void randomNumber(double minNumber, double maxNumber) {
        Random random = new Random();
        double re = minNumber + random.nextDouble() * (maxNumber - minNumber);
        double im = minNumber + random.nextDouble() * (maxNumber - minNumber);
        Re=re;
        Im=im;
    }

    // Сложение двух комплексных чисел
    public ComplexNumber add(ComplexNumber other){
        return new ComplexNumber(this.Re + other.Re, this.Im + other.Im);
    }

    // Разность двух комплексных чисел
    public ComplexNumber subtract(ComplexNumber other){
        return new ComplexNumber(this.Re - other.Re,this.Im - other.Im);
    }

    // Умножение двух комплексных чисел
    public ComplexNumber multiply(ComplexNumber other){
        return new ComplexNumber(this.Re * other.Re - this.Im * other.Im, this.Re * other.Im + this.Im * other.Re);
    }

    // Деление двух комплексных чисел
    public ComplexNumber divide(ComplexNumber other){
        double denominator = other.Re * other.Re + other.Im * other.Im;
        if (denominator == 0){
            throw new ArithmeticException("Деление на ноль!");
        }
        double newRe = (this.Re * other.Re + this.Im * other.Im) / denominator;
        double newIm = (this.Im * other.Re + this.Re * other.Im) / denominator;
        return new ComplexNumber(newRe,newIm);
    }

    public ComplexNumber getConjugate(){
        return new ComplexNumber(Re, -Im);
    }
    // Переопределение метода toString, чтобы получать красивый вывод комплексного числа
    @Override
    public String toString() {
        return String.format("%.2f %s %.2fi", Re, (Im >= 0 ? "+" : "-"), Math.abs(Im));
    }
}
