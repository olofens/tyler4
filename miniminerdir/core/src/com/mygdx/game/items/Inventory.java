package com.mygdx.game.items;

import com.mygdx.game.Utils.Constants;

/**
 * Created by walling on 5/16/2017.
 */

public class Inventory {
    private int cash;
    private int coal;
    private int iron;
    private int gold;
    private int lapiz;
    private int diamond;
    private int redstone;


    public Inventory(){
        this.cash = 1000;
        this.coal = 0;
        this.iron = 0;
        this.gold = 0;
        this.lapiz = 0;
        this.redstone = 0;
        this.diamond = 0;

    }

    public void sellInventory(){
        cash+=(coal* Constants.COAL_VALUE)+(iron*Constants.IRON_VALUE)+(gold*Constants.GOLD_VALUE)+(lapiz*Constants.LAPIZ_VALUE)
                +(redstone*Constants.REDSTONE_VALUE)+(diamond*Constants.DIAMOND_VALUE);
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

    public String getCashString() {
        Integer integerCash = (int) cash;
        return integerCash.toString();
    }

    public void decreaseCash(int cost){
        this.cash -= cost;
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

