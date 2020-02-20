package ebanking.keyword_driven;

public class Driver {

	public static void main(String[] args) {
		// retrieve data from the test cases sheet
		ExcelHelper tcdoc = new ExcelHelper();
		tcdoc.openExcel("resources", "testcases.xlsx", "testcases");

		// create an object for test stpes sheet also
		ExcelHelper tsdoc = new ExcelHelper();
		tsdoc.openExcel("resources", "testcases.xlsx", "teststeps");

		// retrieve the number of rows in tcdoc
		int tcrows = tcdoc.getRows();

		// retrieve number of rows in tsdoc
		int tsrows = tsdoc.getRows();

		// iterate over every row in test case doc
		for (int i = 1; i <= tcrows; i++) {
			String runMode = tcdoc.getCellData(i, 1);
			if (runMode.equals("yes")) {
				// execute test steps related that test case
				// retrieve testcase name from tcdoc
				String tcdTCName = tcdoc.getCellData(i, 0);
				// iterate every row in test step doc
				for (int j = 1; j <= tsrows; j++) {
					// retrieve test case from tsdoc
					String tsdTCName = tsdoc.getCellData(j, 0);

					// compare test case name in both docs
					if (tcdTCName.equals(tsdTCName)) {

						// retrieve each and every column data from test step doc
						String tsname = tsdoc.getCellData(j, 1);
						String locType = tsdoc.getCellData(j, 2);
						String locValue = tsdoc.getCellData(j, 3);
						String keyword = tsdoc.getCellData(j, 4);
						String testdata = tsdoc.getCellData(j, 5);

						System.out.println(keyword);

					}
				}
			}
		}

		// close excel docs
		tcdoc.closeExcel();
		tsdoc.closeExcel();
	}

}
