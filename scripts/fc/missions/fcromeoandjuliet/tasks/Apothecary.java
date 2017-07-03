package scripts.fc.missions.fcromeoandjuliet.tasks;

import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.interaction.impl.npcs.dialogue.NpcDialogue;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fcromeoandjuliet.data.QuestSettings;

public class Apothecary extends Task
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -124115160973447013L;
	private final Positionable APOTHECARY_TILE = new RSTile(3195, 3403, 0);
	private final int DISTANCE_THRESHOLD = 5;
	
	@Override
	public boolean execute()
	{
		if(Player.getPosition().distanceTo(APOTHECARY_TILE) > DISTANCE_THRESHOLD)
			Travel.webWalkTo(APOTHECARY_TILE);
		else
			return new NpcDialogue("Talk-to", "Apothecary", 10, 2, 0).execute();
		
		return false;
	}

	@Override
	public boolean shouldExecute()
	{
		return QuestSettings.APOTHECARY_DIALOGUE_ONE.isValid() || QuestSettings.APOTHECARY_DIALOGUE_TWO.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Apothecary";
	}

}
