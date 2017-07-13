import java.util.ArrayList;
import java.util.Random;

public class CreatureType {

	public String name = "-NONAME-";
	public ArrayList<String> climateList = new ArrayList<String>();
	public ArrayList<String> terrainList = new ArrayList<String>();
	public int appDice = 0;
	public int appDiceType = 0;
	public int appDiceSpecial = 0;
	
	public int hitDice = 0;
	public int hitDiceType = 0;
	public int hitDiceSpecial = 0;
	public int hitDiceVariance = 0;
	public int rarity = 0;

	public ArrayList<String> specTerrainList = new ArrayList<String>();
	public String organization;
	public String activityCycle;
	public String diet;
	public String intelligence;
	public String treasure;
	public String allignment;
	public String ac;
	public String movement;
	public String thac0;
	public String attacks;
	public String damage;
	public String spAttacks;
	public String spDefences;
	public String magicRes;
	public String size;
	public String morale;
	public String xp;
	// spCombat is a yes or no depending on if creature has additional
	// information in combat
	public String spRules;

	public CreatureType() {

	}

//	public CreatureType(String name, ArrayList<String> climateList, int appDice, int appDiceType, int hitDice,
//			int hitDiceType, int hitDiceSpecial) {
//		this.name = name;
//		this.climateList = climateList;
//		this.appDice = appDice;
//		this.appDiceType = appDiceType;
//		this.hitDice = hitDice;
//		this.hitDiceType = hitDiceType;
//		this.hitDiceSpecial = hitDiceSpecial;
//	}

	public Creature rollCreature() {
		Random rng = new Random();
		int hp = 0;
		int hd = this.hitDice;
		System.out.println(hitDiceVariance);
		if (hitDiceVariance > 0) {

			hd = this.hitDice + rng.nextInt(hitDiceVariance);
		}
		for (int i = 1; i <= hd; i++) {
			hp += rng.nextInt(hitDiceType) - 1;
		}
		hp -= this.hitDiceSpecial;

		return new Creature(this.name, hp, hd);
	}

	// ================== Setters and getters start =====================

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

	public void setRarity(int r) {
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

	public String getOrganization() {
		return organization;
	}

	public String getActivityCycle() {
		return activityCycle;
	}

	public String getDiet() {
		return diet;
	}

	public String getIntelligence() {
		return intelligence;
	}

	public String getTreasure() {
		return treasure;
	}

	public String getAllignment() {
		return allignment;
	}

	public String getAc() {
		return ac;
	}

	public String getMovement() {
		return movement;
	}

	public String getThac0() {
		return thac0;
	}

	public String getAttacks() {
		return attacks;
	}

	public String getDamage() {
		return damage;
	}

	public String getSpAttacks() {
		return spAttacks;
	}

	public String getSpDefences() {
		return spDefences;
	}

	public String getMagicRes() {
		return magicRes;
	}

	public String getSize() {
		return size;
	}

	public String getMorale() {
		return morale;
	}

	public String getXp() {
		return xp;
	}

	public String getSpRules() {
		return spRules;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public void setActivityCycle(String activityCycle) {
		this.activityCycle = activityCycle;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	public void setIntelligence(String intelligence) {
		this.intelligence = intelligence;
	}

	public void setTreasure(String treasure) {
		this.treasure = treasure;
	}

	public void setAllignment(String allignment) {
		this.allignment = allignment;
	}

	public void setAc(String ac) {
		this.ac = ac;
	}

	public void setMovement(String movement) {
		this.movement = movement;
	}

	public void setThac0(String thac0) {
		this.thac0 = thac0;
	}

	public void setAttacks(String attacks) {
		this.attacks = attacks;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	public void setSpAttacks(String spAttacks) {
		this.spAttacks = spAttacks;
	}

	public void setSpDefences(String spDefences) {
		this.spDefences = spDefences;
	}

	public void setMagicRes(String magicRes) {
		this.magicRes = magicRes;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public void setMorale(String morale) {
		this.morale = morale;
	}

	public void setXp(String xp) {
		this.xp = xp;
	}

	public void setSpRules(String spRules) {
		this.spRules = spRules;
	}

	public ArrayList<String> getSpecTerrainList() {
		return specTerrainList;
	}

	public void setSpecTerrainList(ArrayList<String> specTerrainList) {
		this.specTerrainList = specTerrainList;
	}

	public int getHitDiceVariance() {
		return hitDiceVariance;
	}

	public void setHitDiceVariance(int hitDiceVariance) {
		this.hitDiceVariance = hitDiceVariance;
	}

	public int getAppDiceSpecial() {
		return appDiceSpecial;
	}

	public void setAppDiceSpecial(int appDiceSpecial) {
		this.appDiceSpecial = appDiceSpecial;
	}

}
