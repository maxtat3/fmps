package model;

import stage1.CommonCalculatedDataStage1;
import stage1.elements.*;
import stage1.elements.C;
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
		private Al al;
		private B b;
		private stage1.elements.C c; // todo - may be renamed to cSt2 in order avoid with C element in stage 1
		private Cr cr;
		private Cu cu;
		private Fe fe;
		private Hf hf;
		private Mg mg;
		private Mn mn;
		private Mo mo;
		private Nb nb;
		private Ni ni;
		private Re re;
		private Si si;
		private Ta ta;
		private Ti ti;
		private V v;
		private W w;
		private Zr zr;
		private stage1.ExtraInputData extraInputData;
		private CommonCalculatedDataStage1 commonCalculatedData;

		public Stage1() {
			al = new Al();
			b = new B();
			c = new stage1.elements.C();
			cr = new Cr();
			cu = new Cu();
			fe = new Fe();
			hf = new Hf();
			mg = new Mg();
			mn = new Mn();
			mo = new Mo();
			nb = new Nb();
			ni = new Ni();
			re = new Re();
			si = new Si();
			ta = new Ta();
			ti = new Ti();
			v = new V();
			w = new W();
			zr = new Zr();
			extraInputData = new stage1.ExtraInputData();
			commonCalculatedData = new CommonCalculatedDataStage1();
		}

		public Al getAl() {
			return al;
		}

		public void setAl(Al al) {
			this.al = al;
		}

		public B getB() {
			return b;
		}

		public void setB(B b) {
			this.b = b;
		}

		public C getC() {
			return c;
		}

		public void setC(C c) {
			this.c = c;
		}

		public Cr getCr() {
			return cr;
		}

		public void setCr(Cr cr) {
			this.cr = cr;
		}

		public Cu getCu() {
			return cu;
		}

		public void setCu(Cu cu) {
			this.cu = cu;
		}

		public Fe getFe() {
			return fe;
		}

		public void setFe(Fe fe) {
			this.fe = fe;
		}

		public Hf getHf() {
			return hf;
		}

		public void setHf(Hf hf) {
			this.hf = hf;
		}

		public Mg getMg() {
			return mg;
		}

		public void setMg(Mg mg) {
			this.mg = mg;
		}

		public Mn getMn() {
			return mn;
		}

		public void setMn(Mn mn) {
			this.mn = mn;
		}

		public Mo getMo() {
			return mo;
		}

		public void setMo(Mo mo) {
			this.mo = mo;
		}

		public Nb getNb() {
			return nb;
		}

		public void setNb(Nb nb) {
			this.nb = nb;
		}

		public Ni getNi() {
			return ni;
		}

		public void setNi(Ni ni) {
			this.ni = ni;
		}

		public Re getRe() {
			return re;
		}

		public void setRe(Re re) {
			this.re = re;
		}

		public Si getSi() {
			return si;
		}

		public void setSi(Si si) {
			this.si = si;
		}

		public Ta getTa() {
			return ta;
		}

		public void setTa(Ta ta) {
			this.ta = ta;
		}

		public Ti getTi() {
			return ti;
		}

		public void setTi(Ti ti) {
			this.ti = ti;
		}

		public V getV() {
			return v;
		}

		public void setV(V v) {
			this.v = v;
		}

		public W getW() {
			return w;
		}

		public void setW(W w) {
			this.w = w;
		}

		public Zr getZr() {
			return zr;
		}

		public void setZr(Zr zr) {
			this.zr = zr;
		}

		public stage1.ExtraInputData getExtraInputData() {
			return extraInputData;
		}

		public void setExtraInputData(stage1.ExtraInputData extraInputData) {
			this.extraInputData = extraInputData;
		}

		public CommonCalculatedDataStage1 getCommonCalculatedData() {
			return commonCalculatedData;
		}

		public void setCommonCalculatedData(CommonCalculatedDataStage1 commonCalculatedData) {
			this.commonCalculatedData = commonCalculatedData;
		}

		public List<GeneralElementStage1> getAllElements() {
			ArrayList<GeneralElementStage1> list = new ArrayList<>();
			list.add(al);
			list.add(b);
			list.add(c);
			list.add(cr);
			list.add(cu);
			list.add(fe);
			list.add(hf);
			list.add(mg);
			list.add(mn);
			list.add(mo);
			list.add(nb);
			list.add(ni);
			list.add(re);
			list.add(si);
			list.add(ta);
			list.add(ti);
			list.add(v);
			list.add(w);
			list.add(zr);
			return list;
		}

		// TODO: 07.06.17 may be add method to return user task elements only (alloy... != 0)
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
