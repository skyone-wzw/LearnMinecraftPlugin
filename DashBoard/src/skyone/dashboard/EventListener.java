package skyone.dashboard;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Objects;
import java.util.Random;

public class EventListener implements Listener {
    public static final Random RANDOM = new Random();
    @EventHandler
    public void onClickButton(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        InventoryView inventory = player.getOpenInventory();
        if (inventory.getTitle().equals(MainMenu.DEFAULT_TITLE)) {
            event.setCancelled(true);
        }
        if (event.getRawSlot() < 0 || event.getRawSlot() > event.getInventory().getSize()) {
            return;
        }
        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null) {
            return;
        }
        /* 退出服务器 */
        if (clickedItem.getItemMeta().getDisplayName().equals(MainMenu.QUIT_SERVER)) {
            player.kickPlayer("您已离开服务器");
            return;
        }
        /* 服务器公告 */
        if (clickedItem.getItemMeta().getDisplayName().equals(MainMenu.SHOW_ANNOUNCEMENT)) {
            ItemStack ann = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta annBm = (BookMeta) ann.getItemMeta();
            String[] acText = Objects.requireNonNullElse(DashBoard.instance.getConfig().getString("announcement.content"), "").split("\\+\\+\\+");
            // Java 默认使用正则表达式进行查找，在正则表达式中，加号是特殊字符，需要使用 \ 转义；而在 Java 中，\ 本身也是特殊字符，因此就需要输入两个 \
            annBm.setPages(acText);
            annBm.setAuthor(Objects.requireNonNullElse(DashBoard.instance.getConfig().getString("announcement.author"), "DashBoard"));
            annBm.setTitle(Objects.requireNonNullElse(DashBoard.instance.getConfig().getString("announcement.author"), "服务器公告"));
            // 三项缺一不可
            ann.setItemMeta(annBm);
            player.openBook(ann);
            return;
        }
        /* 随机传送 */
        if (clickedItem.getItemMeta().getDisplayName().equals(MainMenu.RANDOM_TELEPORT)) {
            player.closeInventory();
            World playerWorld = Bukkit.getWorlds().get(0); // 获得主世界
            double randX = RANDOM.nextInt(200000) - 100000;
            double randZ = RANDOM.nextInt(200000) - 100000;
            Location offset = new Location(playerWorld, randX, 0, randZ).toHighestLocation(); // 获得最高的非空气方块
            player.teleport(player.getLocation().add(offset)); // add 加算距离
            player.sendMessage(ChatColor.GREEN + "传送成功！");
        }
    }
}
