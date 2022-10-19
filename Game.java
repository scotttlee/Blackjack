//Scott Lee
//CS1400
//Assignment 5
//April 6 2021

import java.util.Scanner;

public class Game {
    public static void main(String[] args)
    {
        String hit = "hit";
        int playerWins = 0;
        int dealerWins = 0;

        //Scanner for the user input
        Scanner input = new Scanner(System.in);

        System.out.println("\t***********************************");
        System.out.println("\t* Welcome to the Black Jack Table *");
        System.out.println("\t***********************************");

        //As long as the user inputs "yes" at the end of the game, the while loop/game will
        String decision = "yes";
        while(decision.equals("yes"))
        {
            //Create deck & shuffle it
            Deck d = new Deck();
            d.shuffle();

            //Create a hand for the player and a hand for the dealer
            Hand playerHand = new Hand();
            Hand dealerHand = new Hand();

            //Initialize some variables, totals start at 0, drawNum and anotherCount are manual counters for tracking card numbers
            int playerTotal = 0;
            int dealerTotal = 0;
            int drawNum = 2;
            int anotherCount = 2;

            //Draw two cards for the player
            playerHand.addCard(d.drawCard(0));
            playerHand.addCard(d.drawCard(1));

            //Prints out the players initial hand 
            System.out.println("Your hand is:");
            for(int i = 0; i < playerHand.getSize(); i++)
            {
                System.out.println(d.drawCard(i).getFace() + " of " + d.drawCard(i).getSuit());
            }

            //Prints out the total value of the cards in the players hand and sets it equal to playerTotal
            System.out.println("<" + playerHand.calculateHandValue() + ">");
            playerTotal = playerHand.calculateHandValue();

            //If playerTotal <= 21, will ask user to stand or hit, if hit, will enter a while loop 
            if(playerTotal <= 21)
            {
                System.out.print("Do you want to stand or hit? ");
                String response1 = input.nextLine();

                //If user enters "hit", will enter the following while loop, if not "hit" for ex "stand" then
                //ignores the while loop completely
                if(response1.equals(hit))
                {
                    //drawNum is initialized at a value of 2, drawNum < 52 so the index does not go out of bounds
                    while(response1.equals(hit) && playerTotal <= 21 && drawNum < 52)
                    {
                        //Adds card to playerhand
                        playerHand.addCard(d.drawCard(drawNum));

                        //Prints out new hand with added card
                        System.out.println("\nYour hand is now:");
                        for(int i = 0; i < playerHand.getSize(); i++)
                        {
                            System.out.println(d.drawCard(i).getFace() + " of " + d.drawCard(i).getSuit());
                        }

                        //Calculates the new total value of the hand and sets it to playerTotal
                        System.out.println("<" + playerHand.calculateHandValue() + ">");
                        playerTotal = playerHand.calculateHandValue();

                        //If total > 21, they bust and dealer wins
                        if(playerTotal > 21)
                        {
                            System.out.println("Bust!");
                            System.out.println("\nThe dealer wins!");
                            dealerWins++;
                        } 
                        //If total <= 21, they are asked to stand or hit, if hit, will continue to draw card
                        //in the while loop and perform the same procedure, if stand, will exit the while loop
                        else 
                        {
                            //Adds 1 to drawNum for every iteration in the while loop so that the user gets a new card
                            drawNum++;
                            System.out.print("Do you want to stand or hit? ");
                            response1 = input.nextLine();
                        }
                    }
                }
                //playerTotal is less than 21, so it means they chose to stand
                if(playerTotal <= 21)
                {
                    //Initialized another counter to start drawing cards for the dealer 1 card after where the user left off
                    int anotherNum = drawNum+1;
                    dealerHand.addCard(d.drawCard(anotherNum));
                    dealerHand.addCard(d.drawCard(anotherNum+1));

                    //Prints dealer's hand
                    System.out.println("\nThe dealer's hand is:");
                    System.out.println(d.drawCard(anotherNum).getFace() + " of " + d.drawCard(anotherNum).getSuit());
                    System.out.println(d.drawCard(anotherNum+1).getFace() + " of " + d.drawCard(anotherNum+1).getSuit());

                    //Calculates dealer's hand value and sets it to dealerTotal
                    System.out.println("<" + dealerHand.calculateHandValue()+ ">");
                    dealerTotal = dealerHand.calculateHandValue();
                    
                    //while loop that does the same procedure of adding cards, setting total, and displaying cards as the player's
                    while(dealerTotal < playerTotal && anotherNum < 52)
                    {
                        dealerHand.addCard(d.drawCard(anotherNum+anotherCount));
                        System.out.println("\nThe dealer's hand is now:");
                        for(int j = 0; j < dealerHand.getSize(); j++ )
                        {
                            System.out.println(d.drawCard(anotherNum+j).getFace() + " of " + d.drawCard(anotherNum+j).getSuit());
                        }
                        System.out.println("<" + dealerHand.calculateHandValue() + ">");
                        dealerTotal = dealerHand.calculateHandValue();
                        anotherCount++;

                        
                    }
                    if(dealerTotal == 21 && playerTotal <= 21)
                    {
                        System.out.println("\nThe dealer wins with a Blackjack!");
                        dealerWins++;
                    }
                    else if(dealerTotal == playerTotal && dealerTotal < 21 && playerTotal < 21)
                    {
                        System.out.println("\nThe dealer wins!");
                        dealerWins++;
                    }
                    else if(dealerTotal > playerTotal && dealerTotal < 22)
                    {
                        System.out.println("\nThe dealer wins!");
                        dealerWins++;
                    }
                    else if(dealerTotal > playerTotal && dealerTotal > 21)
                    {
                        System.out.println("Bust!");
                        System.out.println("\nHooray, you win!");
                        playerWins++;
                    } 
                    else
                    {
                        System.out.println("\nHooray, you win!");
                        playerWins++;
                    }
                }
            } 
            else
            {
                System.out.println("Bust!");
                System.out.println("\nThe dealer wins!");
                dealerWins++;
            }
            System.out.println("You have won " + playerWins + " times.");
            System.out.println("The dealer has won " + dealerWins + " times.");
            double winPercentage = ((double)playerWins/(playerWins+dealerWins))*100;
            System.out.printf("Your win percentage is: %.2f percent.%n", winPercentage);
            System.out.print("\nDo you want to play again? (yes/no) \n");
            decision = input.nextLine();
        } 
    }
}