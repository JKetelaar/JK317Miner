package nl.jketelaar.miner.main;

/**
 * @author JKetelaar
 */
public enum Ores {
    COPPER(new int[]{2091}, "Copper"),
    TIN(new int[]{2095}, "Tin"),
    IRON(new int[]{2092, 2093}, "Iron"),
    COAL(new int[]{2097}, "Coal"),
    MITHRIL(new int[]{2103}, "Mithril"),
    ADAMANT(new int[]{2105, 31085, 31083}, "Adamant"),
    RUNE(new int[]{14860}, "Rune");

    private int[] rockID;
    private String rockName;

    Ores(int[] rockID, String rockName) {
        this.rockID = rockID;
        this.rockName = rockName;
    }

    public int[] getRockIDs() {
        return rockID;
    }

    public String getRockName() {
        return rockName;
    }
}
