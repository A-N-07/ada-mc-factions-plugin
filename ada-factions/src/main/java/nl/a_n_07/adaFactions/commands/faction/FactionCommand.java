package nl.a_n_07.adaFactions.commands.faction;

import nl.a_n_07.adaFactions.managers.FactionManager;
import nl.a_n_07.adaFactions.utils.PermissionUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FactionCommand implements CommandExecutor {
    private FactionManager factionManager;

    public FactionCommand(FactionManager factionManager) { this.factionManager = factionManager; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!PermissionUtil.hasModPermission(sender)) return true;
        if (args.length == 0) return false;

        switch (args[0].toLowerCase()) {
            case "add": return new AddFactionCommand(factionManager).execute(sender, args);
            case "remove": return new RemoveFactionCommand(factionManager).execute(sender, args);
            case "editname": return new EditNameFactionCommand(factionManager).execute(sender, args);
            case "list": return new ListFactionCommand(factionManager).execute(sender, args);
            default:
                sender.sendMessage(ChatColor.RED + "Unknown command");
                return true;
        }
    }
}
