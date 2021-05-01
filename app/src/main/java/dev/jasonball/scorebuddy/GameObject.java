package dev.jasonball.scorebuddy;

import java.io.*;
import java.util.*;


public class GameObject {
    private ArrayList<String> players;
    private ArrayList<Integer> scores;

    GameObject() {
        this.players = null;
        this.scores = null;
    }
    GameObject(ArrayList<String> players, ArrayList<Integer> scores) {
        this.players = players;
        this.scores = scores;
    }
    public ArrayList<String> getPlayers() {
        return players;
    }
    public ArrayList<Integer> getScores() {
        return scores;
    }
    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }
    public void setsScores(ArrayList<Integer> scores) {
        this.scores = scores;
    }
}
