package scripts.fc.missions.fcromeoandjuliet.tasks;

import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api.util.abc.ABCProperties;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.abc.PersistantABCUtil;
import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.objects.ClickObject;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.data.Vars;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fcromeoandjuliet.data.QuestSettings;

public class PickBerries extends Task
{
	private static final long serialVersionUID = 1969638374658443588L;

	private static final int ESTIMATED_WAIT = 3000;
	
	private final Positionable BERRY_TILE = new RSTile(3267, 3369, 0);
	private final int DISTANCE_THRESHOLD = 6;
	private final int[] BUSH_IDS = {23625, 23626};
	
	@Override
	public boolean execute()
	{
		if(Player.getPosition().distanceTo(BERRY_TILE) > DISTANCE_THRESHOLD)
			Travel.webWalkTo(BERRY_TILE);
		else
		{
			final int STARTING_SPACE = Inventory.getAll().length;
		
			RSObject[] bushes = Objects.findNearest(10, BUSH_IDS);
			if(bushes.length > 0 && new ClickObject("Pick-from", bushes[0]).execute())
			{
				PersistantABCUtil abc2 = Vars.get().get("abc2");
				abc2.generateTrackers(ESTIMATED_WAIT);
				long startTime = Timing.currentTimeMillis();
				boolean success = Timing.waitCondition(FCConditions.inventoryChanged(STARTING_SPACE), 4000);
				if(success)
				{
					ABCProperties props = Vars.get().get("abc2Props");
					
					props.setWaitingTime(((Long)Timing.timeFromMark(startTime)).intValue());
					props.setWaitingFixed(true);
					abc2.generateAndPerformReaction(props);
				}
				
				return success;
			}
		}
		
		return false;
				
	}

	@Override
	public boolean shouldExecute()
	{
		return QuestSettings.START_PREPARATION.isValid() || QuestSettings.GET_BERRIES.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Pick cadava berries";
	}
}
