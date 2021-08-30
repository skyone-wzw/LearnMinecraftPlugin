package skyone.auth;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;

public class CommandHandle implements CommandExecutor {
    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        if (!(LoginData.hasPlayer(sender.getName()))) {
            sender.sendMessage(ChatColor.GREEN + "你已经登录了！");
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "请输入密码！");
            return false;
        }
        String password = String.join("&", args);
        if (ConfigReader.isRegistered(sender.getName())) {
            if (ConfigReader.verifyPassword(sender.getName(), password)) {
                LoginData.removePlayer(sender.getName());
                sender.sendMessage(ChatColor.GREEN + "欢迎回来，" + sender.getName() + "！");
            } else {
                sender.sendMessage(ChatColor.RED + "密码错误！");
            }
        } else {
            ConfigReader.register(sender.getName(), password);
            LoginData.removePlayer(sender.getName());
            sender.sendMessage(ChatColor.GREEN + "注册成功，欢迎加入！");
        }
        return true;
    }
}
