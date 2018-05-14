package scripts.fc.missions.fcromeoandjuliet.data;

import org.tribot.api2007.Game;

import scripts.fc.framework.quest.QuestBool;
import scripts.fc.missions.fcromeoandjuliet.data.bools.impl.BerryBool;
import scripts.fc.missions.fcromeoandjuliet.data.bools.impl.CutsceneBool;
import scripts.fc.missions.fcromeoandjuliet.data.bools.impl.PotionBool;
import scripts.fc.missions.fcromeoandjuliet.data.bools.impl.SpaceBool;

public enum QuestSettings
{
	START_PREPARATION(new int[][]{{144, 0}}, new BerryBool(false)),
	ROMEO_DIALOGUE_ONE(new int[][]{{144, 0}}, new BerryBool(true)),
	JULIET_DIALOGUE_ONE(new int[][]{{144, 10}}),
	ROMEO_DIALOGUE_TWO(new int[][]{{144, 20}}),
	FATHER_LAWRENCE(new int[][]{{144, 30}}),
	APOTHECARY_DIALOGUE_ONE(new int[][]{{144, 40}}),
	GET_BERRIES(new int[][]{{144, 50}}, new BerryBool(false), new PotionBool(false), new CutsceneBool(false)),
	APOTHECARY_DIALOGUE_TWO(new int[][]{{144, 50}, {673, 0}}, new PotionBool(false), new BerryBool(true), new CutsceneBool(false)),
	JULIET_DIALOGUE_TWO(new int[][]{{144, 50}}, new PotionBool(true), new CutsceneBool(false)),
	JULIET_CUTSCENE(new int[][]{{144, 50}}, new CutsceneBool(true)),
	ROMEO_DIALOGUE_THREE(new int[][]{{144, 60}}, new CutsceneBool(false)),
	FINAL_CUTSCENE(new int[][]{{144, 60}}, new CutsceneBool(true)),
	QUEST_COMPLETE(new int[][]{{144, 100}});
	
	private static final QuestBool SPACE_BOOL = new SpaceBool(true);
	
	private int[][] settings;
	private QuestBool[] bools;
	
	QuestSettings(int[][] settings, QuestBool... bools)
	{
		this.settings = settings;
		this.bools = bools;
	}
	
	public boolean isValid()
	{
		if(Game.getSetting(144) != 100 && !SPACE_BOOL.validate())
			return false;
		
		for(int i = 0; i < settings.length; i++)
		{
			if(Game.getSetting(settings[i][0]) != settings[i][1])
				return false;
		}
		
		for(QuestBool b : bools)
		{
			if(!b.validate())
				return false;
		}
		
		return true;
	}

}
