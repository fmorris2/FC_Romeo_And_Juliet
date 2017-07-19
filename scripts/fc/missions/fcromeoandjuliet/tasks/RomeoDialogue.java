package scripts.fc.missions.fcromeoandjuliet.tasks;

import org.tribot.api.General;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

import scripts.fc.api.interaction.impl.npcs.dialogue.DialogueThread;
import scripts.fc.api.interaction.impl.npcs.dialogue.NpcDialogue;
import scripts.fc.api.travel.Travel;
import scripts.fc.api.wrappers.FCTiming;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fcromeoandjuliet.data.QuestSettings;

public class RomeoDialogue extends Task
{
	private static final long serialVersionUID = 1522412465156316905L;
	
	private final Positionable ROMEO_TILE = new RSTile(3213, 3428, 0);
	private final int DISTANCE_THRESHOLD = 20;
	
	@Override
	public boolean execute()
	{
		if(Player.getPosition().distanceTo(ROMEO_TILE) > DISTANCE_THRESHOLD)
		{
			if(JulietDialogue.isInHouse(2))
				JulietDialogue.exitHouse();
			else
				Travel.webWalkTo(ROMEO_TILE);
		}
		else
		{
			NpcDialogue dialogue = QuestSettings.ROMEO_DIALOGUE_ONE.isValid() ? new NpcDialogue("Talk-to", "Romeo", DISTANCE_THRESHOLD, 0, 0) 
						: new NpcDialogue("Talk-to", "Romeo", DISTANCE_THRESHOLD, 3);
			
			if((dialogue.execute() || dialogue.wentThroughDialogue()) && QuestSettings.ROMEO_DIALOGUE_THREE.isValid())
			{
				General.println("Waiting for cutscene to start...");
				FCTiming.waitCondition(() -> DialogueThread.isInCutscene(), 3500);
			}
		}
			
		return false;
	}

	@Override
	public boolean shouldExecute()
	{
		return QuestSettings.ROMEO_DIALOGUE_ONE.isValid() || QuestSettings.ROMEO_DIALOGUE_TWO.isValid() 
				|| QuestSettings.ROMEO_DIALOGUE_THREE.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Romeo dialogue";
	}

}
