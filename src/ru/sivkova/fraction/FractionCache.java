package ru.sivkova.fraction;

import ru.sivkova.validator.Validator;

public class FractionCache implements Fractions {
    private final Fraction fraction;
    private Double cache;

    public FractionCache(Fraction fraction) {
        Validator.validateFractionNull(fraction);
        this.fraction = fraction;
    }

    @Override
    public double getDoubleFraction() {
        if (cache == null) {
            cache = fraction.getDoubleFraction();
        }
        return cache;
    }

    @Override
    public void setFraction(int numerator, int denominator) {
        fraction.setFraction(numerator, denominator);
        cache = null; // сброс кэша
    }

    @Override
    public String toString() {
        return fraction.toString();
    }
}
