package world.ntdi.chatcoloring.config;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

@Getter
public class ColorSection {
    private final ConfigOptions m_color;
    private final Config m_config;

    private final Material m_material;
    private final String m_name;
    private final String m_lore;
    private final String m_noPermission;
    private final String m_permission;
    private final String m_hexCode1;
    private final String m_hexCode2;
    private final String m_hexCode3;
    private final String m_hexCode4;
    private final String m_hexCode5;
    private final String m_hexCode6;
    private final String m_hexCode7;

    public ColorSection(final ConfigOptions p_color, final Config p_config) {
        m_color = p_color;
        m_config = p_config;

        final ConfigurationSection configurationSection = m_config.getConfigurationSection(p_color.toString());

        m_material = Material.valueOf(configurationSection.getString("item-type"));
        m_name = configurationSection.getString("item-name");
        m_lore = configurationSection.getString("item-lore");
        m_noPermission = configurationSection.getString("no-permission-message");
        m_permission = configurationSection.getString("permission");
        m_hexCode1 = configurationSection.getString("color-hex-option-1");
        m_hexCode2 = configurationSection.getString("color-hex-option-2");
        m_hexCode3 = configurationSection.getString("color-hex-option-3");
        m_hexCode4 = configurationSection.getString("color-hex-option-4");
        m_hexCode5 = configurationSection.getString("color-hex-option-5");
        m_hexCode6 = configurationSection.getString("color-hex-option-6");
        m_hexCode7 = configurationSection.getString("color-hex-option-7");
    }
}
