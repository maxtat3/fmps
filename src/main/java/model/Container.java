package model;

import elements.stage1.C;
import elements.stage1.Fe;
import elements.stage1.GeneralElementStage1;
import elements.stage1.Mn;
import elements.stage2.CO2;
import elements.stage2.O2;

import java.util.ArrayList;
import java.util.List;


/**
 * Give access to chemical components from any application place.
 */
public class Container {

	private static Container container;
	private static Stage1 stage1;
	private static Stage2 stage2;


	public static Container getInstance() {
		if (container == null) {
			container = new Container();
		}
		return container;
	}


	public Stage1 getStage1() {
		if (stage1 == null) {
			stage1 = new Stage1();
		}
		return stage1;
	}

	public Stage2 getStage2() {
		if (stage2 == null) {
			stage2 = new Stage2();
		}
		return stage2;
	}


	public class Stage1 {
		private Fe fe;
		private C c;
		private Mn mn;
		private InputDataStage1 inputDataStage1;

		public Stage1() {
			fe = new Fe();
			c = new C();
			mn = new Mn();
			inputDataStage1 = new InputDataStage1();
			System.out.println("created stage 1 object");
		}

		public Fe getFe() {
			return fe;
		}

		public C getC() {
			return c;
		}

		public Mn getMn() {
			return mn;
		}

		public InputDataStage1 getInputDataStage1() {
			return inputDataStage1;
		}

		public List<GeneralElementStage1> getAllElements() {
			ArrayList<GeneralElementStage1> list = new ArrayList<>();
			list.add(fe);
			list.add(c);
			list.add(mn);
			return list;
		}
	}

	public class Stage2 {
		private O2 o2;
		private CO2 co2;

		public Stage2() {
			o2 = new O2();
			co2 = new CO2();
			System.out.println("created stage 2 object");
		}

		public O2 getO2() {
			return o2;
		}

		public CO2 getCo2() {
			return co2;
		}
	}

}
