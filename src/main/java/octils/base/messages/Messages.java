package octils.base.messages;

import octils.base.util.CC;

public class Messages
{
    public static String NO_PERMISSIONS,
                PLAYER_NOT_FOUND,
                NO_CONSOLE,
                NO_DATA_ERROR,
                SYNTAX,
                CANT_DO_THIS_STATE,
                MUST_BE_NUMBER;
    
    static {
        NO_PERMISSIONS = CC.color("&cYou don't have permission to do this!");
        PLAYER_NOT_FOUND = CC.color("&cPlayer not found!");
        NO_CONSOLE = CC.color("&cYou must be a player to use this command!");
        NO_DATA_ERROR = CC.color("&c&oSorry your data couldn't be loaded, please try again later..");
        SYNTAX = CC.color("&cPlease check your syntax!");
        CANT_DO_THIS_STATE = CC.color("&cYou can't do that in the current state!");
        MUST_BE_NUMBER = CC.color("&cThe amount must be a number!");
    }
}
