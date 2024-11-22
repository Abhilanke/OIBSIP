package intership;

import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalScore = 0; 
        int maxRounds = 3; 
        int maxAttempts = 5;
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Rules:");
        System.out.println("1. Guess a number between 1 and 100.");
        System.out.println("2. You will have " + maxAttempts + " attempts per round.");
        System.out.println("3. Points decrease with more attempts.\n");
        
        for (int round = 1; round <= maxRounds; round++) {
            System.out.println("Round " + round + " starts!");
            int targetNumber = random.nextInt(100) + 1; 
            int attempts = 0;
            boolean isCorrect = false;
            
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;
                
                if (userGuess == targetNumber) {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    int score = (maxAttempts - attempts + 1) * 10; 
                    totalScore += score;
                    System.out.println("You earned " + score + " points this round.\n");
                    isCorrect = true;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }
            
            if (!isCorrect) {
                System.out.println("You've used all attempts! The correct number was " + targetNumber + ".\n");
            }
        }
        
        System.out.println("Game over!");
        System.out.println("Your total score: " + totalScore);
        System.out.println("Thank you for playing!");
        scanner.close();
    }
}

