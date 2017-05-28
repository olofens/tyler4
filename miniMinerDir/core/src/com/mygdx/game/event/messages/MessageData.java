package com.mygdx.game.event.messages;

/**
 * Created by erikstrid on 2017-05-25.
 */

public class MessageData {
    public enum MessageType {
        FUELREPAIR,
        HULLREPAIR,
        FUELUPGRADE,
        HULLUPGRADE,
        ERROR
    }

    private MessageType messageType;
    private int cash;

    public MessageData(MessageType messageType, int cash) {
        this.messageType = messageType;
        this.cash = cash;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public int getCash() {
        return cash;
    }
}
