package com.mygdx.game.items;

/**
 * Created by walling on 5/16/2017.
 */

public class Inventory {
    private int cash = 0;
    private int coal = 0;
    private int iron = 0;
    private int gold = 0;
    private int lapiz = 0;
    private int diamond = 0;
    private int redstone = 0;


    public Inventory(){
        this.cash = 0;
        this.coal = 0;
        this.iron = 0;
        this.gold = 0;
        this.lapiz = 0;
        this.redstone = 0;
        this.diamond = 0;

    }

    public void sellInventory(){
        cash+=(coal*20)+(iron*50)+(gold*120)+(lapiz*250)+(redstone*350)+(diamond*520);
        clearInventory();
    }

    private void clearInventory(){
        setCoal(0);
        setIron(0);
        setGold(0);
        setLapiz(0);
        setDiamond(0);
        setRedstone(0);
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getCoal() {
        return coal;
    }

    public void setCoal(int coal) {
        this.coal = coal;
    }

    public int getIron() {
        return iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLapiz() {
        return lapiz;
    }

    public void setLapiz(int lapiz) {
        this.lapiz = lapiz;
    }

    public int getRedstone() {
        return redstone;
    }

    public void setRedstone(int redstone) {
        this.redstone = redstone;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }
}

