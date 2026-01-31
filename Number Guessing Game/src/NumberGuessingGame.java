import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        // Create a Random object for generating random numbers
        Random random = new Random();

        // Generate a random number between 1 and 100
        int numberToGuess = random.nextInt(100) + 1;
        int userGuess = 0;
        int attempts = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between 1 and 100. Can you guess it?");

        // Loop until the user guesses the correct number
        while (userGuess != numberToGuess) {
            System.out.print("Enter your guess: ");
            // Check if the input is an integer
            if (scanner.hasNextInt()) {
                userGuess = scanner.nextInt();
                attempts++;

                if (userGuess > numberToGuess) {
                    System.out.println("Too high! Try again.");
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                }
            } else {
                System.out.println("Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }

        // Close the scanner
        scanner.close();
    }
}