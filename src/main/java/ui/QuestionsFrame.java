package ui;

import app.Stage;
import domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 */
public class QuestionsFrame extends JFrame {

	private JPanel jpMain = new JPanel();
	private JPanel jpTitle = new JPanel();
	private QuestionPanel qPanel;
	private JPanel jpStatusAndDirection = new JPanel();

	private JButton jBtnExit = new JButton("Выход");
	private JButton jBtnNext = new JButton("Далее >>>");

	private JLabel jlUserFirstName = new JLabel(); // Имя
	private JLabel jlUserMiddleName = new JLabel(); // Отчество
	private JLabel jlUserLastName = new JLabel(); // Фамилия

	private JLabel jlStatusMsg = new JLabel("");


	public QuestionsFrame(User user, Stage stage) {
		super("");

		jpTitle.add(new JLabel(stage.getName()));
		jpTitle.setLayout(new FlowLayout(FlowLayout.CENTER));

		jpMain.setLayout(new BorderLayout());
		jpMain.add(jpTitle, BorderLayout.PAGE_START);

		jpStatusAndDirection.setLayout(new BoxLayout(jpStatusAndDirection, BoxLayout.Y_AXIS));

		jBtnExit.setPreferredSize(new Dimension(100, 25));
		jBtnNext.setPreferredSize(new Dimension(500, 25));
		JPanel jpDirBtns = new JPanel(new FlowLayout());
		jpDirBtns.add(jBtnExit);
		jpDirBtns.add(jBtnNext);
		jpStatusAndDirection.add(jpDirBtns);

		JPanel jpStatAndDir = new JPanel(new FlowLayout(FlowLayout.CENTER));
		jpStatAndDir.add(jlStatusMsg);
		jpStatusAndDirection.add(jpStatAndDir);

		JPanel jpUserFIO = new JPanel(new FlowLayout());
		jpUserFIO.add(jlUserLastName); // Ф
		jpUserFIO.add(jlUserFirstName); // И
		jpUserFIO.add(jlUserMiddleName); // О
		jpStatusAndDirection.add(jpUserFIO);
		jlUserFirstName.setText(user.getFirstName());
		jlUserMiddleName.setText(user.getMiddleName());
		jlUserLastName.setText(user.getLastName());

		switch (stage) {
			case STAGE_1:
				qPanel = new QuestionsStage1(jpMain, this,  jlStatusMsg);
				break;

			case STAGE_2:
//				qPanel = new QuestionsStage2(jpMain, this,  jlStatusMsg);
		}

		jpMain.add(jpStatusAndDirection, BorderLayout.PAGE_END);

		add(jpMain);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		setSize(500, 300);
		setLocation(200, 200);
		setVisible(true);
		pack();

		jBtnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				qPanel.reply();
				repaintWindow();
			}
		});
	}


	public void repaintWindow() {
		revalidate();
		repaint();
		pack();
	}


	public interface QuestionPanel {
		void reply(); // давать ответ
	}

}
