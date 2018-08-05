package uk.co.rightmove.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import uk.co.rightmove.base.TestBase;

public class SearchProperty extends TestBase {

	@FindBy(xpath = "//*[@id=\'rm-site-logo\']")
	private WebElement siteLogo;

	@FindBy(xpath = "//*[@id=\'searchLocation\']")
	private WebElement searchLocation;

	@FindBy(xpath = "//*[@id=\'buy\']")
	private WebElement sale;

	@FindBy(xpath = "//*[@id=\'rent\']")
	private WebElement rent;

	@FindBy(xpath = "//*[@id=\'locationIdentifier\']")
	private WebElement changeLocation;

	@FindBy(xpath = "//*[@id=\'radius\']")
	private WebElement searchRadius;

	@FindBy(xpath = "//*[@id=\'minPrice\']")
	private WebElement minPrice;

	@FindBy(xpath = "//*[@id=\'maxPrice\']")
	private WebElement maxPrice;

	@FindBy(xpath = "//*[@id=\'minBedrooms\']")
	private WebElement minBedrooms;

	@FindBy(xpath = "//*[@id=\'maxBedrooms\']")
	private WebElement maxBedrooms;

	@FindBy(xpath = "//*[@id=\'submit\']")
	private WebElement findProperty;

	@FindBy(xpath = "//*[@id=\'sortType\']")
	private WebElement searchSort;

	@FindBy(xpath = "//*[@id=\'filtersBar\']/div/div[5]/div")
	private WebElement filterBar;

	@FindBy(xpath = "//*[@id=\'secondarycriteria\']/div[3]/label/span")
	private WebElement checkBoxTick;

	@FindBy(xpath = "(//div[not(contains(text(),'Featured Property')) and contains(@class,'propertyCard-moreInfoFeaturedTitle')])[1]/parent::*/parent::*")
	private WebElement firstProperty;

	public SearchProperty() {
		PageFactory.initElements(driver, this);
	}

	public WebElement getSiteLogoHandle() {
		return this.siteLogo;
	}

	public WebElement getFirstProperty() {
		return this.firstProperty;
	}

	public WebElement getSearchSort() {
		return this.searchSort;
	}

	public WebElement getFindProperty() {
		return this.findProperty;
	}

	public WebElement getCheckBoxTick() {
		return this.checkBoxTick;
	}

	public WebElement getMinPrice() {
		return this.minPrice;

	}

	public WebElement getMaxPrice() {
		return this.maxPrice;

	}

	public WebElement getMinBedrooms() {
		return this.minBedrooms;

	}

	public WebElement getMaxBedrooms() {
		return this.maxBedrooms;

	}

	public boolean validateLogoImage() {
		return siteLogo.isDisplayed();
	}

	public WebElement getSearchRadius() {
		return this.searchRadius;
	}

	public boolean validateSearchFieldDisplayed() {
		return searchLocation.isDisplayed();
	}

	public WebElement getRentButtonHandle() {
		return this.rent;
	}

	public WebElement getSaleButtonHandle() {
		return this.sale;
	}

	public WebElement getSearchLocationHandle() {
		return this.searchLocation;
	}

}
