package pageObject.sauceLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUI.liveGuru.PageLoginUIs;
import pageUIs.sauceLab.ProductPageUIs;

public class ProductPageObject extends BasePage{
	private WebDriver driver;
	
	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInProductSortDropdown(String textItem) {
		waitForElementVisible(driver, ProductPageUIs.PRODUCT_CONTAINER_DROPDOWN);
		selectItemInDefaultDropdownBy_Text(driver, ProductPageUIs.PRODUCT_CONTAINER_DROPDOWN, textItem);
	}

	public boolean isProductNaeSortByAscending() {
		// Khai báo ra 1 ArrayList để chứa các product name trên UI
		ArrayList<String> productUIList= new ArrayList<String>();
		
		// Lấy ra tất cả các text product name
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUIs.PRODUCT_NAME_TEXT);
		
		// Dùng vòng lặp để getText và add vào ArrayList trên
		for (WebElement webElement : productNameText) {
			productUIList.add(webElement.getText());
			System.out.println("UI___: " + webElement.getText());
		}
		
		// Tạo ra 1 ArayList mới để sort dữ liệu trong ArrayList cũ xem đúng hay không
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}
		
		// Sort cái prductSortList
		Collections.sort(productSortList);
		
		// Reverse cái productSortList
//		Collections.reverse(productSortList);
		for (String string : productSortList) {
			System.out.println("Name___: " + string);
		}
		
		// So sánh 2 List đã bằng nhau
		return productSortList.equals(productUIList);
		
	}

	public boolean isProductNaeSortByDescending() {
		// Khai báo ra 1 ArrayList để chứa các product name trên UI
		ArrayList<String> productUIList= new ArrayList<>();
		
		// Lấy ra tất cả các text product name
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUIs.PRODUCT_NAME_TEXT);
		
		// Dùng vòng lặp để getText và add vào ArrayList trên
		for (WebElement webElement : productNameText) {
			productUIList.add(webElement.getText());
		}
		
		// Tạo ra 1 ArayList mới để sort dữ liệu trong ArrayList cũ xem đúng hay không
		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productUIList) {
			productSortList.add(product);
		}
		
		// Sort cái prductSortList
		Collections.sort(productSortList);
		
		// Reverse cái productSortList
		Collections.reverse(productSortList);
		
		// So sánh 2 List đã bằng nhau
		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortByAscending() {
		// Khai báo ra 1 ArrayList để chứa các product name trên UI
		ArrayList<Float> productUIList= new ArrayList<Float>();
		
		// Lấy ra tất cả các text product name
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUIs.PRODUCT_PRICE_TEXT);
		
		// Dùng vòng lặp để getText và add vào ArrayList trên
		for (WebElement webElement : productNameText) {
			productUIList.add(Float.parseFloat(webElement.getText().replace("$", "")));
		}
		
		// Tạo ra 1 ArayList mới để sort dữ liệu trong ArrayList cũ xem đúng hay không
		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}
		
		// Sort cái prductSortList
		Collections.sort(productSortList);
		
		// Reverse cái productSortList
//		Collections.reverse(productSortList);
		
		// So sánh 2 List đã bằng nhau
		return productSortList.equals(productUIList);
	}

	public boolean isProductPriceSortByDescending() {
		// Khai báo ra 1 ArrayList để chứa các product name trên UI
		ArrayList<Float> productUIList= new ArrayList<Float>();
		
		// Lấy ra tất cả các text product name
		List<WebElement> productNameText = getListWebElement(driver, ProductPageUIs.PRODUCT_PRICE_TEXT);
		
		// Dùng vòng lặp để getText và add vào ArrayList trên
		for (WebElement webElement : productNameText) {
			productUIList.add(Float.parseFloat(webElement.getText().replace("$", "")));
		}
		
		// Tạo ra 1 ArayList mới để sort dữ liệu trong ArrayList cũ xem đúng hay không
		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIList) {
			productSortList.add(product);
		}
		
		// Sort cái prductSortList
		Collections.sort(productSortList);
		
		// Reverse cái productSortList
		Collections.reverse(productSortList);
		
		// So sánh 2 List đã bằng nhau
		return productSortList.equals(productUIList);
	}
	
}
