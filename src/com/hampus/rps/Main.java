package com.hampus.rps;

import java.util.Scanner;

public class Main
{
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        //Initializing variables
        int p1roundScore = 0;
        int p2roundScore = 0;
        int totalScoreP1;
        int totalScoreP2;
        int playAgain = 0;
        int player1choice;
        int player2choice;
        String player1name;
        String player2name;
        GameData startGame = new GameData();
        Player currentPlayers = new Player();

        //First player menu
        while(true)
        {
            int userChoice = menu();
            switch(userChoice)
            {
                case 1 ->
                {
                    //Adds new players and resets scores to 0
                    startGame.addPlayers(newGame(currentPlayers));
                    startGame.getPlayers().get(0).resetScores();
                }
                case 2 ->
                {
                    //Loads saved game file
                    startGame.loadFromFile("C:/Users/Hampus/IdeaProjects/rock_paper_scissors/playerData.dat");
                    totalScoreP1 = startGame.getPlayers().get(0).getPlayer1score();
                    totalScoreP2 = startGame.getPlayers().get(0).getPlayer2score();
                    player1name = startGame.getPlayers().get(0).getPlayer1name();
                    player2name = startGame.getPlayers().get(0).getPlayer2name();
                    System.out.printf("Scores from last time was:\n%s with %d points\n%s with %d points\n", player1name, totalScoreP1, player2name, totalScoreP2);
                }
                case 3 ->
                {
                    System.out.println("Thanks for playing");
                    System.exit(0);
                }
                default ->
                {
                    System.out.println("Error! ABORTING");
                    System.exit(0);
                }
            }

            //Adding player names to a variable
            player1name = startGame.getPlayers().get(0).getPlayer1name();
            player2name = startGame.getPlayers().get(0).getPlayer2name();
            System.out.println("Alright lets play best of three.");

            //Starts do-while loop as long as the player want to keep playing
            do
            {
                //Prints round-scores
                System.out.printf("Player %s score : %d\n", player1name, p1roundScore);
                System.out.printf("Player %s score : %d\n", player2name, p2roundScore);

                //Records players choice for rock, paper or scissors
                player1choice = rps(startGame.getPlayers().get(0).getPlayer1name());
                player2choice = rps(startGame.getPlayers().get(0).getPlayer2name());

                //Draw condition
                if(player1choice == player2choice)
                {
                    System.out.println("It's A draw!");
                }
                //P2 win condition
                else if(player1choice == 1 && player2choice == 2 || player1choice == 2 && player2choice == 3 || player1choice == 3 && player2choice == 1)
                {
                    System.out.printf("Player %s wins!\n", startGame.getPlayers().get(0).getPlayer2name());
                    p2roundScore++;
                }
                //If no draw or P2 win, P1 wins.
                else
                {
                    System.out.printf("Player %s wins!\n", startGame.getPlayers().get(0).getPlayer1name());
                    p1roundScore++;
                }
                //Best of three controller
                if(p1roundScore == 3 || p2roundScore == 3)
                {
                    if(p1roundScore == 3)
                    {
                        startGame.getPlayers().get(0).addScorePlayer1();
                    }
                    if(p2roundScore == 3)
                    {
                        startGame.getPlayers().get(0).addScorePlayer2();
                    }

                    //Saves game
                    startGame.saveToFile("C:/Users/Hampus/IdeaProjects/rock_paper_scissors/playerData.dat");

                    System.out.println("The round is over\nWhat do you wanna do next?");
                    System.out.println("1. Another round\n2. Exit");

                    playAgain = input.nextInt();

                    //Adds total scores to variable and prints
                    totalScoreP1 = startGame.getPlayers().get(0).getPlayer1score();
                    totalScoreP2 = startGame.getPlayers().get(0).getPlayer2score();

                    System.out.printf("Total scores are:\n%s has %d points\n%s has %d points\n", player1name, totalScoreP1, player2name, totalScoreP2);

                    p1roundScore = 0;
                    p2roundScore = 0;
                }
            } while(playAgain != 2);
        }
    }

    /**
     * Method that presents a menu, new game, load game, exit
     * @return - an integer from the user
     */
    public static int menu()
    {
        System.out.println("Welcome to RPS, what would you like to do?");
        System.out.println("1 : New game\n2 : Load previous game\n3 : Exit");
        return input.nextInt();
    }

    /**
     * Method that gets player names and adds them to the player class
     * @param players - Object which stores player data
     * @return - Same object but with user input player names
     */
    public static Player newGame(Player players)
    {
        System.out.println("First let me get some names\nPlease enter the first players name:");
        String player1 = input.next();
        players.setPlayer1name(player1);
        System.out.printf("Player %s has been added\n", players.getPlayer1name());

        System.out.println("Now lets add player two:");
        String player2 = input.next();
        players.setPlayer2name(player2);
        System.out.printf("Player %s has been added\n", players.getPlayer1name());

        return players;
    }

    /**
     * Method that prints RPS options and request user input
     * @param playerName - Player name for ease of printing
     * @return - integer input
     */
    public static int rps(String playerName)
    {
        System.out.printf("player %s, please choose \n1 = Rock\n2 = Paper\n3 = Scissors\n", playerName);
        return input.nextInt();
    }
}

