package net.hujoe.insilence.server;

import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;

public class RakeManager {
    private ArrayList<String> usernames;
    public RakeManager(){
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

    private static final RakeManager rakeManager = new RakeManager();

    public static RakeManager getRakeManager(){
        return rakeManager;
    }
}
