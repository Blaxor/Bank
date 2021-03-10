package ro.deiutzblaxo.bank.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ro.deiutzblaxo.bank.Main;

import java.io.File;
import java.io.IOException;

public class ymlDataBase {

    File file;
    FileConfiguration fileConfiguration;

    public ymlDataBase(){
        if(!Main.getInstance().getDataFolder().exists()){
            Main.getInstance().getDataFolder().mkdir();
        }
        file = new File(Main.getInstance().getDataFolder(), "data.yml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        load();
    }
    public void load(){
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }
    public void save(){
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }
}
