package world.ntdi.chatcoloring.listener;

import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import world.ntdi.chatcoloring.ChatColoring;
import world.ntdi.chatcoloring.util.HexUtil;

@AllArgsConstructor
public class ChatListener implements Listener {
    private ChatColoring m_chatColoring;
    @EventHandler
    public void onChat(AsyncPlayerChatEvent p_asyncPlayerChatEvent) {
        final String color = m_chatColoring.readColor(p_asyncPlayerChatEvent.getPlayer().getUniqueId());

        if (color != null) {
            p_asyncPlayerChatEvent.setMessage(HexUtil.translateHexColorCodes(color, p_asyncPlayerChatEvent.getMessage()));
        }
    }
}
