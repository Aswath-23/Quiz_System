package com.quiz;

import java.util.*;

public class QuizManager {
    private List<Question> questions;
    private int score;
    private List<Integer> userAnswers;
    private static final int TIME_LIMIT_SECONDS = 15; // 15 seconds per question

    public QuizManager() {
        this.questions = FileHandler.loadQuestions();
        this.score = 0;
        this.userAnswers = new ArrayList<>();
    }

    public void startQuiz(Scanner sc) {
        if (questions.isEmpty()) {
            System.out.println("No questions available. Please ask Admin to add some.");
            return;
        }

        System.out.println("\nStarting Quiz! You have " + TIME_LIMIT_SECONDS + " seconds per question.");
        score = 0;
        userAnswers.clear();

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + q.getQuestionText());
            String[] opts = q.getOptions();
            for (int j = 0; j < 4; j++) {
                System.out.println((j + 1) + ". " + opts[j]);
            }

            long startTime = System.currentTimeMillis();
            System.out.print("Your Answer (1-4) [Wait for input or skip]: ");
            
            int ans = -1;
            while ((System.currentTimeMillis() - startTime) < TIME_LIMIT_SECONDS * 1000) {
                try {
                    if (System.in.available() > 0) {
                        ans = sc.nextInt();
                        break;
                    }
                    Thread.sleep(100); 
                } catch (Exception e) {}
            }

            if (ans == -1) {
                System.out.println("\nTime's up! Moving to next question.");
                userAnswers.add(0); 
            } else {
                userAnswers.add(ans);
                if (ans == q.getCorrectAnswer()) {
                    score++;
                }
            }
        }
        showResults();
    }

    private void showResults() {
        Result result = new Result(questions.size(), score, userAnswers, questions);
        result.displaySummary();
    }
}
