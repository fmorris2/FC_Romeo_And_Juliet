package scripts.fc.missions.fcromeoandjuliet.data.bools.impl;

import org.tribot.api.General;

import scripts.fc.api.interaction.impl.npcs.dialogue.DialogueThread;
import scripts.fc.framework.quest.QuestBool;

public class CutsceneBool extends QuestBool {

	public CutsceneBool(boolean normal) {
		super(normal);
	}

	@Override
	public boolean value() {
		General.println("inCutscene:"  +DialogueThread.isInCutscene());
		return DialogueThread.isInCutscene();
	}

}
