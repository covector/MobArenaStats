package org.mobarena.stats.store;

public class ClassStats {

    public final String className;
    public final int totalSessions;

    public ClassStats(
        String className,
        int totalSessions
    ) {
        this.className = className;
        this.totalSessions = totalSessions;
    }

}
