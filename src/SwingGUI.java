import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.miginfocom.swing.MigLayout;

public class SwingGUI extends JFrame {

	private JPopupMenu popupMenu;
	private JLabel label;
	private JLabel labelEnc;
	private JScrollPane sPane;
	private JScrollPane spEnc;
	public JList<String> list;
	public JList<String> listEnc;
	public Encounter enc = new Encounter();
	public String selType = "Creature Type";
	public String selEnc = "Creature";
	public int selIndex = 0;
	public int selEncIndex = 0;

	private ArrayList<String> fltrList = new ArrayList<String>();

	private String currClimate = "ANY";
	private String currTerrain = "ANY";
	private String currActivity = "ANY";

	private static final long serialVersionUID = 1L;

	public SwingGUI(String frameTitle) {
		super(frameTitle);
	}

	public void createAndShowGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1280, 720);
		setLocationRelativeTo(null);

		MigLayout mig = new MigLayout();
		setLayout(mig);

		JPanel pane = (JPanel) getContentPane();
		pane.setSize(1280, 720);

		createMenuBar();
		createPopupMenu();

		CreatureTable.sort();
		String[] crType = new String[CreatureTable.ctypeTable.size()];
		for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
			crType[i] = CreatureTable.ctypeTable.get(i).getName();
			fltrList.add(CreatureTable.ctypeTable.get(i).getName());
		}

		this.list = new JList<String>(crType);
		updateListListener(list);

		sPane = new JScrollPane();
		sPane.setPreferredSize(new Dimension(222, 1200));
		sPane.getViewport().add(this.list);

		JButton addToEncounter = new JButton("Add to Encounter");
		addToEncounter.addActionListener((ActionEvent e) -> {
			clickAddToEncounter(selType);
		});

		JButton generate = new JButton("Generate");
		generate.addActionListener((ActionEvent e) -> {
			clickGenerate();
		});

		spEnc = new JScrollPane();
		spEnc.setPreferredSize(new Dimension(266, 1200));
		spEnc.getViewport().add(this.listEnc);

		JButton addEnc = new JButton("Add");
		addEnc.addActionListener((ActionEvent e) -> {
			clickAddCreature();
		});
		JButton removeEnc = new JButton("Remove");
		removeEnc.addActionListener((ActionEvent e) -> {
			clickRemoveEncounter(selEncIndex);
		});
		JButton popout = new JButton("Popout");
		popout.addActionListener((ActionEvent e) -> {
			clickPopout(spEnc, listEnc, pane);
		});

		JLabel fill1 = new JLabel("fill1");
		JLabel fill2 = new JLabel("fill2");
		JLabel fill4 = new JLabel("fill4");
		JLabel fill5 = new JLabel("fill5");
		JLabel fill6 = new JLabel("fill6");
		JLabel fill7 = new JLabel("fill7");

		JLabel fill3 = new JLabel("fill3");
		JLabel fill8 = new JLabel("fill8");
		// JLabel fill9 = new JLabel("fill9");
		// JLabel fill10 = new JLabel("fill10");
		// JLabel fill11 = new JLabel("fill11");
		// JLabel fill12 = new JLabel("fill12");
		// JLabel fill13 = new JLabel("fill13");

		// JLabel fill8 = new JLabel("Number of Creatures: \n");

		label = new JLabel("Creature Types");
		labelEnc = new JLabel("Creatures: \n");

		JLabel activityLabel = new JLabel("Activity Cycle:");

		JButton activityDay = new JButton("Day");
		activityDay.addActionListener((ActionEvent e) -> {
			currActivity = clickDayActivity(pane);
		});
		JButton activityAny = new JButton("Any");
		activityAny.addActionListener((ActionEvent e) -> {
			currActivity = clickAnyActivity(pane);
		});
		JButton activityNight = new JButton("Night");
		activityNight.addActionListener((ActionEvent e) -> {
			currActivity = clickNightActivity(pane);
		});

		JLabel climLabel = new JLabel("Filter Climate/Terrain:");

		JButton plainsB = new JButton("Plains");
		JButton forestB = new JButton("Forest");
		JButton jungleB = new JButton("Jungle");
		JButton hillsB = new JButton("Hills");
		JButton mountainsB = new JButton("Mountains");
		JButton swampB = new JButton("Swamp");
		JButton desertB = new JButton("Desert");
		JButton subtB = new JButton("Subterranean");
		JButton aquaB = new JButton("Aquatic");

		plainsB.addActionListener((ActionEvent e) -> {
			selType = "Creature Type";
			label.setText(selType);
			clickPlains(pane);
		});

		forestB.addActionListener((ActionEvent e) -> {
			selType = "Creature Type";
			label.setText(selType);
			clickForest(pane);
		});

		jungleB.addActionListener((ActionEvent e) -> {
			selType = "Creature Type";
			label.setText(selType);
			clickJungle(pane);
		});
		hillsB.addActionListener((ActionEvent e) -> {
			selType = "Creature Type";
			label.setText(selType);
			clickHills(pane);
		});

		mountainsB.addActionListener((ActionEvent e) -> {
			selType = "Creature Type";
			label.setText(selType);
			clickMountains(pane);
		});

		swampB.addActionListener((ActionEvent e) -> {
			selType = "Creature Type";
			label.setText(selType);
			clickSwamp(pane);
		});

		desertB.addActionListener((ActionEvent e) -> {
			selType = "Creature Type";
			label.setText(selType);
			clickDesert(pane);
		});

		subtB.addActionListener((ActionEvent e) -> {
			selType = "Creature Type";
			label.setText(selType);
			clickSubterranian(pane);
		});

		aquaB.addActionListener((ActionEvent e) -> {
			selType = "Creature Type";
			label.setText(selType);
			clickAquatic(pane);
		});

		JButton anyButton = new JButton("Any");
		anyButton.addActionListener((ActionEvent e) -> {
			selType = "Creature Type";
			label.setText(selType);
			currClimate = clickAny(pane);
		});
		JButton tropButton = new JButton("Tropical");
		tropButton.addActionListener((ActionEvent e) -> {
			selType = "Creature Type";
			label.setText(selType);

			currClimate = clickTropical(pane);
		});
		JButton subtropButton = new JButton("Sub-Tropical");
		subtropButton.addActionListener((ActionEvent e) -> {
			selType = "Creature Type";
			label.setText(selType);

			currClimate = clickSubTropical(pane);
		});
		JButton tempButton = new JButton("Temperate");
		tempButton.addActionListener((ActionEvent e) -> {
			selType = "Creature Type";
			label.setText(selType);

			currClimate = clickTemperate(pane);
		});
		JButton subaButton = new JButton("Sub-Arctic");
		subaButton.addActionListener((ActionEvent e) -> {
			selType = "Creature Type";
			label.setText(selType);

			currClimate = clickSubarctic(pane);
		});
		JButton arctButton = new JButton("Arctic");
		arctButton.addActionListener((ActionEvent e) -> {
			selType = "Creature Type";
			label.setText(selType);

			currClimate = clickArctic(pane);
		});

		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});
		quitButton.setToolTipText("Exits the application");

		pane.add(sPane, "span 0 1");

		// pane.add(new JLabel("hi"), "w 250");
		// pane.add(label, "w 250");
		pane.add(fill1);
		pane.add(fill2, "w 250");
		// pane.add(labelEnc, "w 250");
		pane.add(labelEnc, "w 250");

		pane.add(activityLabel, "flowy, aligny top, right, split 4");
		pane.add(activityDay, "right");
		pane.add(activityAny, "right");
		pane.add(activityNight, "right");

		pane.add(fill3, "flowy, aligny top, right");

		pane.add(climLabel, "flowy, aligny top, split 7");

		pane.add(anyButton, "right");
		pane.add(tropButton, "right");
		pane.add(subtropButton, "right");
		pane.add(tempButton, "right");
		pane.add(subaButton, "right");
		pane.add(arctButton, "right, wrap");

		pane.add(addToEncounter, "left, split 2");
		pane.add(generate, "right");

		pane.add(fill4, "w 250");
		pane.add(fill5, "w 250");
		pane.add(fill6, "w 250");
		pane.add(fill7, "w 250");
		pane.add(fill8, "w 250");

		pane.add(quitButton, "right");

		pane.add(plainsB, "flowy, cell 5 0, right");
		pane.add(forestB, "cell 5 0, right");
		pane.add(jungleB, "cell 5 0, right");
		pane.add(hillsB, "cell 5 0, right");
		pane.add(mountainsB, "cell 5 0, right");
		pane.add(swampB, "cell 5 0, right");
		pane.add(desertB, "cell 5 0, right");
		pane.add(subtB, "cell 5 0, right");
		pane.add(aquaB, "cell 5 0, right");

		pane.add(spEnc, "flowy, left, cell 2 0");
		pane.add(addEnc, "split 3, cell 2 1");
		pane.add(removeEnc, "cell 2 1");
		pane.add(popout, "cell 2 1");

		setVisible(true);
	}

	private void clickAddToEncounter(String creatureName) {
		enc.removeAll();
		for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
			if (CreatureTable.ctypeTable.get(i).getName().equals(creatureName)) {

				enc.generateEncounter(CreatureTable.ctypeTable.get(i));
				listEnc = new JList<String>(enc.print());
				labelEnc.setText("Number of Creatures: " + Integer.toString(listEnc.getModel().getSize()));
				// labelEnc = new
				// JLabel(Integer.toString(listEnc.getModel().getSize()));

				spEnc.getViewport().add(listEnc);
				spEnc.getViewport().revalidate();
				spEnc.getViewport().repaint();

				return;
			}
		}
		JOptionPane.showMessageDialog(null, "The selected creature was not found in CreatureList.txt");
	}

	private void clickPopout(JScrollPane spane, JList<String> list, JPanel pane) {
		JFrame popout = new JFrame("Encounter");

		popout.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				pane.add(spEnc, "flowy, cell 2 0");
				pane.revalidate();
				pane.repaint();
				popout.dispose();
			}
		});

		spane.getViewport().add(list);
		popout.add(spane);

		popout.setSize(300, 500);

		spane.validate();
		spane.repaint();
		popout.setVisible(true);

	}

	private void clickGenerate() {
		Random rng = new Random();

		ArrayList<CreatureType> rarityList = new ArrayList<CreatureType>();

		// number ranging from 1-100
		int rarity = rng.nextInt(100) + 1;

		int get;
		if (rarity <= 65) {
			get = 1;
		} else if (rarity <= 85) {
			get = 2;
		} else if (rarity <= 96) {
			get = 3;
		} else {
			get = 4;
		}

		int found = -1;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < fltrList.size(); i++) {
			for (int j = 0; j < CreatureTable.ctypeTable.size(); j++) {

				CreatureType ct = CreatureTable.ctypeTable.get(j);
				if (fltrList.get(i).equals(ct.getName())) {

					found = 1;
					if (get == ct.getRarity()) {
						rarityList.add(ct);

						break;
					}
				}
			}
			if (found < 0) {
				sb.append(fltrList.get(i));
				sb.append("\n");
			}
			found = -1;
		}
		if (sb.length() != 0) {
			System.out.println(sb.toString());
			JOptionPane.showMessageDialog(null, "The following Creatures did not have an entry in CreatureList.txt:\n"
					+ sb.toString() + "\nGeneration was aborted.");
			return;
		}

		int selected = -1;
		try {
			selected = rng.nextInt(rarityList.size());

		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null,
					"The list of Creature Types is missing creatures of the randomized rarity. "
							+ "\nAdd more creatures to the CreatureList or try again.");
		}

		enc.removeAll();
		if (selected >= 0) {
			enc.generateEncounter(rarityList.get(selected));
		} else {
			return;
		}
		listEnc = new JList<String>(enc.print());

		labelEnc.setText("Creatures: " + Integer.toString(listEnc.getModel().getSize()));

		updateEncListListener(listEnc);

		spEnc.getViewport().add(listEnc);
		spEnc.getViewport().revalidate();
		spEnc.getViewport().repaint();
	}

	private void clickRemoveEncounter(int index) {
		try {
			enc.removeCreature(index);
			selEncIndex = 0;
		} catch (IndexOutOfBoundsException e) {
		}
		listEnc = new JList<String>(enc.print());

		labelEnc.setText("Number of Creatures: " + Integer.toString(listEnc.getModel().getSize()));

		updateEncListListener(listEnc);

		spEnc.getViewport().add(listEnc);
		spEnc.getViewport().revalidate();
		spEnc.getViewport().repaint();
	}

	private void clickAddCreature() {

		JFrame frame = new JFrame("Add Creature");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new MigLayout());
		frame.setSize(new Dimension(520, 150));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());

		JLabel namelabel = new JLabel("Name: ");
		JLabel hplabel = new JLabel("HP: ");
		JLabel desclabel = new JLabel("(Leave blank for random)");

		JLabel warning = new JLabel(
				"<html>Creature name not found in list!<br>If this is intentional, make sure hp is set and click \"Add\" again</html>");
		warning.setForeground(Color.RED);
		warning.setVisible(false);

		JTextField hpfield = new JTextField(3);
		hpfield.setEditable(true);

		JButton cancel = new JButton("Cancel");
		cancel.addActionListener((ActionEvent e) -> {
			frame.dispose();
		});

		String[] array = new String[CreatureTable.ctypeTable.size()];
		for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
			array[i] = CreatureTable.ctypeTable.get(i).getName();
		}

		JComboBox<String> nameBox = new JComboBox<String>(array);
		JLabel hdLabel = new JLabel("Hit Dice");
		JComboBox<Integer> hdBox = new JComboBox<Integer>();
		hdBox.setSize(35, 15);
		hdBox.setEditable(true);

		JButton add = new JButton("Add");
		add.addActionListener((ActionEvent e) -> {
			/*
			 * 0= does not contain, 1= contains, 2= does not contain but add
			 * creature
			 */
			Creature c = new Creature("NONE", 0, 0);
			int contains = 0;
			String boxsel = nameBox.getSelectedItem().toString();

			for (String cr : array) {
				if (boxsel.equals(cr)) {
					contains = 1;
					break;
				}
			}

			if (contains == 0 && !warning.isVisible()) {
				warning.setVisible(true);
				// contains = 2;
			} else if (warning.isVisible() && !hpfield.getText().equals("")) {
				try {
					c = new Creature(boxsel, Integer.parseInt(hpfield.getText()), 0);
				} catch (NumberFormatException er) {
					JOptionPane.showMessageDialog(null, "Invalid HP input!");
					return;
				}
				enc.addCreature(c);
			} else if (contains == 1 && hpfield.getText().equals("")) {
				for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
					if (boxsel.equals(CreatureTable.ctypeTable.get(i).getName())) {
						c = CreatureTable.ctypeTable.get(i).rollCreature();
						enc.addCreature(c);
						break;
					}
				}
			} else if (contains == 1 && !hpfield.getText().equals("")) {
				try {
					c = new Creature(boxsel, Integer.parseInt(hpfield.getText()),
							Integer.parseInt(hdBox.getSelectedItem().toString()));
				} catch (NumberFormatException er) {
					JOptionPane.showMessageDialog(null, "Invalid HP or HD input!");
					return;
				}
				enc.addCreature(c);
			}
			listEnc = new JList<String>(enc.print());

			labelEnc.setText("Creatures: " + Integer.toString(listEnc.getModel().getSize()));

			updateEncListListener(listEnc);

			spEnc.getViewport().add(listEnc);
			spEnc.getViewport().revalidate();
			spEnc.getViewport().repaint();

		});

		nameBox.setEditable(true);

		panel.add(namelabel, "split 3, w 130");
		panel.add(hplabel, "shrink");
		panel.add(desclabel, "wrap");

		panel.add(nameBox, "split 2");
		panel.add(hpfield, "wrap");
		panel.add(warning, "wrap");
		panel.add(add, "split 2, cell 2 2, right, bottom");
		panel.add(cancel, "right, bottom");

		panel.add(hdBox, "cell 2 1");
		panel.add(hdLabel, "cell 2 0");
		frame.add(panel);

		frame.setVisible(true);
	}

	private void clickPlains(JPanel pane) {
		if (currClimate.equals("ANY")) {
			clickAny(pane);
		} else if (currClimate.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currClimate.equals("SUBTROPICAL")) {
			clickSubTropical(pane);
		} else if (currClimate.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currClimate.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currClimate.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("plain")) {
						tempList.add(fltrList.get(j));

						break;
					}
				}
			}
		}

		fltrList = tempList;
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(list);

	}

	private void clickForest(JPanel pane) {
		if (currClimate.equals("ANY")) {
			clickAny(pane);
		} else if (currClimate.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currClimate.equals("SUBTROPICAL")) {
			clickSubTropical(pane);
		} else if (currClimate.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currClimate.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currClimate.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("forest")) {
						tempList.add(fltrList.get(j));

						break;
					}
				}
			}
		}

		fltrList = tempList;
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(list);

	}

	private void clickJungle(JPanel pane) {
		if (currClimate.equals("ANY")) {
			clickAny(pane);
		} else if (currClimate.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currClimate.equals("SUBTROPICAL")) {
			clickSubTropical(pane);
		} else if (currClimate.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currClimate.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currClimate.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("jungle")) {
						tempList.add(fltrList.get(j));

						break;
					}
				}
			}
		}

		fltrList = tempList;
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(list);

	}

	private void clickHills(JPanel pane) {
		if (currClimate.equals("ANY")) {
			clickAny(pane);
		} else if (currClimate.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currClimate.equals("SUBTROPICAL")) {
			clickSubTropical(pane);
		} else if (currClimate.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currClimate.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currClimate.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("hill")) {
						tempList.add(fltrList.get(j));

						break;
					}
				}
			}
		}

		fltrList = tempList;
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(list);

	}

	private void clickMountains(JPanel pane) {
		if (currClimate.equals("ANY")) {
			clickAny(pane);
		} else if (currClimate.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currClimate.equals("SUBTROPICAL")) {
			clickSubTropical(pane);
		} else if (currClimate.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currClimate.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currClimate.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("mountain")) {
						tempList.add(fltrList.get(j));

						break;
					}
				}
			}
		}

		fltrList = tempList;
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(list);

	}

	private void clickSwamp(JPanel pane) {
		if (currClimate.equals("ANY")) {
			clickAny(pane);
		} else if (currClimate.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currClimate.equals("SUBTROPICAL")) {
			clickSubTropical(pane);
		} else if (currClimate.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currClimate.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currClimate.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("swamp")) {
						tempList.add(fltrList.get(j));

						break;
					}
				}
			}
		}

		fltrList = tempList;
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(list);

	}

	private void clickDesert(JPanel pane) {
		if (currClimate.equals("ANY")) {
			clickAny(pane);
		} else if (currClimate.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currClimate.equals("SUBTROPICAL")) {
			clickSubTropical(pane);
		} else if (currClimate.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currClimate.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currClimate.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("desert")) {
						tempList.add(fltrList.get(j));

						break;
					}
				}
			}
		}

		fltrList = tempList;
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(list);

	}

	private void clickSubterranian(JPanel pane) {
		if (currClimate.equals("ANY")) {
			clickAny(pane);
		} else if (currClimate.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currClimate.equals("SUBTROPICAL")) {
			clickSubTropical(pane);
		} else if (currClimate.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currClimate.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currClimate.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("subterranean")) {
						tempList.add(fltrList.get(j));

						break;
					}
				}
			}
		}

		fltrList = tempList;
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(list);
	}

	private void clickAquatic(JPanel pane) {
		if (currClimate.equals("ANY")) {
			clickAny(pane);
		} else if (currClimate.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currClimate.equals("SUBTROPICAL")) {
			clickSubTropical(pane);
		} else if (currClimate.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currClimate.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currClimate.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("aquatic")) {
						tempList.add(fltrList.get(j));

						break;
					}
				}
			}
		}

		fltrList = tempList;
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(list);
	}

	private String clickAny(JPanel pane) {
		fltrList.clear();
		for (CreatureType ct : CreatureTable.ctypeTable) {
			fltrList.add(ct.name);

		}
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(this.list);
		sPane.getViewport().revalidate();
		sPane.getViewport().repaint();

		revalidate();

		return "ANY";
	}

	private String clickTropical(JPanel pane) {
		fltrList.clear();
		for (CreatureType ct : CreatureTable.ctypeTable) {
			if (ct.getClimateList().contains("Tropical")) {
				fltrList.add(ct.name);
			}
		}
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(this.list);
		sPane.getViewport().revalidate();
		sPane.getViewport().repaint();
		revalidate();

		return "TROPICAL";
	}

	private String clickSubTropical(JPanel pane) {
		fltrList.clear();
		for (CreatureType ct : CreatureTable.ctypeTable) {
			if (ct.getClimateList().contains("Sub-Tropical")) {
				fltrList.add(ct.name);
			}
		}
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(this.list);
		sPane.getViewport().revalidate();
		sPane.getViewport().repaint();
		revalidate();

		return "SUBTROPICAL";
	}

	private String clickTemperate(JPanel pane) {
		fltrList.clear();
		for (CreatureType ct : CreatureTable.ctypeTable) {
			if (ct.getClimateList().contains("Temperate")) {
				fltrList.add(ct.name);
			}
		}
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(this.list);
		sPane.getViewport().revalidate();
		sPane.getViewport().repaint();
		revalidate();

		return "TEMPERATE";
	}

	private String clickSubarctic(JPanel pane) {
		fltrList.clear();
		for (CreatureType ct : CreatureTable.ctypeTable) {
			if (ct.getClimateList().contains("Sub-Arctic")) {
				fltrList.add(ct.name);
			}
		}
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(this.list);
		sPane.getViewport().revalidate();
		sPane.getViewport().repaint();
		revalidate();

		return "SUBARCTIC";
	}

	private String clickArctic(JPanel pane) {
		fltrList.clear();
		for (CreatureType ct : CreatureTable.ctypeTable) {
			if (ct.getClimateList().contains("Arctic")) {
				fltrList.add(ct.name);
			}
		}
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(this.list);
		sPane.getViewport().revalidate();
		sPane.getViewport().repaint();
		revalidate();

		return "ARCTIC";
	}

	private String clickDayActivity(JPanel pane) {

		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getActivityCycle().contains("Day")
							|| CreatureTable.ctypeTable.get(i).getActivityCycle().contains("Any")) {
						tempList.add(fltrList.get(j));

						break;
					}
				}
			}
		}

		fltrList = tempList;
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(list);

		return "DAY";
	}

	private String clickAnyActivity(JPanel pane) {

		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getActivityCycle().contains("Any")) {
						tempList.add(fltrList.get(j));

						break;
					}
				}
			}
		}

		fltrList = tempList;
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(list);

		return "ANY";
	}

	private String clickNightActivity(JPanel pane) {

		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getActivityCycle().contains("Night")
							|| CreatureTable.ctypeTable.get(i).getActivityCycle().contains("Any")) {
						tempList.add(fltrList.get(j));

						break;
					}
				}
			}
		}

		fltrList = tempList;
		String[] newList = fltrList.toArray(new String[fltrList.size()]);
		this.list = new JList<String>(newList);

		updateListListener(list);

		sPane.getViewport().add(list);

		return "NIGHT";
	}

	private void addListPopMenu(JList<String> list) {
		JPopupMenu pop = new JPopupMenu() {
			/**
			 * ????????????????
			 */
			private static final long serialVersionUID = -4412378649348183793L;

			@Override
			public void show(Component invoker, int x, int y) {
				int row = list.locationToIndex(new Point(x, y));
				if (row != -1) {
					list.setSelectedIndex(row);
					selIndex = list.getSelectedIndex();
				}
				super.show(invoker, x, y);
			}
		};

		JMenuItem remove = new JMenuItem("Remove");
		remove.addActionListener((ActionEvent e) -> {
			fltrList.remove(selIndex);

			this.list = new JList<String>(fltrList.toArray(new String[fltrList.size()]));

			updateListListener(this.list);

			sPane.getViewport().add(this.list);
			sPane.getViewport().revalidate();
			sPane.getViewport().repaint();
		});
		JMenuItem entry = new JMenuItem("View Entry");

		entry.addActionListener((ActionEvent e) -> {
			JFrame entFrame = new JFrame("Creature Entry");
			entFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			entFrame.setLocationRelativeTo(this);
			entFrame.setLocation((int) this.getLocation().getX(), (int) this.getLocation().getY());
			entFrame.setLayout(new MigLayout());
			entFrame.setSize(320, 550);

			JPanel panel = new JPanel();
			JTextArea ta = new JTextArea();
			StringBuilder sb = new StringBuilder();

			CreatureType ct = new CreatureType();

			for (CreatureType crT : CreatureTable.ctypeTable) {
				if (list.getSelectedValue().equals(crT.getName())) {
					ct = crT;

					break;
				}
			}

			sb.append(ct.getName().toUpperCase() + "\n\n");

			sb.append("Climate: ");
			for (String s : ct.getClimateList()) {
				sb.append(s + ", ");
			}
			sb.append("\n");
			sb.append("Terrain: ");
			for (int i = 0; i < ct.getTerrainList().size(); i++) {
				// If the creature has more othan 4 terrains, insert linebreak
				// and allign with first terrain on previous line
				if (i == 4) {
					sb.append("\n               ");
				}
				sb.append(ct.getTerrainList().get(i) + ", ");
			}

			sb.append("\n");
			sb.append("Frequency: ");
			switch (ct.getRarity()) {
			case (1):
				sb.append("Common");
				break;
			case (2):
				sb.append("Uncommon");
				break;
			case (3):
				sb.append("Rare");
				break;
			case (4):
				sb.append("Very Rare");
				break;
			}

			sb.append("\n");
			sb.append("Organization: ");
			sb.append(ct.getOrganization());

			sb.append("\n");
			sb.append("Activity Cycle: ");
			sb.append(ct.getActivityCycle());

			sb.append("\n");
			sb.append("Diet: ");
			sb.append(ct.getDiet());

			sb.append("\n");
			sb.append("Intelligence: ");
			sb.append(ct.getIntelligence());

			sb.append("\n");
			sb.append("Treasure: ");
			sb.append(ct.getTreasure());

			sb.append("\n");
			sb.append("Alignment: ");
			sb.append(ct.getAllignment());

			sb.append("\n");
			sb.append("No. Appearing: ");
			sb.append(ct.getAppDice() + "d" + ct.getAppDiceType());

			sb.append("\n");
			sb.append("Armor Class: ");
			sb.append(ct.getAc());

			sb.append("\n");
			sb.append("Movement: ");
			sb.append(ct.getMovement());

			sb.append("\n");
			sb.append("Hit Dice: ");
			sb.append(ct.getHitDice() + "d" + ct.getHitDiceType());
			if (ct.getHitDiceSpecial() > 0) {
				sb.append(" +" + ct.getHitDiceSpecial());
			} else if (ct.getHitDiceSpecial() < 0) {
				sb.append(" " + ct.getHitDiceSpecial());
			}

			sb.append("\n");
			sb.append("THAC0: ");
			sb.append(ct.getThac0());

			sb.append("\n");
			sb.append("No. of Attacks: ");
			sb.append(ct.getAttacks());

			sb.append("\n");
			sb.append("Damage/Attack: ");
			sb.append(ct.getDamage());

			sb.append("\n");
			sb.append("Special Attacks: ");
			sb.append(ct.getSpAttacks());

			sb.append("\n");
			sb.append("Special Defenses: ");
			sb.append(ct.getSpDefences());

			sb.append("\n");
			sb.append("Magic Resistance: ");
			sb.append(ct.getMagicRes());

			sb.append("\n");
			sb.append("Size: ");
			sb.append(ct.getSize());

			sb.append("\n");
			sb.append("Morale: ");
			sb.append(ct.getMorale());

			sb.append("\n");
			sb.append("XP Value: ");
			sb.append(ct.getXp());

			sb.append("\n");
			sb.append("\n");
			sb.append("Special Rules (See original monster entry): ");
			sb.append(ct.getSpRules());

			sb.append("\n");
			sb.append("\n");
			sb.append("* = Additonal information is listed in the\noriginal creature entry!");

			Font font = ta.getFont();
			ta.setFont(font.deriveFont(Font.BOLD));
			ta.setText(sb.toString());
			ta.setEditable(false);
			ta.setOpaque(false);
			panel.add(ta);
			panel.setOpaque(false);
			entFrame.add(panel);
			entFrame.setResizable(false);
			entFrame.setVisible(true);
		});

		pop.add(entry);
		pop.add(remove);

		list.setComponentPopupMenu(pop);
	}

	private void updateListListener(JList<String> list) {
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					selType = (String) list.getSelectedValue();
					selIndex = list.getSelectedIndex();
					label.setText(selType);
				}
			}
		});

		addListPopMenu(list);
	}

	private void updateEncListListener(JList<String> list) {
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					selEnc = (String) list.getSelectedValue();
					selEncIndex = list.getSelectedIndex();
					// labelEnc.setText("Number of Creatures: " +
					// Integer.toString(list.getModel().getSize()));
					// labelEnc.setText(selEnc);
				}
			}
		});
	}

	private void createPopupMenu() {
		popupMenu = new JPopupMenu();

		JMenuItem quitPop = new JMenuItem("Quit");
		quitPop.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});
		popupMenu.add(quitPop);

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	private void createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem exMenuItem = new JMenuItem("Exit");
		JMenuItem impMenuItem = new JMenuItem("Import List");

		impMenuItem.setToolTipText("Import a filtered Creature List");
		impMenuItem.addActionListener((ActionEvent e) -> {

			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int result = fileChooser.showOpenDialog(this);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt");
			fileChooser.setFileFilter(filter);

			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();

				if (filter.accept(selectedFile)) {

					try {
						Scanner scan = new Scanner(selectedFile);
						ArrayList<String> temp = new ArrayList<String>();
						temp = fltrList;
						fltrList.clear();
						while (scan.hasNext()) {
							if (scan.hasNextInt()) {
								scan.close();
								fltrList = temp;
								throw new IllegalArgumentException("The selected file is not a valid Creature List!");
							}
							String next = scan.next();
							if (next.length() >= 70) {
								scan.close();
								fltrList = temp;
								throw new IllegalArgumentException("The selected file is not a valid Creature List!");
							}
							fltrList.add(next);
						}
						scan.close();

						String[] newList = fltrList.toArray(new String[fltrList.size()]);
						list = new JList<String>(newList);

						updateListListener(list);

						sPane.getViewport().add(this.list);
						sPane.getViewport().revalidate();
						sPane.getViewport().repaint();
						revalidate();
					} catch (FileNotFoundException er1) {
						JOptionPane.showMessageDialog(null, "Ooops! Something went wrong, how embarrassing.");
					} catch (IllegalArgumentException er2) {
						JOptionPane.showMessageDialog(null, er2.getMessage());
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"The selected file was not recognized as a text file.\nMake sure the file has the \".txt\" file extension and try again.");
				}
			}
		});

		exMenuItem.setToolTipText("Exit the application");
		exMenuItem.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});

		file.add(impMenuItem);
		file.add(exMenuItem);
		menuBar.add(file);

		setJMenuBar(menuBar);
	}
}
