package octils.base.util;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URL;
import java.util.Scanner;
import java.util.UUID;

public class MojangApi {

    private MojangApi() {
        throw new RuntimeException("Cannot instantiate a utility class.");
    }

    public static UUID getUUID(String name) {
        try {
            Scanner scanner = new Scanner(new URL("https://api.mojang.com/users/profiles/minecraft/"+name).openStream(), "UTF-8");
            String scan = scanner.next();
            scan = scan.substring(scan.lastIndexOf('{'), scan.lastIndexOf('}') + 1);
            return uuidWithDashes(((JSONObject) new JSONParser().parse(scan)).get("id").toString());
        } catch (Exception exc) {
            return null;
        }
    }


    public static String getName(UUID uuid) {
        try {
            Scanner scanner = new Scanner(new URL("https://api.mojang.com/user/profiles/"
            + uuid.toString().replace("-","") + "/names").openStream(), "UTF-8");
            String s = scanner.next();
            s = s.substring(s.lastIndexOf('{'), s.lastIndexOf('}') + 1);
            return ((JSONObject) new JSONParser().parse(s)).get("name").toString();
        } catch (Exception exc) {
            return null;
        }
    }

    private static UUID uuidWithDashes(String uuid) {
        UUID ud = null;
        if (!uuid.contains("-") && uuid.length() == 32){
            ud = UUID.fromString(uuid.replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)",
    "$1-$2-$3-$4-$5"));
        }
        return ud;
    }
}
