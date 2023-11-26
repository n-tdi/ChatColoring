package world.ntdi.chatcoloring.gui;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import world.ntdi.chatcoloring.ChatColoring;
import world.ntdi.chatcoloring.config.ColorSection;
import world.ntdi.chatcoloring.config.ConfigOptions;
import world.ntdi.chatcoloring.util.HexUtil;
import world.ntdi.chatcoloring.util.ItemBuilder;

import java.util.UUID;

@Getter
public class ColorSelectGui extends GUI {
    private final ChatColoring m_chatColoring;
    private final Player m_player;
    public ColorSelectGui(final ChatColoring p_coloring, final Player p_player) {
        super(
                ChatColor.translateAlternateColorCodes('&',
                        p_coloring.getConfig().getString(ConfigOptions.CHATCOLOR_GUI_TITLE.toString())),
                3, p_coloring);
        m_chatColoring = p_coloring;
        m_player = p_player;
        initCategory();
    }

    public void initCategory() {
        basics();

        int startingIndex = 10;

        final ColorSection colorSectionRed = new ColorSection(ConfigOptions.RED_SECTION, m_chatColoring.getConfig());
        addButton(Button.create(ItemBuilder.of(Material.RED_WOOL, 1,
                ChatColor.translateAlternateColorCodes('&',colorSectionRed.getName()),
                ChatColor.translateAlternateColorCodes('&',colorSectionRed.getLore())).build(),
                p_inventoryClickEvent -> initSelection(colorSectionRed, p_inventoryClickEvent)), startingIndex);
        startingIndex += 1;

        final ColorSection colorSectionYellow = new ColorSection(ConfigOptions.YELLOW_SECTION, m_chatColoring.getConfig());
        addButton(Button.create(ItemBuilder.of(Material.YELLOW_WOOL, 1,
                        ChatColor.translateAlternateColorCodes('&',colorSectionYellow.getName()),
                        ChatColor.translateAlternateColorCodes('&',colorSectionYellow.getLore())).build(),
                p_inventoryClickEvent -> initSelection(colorSectionYellow, p_inventoryClickEvent)), startingIndex);
        startingIndex += 1;

        final ColorSection colorSectionGreen = new ColorSection(ConfigOptions.GREEN_SECTION, m_chatColoring.getConfig());
        addButton(Button.create(ItemBuilder.of(Material.GREEN_WOOL, 1,
                        ChatColor.translateAlternateColorCodes('&',colorSectionGreen.getName()),
                        ChatColor.translateAlternateColorCodes('&',colorSectionGreen.getLore())).build(),
                p_inventoryClickEvent -> initSelection(colorSectionGreen, p_inventoryClickEvent)), startingIndex);
        startingIndex += 1;

        final ColorSection colorSectionTeal = new ColorSection(ConfigOptions.TEAL_SECTION, m_chatColoring.getConfig());
        addButton(Button.create(ItemBuilder.of(Material.LIGHT_BLUE_WOOL, 1,
                        ChatColor.translateAlternateColorCodes('&',colorSectionTeal.getName()),
                        ChatColor.translateAlternateColorCodes('&',colorSectionTeal.getLore())).build(),
                p_inventoryClickEvent -> initSelection(colorSectionTeal, p_inventoryClickEvent)), startingIndex);
        startingIndex += 1;

        final ColorSection colorSectionBlue = new ColorSection(ConfigOptions.BLUE_SECTION, m_chatColoring.getConfig());
        addButton(Button.create(ItemBuilder.of(Material.BLUE_WOOL, 1,
                        ChatColor.translateAlternateColorCodes('&',colorSectionBlue.getName()),
                        ChatColor.translateAlternateColorCodes('&',colorSectionBlue.getLore())).build(),
                p_inventoryClickEvent -> initSelection(colorSectionBlue, p_inventoryClickEvent)), startingIndex);
        startingIndex += 1;

        final ColorSection colorSectionPurple = new ColorSection(ConfigOptions.PURPLE_SECTION, m_chatColoring.getConfig());
        addButton(Button.create(ItemBuilder.of(Material.PURPLE_WOOL, 1,
                        ChatColor.translateAlternateColorCodes('&',colorSectionPurple.getName()),
                        ChatColor.translateAlternateColorCodes('&',colorSectionPurple.getLore())).build(),
                p_inventoryClickEvent -> initSelection(colorSectionPurple, p_inventoryClickEvent)), startingIndex);
        startingIndex += 1;

        final ColorSection colorSectionWhite = new ColorSection(ConfigOptions.WHITE_SECTION, m_chatColoring.getConfig());
        addButton(Button.create(ItemBuilder.of(Material.WHITE_WOOL, 1,
                        ChatColor.translateAlternateColorCodes('&',colorSectionWhite.getName()),
                        ChatColor.translateAlternateColorCodes('&',colorSectionWhite.getLore())).build(),
                p_inventoryClickEvent -> initSelection(colorSectionWhite, p_inventoryClickEvent)), startingIndex);

        final String color = m_chatColoring.readColor(m_player.getUniqueId());
        final String currentColorMsg = (color != null ?
                HexUtil.translateHexColorCodes(color, "You're currently using this color!") :
                ChatColor.RED + "No color currently selected."
        );

        addButton(Button.createBasic(
                ItemBuilder.of(Material.ENDER_EYE, 1,
                        currentColorMsg).build()
        ), 22);

        update();
    }

