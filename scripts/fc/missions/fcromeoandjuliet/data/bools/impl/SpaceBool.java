package scripts.fc.missions.fcromeoandjuliet.data.bools.impl;

import org.tribot.api2007.Inventory;

import scripts.fc.framework.quest.QuestBool;
import scripts.fc.missions.fcromeoandjuliet.FCRomeoAndJuliet;

public class SpaceBool extends QuestBool
{
	private final int MAX_INVENTORY_SPACE = 28;
	
	public SpaceBool(boolean normal)
	{
		super(normal);
	}
	
	@Override
	public boolean value()
	{
		return MAX_INVENTORY_SPACE - Inventory.getAll().length >= FCRomeoAndJuliet.REQUIRED_INVENTORY_SPACE;
	}

}
