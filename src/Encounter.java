import java.util.ArrayList;
import java.util.Random;

public class Encounter {

	public ArrayList<CreatureType> ctList = new ArrayList<CreatureType>();
	public ArrayList<Creature> crList = new ArrayList<Creature>();
	public Random rng = new Random();

	public Encounter() {

	}

	public void removeCreature(int index) {
		crList.remove(index);
	}

	// TODO: memory leak
	public void removeAll() {
		this.crList.clear();
		// this.crList = new ArrayList<Creature>();
		// System.gc();
	}

	// TODO: Shit name for method
	public void addCreature(Creature cr) {

		this.crList.add(cr);
	}

	// public ArrayList<Creature> generateEncounter(ArrayList<CreatureType>
	// ctList) {
	public void generateEncounter(CreatureType ct) {
		crList = generateEncounterCreatures(ct);
		generateEncounterHP(crList);

		// return crList;
	}

	public void generateEncounter(ArrayList<CreatureType> list) {
		crList = generateEncounterCreatures(list);
		generateEncounterHP(crList);
	}

	public void generateEncounterHP(ArrayList<Creature> crList) {
		for (int i = 0; i < crList.size(); i++) {
			for (int j = 0; j < CreatureTable.ctypeTable.size(); j++) {
				if (crList.get(i).name.equals(CreatureTable.ctypeTable.get(j).getName())) {
					int hp = 0;
					for (int k = 1; k <= crList.get(i).getHd(); k++) {
						hp += rng.nextInt(CreatureTable.ctypeTable.get(j).getHitDiceType()) + 1;
					}
					hp += CreatureTable.ctypeTable.get(j).getHitDiceSpecial();
					if(hp <= 0){
						hp = 1;
					}
					crList.get(i).setHP(hp);
				}
			}
		}
	}

	public ArrayList<Creature> generateEncounterCreatures(CreatureType ct) {

		int no = 0;
		for (int k = 1; k <= ct.getAppDice(); k++) {
			no += rng.nextInt(ct.getAppDiceType()) + 1;
		}
		no += ct.getAppDiceSpecial();

		// TODO Roll hp here instead!
		for (int l = 1; l <= no; l++) {
			int hd = ct.getHitDice();
			if(ct.getHitDiceVariance() > 0){

				hd = ct.getHitDice() + rng.nextInt(ct.getHitDiceVariance());
			}
			crList.add(new Creature(ct.getName(), 0, hd));
		}
		return crList;
	}

	public ArrayList<Creature> generateEncounterCreatures(ArrayList<CreatureType> ctList) {

		for (int i = 0; i < ctList.size(); i++) {
			for (int j = 0; j < CreatureTable.ctypeTable.size(); j++) {
				int no = 0;
				for (int k = 1; k <= CreatureTable.ctypeTable.get(j).getAppDice(); k++) {
					no += rng.nextInt(CreatureTable.ctypeTable.get(j).getAppDiceType()) + 1;
				}
				no += CreatureTable.ctypeTable.get(j).getAppDiceSpecial();

				// TODO Roll hp here instead!
				for (int l = 1; l <= no; l++) {
					crList.add(new Creature(CreatureTable.ctypeTable.get(j).getName(), 0, CreatureTable.ctypeTable.get(j).getHitDice()));
				}
			}
		}
		return crList;
	}

	public String[] print() {
		String[] printList = new String[crList.size()];
		for (int i = 0; i < crList.size(); i++) {
			printList[i] = crList.get(i).name + ": " + crList.get(i).hp + "      HD[" + crList.get(i).getHd() + "]";
		}

		return printList;
	}
}
