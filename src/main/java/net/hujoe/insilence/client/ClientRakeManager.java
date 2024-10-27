package net.hujoe.insilence.client;

import java.util.ArrayList;

public class ClientRakeManager {
    private ArrayList<String> usernames;
    public ClientRakeManager(){
        usernames = new ArrayList<String>();
    }

    public void addUser(String username){
        usernames.add(username);
    }

    public void removeUser(String username){
        usernames.remove(username);
    }

    public boolean isRake(String username){
        return usernames.contains(username);
    }

    public void toggleUser(String username){
        if (isRake(username)){
            removeUser(username);
        } else {
            addUser(username);
        }
    }

    private static final ClientRakeManager rakeManager = new ClientRakeManager();

    public static ClientRakeManager getRakeManager(){
        return rakeManager;
    }
}
