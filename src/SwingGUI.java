import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;
import sun.net.www.content.text.PlainTextInputStream;

public class SwingGUI extends JFrame {

	private JPopupMenu popupMenu;
	private JLabel label;
	private JLabel labelEnc;
	private JScrollPane sPane;
	private JScrollPane spEnc;
	public JList<String> list;
	public JList<String> listEnc;
	public Encounter enc = new Encounter();
	public String selType = "Goblin";
	public String selEnc = "-NONE-";
	public int selEncIndex = 0;

	private ArrayList<String> fltrList = new ArrayList<String>();

	private String currC = "ANY";

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

		JLabel fill1 = new JLabel("fill");
		JLabel fill2 = new JLabel("fill");
		JLabel fill3 = new JLabel("fill");
		JLabel fill4 = new JLabel("fill");
		JLabel fill5 = new JLabel("fill");
		JLabel fill6 = new JLabel("fill");
		JLabel fill7 = new JLabel("fill");

		label = new JLabel("Creature Types");
		labelEnc = new JLabel("Creatures");

		JLabel climLabel = new JLabel("Filter by Climate/Terrain:");

		JButton plainsB = new JButton("Plains");
		JButton forestB = new JButton("Forest");
		JButton hillsB = new JButton("Hills");
		JButton mountainsB = new JButton("Mountains");
		JButton swampB = new JButton("Swamp");
		JButton desertB = new JButton("Desert");
		JButton subtB = new JButton("Subterranean");
		JButton aquaB = new JButton("Aquatic");

		plainsB.addActionListener((ActionEvent e) -> {
			clickPlains(pane);
		});

		forestB.addActionListener((ActionEvent e) -> {
			clickForest(pane);
		});

		hillsB.addActionListener((ActionEvent e) -> {
			clickHills(pane);
		});

		mountainsB.addActionListener((ActionEvent e) -> {
			clickMountains(pane);
		});

		swampB.addActionListener((ActionEvent e) -> {
			clickSwamp(pane);
		});

		desertB.addActionListener((ActionEvent e) -> {
			clickDesert(pane);
		});

		subtB.addActionListener((ActionEvent e) -> {
			clickSubterranian(pane);
		});

		aquaB.addActionListener((ActionEvent e) -> {
			clickAquatic(pane);
		});

