package com.mygdx.game.ev.general;

/**
 * Created by erikstrid on 2017-05-10.
 */

public class Shout {

   public enum Tag {
       NEW_TP_DIR,
       STORE,
       STORE_UPGRADE,
       DRILL,
       FUELREPAIR,
       HULLREPAIR,
       FUELUPGRADE,
       HULLUPGRADE;
   }
    private final Tag tag;

    public Shout(Tag tag){
        this.tag = tag;
    }

    public Tag getTag(){
        return this.tag;
    }
}
