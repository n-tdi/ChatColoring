package world.ntdi.chatcoloring;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import world.ntdi.chatcoloring.command.ChatColorCommand;
import world.ntdi.chatcoloring.config.Config;
import world.ntdi.chatcoloring.listener.ChatListener;

import java.io.IOException;
import java.util.UUID;

public final class ChatColoring extends JavaPlugin {
    @Getter
    private Config m_config;
    @Getter
    private Config m_playerSelectedColorConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        m_config = new Config("config.yml", this);
        m_playerSelectedColorConfig = new Config("player-config.yml", this);

        getCommand("chatcolor").setExecutor(new ChatColorCommand(this));
        getCommand("chatcolor").setTabCompleter(new ChatColorCommand(this));

        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }

    @Override
    public void onDisable() {
        try {
            m_playerSelectedColorConfig.save();
        } catch (IOException p_e) {
            throw new RuntimeException(p_e);
        }
    }

    public void setColor(final UUID p_uuid, final String p_location) {
        m_playerSelectedColorConfig.set(String.valueOf(p_uuid), p_location);
    }

    public String readColor(final UUID p_uuid) {
        final String location = m_playerSelectedColorConfig.getString(String.valueOf(p_uuid));

        if (location == null) {
            return null;
        }

        return m_config.getString(location);
    }
}
