import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Run {

	public static void main(String[] args) {

		/* Create the general/default Creature Table for all creatures */
		CreatureTable cTable = new CreatureTable();
		try {

			/*
			 * Creatures are in file by line in order: Name, Climate, Number of
			 * appearing dice, Type of appearing dice, Terrain, Number of HD,
			 * Type of HD, +/- to health, Rarity 1 being Common and 4 being Very
			 * Rare
			 */
			Scanner scan = new Scanner(new File("textfiles/CreatureList.txt"));
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
				if (scan.hasNextInt()) {
					ct.setAppDiceSpecial(scan.nextInt());
				}

				/* Add all the terrains to the creature type */
				ArrayList<String> terrList = new ArrayList<String>();
				while (!scan.hasNextInt()) {
					terrList.add(scan.next());
				}
				ct.setTerrainList(terrList);

				ct.setHitDice(scan.nextInt());
				ct.setHitDiceType(scan.nextInt());
				ct.setHitDiceSpecial(scan.nextInt());
				ct.setHitDiceVariance(scan.nextInt());

				ct.setRarity(scan.nextInt());

				ct.setActivityCycle(scan.next());

				/* Add the Creature Type to the general Creature Table */
				cTable.add(ct);
			}
			scan.close();

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "The file \"CreatureList.txt\" was not found!");
		}
		try {
			Scanner scan = new Scanner(new File("textfiles/CreatureMisc.txt"));
			scan.useDelimiter("_");
			while (scan.hasNext()) {
				String name = scan.next();
				for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
					CreatureType ct = CreatureTable.ctypeTable.get(i);
					if (name.equals(ct.getName())) {
						ct.setOrganization(scan.next());
						ct.setActivityCycle(scan.next());
						ct.setDiet(scan.next());
						ct.setIntelligence(scan.next());
						ct.setTreasure(scan.next());
						ct.setAllignment(scan.next());
						ct.setAc(scan.next());
						ct.setMovement(scan.next());
						ct.setThac0(scan.next());
						ct.setAttacks(scan.next());
						ct.setDamage(scan.next());
						ct.setSpAttacks(scan.next());
						ct.setSpDefences(scan.next());
						ct.setMagicRes(scan.next());
						ct.setSize(scan.next());
						ct.setMorale(scan.next());
						ct.setXp(scan.next());
						ct.setSpRules(scan.next());

						break;
					}
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "The file \"CreatureMisc.txt\" was not found!");
		}

		SwingGUI gui = new SwingGUI("Random Encounter Generator");
		gui.createAndShowGUI();
	}
}
