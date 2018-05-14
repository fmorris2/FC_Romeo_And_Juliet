package scripts.fc.missions.fcromeoandjuliet.data.bools.impl;

import scripts.fc.api.interaction.impl.npcs.dialogue.DialogueThread;
import scripts.fc.framework.quest.QuestBool;

public class CutsceneBool extends QuestBool {

	public CutsceneBool(boolean normal) {
		super(normal);
	}

	@Override
	public boolean value() {
		return DialogueThread.isInCutscene();
	}

}
