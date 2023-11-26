package world.ntdi.chatcoloring.config;

public enum ConfigOptions {
    RELOAD_PERMISSION("reload-permission"),
    CHATCOLOR_COMMAND_PERMISSION("chatcolor-permission"),
    CHATCOLOR_GUI_TITLE("chatcolor-gui-title"),
    RED_SECTION("sections.red"),
    YELLOW_SECTION("sections.yellow"),
    GREEN_SECTION("sections.green"),
    TEAL_SECTION("sections.teal"),
    BLUE_SECTION("sections.blue"),
    PURPLE_SECTION("sections.purple"),
    WHITE_SECTION("sections.white");

    private final String p_name;
    ConfigOptions(final String p_pName) {
        p_name = p_pName;
    }

    public String getName() {
        return p_name;
    }

    @Override
    public String toString() {
        return p_name;
    }
}
