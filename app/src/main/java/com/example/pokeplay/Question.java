package com.example.pokeplay;

public class Question {
    String question;
    String a, b, c, d;
    int correct;

    public Question(String question, String a, String b, String c, String d, int correct) {
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.correct = correct;
    }
}
