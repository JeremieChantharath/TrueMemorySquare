package com.example.chanthaj.truememorysquare;

import java.util.HashMap;
import java.util.Random;

public class Game {

    private String suite;
    private int score;
    private int level;
    private int life;
    //key = level, value = number of columns/lines (it's a square)
    private HashMap<Integer,Integer> numberOfButton;

    public Game(){
        this.score=0;
        this.suite="";
        this.level=1;
        this.life=3;
        this.numberOfButton= new HashMap<>();
        this.numberOfButton.put(1,3);
        this.numberOfButton.put(2,3);
        this.numberOfButton.put(3,4);
        this.numberOfButton.put(4,4);

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

    public void calculScore(){
        //TODO
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setNumberOfButton(HashMap<Integer, Integer> numberOfButton) {
        this.numberOfButton = numberOfButton;
    }
    public int getNumButtons(){
        return this.numberOfButton.get(this.level);
    }

}