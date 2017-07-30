package scripts.fc.missions.fcromeoandjuliet.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.npcs.dialogue.NpcDialogue;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fcromeoandjuliet.data.QuestSettings;

public class JulietDialogue extends Task
{
	private static final long serialVersionUID = -1480455143391513930L;
	
	private static final RSArea JULIET_AREA = new RSArea(new RSTile(3155, 3427, 1), new RSTile(3161, 3424, 1));
	private static final Positionable JULIET_TILE = new RSTile(3158, 3426, 1);
	
	@Override
	public boolean execute()
	{
		if(!JULIET_AREA.contains(Player.getPosition()))
			goToJuliet();
		else
			if(new NpcDialogue("Talk-to", "Juliet", 15, 0).execute())
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
		if(Travel.webWalkTo(JULIET_TILE, FCConditions.inAreaCondition(JULIET_AREA)))
			Timing.waitCondition(FCConditions.inAreaCondition(JULIET_AREA), 5000);
	}

}
