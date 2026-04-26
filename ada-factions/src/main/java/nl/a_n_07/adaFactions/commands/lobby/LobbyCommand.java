package nl.a_n_07.adaFactions.commands.lobby;

import nl.a_n_07.adaFactions.managers.LobbyManager;
import nl.a_n_07.adaFactions.utils.PermissionUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LobbyCommand implements CommandExecutor {
    private final LobbyManager lobbyManager;

    public LobbyCommand(LobbyManager lobbyManager) {
        this.lobbyManager = lobbyManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!PermissionUtil.hasOpPermission(sender)) return true;
        if (args.length == 0) return false;
        switch (args[0].toLowerCase()) {
            case "setlobby": return new SetLobbySpawnCommand(lobbyManager).execute(sender);
            case "unsetlobby": return new UnsetLobbyCommand(lobbyManager).execute(sender);
            default:
                sender.sendMessage(ChatColor.RED + "Unknown command");
                return true;
        }
    }
}