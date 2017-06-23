package scripts.fc.missions.fcromeoandjuliet.data.bools.impl;

import org.tribot.api2007.Inventory;

import scripts.fc.framework.quest.QuestBool;

public class BerryBool extends QuestBool
{
	public BerryBool(boolean normal)
	{
		super(normal);
	}

	@Override
	public boolean value()
	{
		return Inventory.getCount("Cadava berries") > 0;
	}

}
