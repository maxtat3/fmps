package ui;

import stage1.elements.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 */
public class UserInitDataStage1Frame extends JFrame {

	private JPanel jpMain = new JPanel();
	private JPanel jpElements = new JPanel();

	private JPanel jpDirection = new JPanel();

	private java.util.List<JTextField> jtfAlloyComWeights = new ArrayList<>();

	private JButton jbtnOk = new JButton("Ok");
	private JButton jbtnCancel = new JButton("Cancel");


	public UserInitDataStage1Frame() {
		super("User input data");


		// все основные элементы
		java.util.List<GeneralElementStage1> basicElements = new ArrayList<>();
		basicElements.add(new Fe());
		basicElements.add(new Mn());
		basicElements.add(new C());

		// все дополнительне элементы
		java.util.List<GeneralElementStage1> accessoryElements = new ArrayList<>();
		accessoryElements.add(new Al());
		accessoryElements.add(new Si());
		accessoryElements.add(new Ti());


		JPanel jpRows = new JPanel();
		jpRows.setLayout(new BoxLayout(jpRows, BoxLayout.Y_AXIS));

		jpRows.add(new JLabel("Основные химические элементы:"));
		for (GeneralElementStage1 basicElement : basicElements) {
			addRowsToPanel(jpRows, basicElement);
		}

		jpRows.add(new JLabel("Дополнительные химические элементы:"));
		for (GeneralElementStage1 accessoryElement : accessoryElements) {
			addRowsToPanel(jpRows, accessoryElement);
		}


		jpElements.setLayout(new FlowLayout());
		jpElements.add(jpRows);

		jpDirection.setLayout(new FlowLayout());
		jpDirection.add(jbtnOk);
		jpDirection.add(jbtnCancel);


		// set settings for created panels
		jpElements.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "panel 1"
		));


		jpMain.add(jpElements, BorderLayout.CENTER);
		jpMain.add(jpDirection, BorderLayout.CENTER);

		// add main panel to main frame
		jpMain.setLayout(new BoxLayout(jpMain, BoxLayout.Y_AXIS));
		add(jpMain);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocation(200, 200);
		setVisible(true);
		pack();

		jbtnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JTextField jtf : jtfAlloyComWeights) {
					System.out.println(jtf.getName() + " : " + jtf.getText());
				}
			}
		});
	}

	private void addRowsToPanel(JPanel jpRows, GeneralElementStage1 el) {
		JLabel jlElementName = new JLabel(el.toString() + " : ");
		JTextField jtfElementVal = new JTextField();
		jtfElementVal.setColumns(4);
		jtfElementVal.setName(el.toString());
		jtfAlloyComWeights.add(jtfElementVal);
		JLabel jlPercent = new JLabel(" %");

		JPanel jpItems = new JPanel();
		jpItems.add(jlElementName);
		jpItems.add(jtfElementVal);
		jpItems.add(jlPercent);

		jpRows.add(jpItems);
	}

}
