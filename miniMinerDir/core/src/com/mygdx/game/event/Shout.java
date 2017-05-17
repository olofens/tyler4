package com.mygdx.game.event;

/**
 * Created by erikstrid on 2017-05-10.
 */

public class Shout {

   public enum Tag {
       LEFTMINER,
       RIGHTMINER,
       UPRIGHTMINER,
       UPLEFTMINER,
       STORE,
       DRILL,
       FUEL,
       HULL;



   }
    private final Tag tag;

    public Shout(Tag tag){
        this.tag = tag;
    }

    public Tag getTag(){
        return this.tag;
    }
}
