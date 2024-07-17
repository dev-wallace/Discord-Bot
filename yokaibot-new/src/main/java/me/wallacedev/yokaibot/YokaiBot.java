package me.wallacedev.yokaibot;

import java.util.EnumSet;
import javax.security.auth.login.LoginException;

import me.wallacedev.CommandManager;
import me.wallacedev.Listener;
import me.wallacedev.commands.Embed;
import me.wallacedev.commands.Modals;
import me.wallacedev.commands.Sum;
import me.wallacedev.commands.Buttons;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class YokaiBot {
    public static void main(String[] args) throws LoginException {
        
        String token = "meu token"; 
        
        // Inicializa o JDA com todos os intents
        JDA jda = JDABuilder.createDefault(token, EnumSet.allOf(GatewayIntent.class)).build();
        
        // Inicializa o CommandManager e adiciona comandos
        CommandManager manager = new CommandManager();
        manager.add(new Embed());
        manager.add(new Buttons());
        manager.add(new Modals());
        manager.add(new Sum());

        // Adiciona o CommandManager como um listener ao JDA
        jda.addEventListener(manager);

        // Adiciona outros listeners, se necessário
        jda.addEventListener(new Listener());
    }
}