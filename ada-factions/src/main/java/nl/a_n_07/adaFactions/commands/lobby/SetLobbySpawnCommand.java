package nl.a_n_07.adaFactions.commands.lobby;

import nl.a_n_07.adaFactions.managers.LobbyManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobbySpawnCommand {
    private final LobbyManager lobbyManager;

    public SetLobbySpawnCommand(LobbyManager lobbyManager) {
        this.lobbyManager = lobbyManager;
    }

    boolean execute(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        lobbyManager.setLobby(player.getLocation());
        player.sendMessage(ChatColor.GREEN + "Lobby spawn has been set to your location!");
        return true;
    }
}