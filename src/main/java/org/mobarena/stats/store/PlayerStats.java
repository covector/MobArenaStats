package org.mobarena.stats.store;

import java.util.List;

public class PlayerStats {

    public final int totalSessions;
    public final long totalSeconds;
    public final long totalDamageDone;
    public final long totalDamageTaken;
    public final long totalKills;
    public final long totalWaves;
    public final int totalWins;
    public final List<ClassStats> classStats;

    public PlayerStats(
        int totalSessions,
        long totalSeconds,
        long totalKills,
        long totalDamageDone,
        long totalDamageTaken,
        long totalWaves,
        int totalWins,
        List<ClassStats> classStats
    ) {
        this.totalSessions = totalSessions;
        this.totalSeconds = totalSeconds;
        this.totalKills = totalKills;
        this.totalDamageDone = totalDamageDone;
        this.totalDamageTaken = totalDamageTaken;
        this.totalWaves = totalWaves;
        this.totalWins = totalWins;
        this.classStats = classStats;
    }

}
