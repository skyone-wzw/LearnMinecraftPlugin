package skyone.dashboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class MainMenu {
    public Inventory inventory;
    public Player owner;
    public static final String DEFAULT_TITLE = "菜单";
    public static final String QUIT_SERVER = "退出服务器";
    public static final String SHOW_ANNOUNCEMENT = ChatColor.GOLD + "查看公告";
    public static final String RANDOM_TELEPORT = ChatColor.GREEN + "随机传送";

    public MainMenu(Player player) {
        inventory = Bukkit.createInventory(player, 3 * 9, DEFAULT_TITLE);
        owner = player;
        /* 按钮: 退出服务器 */
        ItemStack quitServer = new ItemStack(Material.BARRIER);
        ItemMeta quitServerMeta = quitServer.getItemMeta();
        quitServerMeta.setDisplayName(QUIT_SERVER);
        quitServerMeta.setLore(Collections.singletonList(ChatColor.GRAY + "" + ChatColor.ITALIC + "离开此服务器"));
        quitServer.setItemMeta(quitServerMeta);
        inventory.setItem(9, quitServer);
        /* 按钮: 服务器公告 */
        ItemStack showAnnouncement = new ItemStack(Material.BOOK);
        ItemMeta showAnnouncementMeta = showAnnouncement.getItemMeta();
        showAnnouncementMeta.setDisplayName(SHOW_ANNOUNCEMENT);
        showAnnouncementMeta.setLore(Collections.singletonList(ChatColor.GRAY + "" + ChatColor.ITALIC + "查看公告"));
        showAnnouncement.setItemMeta(showAnnouncementMeta);
        inventory.setItem(13, showAnnouncement);
        /* 按钮: 随机传送 */
        ItemStack randomTeleport = new ItemStack(Material.COMPASS);
        ItemMeta randomTeleportMeta = randomTeleport.getItemMeta();
        randomTeleportMeta.setDisplayName(RANDOM_TELEPORT);
        randomTeleportMeta.setLore(Collections.singletonList(ChatColor.GRAY + "" + ChatColor.ITALIC + "在当前世界随机传送"));
        randomTeleport.setItemMeta(randomTeleportMeta);
        inventory.setItem(17, randomTeleport);
    }

    public void openMenu() {
        owner.openInventory(inventory);
    }
}
