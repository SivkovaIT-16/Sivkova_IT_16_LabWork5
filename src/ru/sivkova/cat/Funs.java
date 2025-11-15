package ru.sivkova.cat;

public class Funs {
    public static void meowsCare(Meow... meows) {
        for (Meow meow : meows) {
            meow.meow();
        }
    }
}
