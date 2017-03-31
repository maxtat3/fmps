package ui;

import controller.StudentCardFrameController;
import stage1.elements.BaseElementStage1;
import stage1.elements.GeneralElementStage1;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 */
public class StudentCardFrame {

	public static final String TXT_STUDENT_CARD = "Карточка студента";
	public static final String TXT_MAIN_ELEMENTS = "Основные элементы:";
	public static final String TXT_ACCESSORY_ELEMENTS = "Дополнительные элементы:";

	private JFrame studentCardFrame = new JFrame(TXT_STUDENT_CARD);
	private StudentCardFrameController controller;

	private JPanel jpMain = new JPanel();
	private JPanel jpElements = new JPanel();
	private JPanel jpDirection = new JPanel();
	private JButton jbtnOk = new JButton("Ok");
	private JButton jbtnCancel = new JButton("Cancel");

	private java.util.List<JTextField> jtfAlloyComWeights = new ArrayList<>();


	public StudentCardFrame() {
		studentCardFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		addComponentToPane(studentCardFrame.getContentPane());
		studentCardFrame.setPreferredSize(new Dimension(500, 500));
		studentCardFrame.pack();
		studentCardFrame.setLocationRelativeTo(null);
		studentCardFrame.setVisible(true);

		controller = new StudentCardFrameController(studentCardFrame, this);
	}

	private void addComponentToPane(Container contentPane) {
		java.util.List<GeneralElementStage1> basicElements = new ArrayList<>(); // все основные элементы
		java.util.List<GeneralElementStage1> accessoryElements = new ArrayList<>(); // все дополнительне элементы
		for(GeneralElementStage1 el : model.Container.getInstance().getStage1().getAllElements()) {
			if (((BaseElementStage1) el).isBasic()) {
				basicElements.add(el);
			} else {
				accessoryElements.add(el);
			}
		}

		JPanel jpRows = new JPanel();
		jpRows.setLayout(new BoxLayout(jpRows, BoxLayout.Y_AXIS));

		jpRows.add(new JLabel(TXT_MAIN_ELEMENTS));
		for (GeneralElementStage1 basicElement : basicElements) {
			addRowsToPanel(jpRows, basicElement);
		}

		jpRows.add(new JLabel(TXT_ACCESSORY_ELEMENTS));
		for (GeneralElementStage1 accessoryElement : accessoryElements) {
			addRowsToPanel(jpRows, accessoryElement);
		}


		jpElements.setLayout(new FlowLayout());
		jpElements.add(jpRows);

		jpDirection.setLayout(new FlowLayout());
		jpDirection.add(jbtnOk);
		jpDirection.add(jbtnCancel);

		jbtnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JTextField jtf : jtfAlloyComWeights) {
					System.out.println(jtf.getName() + " : " + jtf.getText());
				}
			}
		});

		// set settings for created panels
		jpElements.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Химический состав элементов"
		));


		jpMain.add(jpElements, BorderLayout.CENTER);
		jpMain.add(jpDirection, BorderLayout.CENTER);

		// add main panel to main frame
		jpMain.setLayout(new BoxLayout(jpMain, BoxLayout.Y_AXIS));
		contentPane.add(jpMain);
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
