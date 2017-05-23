package ui;

import domain.User;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Stage1QuestionFrame extends JFrame {

	private JPanel jpMain = new JPanel();
	private JPanel jpTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel jpQuestion1 = new JPanel();
	private JPanel jpQuestion2 = new JPanel();
	private JPanel jpQuestion3 = new JPanel();
	private JPanel jpQuestion4 = new JPanel();
	private JPanel jpQuestion5 = new JPanel();
	private JPanel jpQuestion6 = new JPanel();
	private JPanel jpQuestion7 = new JPanel();
	private JPanel jpQuestion8 = new JPanel();
	private JPanel jpQuestion9 = new JPanel();
	private JPanel jpStatusAndDirection = new JPanel();

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

	private JButton jBtnExit = new JButton("Выход");
	private JButton jBtnNext = new JButton("Далее >>>");

	private JLabel jlUserFirstName = new JLabel(); // Имя
	private JLabel jlUserMiddleName = new JLabel(); // Отчество
	private JLabel jlUserLastName = new JLabel(); // Фамилия

	private JLabel jlStatusMsg = new JLabel("Правильных ответов: ");

	private PanelsTag panelsTag;

	private List<JTextField> jtfAlloyComWeights = new ArrayList<>();


	public Stage1QuestionFrame(User user) {
		super("Stage 1, question n");

		// Settings main frame this app
		jpMain.setLayout(new BorderLayout());

		jpTitle.add(new JLabel("Задача 1. Расчет процессов испарения металлов при сварку плавлением."));
		jpMain.add(jpTitle, BorderLayout.PAGE_START);

//		// ======== Panel 1 =========
		jpQuestion1.setLayout(new FlowLayout());
		jpQuestion1.add(jlQuestion1);
		jpQuestion1.add(jtfAnswer1);

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

		// Settings for status and directions panel
		jpStatusAndDirection.setLayout(new BoxLayout(jpStatusAndDirection, BoxLayout.Y_AXIS));
		// add direction buttons
		JPanel jpDirBtns = new JPanel(new FlowLayout());
		jBtnExit.setPreferredSize(new Dimension(100, 25));
		jBtnNext.setPreferredSize(new Dimension(500, 25));
		jpDirBtns.add(jBtnExit);
		jpDirBtns.add(jBtnNext);
		jpStatusAndDirection.add(jpDirBtns);
		// add
		JPanel jpStatAndDir = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpStatAndDir.add(jlStatusMsg);
		jpStatusAndDirection.add(jpStatAndDir);
		// add FIO labels
		JPanel jpUserFIO = new JPanel(new FlowLayout());
		jpUserFIO.add(jlUserLastName); // Ф
		jpUserFIO.add(jlUserFirstName); // И
		jpUserFIO.add(jlUserMiddleName); // О
		jpStatusAndDirection.add(jpUserFIO);
		jlUserFirstName.setText(user.getFirstName());
		jlUserMiddleName.setText(user.getMiddleName());
		jlUserLastName.setText(user.getLastName());

		jlStatusMsg.setText(jlStatusMsg.getText() + "1/8");


		// other panels
		// set settings for created panels
		jpQuestion1.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 1"
		));
		jpQuestion2.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 2"
		));
		jpQuestion3.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 3"
		));
		jpQuestion4.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 4"
		));
		jpQuestion5.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Вопрос 5"
		));

		jpQuestion1.add(new JLabel("Мольная доля всех элементов сплава (%): "));
		jpQuestion2.add(new JLabel("Энтальпия жидкого сплава, энтальпия испарения, энтальпия пара (кДж/моль): ")); //2.1, 2.2, 2.3
		jpQuestion3.add(new JLabel("Давление пара чистых компонентов (Па): "));
		jpQuestion4.add(new JLabel("Парциальное давление компонент над сплавом (Па): "));
		jpQuestion5.add(new JLabel("Давление пара над сплавом (Па): "));
		jpQuestion6.add(new JLabel("Мольная доля каждого компонента в паре (%): "));
		jpQuestion7.add(new JLabel("Весовая доля каждого компонента в паре (%): "));
		jpQuestion8.add(new JLabel("Скорость испарения из сварочной ванны каждого элемента (гр/сек): "));
		jpQuestion9.add(new JLabel("Скорость уменьшения массы расплавленного металла за счет испарения (гр/сек): "));

		panelsTag = PanelsTag.PANEL_1;
		showNextPanel();
		showOtherViewElements();

		add(jpMain);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		setSize(500, 300);
		setLocation(200, 200);
		setVisible(true);
		pack();

//		addComponentsListeners();

		jBtnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (JTextField jtf : jtfAlloyComWeights) {
					System.out.println(jtf.getName() + " : " + jtf.getText());
				}
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
		jpMain.add(jpStatusAndDirection, BorderLayout.PAGE_END);
	}

}
