package ebanking.keyword_driven;

import java.lang.reflect.Method;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Driver extends BaseClass{

	public static void main(String[] args) {
		// create an ExtentReports class object
		ExtentReports reports = new ExtentReports();
		
		// create an object of ExtentHtmlReporter class
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(".//target//report.html");
		
		// add reporter to the reports
		reports.attachReporter(reporter);
		
		// create an object of the Keywords class so that we call all the methods of that class
		Keywords keywords = new Keywords();
		// retrieve all the methods of the Keywords class
		Method[] methods = keywords.getClass().getMethods();
		
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
			
			// retrieve runMode which is in second column
			String runMode = tcdoc.getCellData(i, 1);
			
			// compare the runMode is yes or not
			if (runMode.equals("yes")) {
				
				
				// execute test steps related that test case
				// retrieve testcase name from tcdoc
				String tcdTCName = tcdoc.getCellData(i, 0);
				
				// create an ExtentTest object
				test = reports.createTest(tcdTCName);
				
				
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

						// iterate over every method in the Keywords class
						for(Method method : methods) {
							// compare method name with the keyword
							if(method.getName().equals(keyword)) {
								try {
									test.log(Status.INFO, tsname);
									method.invoke(keywords, locType, locValue, testdata);
									break; // breaks method loop
								} catch (Exception e) {
									System.out.println(e.getMessage());
								}
							}
						}

					}
				}
			}
		}

		// close excel docs
		tcdoc.closeExcel();
		tsdoc.closeExcel();
		
		// save the reports
		reports.flush();
	}

}
