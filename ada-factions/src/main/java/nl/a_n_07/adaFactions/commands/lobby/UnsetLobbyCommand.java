package nl.a_n_07.adaFactions.commands.lobby;

import nl.a_n_07.adaFactions.managers.LobbyManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class UnsetLobbyCommand {
    private final LobbyManager lobbyManager;

    public UnsetLobbyCommand(LobbyManager lobbyManager) {
        this.lobbyManager = lobbyManager;
    }

    boolean execute(CommandSender sender) {
        if (!lobbyManager.hasLobby()) {
            sender.sendMessage(ChatColor.RED + "No lobby spawn is currently set!");
            return true;
        }

        lobbyManager.unsetLobby();
        sender.sendMessage(ChatColor.GREEN + "Lobby spawn has been unset!");
        return true;
    }
}