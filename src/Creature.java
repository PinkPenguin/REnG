
public class Creature {
	
	public String name;
	public int hp;
	
	public Creature(){
		
	}
	
	public Creature(String name, int hp){
		this.name = name;
		this.hp = hp;
	}

	public void setName(String name){
		this.name = name;
	}
	public void setHP(int hp){
		this.hp = hp;
	}
}
