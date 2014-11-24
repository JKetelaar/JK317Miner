package nl.jketelaar.miner.strategies;

import nl.jketelaar.miner.main.Main;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

/**
 * @author JKetelaar
 */
public class Miner implements Strategy {

    private SceneObject currentObject;
    public Main core;

    public Miner(Main main){
        this.core = main;
    }


    @Override
    public boolean activate() {
        if (Inventory.getItems().length < 28 && Players.getMyPlayer().getAnimation() == -1) {
            for (int i : core.getRockIDs()) {
                for (SceneObject so : SceneObjects.getNearest(i)) {
                    if (so != null && so.distanceTo() <= core.getDistance()) {
                        this.currentObject = so;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void execute() {
        if (this.currentObject != null){
            final int inventoryCount = Inventory.getItems().length;
            currentObject.interact(0);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Inventory.getItems().length > inventoryCount;
                }
            }, 7500);
        }
    }
}
