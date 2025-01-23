
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random  random = new Random();

        String[] choice = {"rock" , "paper" , "scissor"};
        String computerinput;
        String userinput;
        String playagain = "yes";

        int userWins= 0;
        int userLosses = 0;
        int userTies= 0;

        do {
            System.out.print("Enter your move(rock, paper, scissor): ");
            userinput = sc.nextLine().toLowerCase();

            if(!userinput.equals("rock") &&
                    !userinput.equals("paper") &&
                    !userinput.equals("scissor") ){
                System.out.println("Invalid Move !!!");
                continue;
            }

            computerinput = choice[random.nextInt(3)];
            System.out.println("computer choice: " + computerinput);

            if(userinput.equals(computerinput)){
                System.out.println("It's a tie!!!");
                userTies++;
            }
            else if (userinput.equals("rock" ) && computerinput.equals("scissor")
                    || userinput.equals("paper")  && computerinput.equals("rock")
                    || userinput.equals("scissor")  && computerinput.equals("paper")) {
                System.out.println("You won the Game!!!");
                userWins++;
            }
            else
            {
                System.out.println("You lost the Game!!!");
                userLosses++ ;
            }

            System.out.print("Do you want to play again (yes/no): ");
            playagain = sc.nextLine().toLowerCase();

        }
        while (playagain.equals("yes"));
        System.out.println("Final Stats: Wins: " + userWins + ", Losses: " + userLosses + ", Ties: " + userTies);
        System.out.println("Thanks for playing!");
        sc.close();



        sc.close();
    }

}
