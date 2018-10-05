package scripts.fc.missions.fcromeoandjuliet.tasks;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.interaction.impl.npcs.dialogue.NpcDialogue;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fcromeoandjuliet.data.QuestSettings;

public class JulietDialogue extends Task
{
	private static final long serialVersionUID = -1480455143391513930L;
	
	private static final Positionable JULIET_TILE = new RSTile(3158, 3426, 1);
	private static final int DIST_THRESH = 6;
	
	@Override
	public boolean execute()
	{
		final RSTile playerPos = Player.getPosition();
		if(playerPos.getPlane() != JULIET_TILE.getPosition().getPlane() || playerPos.distanceTo(JULIET_TILE) > DIST_THRESH) {
			goToJuliet();
		}
		else {
			if(new NpcDialogue("Talk-to", "Juliet", 15, 0).execute())
				General.sleep(800, 1800);
		}
		
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
		Travel.shouldFallBackOnTribotWeb = false;
		if(Travel.webWalkTo(JULIET_TILE, FCConditions.withinDistanceOfTile(JULIET_TILE, DIST_THRESH)))
			Timing.waitCondition(FCConditions.withinDistanceOfTile(JULIET_TILE, DIST_THRESH), 5000);
		
		Travel.shouldFallBackOnTribotWeb = true;
	}

}
