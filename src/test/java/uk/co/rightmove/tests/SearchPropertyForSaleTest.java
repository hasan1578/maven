package uk.co.rightmove.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import uk.co.rightmove.base.TestBase;
import uk.co.rightmove.pages.SearchProperty;
import org.apache.log4j.Logger;

@Test
public class SearchPropertyForSaleTest extends TestBase {

	private SearchProperty SearchProperty;

	Logger log = Logger.getLogger(SearchPropertyForSaleTest.class);

	private static final String BASE_URL = "https://www.rightmove.co.uk/";
	private static final String SITE_TEXT = "Rightmove - UK's number one property website for properties for sale and to rent";
	private static final String SEARCH_LOCATION = "Yorkshire Dales";
	private static final String TITLE_STRING = "Find property for sale in";
	private static final String URL_STRING = "property-for-sale";
	private static final String RADIUS = "Within 30 miles";
	private static final String RADIUS_EXPECTATION = "30.0";
	private static final String MIN_PRICE = "60,000";
	private static final String MIN_PRICE_EXPECTATION = "60000";
	private static final String MAX_PRICE = "120,000";
	private static final String MAX_PRICE_EXPECTATION = "120000";
	private static final String MIN_BEDROOMS = "1";
	private static final String MAX_BEDROOMS = "5";
	private static final String SEARCH_ORDER = "Newest Listed";
	private static final String SEARCH_ORDER_EXPECTATION = "6";

	public SearchPropertyForSaleTest() {
		super();
	}

	@BeforeClass
	@Parameters({ "url", "browser" })
	public void setUp(String url, String browser) {
		log.info("\n\n****************** Lauching Browser (Setup)  ******************\n");
		log.info("\n\n****************** Executing test cases for SearchPropertyForProperty4Sale ******************\n");
		initialization(url, browser);
		SearchProperty = new SearchProperty();
		log.info("\n\n****************** Browser is lauched ***********************\n\n\n");

	}

	@Test(priority = 0)
	public void verifyCorrectBaseUrl() {
		log.info("\n\n****************** verifyCorrectBaseUrl ******************\n");
		Assert.assertEquals(getCurrentURL(), BASE_URL, "Wrong base URL");
		Assert.assertTrue(validatePageTitle().contains(SITE_TEXT), "Wrong page");
		log.info("\n\n****************** Test step ended ***********************\n\n\n");
	}

	@Test(priority = 1)
	public void verifySearchPropertyforSaleByLocation() {
		log.info("\n\n****************** verifySearchPropertyforSaleByLocation ******************\n");
		Assert.assertTrue(SearchProperty.getSearchLocationHandle().isDisplayed());
		SearchProperty.getSearchLocationHandle().clear();
		SearchProperty.getSearchLocationHandle().sendKeys(SEARCH_LOCATION);
		Assert.assertTrue(SearchProperty.getSearchLocationHandle().getAttribute("value").toString().contentEquals(SEARCH_LOCATION),"Wrong location area");
		
		Assert.assertTrue(SearchProperty.getSaleButtonHandle().isDisplayed());
		Assert.assertTrue(SearchProperty.getSaleButtonHandle().isEnabled());
		SearchProperty.getSaleButtonHandle().click();
		log.info("\n\n****************** Test step ended ***********************\n\n\n");
	}

	@Test(priority = 2)
	public void verifyUserIsOnSalePropertyPage() {
		log.info("\n\n****************** verifyUserIsOnSalePropertyPage ******************\n");
		Assert.assertTrue(validatePageTitle().contains(TITLE_STRING));
		Assert.assertTrue(getCurrentURL().contains(URL_STRING), "URL is not for selling property");
		log.info("\n\n****************** Test step ended ***********************\n\n\n");
	}

