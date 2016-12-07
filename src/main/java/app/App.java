package app;


public class App {
	public static void main(String[] args) {

		Stage1 stage1 = new Stage1();

		double checkAllElemSum = stage1.checkAllElementsSum();
		System.out.println("checkAllElemSum = " + checkAllElemSum);

		double[] moles = stage1.moleFractionAllElements();
		for (double mole : moles) {
			System.out.println("moles = " + mole * 100);
		}
	}
}
