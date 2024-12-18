package net.hujoe.insilence.server;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hujoe.insilence.network.payloads.MouseUpdatePayload;
import net.hujoe.insilence.network.payloads.RakeUpdatePayload;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

import java.util.ArrayList;

public class RakeManager {
    private ArrayList<String> usernames;
    private ArrayList<String> miceUsernames;
    public RakeManager(){
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

    public void toggleRakeUser(String username, World world){
        if (isMouse(username)){
            removeUser(username, this.miceUsernames);
            for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld) world)) {
                ServerPlayNetworking.send(player, new MouseUpdatePayload(username));
            }
        }

        if (isRake(username)){
            removeUser(username, this.usernames);
        } else {
            addUser(username, this.usernames);
        }
        for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld) world)) {
            ServerPlayNetworking.send(player, new RakeUpdatePayload(username));
        }
    }

    public void toggleMouseUser(String username, World world){
        if (isRake(username)){
            removeUser(username, this.usernames);
            for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld) world)) {
                ServerPlayNetworking.send(player, new RakeUpdatePayload(username));
            }
        }

        if (isMouse(username)){
            removeUser(username, this.miceUsernames);
        } else {
            addUser(username, this.miceUsernames);
        }
        for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld) world)) {
            ServerPlayNetworking.send(player, new MouseUpdatePayload(username));
        }
    }

    public ArrayList<String> getRakeList(){
        return usernames;
    }
    public ArrayList<String> getMouseList(){
        return miceUsernames;
    }

    private static final RakeManager rakeManager = new RakeManager();

    public static RakeManager getRakeManager(){
        return rakeManager;
    }
}
