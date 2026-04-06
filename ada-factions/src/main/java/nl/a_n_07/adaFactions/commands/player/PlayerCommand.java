package nl.a_n_07.adaFactions.commands.player;

import nl.a_n_07.adaFactions.managers.FactionManager;
import nl.a_n_07.adaFactions.managers.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.util.Arrays;

public class PlayerCommand implements CommandExecutor {
    private PlayerManager playerManager;
    private FactionManager factionManager;

    public PlayerCommand(PlayerManager playerManager, FactionManager factionManager) {
        this.playerManager = playerManager;
        this.factionManager = factionManager;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) return false;

        switch (args[0].toLowerCase()) {
            case "list": return new ListPlayerFactionsCommand(factionManager).execute(sender, Arrays.copyOfRange(args, 1, args.length));
            case "setfaction": return new SetPlayerFactionCommand(playerManager, factionManager).execute(sender, Arrays.copyOfRange(args, 1, args.length));
            default:
                sender.sendMessage(ChatColor.RED + "Unknown command");
                return true;
        }
    }
}
