import java.util.ArrayList;
import java.util.Collections;

public final class CreatureTable {
	
	public static ArrayList<CreatureType> ctypeTable = new ArrayList<CreatureType>();
	
	
	public CreatureTable(){
		
	}
	
	public void add(CreatureType ct){
		CreatureTable.ctypeTable.add(ct);
	}
	
	public static void sort() {
		Collections.sort(ctypeTable, (n1, n2) -> n1.getName().compareTo(n2.getName()));
	}
}
