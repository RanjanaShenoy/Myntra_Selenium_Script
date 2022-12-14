//Ranjana   7204012347    R@njana72040
//Abhi      7385919107    Pass@123
//Rutuja    7768098324    Rutuja@123
//sakshi    8983838316    @Admin123



package combinedcode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import pages.AddToCart;
import pages.Dashboard;
import pages.Filters;
import pages.HelpCenter;
import pages.LoginPage;
import pages.Logout;
import pages.MyOrders;
import pages.MyProfile;
import pages.PlaceOrder;
import pages.SearchBar;
import pages.Wishlist;
import utils.TestReadExcel;
import utils.WebDriverPage;

public class IntegratedCode {
	
	WebDriver driver;
	String url="https://www.myntra.com/";
	
	WebDriverPage web;
	boolean value1;	
	String value2;
	public static String title,count,title1,count1,title2,count2,title3,count3;
	String header,text1,text2,text3,text4;
	boolean chat,callNow,writeToUs;
	int ButtonCount;
	String logindata[][];
	TestReadExcel test;
	Logger log = LogManager.getLogger(IntegratedCode.class.getName());
	
	MyOrders order;
	AddToCart a;
	Filters f;
	HelpCenter help;
	LoginPage login;
	Logout logOut;
	MyProfile myprofile;
	PlaceOrder place;
	SearchBar sbp;
	Wishlist w;
	Dashboard dashboard;
	
	
	@BeforeTest
	  public void test() {
		
			web=new WebDriverPage(driver);
			driver=web.setBrowser("firefox");

			driver.manage().window().maximize();
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
			
			log.info("-----Test Started-----");
			test=new TestReadExcel();
	        try {
				logindata= test.testWeb();
			} catch (EncryptedDocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			order=new MyOrders(driver);
			a=new AddToCart(driver);
			f=new Filters(driver);
			help=new HelpCenter(driver);
			login=new LoginPage(driver);
			logOut=new Logout(driver);
			myprofile=new MyProfile(driver);
			place=new PlaceOrder(driver);
			sbp=new SearchBar(driver);
			w=new Wishlist(driver);
			dashboard=new Dashboard(driver);
			driver.get(url);
	  }

	@Test(priority=1)
	public void login()
	{
				 	
		 log.info("Login Test started");			 
		// Login with invalid credentials (Wrong mobile number)
		 
		 log.info("Hovered profile icon");
			
		 login.hoverProfile();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		 login.clickLogin();
		 log.info("Clicked Login");
		 login.sendMobileNo("89838383");
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		 login.clickContinue();
		 System.out.println(login.getErrorMessage());
		 log.info("Got error message for wrong mobile number");
		 
		//If limit exceeds for a particular user
		 login.clearMobileNo();
		 login.sendMobileNo(logindata[2][0]);
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		 login.clickContinue();
		 log.info("Entered the phone number");
		 
		 try {
			Thread.sleep(30000);
		} catch (InterruptedException e1) {
			
			e1.printStackTrace();
		}
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		 login.clickContinue();
		 
		 try {
				Thread.sleep(30000);
			} catch (InterruptedException e1) {
				
				e1.printStackTrace();
			}
		 log.info("login when OTP limit exceeds");
		 int i;
		 for(i=0;i<2;i++)
		 {
			 login.resendButton();
			 try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		 }
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		 login.resendButton();
		 login.takeScreenshot2();
		 log.info("OTP limit exceeded");
		 
		// Login with invalid credentials (Wrong password)
		
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		 login.clickPassword();
		 login.sendPassword("Admin");
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		 login.clickLoginButton();
		 login.takeScreenshot1();
		 log.info("Entered password is wrong");
		 				 
		 //Login with valid credentials
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 login.clearPassword();
		 login.sendPassword(logindata[2][1]);
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
		 login.clickLoginButton();
		 

		 log.info("Clicked the login button.");
		 log.info("Login test completed.");
		 		 		 
	}
	
	@Test(priority=2)
	public void dashboard()
	{
		log.info("Dashboard Test started.");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		
		System.out.println("Mens Tab present? "+dashboard.menTabMeth());
		log.info("Mens Tab Present.");
		
		System.out.println("Womens Tab present? "+dashboard.womenTabMeth());
		log.info("Womens Tab Present.");
		
		System.out.println("Kids Tab present? "+dashboard.kidsTabMeth());
		log.info("Kids Tab Present.");
		
		System.out.println("Home and Living Tab present? "+dashboard.hnlTabMeth());
		log.info("Home and Living Present.");
		
		System.out.println("beauty Tab present? "+dashboard.beautyTabMeth());
		log.info("Dashboard test completed.");
	}
	
	@Test(priority=3)
	public void searchBar()
	{
		log.info("Search Bar Test Started.");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		value1=sbp.isHintDisplayed();
		System.out.println("is hint displayed? "+value1);
		
		value2 = sbp.getHintText();
		System.out.println("Hint text val: "+value2);
		
		sbp.searchBarKeyword("Tops");
		System.out.println("is tops text displayed?"+sbp.isOptionsDisplayed());
		System.out.println("Hint text val: "+value2);
		sbp.clickSearchBar();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		log.debug("entered the Tops keyword in the searchbar and clicked search.");
		
		sbp.searchBarKeyword("12tbdhf\n");
		sbp.clickSearchBar();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		log.debug("entered the incorrect keyword in the searchbar and clicked search.");
				
		sbp.searchBarKeyword("puma\n");
		System.out.println("Is the text displayed? "+sbp.isCorrectTextDisplayed());
		
		System.out.println("Text present is: "+sbp.getCorrectText());
		
		sbp.clickSearchBar();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		log.debug("entered the correct brandname in the searchbar and clicked search.");
		
		sbp.searchBarKeyword("xyz\n");
		sbp.clickSearchBar();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		
		//take screenshot 
		
		try {
			sbp.takeScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Incorrect BrandName: "+sbp.getIncorrectBrandName());
		log.debug("entered the incorrect brandname in the searchbar and clicked search.");
		log.info("Completed the search bar test.");
		
	}
	
	@Test(priority=4)
	public void filters()
	{
		log.info("Started the filter test.");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		f.setSearch("tops");
		f.clickSearch();
		  title=f.getTitle();
		  count=f.getCount();
		  
		  log.debug("Entered the keyword tops in the search bar.");
		  System.out.println(title+"  "+count);
		  
		  f.clickFilter1();
		  try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		  title1=f.getTitle();
		  count1=f.getCount();
		  
		  log.debug("Filtered the search items for the tops.Got the title and count.");		  
 
		  System.out.println(title1+"  "+count1);
		 
		  log.debug("Entered the keyword tops in the search bar.");
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		  f.clickFilter2();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		  log.debug("Filtered the results by applying men filter.");
		  
		  JavascriptExecutor js = (JavascriptExecutor) driver;
	       js.executeScript("window.scrollBy(0,300)", "");
	       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		  f.clickFilter3();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		  f.clickFilter4();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		  title2=f.getTitle();
		  count2=f.getCount();
		  
		  log.debug("After applying multiple filter(girls and H&M and price)");
		  System.out.println(title2+"  "+count2);
		  
		  f.clickClear();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		  title3=f.getTitle();
		  count3=f.getCount();
		  
		  log.debug("Applied multiple filters.");
		  
		  System.out.println(title3+"  "+count3);
		  
		  log.debug("Cleared all the filters.");
		  log.info("Completed the filters test.");
		
		
	}
	
	@Test(priority=5)
	public void myProfile()
	{	
		log.info("Started MyProfile Test.");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		myprofile.hoverProfile();
		myprofile.clickProfile();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

    	log.info("Get the details present in the myprofile. ");
		System.out.println(myprofile.getFullName());
		
		log.debug("Full Name Present.");
		System.out.println(myprofile.getMobileNum());
		
		log.debug("Mobile Number Present");
		System.out.println(myprofile.getEmail());
		
		log.debug("Email details Present.");
		System.out.println(myprofile.getGender());
		
		log.debug("Gender details present");
		System.out.println(myprofile.getDateOfBirth());
		
		log.debug("date of birth details present.");
		System.out.print(myprofile.getLocation());
		
		log.debug("Location details Present.");
		System.out.println(myprofile.getHint());;
		log.debug("Hint present.");
		log.info("All the details of myprofile present.");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,250)", "");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	    myprofile.clickEdit();
		log.debug("Clicked edit.");
	    myprofile.setAlternateMobile("9146134449");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	    myprofile.clickSave();
	    log.debug("Entered the alternate mobile number and saved.");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	    myprofile.clickClose();
	    log.debug("Clicked the close.");
		log.info("MyProfile Test completed.");
	}

	@Test(priority=6)
	public void wishlist()
	{
		log.info("Wishlist test started.");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        w.WishList_click();
        log.debug("Clicked the wishlist.");
        System.out.println(w.Head_title());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        w.Remove_item1();
        log.debug("Removed the item");
        
        //Take screenshots
        
        try {
			w.takeScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        log.info("Items after removing item");
        System.out.println(w.Head_title());
        log.info("Completed the wishlist test.");
	}
	
	@Test(priority=7)
	public void addToCart() throws InterruptedException
	{   
		log.info("Started the add to cart test.");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		 a.clickItem1();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		 a.clickSize1();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		 a.clickDone();
		 log.debug("Clicked the item, size and submitted.Item Added.");
		 
		 // Take Screenshot
		 try {
			a.takeScreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		 a.clickBag();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,200)", "");
		      
		 driver.navigate().back();
		      
		 a.clickItem2();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		 a.clickSize2();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		 a.clickDone();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		 log.debug("Second Item Added.");
		      
		 a.clickBag();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		 js.executeScript("window.scrollBy(0,200)", "");
		 log.info("WishList test completed.");
	}
	
	@Test(priority=8)
	public void placeOrder()
	{
		log.info("Place order test started.");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		System.out.println(place.getTotalAmount());
		place.clickPlaceOrder();
		log.debug("Fetched the total Amount and clicked to place the order.");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		
		System.out.println("Estimated Delivery By");
		System.out.println(place.getDate());
		
		System.out.println("Address Details:");		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		
		System.out.println(place.getAddress1());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		
		System.out.println(place.getContactDetails());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		log.debug("Details of the delivery, address, contact details present.");
		
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		place.clickGoBack();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.navigate().back();
		log.debug("Navigated back.");
		log.info("Completed the Placeorder test.");

	}
	
	@Test(priority=9)
	public void myOrders()
	{
		log.info("Started the My Orders Test.");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		
		order.hoverProfile();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		order.hoverOrder();
		
		log.debug("Hovered my profile and order.");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		order.clickOrder1();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		System.out.println(order.checkStatus1());
		System.out.println(order.checkStatus2());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		log.debug("Clicked orders and checked the status.");
		
		order.clickExchange();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		order.clickClose1();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		order.clickReturn();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		log.debug("Clicked the exchange.");
		
		order.clickClose2();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.navigate().back();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		order.clickReview();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		order.setReview("Product and its material is good");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		order.clickClose3();
		
		log.debug("Clicked the review, set the review and closed.");
		log.info("Completed the myorder test.");
	}
	
	@Test(priority=10)
	public void helpCenter()
	{
				log.info("Contact Us test started.");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
				
				help.hoverProfile();
				help.contact();
				help.nonOrder();
				
				//getting all options
				header = help.getOption();
				System.out.println(header);
				
				text1 = help.getOption1();
				System.out.println(text1);
				
				text2 = help.getOption2();
				System.out.println(text2);
				
				text3 = help.getOption3();
				System.out.println(text3);
				
				text4 = help.getOption4();
				System.out.println(text4);
				
				//click on payment(option1)
				help.clickOption1();
				
				//click on the 'unable to use gift card option'
				help.clickGift();
				
				chat = help.getChat();
				System.out.println(chat);
				
				callNow = help.getCall();
				System.out.println(callNow);
				
				// take screenshots
				
				try {
					help.takeScreenshot();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				help.getIssues();
				
				help.getFAQ();
				
				ButtonCount = help.getButtonCount();
			    System.out.println(ButtonCount);		
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
			    js.executeScript("window.scrollBy(0,800)", "");
			    log.info("Completed the Help Center test.");
	}
	
	@Test(priority=11)
	public void logout()
	{
		log.info("Started the logout test");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		logOut.hoverProfile1();
		logOut.clickLogOut();
		log.info("Completed the logout test.");
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}

