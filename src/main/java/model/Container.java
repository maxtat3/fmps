package model;

import stage1.ExtraCalcDataStage1;
import stage1.elements.*;
import stage2.elements.CO2;
import stage2.elements.O2;
import stage1.ExtraInputDataStage1;

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
		private Al al;
		private Si si;
		private Ti ti;
		private ExtraInputDataStage1 extraInputDataStage1;
		private ExtraCalcDataStage1 extraCalcDataStage1;

		public Stage1() {
			fe = new Fe();
			c = new C();
			mn = new Mn();
			al = new Al();
			si = new Si();
			ti = new Ti();
			extraInputDataStage1 = new ExtraInputDataStage1();
			extraCalcDataStage1 = new ExtraCalcDataStage1();
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

		public Al getAl() {
			return al;
		}

		public Si getSi() {
			return si;
		}

		public Ti getTi() {
			return ti;
		}

		public ExtraInputDataStage1 getExtraInputDataStage1() {
			return extraInputDataStage1;
		}

		public ExtraCalcDataStage1 getExtraCalcDataStage1() {
			return extraCalcDataStage1;
		}

		public List<GeneralElementStage1> getAllElements() {
			ArrayList<GeneralElementStage1> list = new ArrayList<>();
			list.add(fe);
			list.add(c);
			list.add(mn);
			list.add(al);
			list.add(si);
			list.add(ti);
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
