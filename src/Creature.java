
public class Creature {
	
	public String name;
	public int hp;
	public int hd;
	
	public Creature(){
		
	}
	
	public Creature(String name, int hp, int hd){
		this.name = name;
		this.hp = hp;
		this.hd = hd;
	}
	
	//================== Setters and getters start =====================

	public void setName(String name){
		this.name = name;
	}
	public void setHP(int hp){
		this.hp = hp;
	}

	public String getName() {
		return name;
	}

	public int getHp() {
		return hp;
	}

	public int getHd() {
		return hd;
	}


	public void setHd(int hd) {
		this.hd = hd;
	}
	
}
