package world.ntdi.chatcoloring.util;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexUtil {
    public static final char COLOR_CHAR = ChatColor.COLOR_CHAR;

    public static String translateHexColorCodes(String startTag, String message) {
        startTag = startTag.replaceAll("#", "");

        final String prefix = COLOR_CHAR + "x"
                + COLOR_CHAR + startTag.charAt(0) + COLOR_CHAR + startTag.charAt(1)
                + COLOR_CHAR + startTag.charAt(2) + COLOR_CHAR + startTag.charAt(3)
                + COLOR_CHAR + startTag.charAt(4) + COLOR_CHAR + startTag.charAt(5);

        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes(COLOR_CHAR, prefix + message);
    }
    public static String translateHexColorCodes(String startTag, String endTag, String message) {
        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find())
        {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(buffer).toString();
    }
}
