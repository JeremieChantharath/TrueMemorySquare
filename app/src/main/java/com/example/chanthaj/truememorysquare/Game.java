package com.example.chanthaj.truememorysquare;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game {

    private ArrayList<Integer> suite;
    private int score;
    private int level;
    private int life;
    //key = level, value = number of columns/lines (it's a square)
    private HashMap<Integer,Integer> numberOfButton;

    public Game(){
        this.score=0;
        this.level=1;
        this.life=3;

        this.numberOfButton= new HashMap<>();
        this.numberOfButton.put(1,3);
        this.numberOfButton.put(2,3);
        this.numberOfButton.put(3,4);
        this.numberOfButton.put(4,4);

        this.suite= new ArrayList<>();
        createSuite();

    }

    private void debutTour(){

    }

    private int getNumberOfButton(){
        return this.numberOfButton.get(this.level);
    }

    private void createSuite(){
        this.suite.clear();
        Random r = new Random();
        for (int i = 1; i <= this.level*2 ; i++) {
            this.suite.add(r.nextInt(10));
        }
    }


    private void nextLevel(){
        this.level++;
    }
    private void levelBefore(){
        if(this.level > 1)
            this.level--;
    }

    public void verif(int userEntry){
        if(this.suite.contains(userEntry)){
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

    public int getNumButtons(){
        return this.numberOfButton.get(this.level);
    }

    public int[] getSuite(){
        int[] res= new int[this.suite.size()];
        for(int i=0; i <this.suite.size(); i++){
            res[i] = this.suite.get(i);
        }
        return res;
    }
}