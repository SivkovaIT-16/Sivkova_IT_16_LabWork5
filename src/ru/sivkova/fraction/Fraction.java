package ru.sivkova.fraction;

import ru.sivkova.validator.*;

import java.util.Objects;

public class Fraction implements Fractoions {
    //Поля
    private int numerator;
    private int denominator;
    private Double cachedDoubleValue;
    private boolean isCache;

    //Свойства
    public int getNumerator() {

        return numerator;
    }

    public int getDenominator() {

        return denominator;
    }

    public Double getCachedDoubleValue() {
        return cachedDoubleValue;
    }

    public boolean isCache() {
        return isCache;
    }

    @Override
    public void setNumerator(int numerator) {

        this.numerator = numerator;
        this.cachedDoubleValue = null;
        this.isCache = false;
        simplification();
    }

    @Override
    public void setDenominator(int denominator) {
        Validator.validateDenominator(denominator);
        this.denominator = denominator;
        this.cachedDoubleValue = null;
        this.isCache = false;
        simplification();
    }

    //Конструкторы
    //По умолчанию
    public Fraction() {
        this.numerator = 2;
        this.denominator = 3;
        this.cachedDoubleValue = null;
        this.isCache = false;
    }

    //С параметрами
    public Fraction(int numerator, int denominator) {
        Validator.validateDenominator(denominator);
        this.numerator = numerator;
        this.denominator = denominator;
        this.cachedDoubleValue = null;
        this.isCache = false;
        simplification();
    }

    //Копирования
    public Fraction(Fraction fraction) {
        this.numerator = fraction.numerator;
        this.denominator = fraction.denominator;
        this.cachedDoubleValue = fraction.cachedDoubleValue;
        this.isCache = fraction.isCache;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public double getDoubleFraction() {
        if (!isCache || cachedDoubleValue == null) {
            cachedDoubleValue = (double) numerator / (double) denominator;
            isCache = true;
        }
        return cachedDoubleValue;
    }

    //Арифметические операции
    //Сумма дробей
    public Fraction sum(Fraction fraction) {
        int newNumerator = this.numerator * fraction.denominator + fraction.numerator * this.denominator;
        int newDenominator = this.denominator * fraction.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    //Сумма дроби и целого числа
    public Fraction sum(int number) {
        return sum(new Fraction(number, 1));
    }

    //Разность дробей
    public Fraction difference(Fraction fraction) {
        int newNumerator = this.numerator * fraction.denominator - fraction.numerator * this.denominator;
        int newDenominator = this.denominator * fraction.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    //Разность дроби и целого числа
    public Fraction difference(int number) {
        return difference(new Fraction(number, 1));
    }

    //Произведение дробей
    public Fraction composition(Fraction fraction) {
        int newNumerator = this.numerator * fraction.numerator;
        int newDenominator = this.denominator * fraction.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    //Произведение дроби и целого числа
    public Fraction composition(int number) {
        return composition(new Fraction(number, 1));
    }

    //Деление дробей
    public Fraction division(Fraction fraction) {
        Validator.validateFraction(fraction);
        int newNumerator = this.numerator * fraction.denominator;
        int newDenominator = this.denominator * fraction.numerator;
        return new Fraction(newNumerator, newDenominator);
    }

    //Деление дроби и целого числа
    public Fraction division(int number) {
        Validator.validateNumber(number);
        return division(new Fraction(number, 1));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        Fraction fraction = null;
        if (object instanceof Fraction) {
            fraction = (Fraction) object;
        } else {
            return false;
        }
        return numerator == fraction.numerator &&
                denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    //Упрощение дроби
    private void simplification(){
        //Знак в числителе
        if (denominator < 0){
            numerator = -numerator;
            denominator = -denominator;
        }

        //Сокращение дроби
        int shorten = NOD(numerator, denominator);
        numerator /= shorten;
        denominator /= shorten;

    }

    //НОД (метод Евклида)
    private int NOD(int a, int b){
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0){
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
