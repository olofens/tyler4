package com.mygdx.game.items;

/**
 * Created by walling on 5/16/2017.
 */

public class Inventory {
    private static int cash = 0;
    private static int coal = 0;
    private static int iron = 0;
    private static int gold = 0;
    private static int lapiz = 0;
    private static int diamond = 0;



    public static void sellInventory(){
        cash+=(coal*20)+(iron*50)+(gold*120)+(lapiz*250)+(diamond*520);
        clearInventory();
    }

    public static void clearInventory(){
        setCoal(0);
        setIron(0);
        setGold(0);
        setLapiz(0);
        setDiamond(0);
    }

    public static int getCash() {
        return cash;
    }

    public static void setCash(int cash) {
        Inventory.cash = cash;
    }

    public static int getCoal() {
        return coal;
    }

    public static void setCoal(int coal) {
        Inventory.coal = coal;
    }

    public static int getIron() {
        return iron;
    }

    public static void setIron(int iron) {
        Inventory.iron = iron;
    }

    public static int getGold() {
        return gold;
    }

    public static void setGold(int gold) {
        Inventory.gold = gold;
    }

    public static int getLapiz() {
        return lapiz;
    }

    public static void setLapiz(int lapiz) {
        Inventory.lapiz = lapiz;
    }

    public static int getDiamond() {
        return diamond;
    }

    public static void setDiamond(int diamond) {
        Inventory.diamond = diamond;
    }
}