	@Test(priority = 3)
	public void verifyUserSearchFiltersUsed() {
		log.info("\n\n****************** verifyUserSearchFiltersUsed ******************\n");
		Assert.assertTrue(SearchProperty.getSearchRadius().isDisplayed(), "Radius dropdown is not displayed");
		Assert.assertTrue(SearchProperty.getSearchRadius().isEnabled(), "Radius dropdown is not enabled");
		SearchProperty.getSearchRadius().sendKeys(RADIUS);
		Assert.assertTrue(SearchProperty.getSearchRadius().getAttribute("value").toString().contentEquals(RADIUS_EXPECTATION),"Wrong radius"); //radius expected value is different from radius string

		Assert.assertTrue(SearchProperty.getMinPrice().isDisplayed(), "Minimum price dropdown is not displayed ");
		Assert.assertTrue(SearchProperty.getMinPrice().isEnabled(), "Minimum price dropdown is not enabled");
		SearchProperty.getMinPrice().sendKeys(MIN_PRICE);
		Assert.assertTrue(SearchProperty.getMinPrice().getAttribute("value").toString().contentEquals(MIN_PRICE_EXPECTATION),"Wrong minimum price"); 

		Assert.assertTrue(SearchProperty.getMaxPrice().isDisplayed(), "Maximum price dropdown is not displayed ");
		Assert.assertTrue(SearchProperty.getMaxPrice().isEnabled(), "Maximum price dropdown is not enabled");
		SearchProperty.getMaxPrice().sendKeys(MAX_PRICE);
		Assert.assertTrue(SearchProperty.getMaxPrice().getAttribute("value").toString().contentEquals(MAX_PRICE_EXPECTATION),"Wrong maximum price"); 

		Assert.assertTrue(SearchProperty.getMinBedrooms().isDisplayed(), "Minimum bedrooms dropdown is not displayed");
		Assert.assertTrue(SearchProperty.getMinBedrooms().isEnabled(), "Minimum bedrooms dropdown is not enabled");
		SearchProperty.getMinBedrooms().sendKeys(MIN_BEDROOMS);
		Assert.assertTrue(SearchProperty.getMinBedrooms().getAttribute("value").toString().contentEquals(MIN_BEDROOMS),"Wrong Minimum bedrooms"); 

		Assert.assertTrue(SearchProperty.getMaxBedrooms().isDisplayed(), "Maximum bedrooms dropdown is not displayed");
		Assert.assertTrue(SearchProperty.getMaxBedrooms().isEnabled(), "Maximum bedroom dropdown is not enabled");
		SearchProperty.getMaxBedrooms().sendKeys(MAX_BEDROOMS);
		Assert.assertTrue(SearchProperty.getMaxBedrooms().getAttribute("value").toString().contentEquals(MAX_BEDROOMS),"Wrong maximum bedrooms"); 

		Assert.assertTrue(SearchProperty.getCheckBoxTick().isDisplayed(),
				"Checkbox for 'include Under Offer, Sold STC' is not displayed");
		Assert.assertTrue(SearchProperty.getCheckBoxTick().isEnabled(),
				"Checkbox for 'include Under Offer, Sold STC' is not enabled");
		SearchProperty.getCheckBoxTick().click();

		Assert.assertTrue(SearchProperty.getFindProperty().isDisplayed(), "Find prorperty button is not displayed");
		Assert.assertTrue(SearchProperty.getFindProperty().isEnabled(), "Find prorperty button is not displayed");
		SearchProperty.getFindProperty().submit();
		log.info("\n\n****************** Test step ended ***********************\n\n\n");

	}

	@Test(priority = 4)
	public void verifyUserSelectNewestProperty() {
		log.info("\n\n****************** verifyUserSelectNewestProperty ******************\n");
		Assert.assertTrue(SearchProperty.getSearchSort().isDisplayed());
		Assert.assertTrue(SearchProperty.getSearchSort().isEnabled());
		SearchProperty.getSearchSort().sendKeys(SEARCH_ORDER);
		Assert.assertTrue(SearchProperty.getSearchSort().getAttribute("value").toString().contentEquals(SEARCH_ORDER_EXPECTATION),"Wrong search order"); 

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("\n\n****************** Test step ended ***********************\n\n\n");
	}

	@Test(priority = 5)
	public void verifyUserIsNavigatedToFirstNonFeatureProperty() {
		log.info("\n\n****************** verifyUserIsNavigatedToFirstNonFeatureProperty ******************\n");
		Assert.assertTrue(SearchProperty.getFirstProperty().isDisplayed());
		Assert.assertTrue(SearchProperty.getFirstProperty().isEnabled());
		SearchProperty.getFirstProperty().click();
		log.info("\n\n****************** Test step ended ***********************\n\n\n");
	}

	@AfterClass
	public void tearDown() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
		log.info("\n\n****************** Shutdown Browser (tearDown-SearchPropertyForSaleTest) ******************\n");
	}
}