		JButton anyButton = new JButton("Any");
		anyButton.addActionListener((ActionEvent e) -> {

			currC = clickAny(pane);
		});
		JButton tropButton = new JButton("Tropical");
		tropButton.addActionListener((ActionEvent e) -> {

			currC = clickTropical(pane);
		});
		JButton tempButton = new JButton("Temperate");
		tempButton.addActionListener((ActionEvent e) -> {

			currC = clickTemperate(pane);
		});
		JButton subaButton = new JButton("Sub-Arctic");
		subaButton.addActionListener((ActionEvent e) -> {

			currC = clickSubarctic(pane);
		});
		JButton arctButton = new JButton("Arctic");
		arctButton.addActionListener((ActionEvent e) -> {

			currC = clickArctic(pane);
		});

		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});
		quitButton.setToolTipText("Exits the application");

		pane.add(sPane, "span 0 1");

		// pane.add(new JLabel("hi"), "w 250");
		pane.add(label, "w 250");
		pane.add(fill1, "w 250");
		pane.add(labelEnc, "w 250");
		// pane.add(fill2, "w 250");
		pane.add(fill3, "w 250, flowy, top");

		pane.add(climLabel, "flowy, aligny top, split 6");

		pane.add(anyButton, "right");
		pane.add(tropButton, "right");
		pane.add(tempButton, "right");
		pane.add(subaButton, "right");
		pane.add(arctButton, "right, wrap");

		pane.add(addToEncounter, "left, split 2");
		pane.add(generate, "right");

		pane.add(fill4, "w 250");
		pane.add(fill5, "w 250");
		pane.add(fill6, "w 250");
		pane.add(fill7, "w 250");

		pane.add(quitButton, "right");

		pane.add(plainsB, "flowy, cell 4 0, right");
		pane.add(forestB, "cell 4 0, right");
		pane.add(hillsB, "cell 4 0, right");
		pane.add(mountainsB, "cell 4 0, right");
		pane.add(swampB, "cell 4 0, right");
		pane.add(desertB, "cell 4 0, right");
		pane.add(subtB, "cell 4 0, right");
		pane.add(aquaB, "cell 4 0, right");

		pane.add(spEnc, "flowy, cell 2 0");
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

				spEnc.getViewport().add(listEnc);
				spEnc.getViewport().revalidate();
				spEnc.getViewport().repaint();
			}
		}
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

		System.out.println("Filter List size: " + fltrList.size());
		for (int i = 0; i < fltrList.size(); i++) {
			for (int j = 0; j < CreatureTable.ctypeTable.size(); j++) {

				CreatureType ct = CreatureTable.ctypeTable.get(j);
				if (fltrList.get(i).equals(ct.getName())) {
					if (get == ct.getRarity()) {
						rarityList.add(ct);

						break;
					}

				}
			}
		}

		// TODO: Doesnt generateEncounter do this already?
		int selected = -1;
		try {
			selected = rng.nextInt(rarityList.size());

		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "List of Creature Types is missing creatures of the randomized rarity. "
					+ "The first creatures in the list have been added.\nAdd more creatures to the CreatureList or try again.");
		}

		enc.removeAll();
		if (selected >= 0) {
			enc.generateEncounter(rarityList.get(selected));
		} else {
			// Something went wrong with giving "selected" a value, autorolled
			// whatever is in the first element of the CreatureTable
			enc.generateEncounter(CreatureTable.ctypeTable.get(0));
		}
		listEnc = new JList<String>(enc.print());

		updateEncListListener(listEnc);

		spEnc.getViewport().add(listEnc);
		spEnc.getViewport().revalidate();
		spEnc.getViewport().repaint();
	}

	private void clickRemoveEncounter(int index) {
		try {
			enc.removeCreature(index);
			// TODO: Think this through, looks like hack to me
			// selEncIndex = Integer.MAX_VALUE;
		} catch (IndexOutOfBoundsException e) {
			// TODO: Deal with error better.
			System.out.println("No more creatures to remove!");
		}
		listEnc = new JList<String>(enc.print());

		updateEncListListener(listEnc);

		spEnc.getViewport().add(listEnc);
		spEnc.getViewport().revalidate();
		spEnc.getViewport().repaint();
	}

	private void clickAddCreature() {

		JFrame frame = new JFrame("Add Creature");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new MigLayout());
		frame.setSize(new Dimension(420, 150));
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

		JComboBox<String> box = new JComboBox<String>(array);

		JButton add = new JButton("Add");
		add.addActionListener((ActionEvent e) -> {
			/*
			 * 0= does not contain, 1= contains, 2= does not contain but add
			 * creature
			 */
			Creature c = new Creature("NONE", 0);
			int contains = 0;
			String boxsel = box.getSelectedItem().toString();

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
				// TODO Make sure the hp is actually an INT before doing this
				c = new Creature(boxsel, Integer.parseInt(hpfield.getText()));
				enc.addRandom(c);
			} else if (contains == 1 && hpfield.getText().equals("")) {
				for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
					if (boxsel.equals(CreatureTable.ctypeTable.get(i).getName())) {
						c = CreatureTable.ctypeTable.get(i).rollCreature();
						enc.addRandom(c);
					}
				}
			} else if (contains == 1 && !hpfield.getText().equals("")) {
				c = new Creature(boxsel, Integer.parseInt(hpfield.getText()));
				enc.addRandom(c);
			}

			// enc.addRandom(c);
			listEnc = new JList<String>(enc.print());

			// TODO Make sure that this is seperated from the rest of the
			// listeners,
			// as in make a new one specifically for this
			// because right now its competing with the other panel for which
			// thing
			// is selected ATM
			updateEncListListener(listEnc);

			spEnc.getViewport().add(listEnc);
			spEnc.getViewport().revalidate();
			spEnc.getViewport().repaint();

		});

		box.setEditable(true);

		panel.add(namelabel, "split 3, w 130");
		panel.add(hplabel, "shrink");
		panel.add(desclabel, "wrap");

		panel.add(box, "split 2");
		panel.add(hpfield, "wrap");
		panel.add(warning, "wrap");
		panel.add(add, "split 2, cell 2 2, right, bottom");
		panel.add(cancel, "right, bottom");

		frame.add(panel);

		frame.setVisible(true);
	}

	private void clickPlains(JPanel pane) {
		if (currC.equals("ANY")) {
			clickAny(pane);
		} else if (currC.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currC.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currC.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currC.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("plain")) {
						tempList.add(fltrList.get(j));
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
		if (currC.equals("ANY")) {
			clickAny(pane);
		} else if (currC.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currC.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currC.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currC.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("forest")) {
						tempList.add(fltrList.get(j));
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
		if (currC.equals("ANY")) {
			clickAny(pane);
		} else if (currC.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currC.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currC.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currC.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("hill")) {
						tempList.add(fltrList.get(j));
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
		if (currC.equals("ANY")) {
			clickAny(pane);
		} else if (currC.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currC.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currC.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currC.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("mountain")) {
						tempList.add(fltrList.get(j));
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
		if (currC.equals("ANY")) {
			clickAny(pane);
		} else if (currC.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currC.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currC.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currC.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("swamp")) {
						tempList.add(fltrList.get(j));
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
		if (currC.equals("ANY")) {
			clickAny(pane);
		} else if (currC.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currC.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currC.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currC.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("desert")) {
						tempList.add(fltrList.get(j));
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
		if (currC.equals("ANY")) {
			clickAny(pane);
		} else if (currC.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currC.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currC.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currC.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("subterranean")) {
						tempList.add(fltrList.get(j));
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
		if (currC.equals("ANY")) {
			clickAny(pane);
		} else if (currC.equals("TROPICAL")) {
			clickTropical(pane);
		} else if (currC.equals("TEMPERATE")) {
			clickTemperate(pane);
		} else if (currC.equals("SUBARCTIC")) {
			clickSubarctic(pane);
		} else if (currC.equals("ARCTIC")) {
			clickArctic(pane);
		}
		ArrayList<String> tempList = new ArrayList<String>();

		for (int j = 0; j < fltrList.size(); j++) {
			for (int i = 0; i < CreatureTable.ctypeTable.size(); i++) {
				if (CreatureTable.ctypeTable.get(i).getName().equals(fltrList.get(j))) {
					if (CreatureTable.ctypeTable.get(i).getTerrainList().contains("aquatic")) {
						tempList.add(fltrList.get(j));
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

	private void updateListListener(JList<String> list) {

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					selType = (String) list.getSelectedValue();
					label.setText(selType);
				}
			}
		});
	}

	private void updateEncListListener(JList<String> list) {
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					selEnc = (String) list.getSelectedValue();
					selEncIndex = list.getSelectedIndex();
					labelEnc.setText(selEnc);
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

		exMenuItem.setToolTipText("Exit the application");
		exMenuItem.addActionListener((ActionEvent e) -> {
			System.exit(0);
		});

		file.add(exMenuItem);
		menuBar.add(file);

		setJMenuBar(menuBar);
	}
}
