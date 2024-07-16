package me.wallacedev.yokaibot;

import java.util.EnumSet;
import javax.security.auth.login.LoginException;

import me.wallacedev.Listener;
import me.wallacedev.commands.Sum;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class YokaiBot {
    public static void main(String[] args) throws LoginException {
        
        String token = "meu_token";
        
       
        JDA jda = JDABuilder.createDefault(token, EnumSet.allOf(GatewayIntent.class)).build();
        jda.addEventListener(new Listener());
        jda.addEventListener(new Sum());
    }
}

