package scripts.fc.missions.fcromeoandjuliet.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.util.DPathNavigator;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.npcs.dialogue.NpcDialogue;
import scripts.fc.api.interaction.impl.objects.ClickObject;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fcromeoandjuliet.data.QuestSettings;

public class JulietDialogue extends Task
{
	private static final RSArea JULIET_HOUSE_FLOOR_ONE = new RSArea(new RSTile(3157, 3433, 0), 10);
	private static final RSArea JULIET_HOUSE_FLOOR_TWO = new RSArea(new RSTile(3157, 3433, 1), 10);
	private static final RSArea JULIET_AREA = new RSArea(new RSTile(3155, 3427, 1), new RSTile(3161, 3424, 1));
	
	private static final Positionable ENTRANCE_TILE = new RSTile(3171, 3433, 0);
	private static final Positionable STAIR_TILE = new RSTile(3160, 3435, 0);
	private static final Positionable DOWN_STAIR_TILE = new RSTile(3155, 3435, 1);
	private static final Positionable JULIET_TILE = new RSTile(3158, 3426, 1);
	
	@Override
	public boolean execute()
	{
		if(!JULIET_AREA.contains(Player.getPosition()))
			goToJuliet();
		else
			if(new NpcDialogue("Talk-to", "Juliet", 10, 0).execute())
				General.sleep(800, 1800);
		
		return false;
	}

	@Override
	public boolean shouldExecute()
	{
		return QuestSettings.JULIET_DIALOGUE_ONE.isValid() || QuestSettings.JULIET_DIALOGUE_TWO.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Juliet dialogue";
	}
	
	public void goToJuliet()
	{
		if(JULIET_HOUSE_FLOOR_TWO.contains(Player.getPosition()))
		{
			if(new DPathNavigator().traverse(JULIET_TILE))
				Timing.waitCondition(FCConditions.inAreaCondition(JULIET_AREA), 5000);
		}
		else if(JULIET_HOUSE_FLOOR_ONE.contains(Player.getPosition()))
		{
			if(new DPathNavigator().traverse(STAIR_TILE))
				if(new ClickObject("Climb-up", "Staircase", 6).execute())
					Timing.waitCondition(FCConditions.planeChanged(0), 3500);
		}
		else
		{
			if(Travel.webWalkTo(ENTRANCE_TILE))
				new DPathNavigator().traverse(STAIR_TILE);
		}
	}
	
	public static void exitHouse()
	{
		if(JULIET_HOUSE_FLOOR_TWO.contains(Player.getPosition()))
		{
			if(new DPathNavigator().traverse(DOWN_STAIR_TILE))
				if(new ClickObject("Climb-down", "Staircase", 6).execute())
					Timing.waitCondition(FCConditions.planeChanged(1), 3500);
		}
		else if(JULIET_HOUSE_FLOOR_ONE.contains(Player.getPosition()))
			new DPathNavigator().traverse(ENTRANCE_TILE);
	}
	
	public static boolean isInHouse(int plane)
	{
		return plane == 0 ? JULIET_HOUSE_FLOOR_ONE.contains(Player.getPosition()) 
				: JULIET_HOUSE_FLOOR_TWO.contains(Player.getPosition());
	}

}
