package world.ntdi.chatcoloring.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import world.ntdi.chatcoloring.ChatColoring;
import world.ntdi.chatcoloring.config.ConfigOptions;
import world.ntdi.chatcoloring.gui.ColorSelectGui;

import java.util.Collections;
import java.util.List;

public class ChatColorCommand implements TabExecutor {
    private final ChatColoring m_chatColoring;

    public ChatColorCommand(final ChatColoring p_chatColoring) {
        m_chatColoring = p_chatColoring;
    }

    @Override
    public boolean onCommand(final CommandSender p_commandSender, final Command p_command, final String p_s, final String[] p_strings) {
        if (p_commandSender.hasPermission(m_chatColoring.getConfig().getString(ConfigOptions.RELOAD_PERMISSION.toString()))) {
            if (p_strings.length > 0) {
                m_chatColoring.getConfig().reload();
                p_commandSender.sendMessage(ChatColor.GREEN + "Reload complete.");
                return true;
            }
        }

        if (!(p_commandSender instanceof Player p_player)) {
            return false;
        }

        new ColorSelectGui(m_chatColoring, p_player).open(p_player);

        return true;
    }

    @Override
    public List<String> onTabComplete(final CommandSender p_commandSender, final Command p_command, final String p_s, final String[] p_strings) {
        if (p_commandSender.hasPermission(m_chatColoring.getConfig().getString(ConfigOptions.RELOAD_PERMISSION.toString()))) {
            return Collections.singletonList("reload");
        }
        return Collections.singletonList("");
    }
}
