package model;

import stage1.CommonCalcDataStage1;
import stage1.ExtraInputDataStage1;
import stage1.elements.*;
import stage2.CalcDataStage2;
import stage2.ExtraInputDataStage2;
import stage2.elements.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Give access to chemical components from any application place.
 */
public class Container {

	private static Container container;
	private  Stage1 stage1;
	private  Stage2 stage2;


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

	public void setStage1(Stage1 stage1) {
		this.stage1 = stage1;
	}

	public void setStage2(Stage2 stage2) {
		this.stage2 = stage2;
	}

	public class Stage1 {
		private Fe fe;
		private stage1.elements.C c; // todo - may be renamed to cSt2 in order avoid with C element in stage 1
		private Mn mn;
		private Al al;
		private Si si;
		private Ti ti;
		private ExtraInputDataStage1 extraInputDataStage1;
		private CommonCalcDataStage1 commonCalcDataStage1;

		public Stage1() {
			fe = new Fe();
			c = new stage1.elements.C();
			mn = new Mn();
			al = new Al();
			si = new Si();
			ti = new Ti();
			extraInputDataStage1 = new ExtraInputDataStage1();
			commonCalcDataStage1 = new CommonCalcDataStage1();
		}

		public Fe getFe() {
			return fe;
		}

		public stage1.elements.C getC() {
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

		public CommonCalcDataStage1 getCommonCalcDataStage1() {
			return commonCalcDataStage1;
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
		private Ar ar;
		private CO2 co2;
		private O2 o2;
		private CO co;
		private O o;
		private stage2.elements.C c;
		ExtraInputDataStage2 extraInputDataStage2;
		CalcDataStage2 calcDataStage2;

		public Stage2() {
			ar = new Ar();
			co2 = new CO2();
			o2 = new O2();
			co = new CO();
			o = new O();
			c = new stage2.elements.C();
			extraInputDataStage2 = new ExtraInputDataStage2();
			calcDataStage2 = new CalcDataStage2();
		}

		public Ar getAr() {
			return ar;
		}

		public CO2 getCo2() {
			return co2;
		}

		public O2 getO2() {
			return o2;
		}

		public CO getCo() {
			return co;
		}

		public O getO() {
			return o;
		}

		public stage2.elements.C getC() {
			return c;
		}

		public ExtraInputDataStage2 getExtraInputDataStage2() {
			return extraInputDataStage2;
		}

		public CalcDataStage2 getCalcDataStage2() {
			return calcDataStage2;
		}

		public List<GeneralElementStage2> getAllElements() {
			List<GeneralElementStage2> list = new ArrayList<>();
			list.add(ar);
			list.add(co2);
			list.add(o2);
			list.add(co);
			list.add(o);
			list.add(c);
			return list;
		}

	}

}
