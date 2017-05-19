package com.mygdx.game.event.general;

/**
 * Created by erikstrid on 2017-05-10.
 */

public class Shout {

   public enum Tag {
       NEW_TP_DIR,
       KEY_A,
       STORE,
       DRILL,
       FUELREPAIR,
       HULLREPAIR;
   }
    private final Tag tag;

    public Shout(Tag tag){
        this.tag = tag;
    }

    public Tag getTag(){
        return this.tag;
    }
}
