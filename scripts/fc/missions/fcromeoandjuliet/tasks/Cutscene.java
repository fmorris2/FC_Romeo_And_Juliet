package scripts.fc.missions.fcromeoandjuliet.tasks;

import org.tribot.api.General;
import org.tribot.api2007.NPCChat;

import scripts.fc.api.interaction.impl.npcs.dialogue.DialogueThread;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fcromeoandjuliet.data.QuestSettings;

public class Cutscene extends Task
{
	private static final long serialVersionUID = -6925074723586202476L;
	
	@Override
	public boolean execute()
	{	
		if(NPCChat.getClickContinueInterface() != null)
			DialogueThread.doClickToContinue();
		
		General.sleep(400, 800);
		
		return false;
		
	}

	@Override
	public boolean shouldExecute()
	{
		return QuestSettings.JULIET_CUTSCENE.isValid() || QuestSettings.FINAL_CUTSCENE.isValid();
	}

	@Override
	public String getStatus()
	{
		return "Cutscene";
	}

}
