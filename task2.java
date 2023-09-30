import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class WordCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("Enter text or provide a file path to count the words (type 'exit' to quit):");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break; // Exit the loop if the user enters 'exit'.
            }

            int wordCount = 0;

            if (input.startsWith("file:")) {
                String filePath = input.substring(5);
                wordCount = countWordsInFile(filePath);
            } else {
                wordCount = countWordsInText(input);
            }

            System.out.println("Total words: " + wordCount);
        }

        scanner.close();
    }

    // Count words in a given text.
    private static int countWordsInText(String text) {
        String[] words = text.split("\\s+|\\p{Punct}");
        return words.length;
    }

    // Count words in a file.
    private static int countWordsInFile(String filePath) {
        int wordCount = 0;
        try {
            File file = new File(filePath);
            // Use try-with-resources to automatically close the file.
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    wordCount += countWordsInText(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + filePath);
        }
        return wordCount;
    }
}
