package skyone.auth;

import java.util.ArrayList;
import java.util.List;

public final class LoginData {
    private static final List<String> RESTRICTS = new ArrayList<>();

    public static void addPlayer(String playerName) {
        String convertedName = playerName.toLowerCase();
        if (!RESTRICTS.contains(convertedName)) {
            RESTRICTS.add(convertedName);
        }
    }

    public static void removePlayer(String playerName) {
        String convertedName = playerName.toLowerCase();
        RESTRICTS.remove(convertedName);
    }

    public static boolean hasPlayer(String playerName) {
        return RESTRICTS.contains(playerName.toLowerCase());
    }
}
