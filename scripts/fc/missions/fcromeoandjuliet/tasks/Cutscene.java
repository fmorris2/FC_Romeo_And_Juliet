package scripts.fc.missions.fcromeoandjuliet.tasks;

import org.tribot.api.General;
import org.tribot.api2007.NPCChat;

import scripts.fc.framework.task.Task;
import scripts.fc.missions.fcromeoandjuliet.data.QuestSettings;

public class Cutscene extends Task
{

	@Override
	public boolean execute()
	{
		if(NPCChat.getClickContinueInterface() != null)
		{
			NPCChat.clickContinue(true);
		}
		
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
