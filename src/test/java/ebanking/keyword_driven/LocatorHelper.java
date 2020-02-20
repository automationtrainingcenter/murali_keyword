package ebanking.keyword_driven;

import org.openqa.selenium.By;

public class LocatorHelper {
	
	public static By locate(String locType, String locValue) {
		By loc = null;
		
		switch (locType.toLowerCase()) {
		case "id":
			loc = By.id(locValue);
			break;
		case "name":
			loc = By.name(locValue);
			break;

		case "xpath":
			loc = By.xpath(locValue);
			break;

		case "cssselector":
			loc = By.cssSelector(locValue);
			break;

		case "linktext":
			loc = By.linkText(locValue);
			break;
		case "partiallinktext":
			loc = By.partialLinkText(locValue);
			break;
		case "tagname":
			loc = By.tagName(locValue);
			break;
		case "classname":
			loc = By.className(locValue);
			break;
		default:	
			throw new RuntimeException("invalid loc type");
		}
		
		
		return loc;
	}

}
