package com.quiz;

import java.io.Serializable;

public class Question implements Serializable {
    private String questionText;
    private String[] options;
    private int correctAnswer; // 1 to 4
    private String category;
    private String difficulty;

    public Question(String questionText, String[] options, int correctAnswer, String category, String difficulty) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.difficulty = difficulty;
    }

    // Getters
    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    // Format for file storage
    @Override
    public String toString() {
        return questionText + "||" + options[0] + "||" + options[1] + "||" +
                options[2] + "||" + options[3] + "||" + correctAnswer + "||" +
                category + "||" + difficulty;
    }
}
