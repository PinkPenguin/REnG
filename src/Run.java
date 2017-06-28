import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Run {

	public static void main(String[] args) {

		/* Create the general Creature Table for all creatures */
		CreatureTable cTable = new CreatureTable();
		try {

			/*
			 * Creatures are in file by line in order: Name, number of
			 * appearence dice, type of appearence dice, no. hit dice, type of
			 * hit dice, special +/- to hp
			 */
			Scanner scan = new Scanner(new File("textfiles/CreatureList"));
			while (scan.hasNext()) {

				/* New Creature Type */
				CreatureType ct = new CreatureType();
				ct.setName(scan.next());

				ArrayList<String> climList = new ArrayList<String>();

				/* Add all the climates to a list */
				while (!scan.hasNextInt()) {
					climList.add(scan.next());
				}
				ct.setClimateList(climList);

				/* Fill rest of No.App and hit dice numbers */
				ct.setAppDice(scan.nextInt());
				ct.setAppDiceType(scan.nextInt());

				/* Add all the terrains to the creature type */
				ArrayList<String> terrList = new ArrayList<String>();
				while (!scan.hasNextInt()) {
					terrList.add(scan.next());
				}
				ct.setTerrainList(terrList);

				ct.setHitDice(scan.nextInt());
				ct.setHitDiceType(scan.nextInt());
				ct.setHitDiceSpecial(scan.nextInt());

				/* Add the Creature Type to the general Creature Table */
				cTable.add(ct);
			}
			scan.close();

			SwingGUI gui = new SwingGUI("Random Encounter Generator");
			gui.createAndShowGUI();

		} catch (FileNotFoundException e) {
			System.out.println("Filerna kunde inte läsas!");
		}

		// Encounter enc = new Encounter();

	}

}
