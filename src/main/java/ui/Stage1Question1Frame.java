package ui;

import stage1.elements.C;
import stage1.elements.Fe;
import stage1.elements.Mn;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Stage1Question1Frame extends JFrame {

	private JPanel jpMain = new JPanel();
	private JPanel jpQuestion1 = new JPanel();
	private JPanel jpQuestion2 = new JPanel();
	private JPanel jpQuestion3 = new JPanel();
	private JPanel jpQuestion4 = new JPanel();
	private JPanel jpQuestion5 = new JPanel();
	private JPanel jpQuestion6 = new JPanel();
	private JPanel jpQuestion7 = new JPanel();
	private JPanel jpDirection = new JPanel();
	private JPanel jpStatusBar = new JPanel();

	private JLabel jlQuestion1 = new JLabel("2 + 3 = ");
	private JTextField jtfAnswer1 = new JTextField("5");
	private JLabel jlQuestion2 = new JLabel("5 - 1 = ");
	private JTextField jtfAnswer2 = new JTextField("    ");
	private JLabel jlQuestion3 = new JLabel("11 - 7 = ");
	private JTextField jtfAnswer3 = new JTextField("    ");
	private JLabel jlQuestion4 = new JLabel("15 - 10 = ");
	private JTextField jtfAnswer4 = new JTextField("    ");
	private JLabel jlQuestion5 = new JLabel("3 * 8 = ");
	private JTextField jtfAnswer5 = new JTextField("    ");

	private JButton jbtnOk = new JButton("Ok");
	private JButton jbtnCancel = new JButton("Cancel");

	private JLabel jlStatusMsg = new JLabel("info");

	private PanelsTag panelsTag;


	private HashMap<String, JComponent[]> viewRows = new HashMap<>();


	public Stage1Question1Frame() {
		super("Stage 1, question 1");

		final HashMap<String, Object> userTaskElementsFromDB = getUserTaskElementsFromDB();
//		System.out.println(userTaskElementsFromDB.size());

//		buildElementRows()
//		JLabel jlDelim = new JLabel(" : ");
//		JLabel jlEnd = new JLabel(" % ");

		for (Object o : userTaskElementsFromDB.entrySet()) {
			System.out.println(o.toString());
			JComponent[] oneRowComponents = new JComponent[4];
			oneRowComponents[0] = new JLabel(o.toString());
			oneRowComponents[1] = new JLabel(":");
			oneRowComponents[2] = new JTextField("****");
			oneRowComponents[3] = new JLabel("% ");

			System.out.println(o.toString());
			viewRows.put(o.toString(), oneRowComponents);
		}

		// ======== Panel 1 =========
		jpQuestion1.setLayout(new FlowLayout());
//		jpQuestion1.add(jlQuestion1);
//		jpQuestion1.add(jtfAnswer1);
		for (Map.Entry<String, JComponent[]> roe : viewRows.entrySet()) {
			for(int i = 0; i < roe.getValue().length; i++) {
				jpQuestion1.add(roe.getValue()[0]);
				jpQuestion1.add(roe.getValue()[1]);
				jpQuestion1.add(roe.getValue()[2]);
				jpQuestion1.add(roe.getValue()[3]);
			}
		}

		// ========= Panel 2 =========
		jpQuestion2.setLayout(new FlowLayout());
		jpQuestion2.add(jlQuestion2);
		jpQuestion2.add(jtfAnswer2);

		// ========== Panel 3 ===============
		jpQuestion3.setLayout(new FlowLayout());
		jpQuestion3.add(jlQuestion3);
		jpQuestion3.add(jtfAnswer3);

		// ========= panel 4 ============
		jpQuestion4.setLayout(new FlowLayout());
		jpQuestion4.add(jlQuestion4);
		jpQuestion4.add(jtfAnswer4);

		// ========= panel 5 ============
		jpQuestion5.setLayout(new FlowLayout());
		jpQuestion5.add(jlQuestion5);
		jpQuestion5.add(jtfAnswer5);

		jpDirection.setLayout(new FlowLayout());
		jpDirection.add(jbtnOk);
		jpDirection.add(jbtnCancel);

		jpStatusBar.setLayout(new FlowLayout());
		jpStatusBar.add(jlStatusMsg);

		// other panels
		// Settings main frame this app
		jpMain.setLayout(new BoxLayout(jpMain, BoxLayout.Y_AXIS));


		// set settings for created panels
		jpQuestion1.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "panel 1"
		));
		jpQuestion2.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "panel 2"
		));
		jpQuestion3.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "panel 3"
		));
		jpQuestion4.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "panel 4"
		));
		jpQuestion5.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "panel 5"
		));

		panelsTag = PanelsTag.PANEL_1;
		showNextPanel();
		showOtherViewElements();

		// add main panel to main frame
		add(jpMain);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		setSize(500, 300);
		setLocation(200, 200);
		setVisible(true);
		pack();

//		addComponentsListeners();

		jbtnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				checkAnswer();
				System.out.println(((JTextField) viewRows.get("FE=FE")[2]).getText());
				System.out.println(((JTextField) viewRows.get("CU=CU")[2]).getText());
				System.out.println(((JTextField) viewRows.get("C=C")[2]).getText());
				System.out.println(((JTextField) viewRows.get("MN=MN")[2]).getText());
				System.out.println(((JTextField) viewRows.get("MG=MG")[2]).getText());
			}
		});
	}

	private void checkAnswer() {
		System.out.println(jtfAnswer2.getText());
		if (jtfAnswer1.getText().equals("5") && panelsTag == PanelsTag.PANEL_1) {
			panelsTag = PanelsTag.PANEL_2;

		} else if (jtfAnswer2.getText().equals("4") && panelsTag == PanelsTag.PANEL_2) {
			panelsTag = PanelsTag.PANEL_3;
			System.out.println("correct answer 2");

		} else if (jtfAnswer3.getText().equals("8") && panelsTag == PanelsTag.PANEL_3){
			panelsTag = PanelsTag.PANEL_4;
		}
		else {
			JOptionPane.showMessageDialog(rootPane, "Не правильный ответ", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		showNextPanel();
	}

	private void showNextPanel() {
		System.out.println("show");
		switch (panelsTag) {
			case PANEL_1:
				jpMain.add(jpQuestion1, BorderLayout.CENTER);
				break;
			case PANEL_2:
				jpMain.remove(jpQuestion1);
				jpMain.add(jpQuestion2, BorderLayout.CENTER);
				break;
			case PANEL_3:
				System.out.println("show panel 3");
				jpMain.remove(jpQuestion2);
				jpMain.add(jpQuestion3, BorderLayout.CENTER);
				break;
		}
		showOtherViewElements();
		revalidate();
		repaint();
		pack();
	}

	private enum PanelsTag {
		PANEL_1,
		PANEL_2,
		PANEL_3,
		PANEL_4,
		PANEL_5;


	}
	private void showOtherViewElements() {
		jpMain.add(jpDirection);
		jpMain.add(jpStatusBar);
	}
	// NOTE ! This method must be moved to controller !
	private HashMap<String, Object> getUserTaskElementsFromDB() {
		Fe fe = new Fe();
		Mn mn = new Mn();
		C c = new C();

		HashMap<String, Object> userElements = new HashMap<>();
		userElements.put(fe.toString(), fe);
		userElements.put(mn.toString(), mn);
		userElements.put(c.toString(), c);

		return userElements;
	}

}