package app;

/**
 *
 */
public class ShowChartSelector {

	public enum Stage1 {
		CHART_1("stage 1 - chart name 1", 1),
		CHART_2("stage 1 - chart name 2", 2);

		private String name;
		private int number;

		Stage1(String name, int number) {
			this.name = name;
			this.number = number;
		}

		public String getName() {
			return name;
		}

		public int getNumber() {
			return number;
		}
	}

}
