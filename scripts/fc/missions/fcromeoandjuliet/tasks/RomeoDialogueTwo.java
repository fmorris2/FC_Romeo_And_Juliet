package scripts.fc.missions.fcromeoandjuliet.tasks;

import scripts.fc.api.items.FCItem;
import scripts.fc.framework.task.ItemsRequiredTask;
import scripts.fc.missions.fcromeoandjuliet.data.QuestSettings;

public class RomeoDialogueTwo extends RomeoDialogue implements ItemsRequiredTask {

	private static final long serialVersionUID = 1L;
	private static final int NOTE_ID = 755;
	
	@Override
	public FCItem[] getRequiredItems() {
		return new FCItem[] {
				new FCItem(1, false, NOTE_ID)
		};
	}
	
	@Override
	public boolean shouldExecute()
	{
		return QuestSettings.ROMEO_DIALOGUE_TWO.isValid();
	}

}
