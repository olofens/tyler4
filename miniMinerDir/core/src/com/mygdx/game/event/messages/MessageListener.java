package com.mygdx.game.event.messages;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by erikstrid on 2017-05-25.
 */

public enum MessageListener {

    BUS;

    private List<IMessageListener> listeners = new ArrayList<IMessageListener>();

    public void addListener(IMessageListener listener) {
        listeners.add(listener);
    }

    public void updateMessage(MessageData messageData) {

        for( IMessageListener messageUpdater : listeners){
            messageUpdater.update(messageData);
        }
    }



}
