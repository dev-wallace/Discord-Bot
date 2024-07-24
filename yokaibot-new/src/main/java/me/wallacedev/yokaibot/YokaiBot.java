package me.wallacedev.yokaibot;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.EnumSet;
import javax.security.auth.login.LoginException;


import me.wallacedev.CommandManager;
import me.wallacedev.Listener;
import me.wallacedev.commands.*;
import me.wallacedev.commands.music.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class YokaiBot {
    
    public static void main(String[] args) throws LoginException, InterruptedException, IOException, URISyntaxException {
        
        
       
        String token = "meu token"; 
        
       
        JDA jda = JDABuilder.createDefault(token, EnumSet.allOf(GatewayIntent.class)).build();
      
        CommandManager manager = new CommandManager();
        manager.add(new Embed());
        manager.add(new Buttons());
        manager.add(new Modals());
        manager.add(new Sum());
        manager.add(new Roles());
        manager.add(new Mute());
        manager.add(new Unmute());
        manager.add(new Play()); 
          manager.add(new Skip());
        manager.add(new Stop());
        manager.add(new NowPlaying());
        manager.add(new Queue());
        manager.add(new Repeat());

        
        jda.addEventListener(manager);

        // Aguarda até que o JDA esteja totalmente carregado
        jda.awaitReady();

        
        jda.addEventListener(new Listener());
    }
}
