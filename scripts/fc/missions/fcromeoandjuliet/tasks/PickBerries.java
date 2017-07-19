package scripts.fc.missions.fcromeoandjuliet.tasks;

import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.abc.ABC2Reaction;
import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.objects.ClickObject;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fcromeoandjuliet.data.QuestSettings;

public class PickBerries extends Task
{
	private static final long serialVersionUID = 1969638374658443588L;

	private static final int ESTIMATED_WAIT = 3000;
	
	private final Positionable BERRY_TILE = new RSTile(3267, 3369, 0);
	private final int DISTANCE_THRESHOLD = 6;
	private final int[] BUSH_IDS = {23625, 23626};
	
	private ABC2Reaction reaction = new ABC2Reaction(true, ESTIMATED_WAIT);
	
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
				reaction.start();
				boolean success = Timing.waitCondition(FCConditions.inventoryChanged(STARTING_SPACE), 4000);
				
				if(success)
					reaction.react();
				
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
