package com.quiz;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_PATH = "data/questions.txt";

    // Ensure data directory exists
    static {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    public static void saveQuestion(Question q) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_PATH, true)))) {
            out.println(q.toString());
        } catch (IOException e) {
            System.out.println("Error saving question: " + e.getMessage());
        }
    }

    public static List<Question> loadQuestions() {
        List<Question> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        
        if (!file.exists()) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|\\|");
                if (parts.length == 8) {
                    String[] opts = {parts[1], parts[2], parts[3], parts[4]};
                    list.add(new Question(parts[0], opts, Integer.parseInt(parts[5]), parts[6], parts[7]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading questions: " + e.getMessage());
        }
        return list;
    }

    public static void overwriteQuestions(List<Question> list) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(FILE_PATH, false)))) {
            for (Question q : list) {
                out.println(q.toString());
            }
        } catch (IOException e) {
            System.out.println("Error overwriting questions: " + e.getMessage());
        }
    }
}
