import java.util.Scanner;
import java.util.Random;

public class GusseANumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String play = "yes";
        
        while (play.equals("yes")) {
            Random ran = new Random();
            int num = ran.nextInt(100) + 1; 
            int guess = -1;
            int attempts = 0;
            
            System.out.println("Welcome to the Number Guessing Game!");
            
            while (guess != num) {
                System.out.print("Enter a number between 1 and 100: ");
                
                try {
                    guess = sc.nextInt(); 
                    attempts++;
                    
                    if (guess == num) {
                        System.out.println("Congratulations! You guessed the right number.");
                        System.out.println("It took you " + attempts + " attempts.");
                        
                        System.out.print("Would you like to play again (yes/no)? ");
                        play = sc.next().toLowerCase();
                    } else if (guess > num) {
                        System.out.println("Your guess is too high. Try again.");
                    } else {
                        System.out.println("Your guess is too low. Try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter a valid number.");
                    sc.next(); 
                }
            }
        }
        
        System.out.println("Thank you for playing!");
        sc.close();
    }
}
