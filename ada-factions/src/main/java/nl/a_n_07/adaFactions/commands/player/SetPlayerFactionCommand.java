package nl.a_n_07.adaFactions.commands.player;

import nl.a_n_07.adaFactions.managers.FactionManager;
import nl.a_n_07.adaFactions.managers.PlayerManager;
import nl.a_n_07.adaFactions.models.AdaPlayer;
import nl.a_n_07.adaFactions.models.Faction;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetPlayerFactionCommand {
    PlayerManager playerManager;
    FactionManager factionManager;
    private static final int MAX_FACTION_MEMBERS = 10;

    public SetPlayerFactionCommand(PlayerManager playerManager, FactionManager factionManager) {
        this.playerManager = playerManager;
        this.factionManager = factionManager;
    }

    public boolean execute(CommandSender sender, String[] args) {
        final int FACTION_NAME = 2;
        Player player = (Player) sender;
        AdaPlayer adaPlayer = playerManager.getPlayer(player.getUniqueId());
        Faction faction = factionManager.getFactionByName(args[FACTION_NAME]);
        faction.addPlayer(adaPlayer);
        return true;
    }
}
