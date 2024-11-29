package net.hujoe.insilence.server;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.hujoe.insilence.network.payloads.RakeUpdatePayload;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;

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
    public boolean isMouse(String username){ return false; }

    public void toggleUser(String username, World world){
        if (isRake(username)){
            removeUser(username);
        } else {
            addUser(username);
        }
        for (ServerPlayerEntity player : PlayerLookup.world((ServerWorld) world)) {
            ServerPlayNetworking.send(player, new RakeUpdatePayload(username));
        }
    }

    public ArrayList<String> getList(){
        return usernames;
    }

    private static final RakeManager rakeManager = new RakeManager();

    public static RakeManager getRakeManager(){
        return rakeManager;
    }
}
