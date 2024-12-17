package net.hujoe.insilence.client;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hujoe.insilence.network.payloads.RakeUpdatePayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.util.ArrayList;

public class ClientRakeManager {
    private ArrayList<String> usernames;
    private ArrayList<String> miceUsernames;
    public ClientRakeManager(){
        usernames = new ArrayList<>();
        miceUsernames = new ArrayList<>();
    }

    public void addUser(String username, ArrayList<String> list){
        list.add(username);
    }

    public void removeUser(String username, ArrayList<String> list){
        list.remove(username);
    }

    public boolean isRake(String username){
        return usernames.contains(username);
    }
    public boolean isMouse(String username){
        return miceUsernames.contains(username);
    }

    public void toggleRakeUser(String username){
        if (isRake(username)){
            removeUser(username, this.usernames);
        } else {
            addUser(username, this.usernames);
        }
    }

    public void toggleMouseUser(String username){
        if (isMouse(username)){
            removeUser(username, this.miceUsernames);
        } else {
            addUser(username, this.miceUsernames);
        }
    }

    public void receiveRakeList(ArrayList<String> list){
        usernames = list;
    }
    public void receiveMouseList(ArrayList<String> list){
        miceUsernames = list;
    }

    private static final ClientRakeManager rakeManager = new ClientRakeManager();

    public static ClientRakeManager getRakeManager(){
        return rakeManager;
    }
}
