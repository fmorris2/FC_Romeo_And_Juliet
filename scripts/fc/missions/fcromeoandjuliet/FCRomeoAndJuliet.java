package scripts.fc.missions.fcromeoandjuliet;

import java.util.Arrays;
import java.util.LinkedList;

import org.tribot.api.General;
import org.tribot.api2007.Login;
import org.tribot.api2007.Login.STATE;

import scripts.fc.framework.mission.Mission;
import scripts.fc.framework.mission.MissionManager;
import scripts.fc.framework.script.FCMissionScript;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fcromeoandjuliet.data.QuestSettings;
import scripts.fc.missions.fcromeoandjuliet.tasks.Apothecary;
import scripts.fc.missions.fcromeoandjuliet.tasks.Cutscene;
import scripts.fc.missions.fcromeoandjuliet.tasks.FatherLawrence;
import scripts.fc.missions.fcromeoandjuliet.tasks.InventoryCheck;
import scripts.fc.missions.fcromeoandjuliet.tasks.JulietDialogue;
import scripts.fc.missions.fcromeoandjuliet.tasks.PickBerries;
import scripts.fc.missions.fcromeoandjuliet.tasks.RomeoDialogue;
import scripts.fc.missions.fcromeoandjuliet.tasks.RomeoDialogueTwo;

public class FCRomeoAndJuliet extends MissionManager implements Mission
{
	public FCRomeoAndJuliet(FCMissionScript script)
	{
		super(script);
	}

	public static final int REQUIRED_INVENTORY_SPACE = 2;
	public static final int QUEST_INDEX = 144;
	public static final int QUEST_DONE = 100;
	
	@Override
	public boolean hasReachedEndingCondition()
	{
		return QuestSettings.QUEST_COMPLETE.isValid();
	}

	@Override
	public String getMissionName()
	{
		return "Romeo and Juliet";
	}

	@Override
	public String getCurrentTaskName()
	{
		return currentTask == null ? "null" : currentTask.getStatus();
	}

	@Override
	public void execute()
	{
		if(Login.getLoginState() != STATE.INGAME)
		{
			General.println("Waiting for login...");
			return;
		}
		
		executeTasks();
	}

	@Override
	public LinkedList<Task> getTaskList()
	{
		return new LinkedList<Task>(Arrays.asList(new InventoryCheck(), new PickBerries(),
				new RomeoDialogue(), new RomeoDialogueTwo(), new JulietDialogue(), new FatherLawrence(), new Apothecary(),
				new Cutscene()));
	}

	@Override
	public String getEndingMessage()
	{
		return "Romeo and Juliet has been completed.";
	}

	public String toString()
	{
		return getMissionName();
	}

	@Override
	public String[] getMissionSpecificPaint()
	{
		return new String[0];
	}

	@Override
	public void resetStatistics()
	{
	}

}
