package nl.jketelaar.miner.main;

import java.util.ArrayList;

/**
 * @author JKetelaar
 */
public interface Main {
    public int[] getRockIDs();

    public ArrayList<Long> getStartingItems();

    public void setStartingItems(ArrayList<Long> items);

    public int getDistance();

    public void setDistance(int i);

    public void setRockIDs(int[] i);
}
