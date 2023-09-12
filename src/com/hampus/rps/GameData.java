package com.hampus.rps;

import java.io.*;
import java.util.ArrayList;

public class GameData
{
    private ArrayList<Player> players = new ArrayList<>();

    public void saveToFile(String fileName)
    {
        try(FileOutputStream fos = new FileOutputStream(fileName); ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(players);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void addPlayers(Player players)
    {
        this.players.add(players);
    }

    public ArrayList<Player> getPlayers()
    {
        return this.players;
    }

    public void loadFromFile(String fileName)
    {
        File file = new File(fileName);
        if(file.exists())
        {
            try(FileInputStream fis = new FileInputStream(fileName); ObjectInputStream ois = new ObjectInputStream(fis))
            {
                players = (ArrayList<Player>) ois.readObject();
            }
            catch(IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("file does not exist: " + fileName);
        }
    }
}
