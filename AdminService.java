package com.quiz;

import java.util.Scanner;

public class AdminService {
    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "admin123";

    public boolean login(Scanner sc) {
        System.out.print("Enter Admin Username: ");
        String user = sc.next();
        System.out.print("Enter Admin Password: ");
        String pass = sc.next();
        return user.equals(ADMIN_USER) && pass.equals(ADMIN_PASS);
    }

    public void addQuestion(Scanner sc) {
        sc.nextLine(); // clear buffer
        System.out.println("\n--- Add New Question ---");
        System.out.print("Enter Question Text: ");
        String text = sc.nextLine();

        String[] opts = new String[4];
        for (int i = 0; i < 4; i++) {
            System.out.print("Option " + (i + 1) + ": ");
            opts[i] = sc.nextLine();
        }

        System.out.print("Correct Option Number (1-4): ");
        int correct = sc.nextInt();
        sc.nextLine();

        System.out.print("Category (Java/GK/Math): ");
        String cat = sc.nextLine();

        System.out.print("Difficulty (Easy/Medium/Hard): ");
        String diff = sc.nextLine();

        Question q = new Question(text, opts, correct, cat, diff);
        FileHandler.saveQuestion(q);
        System.out.println("Question added successfully!");
    }

    public void deleteQuestion(Scanner sc) {
        java.util.List<Question> questions = FileHandler.loadQuestions();
        if (questions.isEmpty()) {
            System.out.println("No questions available to delete.");
            return;
        }

        System.out.println("\n--- Delete Question ---");
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i).getQuestionText());
        }

        System.out.print("Enter question number to delete (0 to cancel): ");
        int choice = sc.nextInt();
        if (choice > 0 && choice <= questions.size()) {
            questions.remove(choice - 1);
            FileHandler.overwriteQuestions(questions);
            System.out.println("Question deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
}
