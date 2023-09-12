package com.hampus.rps;
import java.io.Serializable;

public class Player implements Serializable
{
    private String player1;
    private String player2;
    private int player1score = 0;
    private int player2score = 0;

    public void setPlayer1name(String playerName)
    {
        this.player1 = playerName;
    }

    public String getPlayer1name()
    {
        return this.player1;
    }

    public void setPlayer2name(String playerName)
    {
        this.player2 = playerName;
    }

    public String getPlayer2name()
    {
        return this.player2;
    }

    public void addScorePlayer1()
    {
        this.player1score++;
    }

    public void addScorePlayer2()
    {
        this.player2score++;
    }

    public int getPlayer1score()
    {
        return this.player1score;
    }

    public int getPlayer2score()
    {
        return this.player2score;
    }
    public void resetScores()
    {
        player1score = 0;
        player2score = 0;
    }
}
