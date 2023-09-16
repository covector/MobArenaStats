package org.mobarena.stats.command;

import com.garbagemule.MobArena.Messenger;
import com.garbagemule.MobArena.commands.Command;
import com.garbagemule.MobArena.commands.CommandInfo;
import com.garbagemule.MobArena.framework.ArenaMaster;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mobarena.stats.MobArenaStats;
import org.mobarena.stats.store.PlayerStats;
import org.mobarena.stats.store.StatsStore;
import org.mobarena.stats.store.ClassStats;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static java.lang.String.format;
import static org.bukkit.ChatColor.AQUA;
import static org.bukkit.ChatColor.RESET;
import static org.bukkit.ChatColor.YELLOW;

import org.bukkit.Bukkit;

@CommandInfo(
    name = "player-stats",
    pattern = "player-stats",
    usage = "/ma player-stats <player>",
    desc = "show overall stats for the given player",
    permission = "mobarenastats.command.player-stats"
)
public class PlayerStatsCommand implements Command {

    private final MobArenaStats plugin;

    public PlayerStatsCommand(MobArenaStats plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean execute(ArenaMaster am, CommandSender sender, String... args) {
        String name;
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                return false;
            }
            name = sender.getName();
        } else {
            name = args[0];
        }

        Messenger messenger = am.getGlobalMessenger();
        plugin.getAsyncExecutor().execute(() -> {
            StatsStore store = plugin.getStatsStore();
            PlayerStats stats = store.getPlayerStats(name);
            ArrayList<String> lines = new ArrayList<>(Arrays.asList(
                format("Stats for player %s%s%s:", YELLOW, name, RESET),
                format("- Total sessions: %s%d%s", AQUA, stats.totalSessions, RESET),
                format("- Total duration: %s%d%s secs", AQUA, stats.totalSeconds, RESET),
                format("- Total kills: %s%d%s", AQUA, stats.totalKills, RESET),
                format("- Total damage done: %s%d%s", AQUA, stats.totalDamageDone, RESET),
                format("- Total damage taken: %s%d%s", AQUA, stats.totalDamageTaken, RESET),
                format("- Total waves: %s%d%s", AQUA, stats.totalWaves, RESET),
                format("- Total wins: %s%d%s", AQUA, stats.totalWins, RESET),
                "",
                "Class Usage:"
            ));
            for (ClassStats classStats : stats.classStats) {
                lines.add(format("- %s: %s%d%s", classStats.className, AQUA, classStats.totalSessions, RESET));
            }
            messenger.tell(sender, String.join("\n", lines));
        });

        return true;
    }

    @Override
    public List<String> tab(ArenaMaster am, Player player, String... args) {
        // TODO: tab complete player names?
        return Command.super.tab(am, player, args);
    }

}
