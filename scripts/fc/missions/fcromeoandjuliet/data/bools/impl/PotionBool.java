package scripts.fc.missions.fcromeoandjuliet.data.bools.impl;

import org.tribot.api2007.Inventory;

import scripts.fc.framework.quest.QuestBool;

public class PotionBool extends QuestBool
{
	public PotionBool(boolean normal)
	{
		super(normal);
	}

	@Override
	public boolean value()
	{
		return Inventory.getCount("Cadava potion") > 0;
	} 

}
