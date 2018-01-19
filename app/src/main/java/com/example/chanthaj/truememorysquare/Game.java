package com.example.chanthaj.truememorysquare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game {

    private String suite;
    private int score;
    private int level;
    private int life;
    private HashMap<Integer,Integer> numberOfButton;

    public Game(){
        this.score=0;
        this.suite="";
        this.level=1;
        this.life=3;
        this.numberOfButton= new HashMap<>();
        this.numberOfButton.put(1,9);
        this.numberOfButton.put(2,9);
        this.numberOfButton.put(3,16);
        this.numberOfButton.put(4,16);

    }

    private void debutTour(){

    }

    private int getNumberOfButton(){
        return this.numberOfButton.get(this.level);
    }

    private void createSuite(){
        Random r = new Random();
        for (int i = 1; i <= this.level*2 ; i++) {
            this.suite = Integer.toString(r.nextInt(10));
        }
    }


    private void nextLevel(){
        this.level++;
    }
    private void levelBefore(){
        if(this.level > 1)
            this.level--;
    }

    public void verif(String userEntry){
        if(userEntry.compareTo(this.suite) == 0){
            nextLevel();
        }
        else{
            levelBefore();
            this.life--; //TODO: fonction à part
        }
    }
}