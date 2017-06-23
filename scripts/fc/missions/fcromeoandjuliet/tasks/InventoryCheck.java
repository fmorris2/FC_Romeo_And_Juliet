package scripts.fc.missions.fcromeoandjuliet.tasks;

import org.tribot.api.Timing;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Game;
import org.tribot.api2007.WebWalking;

import scripts.fc.api.generic.FCConditions;
import scripts.fc.api.travel.Travel;
import scripts.fc.framework.task.Task;
import scripts.fc.missions.fcromeoandjuliet.FCRomeoAndJuliet;
import scripts.fc.missions.fcromeoandjuliet.data.QuestSettings;
import scripts.fc.missions.fcromeoandjuliet.data.bools.impl.SpaceBool;

public class InventoryCheck extends Task
{
	private final SpaceBool SPACE_BOOL = new SpaceBool(false);
	
	@Override
	public boolean execute()
	{
		if(!Banking.isInBank())
			Travel.walkToBank();
		else
		{
			if(!Banking.isBankScreenOpen())
			{
				if(Banking.openBank())
					Timing.waitCondition(FCConditions.BANK_LOADED_CONDITION, 4000);
			}
			else
				Banking.depositAll();
		}	
		
		return false;
	}

	@Override
	public boolean shouldExecute()
	{
		return Game.getSetting(FCRomeoAndJuliet.QUEST_INDEX) != FCRomeoAndJuliet.QUEST_DONE && SPACE_BOOL.validate();
	}

	@Override
	public String getStatus()
	{
		return "Inventory check";
	}
	
}
