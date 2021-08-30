package skyone.auth;

import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;

public class EventListener implements Listener {
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        LoginData.addPlayer(event.getPlayer().getName());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        LoginData.removePlayer(event.getPlayer().getName());
    }

    public static void cancelIfNotLoggedIn(Cancellable event) {
        if (event instanceof PlayerEvent) {
            if (LoginData.hasPlayer(((PlayerEvent) event).getPlayer().getName())) {
                event.setCancelled(true);
            }
        } else if (event instanceof InventoryOpenEvent) {
            if (LoginData.hasPlayer(((InventoryOpenEvent) event).getPlayer().getName())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void restrictMove(PlayerMoveEvent event) {
        cancelIfNotLoggedIn(event);
    }

    @EventHandler
    public void restrictInteract(PlayerInteractEvent event) {
        cancelIfNotLoggedIn(event);
    }

    @EventHandler
    public void restrictInteractAtEntity(PlayerInteractAtEntityEvent event) {
        cancelIfNotLoggedIn(event);
    }

    @EventHandler
    public void restrictPortal(PlayerPortalEvent event) {
        cancelIfNotLoggedIn(event);
    }

    @EventHandler
    public void restrictTeleport(PlayerTeleportEvent event) {
        cancelIfNotLoggedIn(event);
    }

    @EventHandler
    public void restrictOpenInventory(InventoryOpenEvent event) {
        cancelIfNotLoggedIn(event);
    }
}
