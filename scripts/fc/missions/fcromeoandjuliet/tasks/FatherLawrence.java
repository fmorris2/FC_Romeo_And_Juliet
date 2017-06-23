package scripts.fc.missions.fcromeoandjuliet.tasks;

import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.npcs.dialogue.NpcDialogue;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fcromeoandjuliet.data.QuestSettings;

public class FatherLawrence extends Task
{
	private final Positionable CHURCH_TILE = new RSTile(3254, 3479, 0);
	private final int DISTANCE_THRESHOLD = 10;
	
	@Override
	public boolean execute()
	{
		if(Player.getPosition().distanceTo(CHURCH_TILE) > DISTANCE_THRESHOLD)
			Travel.webWalkTo(CHURCH_TILE);
		else
			if(new NpcDialogue("Talk-to", "Father Lawrence", 15, 0).execute() && Timing.waitCondition(FCConditions.IN_DIALOGUE_CONDITION, 10000))
				new NpcDialogue("Talk-to", "Father Lawrence", 15, 0).execute();
		
		return false;
	}

	@Override
	public boolean shouldExecute()
	{
		return QuestSettings.FATHER_LAWRENCE.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Father Lawrence";
	}

}
