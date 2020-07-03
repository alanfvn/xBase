package octils.base.messages;

import octils.base.util.FUtils;

public class Messages
{
    public static String NO_PERMISSIONS;
    public static String PLAYER_NOT_FOUND;
    public static String NO_CONSOLE;
    public static String NO_DATA_ERROR;
    public static String SYNTAX;
    public static String CANT_DO_THIS_STATE;
    public static String MUST_BE_NUMBER;
    
    static {
        NO_PERMISSIONS = FUtils.color("&cYou don't have permission to do this!");
        PLAYER_NOT_FOUND = FUtils.color("&cPlayer not found!");
        NO_CONSOLE = FUtils.color("&cYou must be a player to use this command!");
        NO_DATA_ERROR = FUtils.color("&c&oSorry your data couldn't be loaded, please try again later..");
        SYNTAX = FUtils.color("&cPlease check your syntax!");
        CANT_DO_THIS_STATE = FUtils.color("&cYou can't do that in the current state!");
        MUST_BE_NUMBER = FUtils.color("&cThe amount must be a number!");
    }
}
