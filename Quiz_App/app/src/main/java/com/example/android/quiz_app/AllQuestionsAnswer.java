package com.example.android.quiz_app;

import java.util.PriorityQueue;

/**
 * Created by android on 9/18/2016.
 */
public class AllQuestionsAnswer {
    private String Question;

    private String Ansoption1;

    private String Ansoption2;

    private String Ansoption3;

    private String Ansoption4;

    //Constructor all possible option

    AllQuestionsAnswer(final String question, final String ansoption1,
                       final String ansoption2, final String ansoption3, final String ansoption4){

        this.Question = question;

        this.Ansoption1 = ansoption1;

        this.Ansoption2 = ansoption2;

        this.Ansoption3 = ansoption3;

        this.Ansoption4 = ansoption4;
    }

    //Constructor Maximum 3 possible option

    AllQuestionsAnswer(final String question, final String ansoption1,
                       final String ansoption2, final String ansoption3){

        this.Question = question;

        this.Ansoption1 = ansoption1;

        this.Ansoption2 = ansoption2;

        this.Ansoption3 = ansoption3;

    }

    //Constructor Maximum 2 possible option

    AllQuestionsAnswer(final String question, final String ansoption1,
                       final String ansoption2){

        this.Question = question;

        this.Ansoption1 = ansoption1;

        this.Ansoption2 = ansoption2;

    }

    public String getQuestion() {
        return Question;
    }

    public String getAnsoption1() {
        return Ansoption1;
    }

    public String getAnsoption2() {
        return Ansoption2;
    }

    public String getAnsoption3() {
        return Ansoption3;
    }

    public String getAnsoption4() {
        return Ansoption4;
    }
}
