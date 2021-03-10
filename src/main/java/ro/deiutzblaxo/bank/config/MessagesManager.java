package ro.deiutzblaxo.bank.config;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ro.deiutzblaxo.bank.Main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MessagesManager {

    private Main plugin;
    private File file;
    private FileConfiguration config;


    public MessagesManager (Main plugin){
        this.plugin = plugin;

        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }
        file = new File(plugin.getDataFolder(),"lang.yml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            config = YamlConfiguration.loadConfiguration(file);
            for(MESSAGE message : MESSAGE.values()){
                setDefault(message);
            }
            for(MESSAGELIST message : MESSAGELIST.values()){
                setDefaultList(message);
            }
        }

    }
    public String get(MESSAGE message){
        config= YamlConfiguration.loadConfiguration(file);

        if(config.contains(message.path)){
            return config.getString(message.path);
        }else{
            setDefault(message);
            return get(message);
        }
    }
    public List<String> getList(MESSAGELIST list){
        config= YamlConfiguration.loadConfiguration(file);

        if(config.contains(list.path)){
            return config.getStringList(list.path);
        }else{
            setDefaultList(list);
            return getList(list);
        }
    }
    private void setDefaultList(MESSAGELIST message){
        config.set(message.path,message._default);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setDefault(MESSAGE message){
        config.set(message.path,message.Default);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String translate(String string){
        return ChatColor.translateAlternateColorCodes('&',string);
    }
}
