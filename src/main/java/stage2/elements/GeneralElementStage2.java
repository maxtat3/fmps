package stage2.elements;

import com.google.common.collect.ImmutableTable;
import model.Container;

/**
 *
 */
public interface GeneralElementStage2 {

	String AR = Container.getInstance().getStage2().getAr().toString();
	String CO2 = Container.getInstance().getStage2().getCo2().toString();
	String O2 = Container.getInstance().getStage2().getO2().toString();
	String CO = Container.getInstance().getStage2().getCo().toString();
	String O = Container.getInstance().getStage2().getO().toString();
	String C = Container.getInstance().getStage2().getC().toString();

	String DT_H_298 = "delta_H_298";

	String DT_S_298 = "delta_S_298";

	String DT_Cp_298 = "delta_cp_298";

	/**
	 * Таблица констант элементов.
	 */
	ImmutableTable<String, String, Double> CONST_ELEMS =
		new ImmutableTable.Builder<String, String, Double>()
			.put(AR, DT_H_298, -393.5)
			.put(CO2, DT_H_298, 0d)
			.put(O2, DT_H_298, -110.5)
			.put(CO, DT_H_298, 247.4)
			.put(O, DT_H_298, 0d)
			.put(C, DT_H_298, 0d) // wrong value - where is find ?

			.put(AR, DT_S_298, 213.6)
			.put(CO2, DT_S_298, 205.0)
			.put(O2, DT_S_298, 197.4)
			.put(CO, DT_S_298, 161.0)
			.put(O, DT_S_298, 5.74)
			.put(C, DT_S_298, 0d) // wrong value - where is find ?

			.put(AR, DT_Cp_298, 37.1)
			.put(CO2, DT_Cp_298, 29.36)
			.put(O2, DT_Cp_298, 29.2)
			.put(CO, DT_Cp_298, 21.9)
			.put(O, DT_Cp_298, 8.3)
			.put(C, DT_Cp_298, 0d) // wrong value - where is find ?

			.build(); // write to table

}
