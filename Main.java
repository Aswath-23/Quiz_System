package com.quiz;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AdminService admin = new AdminService();
        QuizManager quiz = new QuizManager();

        while (true) {
            System.out.println("\n*******************************");
            System.out.println("* ONLINE QUIZ & ASSESSMENT SYSTEM *");
            System.out.println("*******************************");
            System.out.println("1. Admin Login");
            System.out.println("2. User Quiz");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            
            try {
                if (sc.hasNextInt()) {
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            if (admin.login(sc)) {
                                System.out.println("Login Successful!");
                                boolean adminLoop = true;
                                while (adminLoop) {
                                    System.out.println("\n[Admin Menu] 1. Add Question  2. Delete Question  3. Back");
                                    if (sc.hasNextInt()) {
                                        int admChoice = sc.nextInt();
                                        if (admChoice == 1) admin.addQuestion(sc);
                                        else if (admChoice == 2) admin.deleteQuestion(sc);
                                        else adminLoop = false;
                                    } else {
                                        sc.next(); // clear invalid input
                                    }
                                }
                            } else {
                                System.out.println("Invalid credentials.");
                            }
                            break;
                        case 2:
                            quiz = new QuizManager();
                            quiz.startQuiz(sc);
                            break;
                        case 3:
                            System.out.println("Closing the system. Goodbye!");
                            System.exit(0);
                        default:
                            System.out.println("Invalid choice.");
                    }
                } else {
                    System.out.println("Please enter a valid number.");
                    sc.next(); // clear invalid input
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                sc.nextLine(); 
            }
        }
    }
}
