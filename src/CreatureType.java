import java.util.ArrayList;
import java.util.Random;

public class CreatureType {

	// TODO: (Maybe)Add 'int creatureWeight'. When generating an encounter, the weight
	// descides how many times an instance of that creature type is put into the
	// list to roll on.

	public String name = "-NONAME-";
	public ArrayList<String> climateList = new ArrayList<String>();
	public ArrayList<String> terrainList = new ArrayList<String>();
	public ArrayList<String> specTerrainList = new ArrayList<String>();
	public int appDice = 0;
	public int appDiceType = 0;
	public int hitDice = 0;
	public int hitDiceType = 0;
	public int hitDiceSpecial = 0;
	public int rarity = 0;
	public static final int COMMON = 1;
	public static final int UNCOMMON = 2;
	public static final int RARE = 3;
	public static final int VERYRARE = 4;

	public CreatureType() {

	}

	public CreatureType(String name, ArrayList<String> climateList, int appDice, int appDiceType, int hitDice,
			int hitDiceType, int hitDiceSpecial) {
		this.name = name;
		this.climateList = climateList;
		this.appDice = appDice;
		this.appDiceType = appDiceType;
		this.hitDice = hitDice;
		this.hitDiceType = hitDiceType;
		this.hitDiceSpecial = hitDiceSpecial;
	}

	public Creature rollCreature() {
		Random rng = new Random();
		int hp = 0;
		for (int i = 1; i <= this.hitDice; i++) {
			hp += rng.nextInt(hitDiceType) - 1;
		}
		hp -= this.hitDiceSpecial;

		return new Creature(this.name, hp);
	}

	public String getName() {
		return this.name;
	}
	public int getRarity() {
		return rarity;
	}

	public ArrayList<String> getClimateList() {
		return this.climateList;
	}

	public ArrayList<String> getTerrainList() {
		return this.terrainList;
	}

	public int getAppDice() {
		return this.appDice;
	}

	public int getAppDiceType() {
		return this.appDiceType;
	}

	public int getHitDice() {
		return this.hitDice;
	}

	public int getHitDiceType() {
		return this.hitDiceType;
	}

	public int getHitDiceSpecial() {
		return this.hitDiceSpecial;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setRarity(int r){
		this.rarity = r;
	}

	public void setClimateList(ArrayList<String> climateList) {
		this.climateList = climateList;
	}

	public void setTerrainList(ArrayList<String> terrainList) {
		this.terrainList = terrainList;
	}

	public void setAppDice(int appDice) {
		this.appDice = appDice;
	}

	public void setAppDiceType(int appDiceType) {
		this.appDiceType = appDiceType;
	}

	public void setHitDice(int hitDice) {
		this.hitDice = hitDice;
	}

	public void setHitDiceType(int hitDiceType) {
		this.hitDiceType = hitDiceType;
	}

	public void setHitDiceSpecial(int hitDiceSpecial) {
		this.hitDiceSpecial = hitDiceSpecial;
	}

}
