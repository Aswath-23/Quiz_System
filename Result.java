package com.quiz;

import java.util.List;

public class Result {
    private int total;
    private int correct;
    private List<Integer> userAnswers;
    private List<Question> questions;

    public Result(int total, int correct, List<Integer> userAnswers, List<Question> questions) {
        this.total = total;
        this.correct = correct;
        this.userAnswers = userAnswers;
        this.questions = questions;
    }

    public void displaySummary() {
        double percentage = ((double) correct / total) * 100;
        int wrong = total - correct;
        
        System.out.println("\n============================");
        System.out.println("      QUIZ RESULT           ");
        System.out.println("============================");
        System.out.println("Total Questions: " + total);
        System.out.println("Correct Answers: " + correct);
        System.out.println("Wrong Answers  : " + wrong);
        System.out.printf("Percentage     : %.2f%%\n", percentage);
        
        System.out.print("Performance    : ");
        if (percentage >= 90) System.out.println("Excellent!");
        else if (percentage >= 75) System.out.println("Good");
        else if (percentage >= 50) System.out.println("Average");
        else System.out.println("Needs Improvement");
        System.out.println("============================");

        System.out.println("\n--- Detailed Review ---");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            int userAns = userAnswers.get(i);
            String status = (userAns == q.getCorrectAnswer()) ? "[Correct]" : "[Wrong/Skipped]";
            System.out.println("Q" + (i + 1) + ": " + q.getQuestionText());
            System.out.println("   Your Answer: " + (userAns == 0 ? "Skipped" : userAns));
            System.out.println("   Correct Ans: " + q.getCorrectAnswer() + " " + status);
        }
    }
}
