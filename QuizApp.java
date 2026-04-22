import java.util.*;
import java.io.*;

public class QuizApp {
    static String FILE_NAME = "data/quiz.txt";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- ONLINE QUIZ SYSTEM ---");
            System.out.println("1. Admin Login");
            System.out.println("2. Start Quiz");
            System.out.println("3. Exit");
            System.out.print("Select Option: ");

            String choice = sc.next();
            if (choice.equals("1")) {
                adminLogin();
            } else if (choice.equals("2")) {
                startQuiz();
            } else if (choice.equals("3")) {
                System.out.println("Exiting... Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    static void adminLogin() {
        System.out.print("Enter Admin Username: ");
        String user = sc.next();
        System.out.print("Enter Admin Password: ");
        String pass = sc.next();

        if (user.equals("admin") && pass.equals("admin123")) {
            while (true) {
                System.out.println("\n--- ADMIN MENU ---");
                System.out.println("1. View Questions");
                System.out.println("2. Add Question");
                System.out.println("3. Delete Question");
                System.out.println("4. Logout");
                System.out.print("Select Option: ");

                String choice = sc.next();
                if (choice.equals("1"))
                    viewQuestions();
                else if (choice.equals("2"))
                    addQuestion();
                else if (choice.equals("3"))
                    deleteQuestion();
                else if (choice.equals("4"))
                    break;
                else
                    System.out.println("Invalid Option!");
            }
        } else {
            System.out.println("Login Failed!");
        }
    }

    static void viewQuestions() {
        try {
            Scanner fs = new Scanner(new File(FILE_NAME));
            int i = 1;
            System.out.println("\n--- ALL QUESTIONS ---");
            while (fs.hasNextLine()) {
                String[] data = fs.nextLine().split("\\|\\|");
                System.out.println(i + ". " + data[0]);
                i++;
            }
            fs.close();
            if (i == 1)
                System.out.println("No questions found.");
        } catch (Exception e) {
            System.out.println("Error reading questions.");
        }
    }

    static void addQuestion() {
        sc.nextLine();
        System.out.print("Enter Question: ");
        String q = sc.nextLine();
        System.out.print("Option 1: ");
        String o1 = sc.nextLine();
        System.out.print("Option 2: ");
        String o2 = sc.nextLine();
        System.out.print("Option 3: ");
        String o3 = sc.nextLine();
        System.out.print("Option 4: ");
        String o4 = sc.nextLine();
        System.out.print("Correct Option Number (1-4): ");
        String correct = sc.next();

        String row = q + "||" + o1 + "||" + o2 + "||" + o3 + "||" + o4 + "||" + correct;

        try {
            FileWriter fw = new FileWriter(FILE_NAME, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(row);
            pw.close();
            System.out.println("Question added successfully!");
        } catch (Exception e) {
            System.out.println("Error saving question.");
        }
    }

    static void deleteQuestion() {
        List<String> lines = new ArrayList<>();
        try {
            Scanner fs = new Scanner(new File(FILE_NAME));
            while (fs.hasNextLine())
                lines.add(fs.nextLine());
            fs.close();
        } catch (Exception e) {
        }

        if (lines.isEmpty()) {
            System.out.println("No questions to delete.");
            return;
        }

        viewQuestions();
        System.out.print("Enter Question Number to Delete (0 to cancel): ");
        int index = sc.nextInt();

        if (index > 0 && index <= lines.size()) {
            lines.remove(index - 1);
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME));
                for (String line : lines)
                    pw.println(line);
                pw.close();
                System.out.println("Question deleted!");
            } catch (Exception e) {
                System.out.println("Error updating file.");
            }
        }
    }

    static void startQuiz() {
        List<String> lines = new ArrayList<>();
        try {
            Scanner fs = new Scanner(new File(FILE_NAME));
            while (fs.hasNextLine()) {
                lines.add(fs.nextLine());
            }
            fs.close();
        } catch (Exception e) {
            System.out.println("No questions found!");
            return;
        }

        if (lines.isEmpty()) {
            System.out.println("No questions found!");
            return;
        }

        int score = 0;
        int total = lines.size();
        System.out.println("\n--- QUIZ STARTED (10 Seconds per question) ---");

        for (int i = 0; i < lines.size(); i++) {
            String[] data = lines.get(i).split("\\|\\|");
            System.out.println("\nQ" + (i + 1) + ": " + data[0]);
            System.out.println("1. " + data[1]);
            System.out.println("2. " + data[2]);
            System.out.println("3. " + data[3]);
            System.out.println("4. " + data[4]);

            System.out.print("Your Answer (1-4): ");

            long startTime = System.currentTimeMillis();
            int answer = -1;
            while ((System.currentTimeMillis() - startTime) < 10000) { // 10 seconds
                try {
                    if (System.in.available() > 0) {
                        answer = sc.nextInt();
                        break;
                    }
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }

            if (answer == -1) {
                System.out.println("\nTime's Up! Moving to next question.");
            } else {
                int correctAns = Integer.parseInt(data[5]);
                if (answer == correctAns) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Wrong! Correct was " + correctAns);
                }
            }
        }

        System.out.println("\n--- QUIZ RESULT SUMMARY ---");
        System.out.println("Total Questions: " + total);
        System.out.println("Correct Answers: " + score);
        double percentage = ((double) score / total) * 100;
        System.out.println("Performance: " + percentage + "%");

        if (percentage >= 80)
            System.out.println("Status: Excellent!");
        else if (percentage >= 50)
            System.out.println("Status: Good Job!");
        else
            System.out.println("Status: Keep Practicing!");
    }
}
