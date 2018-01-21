package com.example.chanthaj.truememorysquare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game {

    private ArrayList<Integer> suite;
    private int score;
    private int level;
    private int levelLife;
    private int life;
    //key = level, value = number of columns/lines (it's a square)
    private HashMap<Integer,Integer> numberOfButton;

    public Game(){
        this.score=0;
        this.level=1;
        this.levelLife = 3;
        this.life = 3;

        this.numberOfButton= new HashMap<>();
        this.numberOfButton.put(1,3);
        this.numberOfButton.put(2,3);
        this.numberOfButton.put(3,4);
        this.numberOfButton.put(4,4);
        this.numberOfButton.put(5,5);
        this.numberOfButton.put(6,5);
        this.numberOfButton.put(7,6);
        this.numberOfButton.put(8,6);
        this.numberOfButton.put(9,7);
        this.numberOfButton.put(10,7);

        this.suite= new ArrayList<>();
        createSuite();

    }

    public boolean gameOver(){
       return this.life<=0;
    }

    public boolean levelLost(){
        return this.levelLife<=0;
    }

    public boolean nextLevel(ArrayList<Integer> userEntries){
        return userEntries.size() == this.suite.size();
    }

    public void createSuite(){
        this.suite.clear();
        Random r = new Random();
        for (int i = 1; i <= this.level*2 ; i++) {
            //-1)+1 ----> +1 en dehors du random car doit pas etre 0, -1 car doit etre en 1 et le nombre de carrés qu'il y aura et non le nb de carré+1
            int newEntry = r.nextInt(getNumButtons()*getNumButtons()-1)+1;
            while(this.suite.contains(newEntry))
                newEntry = r.nextInt(getNumButtons()*getNumButtons()-1)+1;
            this.suite.add(newEntry);
        }
    }

    //Verifie si le carré appuyé est bon
    public boolean verifCarre(int userEntry){
        return this.suite.contains(userEntry);
    }

    public void calculScore(){
        this.score = this.score + this.level;
    }


    /******** GETTERS AND SETTERS  ***********/
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevelLife() {
        return levelLife;
    }

    public void setLevelLife(int levelLife) {
        this.levelLife = levelLife;
    }

    public int getNumButtons(){
        return this.numberOfButton.get(this.level);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }


    public ArrayList<Integer> getSuite(){
        return this.suite;
    }

    public String getStringSuite(){
        String s ="";
        for(int i = 0; i < this.suite.size(); i++)
        {
            s += Integer.toString(this.suite.get(i)) + " ";
        }
        return s;
    }
}