    public void initSelection(ColorSection p_colorSection, InventoryClickEvent p_inventoryClickEvent) {
        final Player player = (Player) p_inventoryClickEvent.getWhoClicked();

        if (!p_colorSection.getPermission().equals("none") && !player.hasPermission(p_colorSection.getPermission())) {
            player.closeInventory();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', p_colorSection.getNoPermission()));
        }

        basics();

        int startingIndex = 10;
        addButton(Button.create(
                ItemBuilder.of(Material.NAME_TAG, 1,
                        HexUtil.translateHexColorCodes(p_colorSection.getHexCode1(), "Select This Color!")).build(),
                p_inventoryClickEvent1 -> {
                    selectColor(player.getUniqueId(), p_colorSection.getColor().toString() + ".color-hex-option-1");
                    player.closeInventory();
                    player.sendMessage(HexUtil.translateHexColorCodes(p_colorSection.getHexCode1(), "Now using this color for your chat messages."));
                }
        ), startingIndex);

        startingIndex += 1;

        addButton(Button.create(
                ItemBuilder.of(Material.NAME_TAG, 1,
                        HexUtil.translateHexColorCodes(p_colorSection.getHexCode2(), "Select This Color!")).build(),
                p_inventoryClickEvent1 -> {
                    selectColor(player.getUniqueId(), p_colorSection.getColor().toString() + ".color-hex-option-2");
                    player.closeInventory();
                    player.sendMessage(HexUtil.translateHexColorCodes(p_colorSection.getHexCode2(), "Now using this color for your chat messages."));
                }
        ), startingIndex);

        startingIndex += 1;

        addButton(Button.create(
                ItemBuilder.of(Material.NAME_TAG, 1,
                        HexUtil.translateHexColorCodes(p_colorSection.getHexCode3(), "Select This Color!")).build(),
                p_inventoryClickEvent1 -> {
                    selectColor(player.getUniqueId(), p_colorSection.getColor().toString() + ".color-hex-option-3");
                    player.closeInventory();
                    player.sendMessage(HexUtil.translateHexColorCodes(p_colorSection.getHexCode3(), "Now using this color for your chat messages."));
                }
        ), startingIndex);

        startingIndex += 1;

        addButton(Button.create(
                ItemBuilder.of(Material.NAME_TAG, 1,
                        HexUtil.translateHexColorCodes(p_colorSection.getHexCode4(), "Select This Color!")).build(),
                p_inventoryClickEvent1 -> {
                    selectColor(player.getUniqueId(), p_colorSection.getColor().toString() + ".color-hex-option-4");
                    player.closeInventory();
                    player.sendMessage(HexUtil.translateHexColorCodes(p_colorSection.getHexCode4(), "Now using this color for your chat messages."));
                }
        ), startingIndex);

        startingIndex += 1;

        addButton(Button.create(
                ItemBuilder.of(Material.NAME_TAG, 1,
                        HexUtil.translateHexColorCodes(p_colorSection.getHexCode5(), "Select This Color!")).build(),
                p_inventoryClickEvent1 -> {
                    selectColor(player.getUniqueId(), p_colorSection.getColor().toString() + ".color-hex-option-5");
                    player.closeInventory();
                    player.sendMessage(HexUtil.translateHexColorCodes(p_colorSection.getHexCode5(), "Now using this color for your chat messages."));
                }
        ), startingIndex);

        startingIndex += 1;

        addButton(Button.create(
                ItemBuilder.of(Material.NAME_TAG, 1,
                        HexUtil.translateHexColorCodes(p_colorSection.getHexCode6(), "Select This Color!")).build(),
                p_inventoryClickEvent1 -> {
                    selectColor(player.getUniqueId(), p_colorSection.getColor().toString() + ".color-hex-option-6");
                    player.closeInventory();
                    player.sendMessage(HexUtil.translateHexColorCodes(p_colorSection.getHexCode6(), "Now using this color for your chat messages."));
                }
        ), startingIndex);

        startingIndex += 1;

        addButton(Button.create(
                ItemBuilder.of(Material.NAME_TAG, 1,
                        HexUtil.translateHexColorCodes(p_colorSection.getHexCode7(), "Select This Color!")).build(),
                p_inventoryClickEvent1 -> {
                    selectColor(player.getUniqueId(), p_colorSection.getColor().toString() + ".color-hex-option-7");
                    player.closeInventory();
                    player.sendMessage(HexUtil.translateHexColorCodes(p_colorSection.getHexCode7(), "Now using this color for your chat messages."));
                }
        ), startingIndex);

        startingIndex += 1;

        addButton(Button.create(
                ItemBuilder.of(Material.ARROW, 1,
                        ChatColor.GOLD + "Go Back").build(),
                p_inventoryClickEvent1 -> {
                    initCategory();
                }
        ), 22);

        update();
    }

    public void selectColor(final UUID p_uuid, final String location) {
        m_chatColoring.setColor(p_uuid, location);
    }

    public void basics() {
        clear();
        fill(0, 3*9, ItemBuilder.of(Material.BLACK_STAINED_GLASS_PANE, 1, " ").build());
    }

}
