import dev.failsafe.internal.util.Assert;
import org.example.Main;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

@DisplayName("Approval Tab test case")
public class LoginTests {
    private WebDriver driver;
    private Page page;
    private  LoginPage loginPage;
    private MainPage mainPage;
    private String user ="amdocstozturk";
    private String userPassword ="GNV?*?oikbz838";

    @BeforeEach
    void Setup(){
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        //driver.get("http://eshop-ui-eshop-mobile-test.apps.mbt.vodafone.local/approvals");
        driver.get("http://eshop-ui-eshop-mobile-test.apps.mbt.vodafone.local/");
        loginPage.elementToWait(By.xpath(loginPage.username));
        //driver.findElement(By.xpath("//input[contains(@id,'username')]")).sendKeys("amdocstozturk");
        loginPage.sendKeysToLabel(By.xpath(loginPage.username),user);
        loginPage.elementToWait(By.xpath(loginPage.password));
        // driver.findElement(By.xpath(" //input[contains(@id,'password')]")).sendKeys("GNV?*?oikbz838");
        loginPage.sendKeysToLabel(By.xpath(loginPage.password),userPassword);
        //driver.findElement(By.xpath("//input[contains(@id,'kc-login')]")).click();
        loginPage.clickToButton(By.xpath(loginPage.loginButton));


    }
    @DisplayName("DPC Login Success Username and Password")
    @Test
    void Login() throws InterruptedException{
        Thread.sleep(2000);
        mainPage.elementToWait(By.xpath(mainPage.mainPageLogo));
        System.out.println(driver.findElements(By.xpath(mainPage.mainPageLogo)).size());
        assertTrue(driver.findElements(By.xpath(mainPage.mainPageLogo)).size() > 0,"DPC Anasayfasına girilemedi");
    }
    @DisplayName("Approval Tab")
    @Test
    void ApprovalTab() throws InterruptedException {
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        Thread.sleep(2000);
        mainPage.elementToWait(By.xpath(mainPage.approvalHead));
        boolean approvalHead = driver.findElement(By.xpath(mainPage.approvalHead)).isDisplayed();
        assertTrue(approvalHead,"Approval alanına ulaşılamadı");

    }
    @DisplayName("Create second Approval over existing Approval")
    @Test
    void ApprovalLogin() throws InterruptedException {
       // driver.findElement(By.cssSelector("#approvals > div.center > div.header.white-fg > button > span")).click();
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        Thread.sleep(4000);
        mainPage.elementToWait(By.xpath(mainPage.addApprovalButton));
        Thread.sleep(2000);
        mainPage.clickToButton(By.xpath(mainPage.addApprovalButton));
        mainPage.elementToWait(By.xpath(mainPage.addApprovalPopUp));
        String element = driver.findElement(By.xpath("//*[@class=\"cdk-overlay-pane\"]/snack-bar-container/simple-snack-bar")).getText();
        System.out.println(element);
        String element2 = "tarkan.ozturk@vodafone.com";
        assertThat("Mevcut hesabın Approval kaydı vardır pop-up hatası",element, containsString(element2));
       // assertThat(x).contains("foo");

    }
    @DisplayName("delete approval")
    @Test
    void ApprovalRegisterDelete() throws InterruptedException{
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        mainPage.elementToWait(By.xpath(mainPage.activeUserApprovalRegister));
        mainPage.clickToButton(By.xpath(mainPage.activeUserApprovalRegister));
        mainPage.elementToWait(By.xpath(mainPage.closedApprovalButton));
        mainPage.clickToButton(By.xpath(mainPage.closedApprovalButton));
        mainPage.elementToWait(By.cssSelector(mainPage.arrowBackApprovalDashboard));
        mainPage.clickToButton(By.cssSelector(mainPage.arrowBackApprovalDashboard));
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath(mainPage.elementsCount));
        int elementCount  = elements.size();
        System.out.println(elementCount);
        List<String> userList = new ArrayList<>();
        for(int i=1;i<=elementCount ;i++){
            //System.out.println(i);
            String newString = String.format(mainPage.element, i);
           // System.out.println(newString);
            WebElement elementText = driver.findElement(By.xpath(newString));
            //System.out.println( elementText.getText());
            //dizi.add(elementText.getText());
            userList.add(elementText.getText());
           // System.out.println(userList);
        }
        assertFalse(userList.contains(user),"User'ın Approval kaydı hala açık.");
       // for (WebElement element : elementCount){
         //   element.getText();
        //}
    }
    @DisplayName("Create New Approva")
    @Test
    void ApprovalRegister() throws InterruptedException{
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        mainPage.elementToWait(By.xpath(mainPage.addApprovalButton));
        Thread.sleep(2000);
        mainPage.clickToButton(By.xpath(mainPage.addApprovalButton));
        mainPage.elementToWait(By.xpath(mainPage.saveApprovalButton));
        mainPage.clickToButton(By.xpath(mainPage.saveApprovalButton));
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath(mainPage.elementsCount));
        //List<WebElement> elements = driver.findElements(By.xpath(mainPage.elementsCount));
        int elementCount  = elements.size();
        System.out.println(elementCount);
        List<String> userList = new ArrayList<>();
        for(int i=1;i<=elementCount ;i++){
            //System.out.println(i);
            String newString = String.format(mainPage.element, i);
            // System.out.println(newString);
            WebElement elementText = driver.findElement(By.xpath(newString));
            //System.out.println( elementText.getText());
            //dizi.add(elementText.getText());
            userList.add(elementText.getText());
            // System.out.println(userList);
        }
        assertTrue(userList.contains(user),"User'ın Approval kaydı bulunamadı.");
    }
    @DisplayName("Approval Dashboard (Code) arrow")
    @Test
    void approvalDashboardCodeOk() throws InterruptedException{
        mainPage.elementToWait(By.xpath(mainPage.mainPageLogo));
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath(mainPage.elementsCount));
        int elementsCount  = elements.size();
        System.out.println(elementsCount);
        List<String> codeList = new ArrayList<>();
        for(int i=1;i<=elementsCount ;i++){
            System.out.println(i);
            String newString = String.format(mainPage.elementCode, i);
            // System.out.println(newString);
            WebElement elementText = driver.findElement(By.xpath(newString));
            //System.out.println( elementText.getText());
            //dizi.add(elementText.getText());
            codeList.add(elementText.getText());
        }
        int codeValue = 0;
        String codeListItemFirst = codeList.get(0);
        System.out.println(codeListItemFirst);
        String codeListItemLast = codeList.get(codeList.size() - 1);
        System.out.println(codeListItemLast);
        mainPage.elementToWait(By.xpath(mainPage.buttonCode));
        mainPage.clickToButton(By.xpath(mainPage.buttonCode));
        System.out.println(codeList.get(0));
        System.out.println(codeList.get(codeList.size() - 1));
        if(codeListItemLast == codeList.get(0) && codeListItemFirst == codeList.get(codeList.size() - 1)){
            codeValue = 1;
        }
        assertEquals(codeValue,1);
        System.out.println(codeList);


    }
    @DisplayName("Approval Dashboard (User) arrow")
    @Test
    void approvalDashboardUserOk() throws InterruptedException{
        mainPage.elementToWait(By.xpath(mainPage.mainPageLogo));
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath(mainPage.elementsCount));
        int elementsCount  = elements.size();
        System.out.println(elementsCount);
        List<String> userList = new ArrayList<>();
        for(int i=1;i<=elementsCount ;i++){
            System.out.println(i);
            String newString = String.format(mainPage.elementCode, i);
            // System.out.println(newString);
            WebElement elementText = driver.findElement(By.xpath(newString));
            //System.out.println( elementText.getText());
            //dizi.add(elementText.getText());
            userList.add(elementText.getText());
        }
        int codeValue = 0;
        String codeListItemFirst = userList.get(0);
        System.out.println(codeListItemFirst);
        String codeListItemLast = userList.get(userList.size() - 1);
        System.out.println(codeListItemLast);
        mainPage.elementToWait(By.xpath(mainPage.buttonUser));
        mainPage.clickToButton(By.xpath(mainPage.buttonUser));
        System.out.println(userList.get(0));
        System.out.println(userList.get(userList.size() - 1));
        if(codeListItemLast == userList.get(0) && codeListItemFirst == userList.get(userList.size() - 1)){
            codeValue = 1;
        }
        assertEquals(codeValue,1);
        System.out.println(userList);


    }
    @DisplayName("Approval Dashboard (Email) arrow")
    @Test
    void approvalDashboardEmailOk() throws InterruptedException{
        mainPage.elementToWait(By.xpath(mainPage.mainPageLogo));
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath(mainPage.elementsCount));
        int elementsCount  = elements.size();
        System.out.println(elementsCount);
        List<String> emailList = new ArrayList<>();
        for(int i=1;i<=elementsCount ;i++){
            System.out.println(i);
            String newString = String.format(mainPage.elementCode, i);
            // System.out.println(newString);
            WebElement elementText = driver.findElement(By.xpath(newString));
            //System.out.println( elementText.getText());
            //dizi.add(elementText.getText());
            emailList.add(elementText.getText());
        }
        int emailValue = 0;
        String emailListItemFirst = emailList.get(0);
        System.out.println(emailListItemFirst);
        String codeListItemLast = emailList.get(emailList.size() - 1);
        System.out.println(codeListItemLast);
        mainPage.elementToWait(By.xpath(mainPage.buttonEmail));
        mainPage.clickToButton(By.xpath(mainPage.buttonEmail));
        System.out.println(emailList.get(0));
        System.out.println(emailList.get(emailList.size() - 1));
        if(codeListItemLast == emailList.get(0) && emailListItemFirst == emailList.get(emailList.size() - 1)){
            emailValue = 1;
        }
        assertEquals(emailValue,1);
        System.out.println(emailList);


    }
    @DisplayName("Approval unique identifier Control")
    @Test
    void userUniqueIdentifer(){
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        mainPage.elementToWait(By.xpath(mainPage.activeUserApprovalRegister));
        mainPage.clickToButton(By.xpath(mainPage.activeUserApprovalRegister));
        mainPage.elementToWait(By.xpath(mainPage.uniqueIdentifierInput));
        String uniqueIdentifierInputText = driver.findElement(By.xpath(mainPage.uniqueIdentifierInput)).getAttribute("value");
        System.out.println(uniqueIdentifierInputText);
        int uniqueIdentifierValue =0;
        if(uniqueIdentifierInputText.length() > 0){
            uniqueIdentifierValue = 1;
        }
        assertEquals(uniqueIdentifierValue,1);
    }
    @DisplayName("Approval Status Control")
    @Test
    void approvalStatus(){
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        mainPage.elementToWait(By.xpath(mainPage.activeUserApprovalRegister));
        mainPage.clickToButton(By.xpath(mainPage.activeUserApprovalRegister));
        mainPage.elementToWait(By.xpath(mainPage.approvalStatusInput));
        String approvalStatusInputText = driver.findElement(By.xpath(mainPage.approvalStatusInput)).getAttribute("value");
        System.out.println(approvalStatusInputText);
        int approvalStatusValue =0;
        if(approvalStatusInputText.length() > 0){
            approvalStatusValue = 1;
        }
        assertEquals(approvalStatusValue,1);
    }
    @DisplayName("Approval ldap user email Control")
    @Test
    void ldapUserEmail(){
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        mainPage.elementToWait(By.xpath(mainPage.activeUserApprovalRegister));
        mainPage.clickToButton(By.xpath(mainPage.activeUserApprovalRegister));
        mainPage.elementToWait(By.xpath(mainPage.ldapUserEmailInput));
        String ldapUserEmailInputText = driver.findElement(By.xpath(mainPage.ldapUserEmailInput)).getAttribute("value");
        System.out.println(ldapUserEmailInputText);
        int ldapUserEmailValue =0;
        if(ldapUserEmailInputText.length() > 0){
            ldapUserEmailValue = 1;
        }
        assertEquals(ldapUserEmailValue,1);
    }
    @DisplayName("Approval  created add Control")
    @Test
    void createdAt(){
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        mainPage.elementToWait(By.xpath(mainPage.activeUserApprovalRegister));
        mainPage.clickToButton(By.xpath(mainPage.activeUserApprovalRegister));
        mainPage.elementToWait(By.xpath(mainPage.createdAtInput));
        String createdAtInputInputText = driver.findElement(By.xpath(mainPage.createdAtInput)).getAttribute("value");
        System.out.println(createdAtInputInputText);
        int createdAtInputValue =0;
        if(createdAtInputInputText.length() > 0){
            createdAtInputValue = 1;
        }
        assertEquals(createdAtInputValue,1);
    }
    @DisplayName("Approval ldap user group Control")
    @Test
    void ldapUserGroup(){
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        mainPage.elementToWait(By.xpath(mainPage.activeUserApprovalRegister));
        mainPage.clickToButton(By.xpath(mainPage.activeUserApprovalRegister));
        mainPage.elementToWait(By.xpath(mainPage.createdAtInput));
        String ldapUserGroupInputText = driver.findElement(By.xpath(mainPage.ldapUserGroupInput)).getAttribute("value");
        System.out.println(ldapUserGroupInputText);
        int ldapUserGroupInputValue =0;
        if(ldapUserGroupInputText.length() > 0){
            ldapUserGroupInputValue = 1;
        }
        assertEquals(ldapUserGroupInputValue,1);
    }
    @DisplayName("Approval ldap user name Control")
    @Test
    void  ldapUsername(){
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        mainPage.elementToWait(By.xpath(mainPage.activeUserApprovalRegister));
        mainPage.clickToButton(By.xpath(mainPage.activeUserApprovalRegister));
        mainPage.elementToWait(By.xpath(mainPage.createdAtInput));
        String  ldapUsernameInputText = driver.findElement(By.xpath(mainPage.ldapUsernameInput)).getAttribute("value");
        System.out.println( ldapUsernameInputText);
        int  ldapUsernameInputValue =0;
        if( ldapUsernameInputText.length() > 0){
            ldapUsernameInputValue = 1;
        }
        assertEquals( ldapUsernameInputValue,1);
    }
    @DisplayName("TC005")
    @Test
    void notSaveUniqCode() throws InterruptedException{
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath(mainPage.elementsCount));
        int elementsCount  = elements.size();
        System.out.println(elementsCount);
        List<String> codeList = new ArrayList<>();
        for(int i=1;i<=elementsCount ;i++){
            System.out.println(i);
            String newString = String.format(mainPage.elementCode, i);
            // System.out.println(newString);
            WebElement elementText = driver.findElement(By.xpath(newString));
            //System.out.println( elementText.getText());
            //dizi.add(elementText.getText());
            codeList.add(elementText.getText());
        }
        mainPage.elementToWait(By.xpath(mainPage.addApprovalButton));
        Thread.sleep(2000);
        mainPage.clickToButton(By.xpath(mainPage.addApprovalButton));
        mainPage.elementToWait(By.xpath(mainPage.newCreatedUserCode));
        String newCreatedUserCodeText = driver.findElement(By.xpath(mainPage.newCreatedUserCode)).getText();
        System.out.println(newCreatedUserCodeText);
        assertFalse(codeList.contains(newCreatedUserCodeText),"Aynı kod kaydı bulunamadı.");
    }
    @DisplayName("TC005")
    @Test
    void notSaveNotClosed() throws InterruptedException {
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        Thread.sleep(2000);
        mainPage.elementToWait(By.xpath(mainPage.addApprovalButton));
        Thread.sleep(2000);
        mainPage.clickToButton(By.xpath(mainPage.addApprovalButton));
        mainPage.elementToWait(By.xpath(mainPage.closedApprovalButton));
        mainPage.clickToButton(By.xpath(mainPage.closedApprovalButton));
        Thread.sleep(5000);
        mainPage.elementToWait(By.xpath(mainPage.closedApprovalButtonPopUp));
        String element = driver.findElement(By.xpath(mainPage.closedApprovalButtonPopUp)).getText();
        String element2 = "Bad request due to argument type mismatch with target object field id : null";
        assertThat("Closed pop-up bulunamadı",element, containsString(element2));

    }
    @DisplayName("Approval Dashboard (Group) arrow")
    @Test
    void approvalDashboardGroupOk() throws InterruptedException{
        mainPage.elementToWait(By.xpath(mainPage.mainPageLogo));
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath(mainPage.elementsCount));
        int elementsCount  = elements.size();
        System.out.println(elementsCount);
        List<String> emailList = new ArrayList<>();
        for(int i=1;i<=elementsCount ;i++){
            System.out.println(i);
            String newString = String.format(mainPage.elementCode, i);
            // System.out.println(newString);
            WebElement elementText = driver.findElement(By.xpath(newString));
            //System.out.println( elementText.getText());
            //dizi.add(elementText.getText());
            emailList.add(elementText.getText());
        }
        int emailValue = 0;
        String emailListItemFirst = emailList.get(0);
        System.out.println(emailListItemFirst);
        String codeListItemLast = emailList.get(emailList.size() - 1);
        System.out.println(codeListItemLast);
        mainPage.elementToWait(By.xpath(mainPage.buttonGroup));
        mainPage.clickToButton(By.xpath(mainPage.buttonGroup));
        System.out.println(emailList.get(0));
        System.out.println(emailList.get(emailList.size() - 1));
        if(codeListItemLast == emailList.get(0) && emailListItemFirst == emailList.get(emailList.size() - 1)){
            emailValue = 1;
        }
        assertEquals(emailValue,1);
        System.out.println(emailList);

    }
    @DisplayName("Approval Dashboard (Status) arrow")
    @Test
    void approvalDashboardStatusOk() throws InterruptedException{
        mainPage.elementToWait(By.xpath(mainPage.mainPageLogo));
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath(mainPage.elementsCount));
        int elementsCount  = elements.size();
        System.out.println(elementsCount);
        List<String> emailList = new ArrayList<>();
        for(int i=1;i<=elementsCount ;i++){
            System.out.println(i);
            String newString = String.format(mainPage.elementCode, i);
            // System.out.println(newString);
            WebElement elementText = driver.findElement(By.xpath(newString));
            //System.out.println( elementText.getText());
            //dizi.add(elementText.getText());
            emailList.add(elementText.getText());
        }
        int emailValue = 0;
        String emailListItemFirst = emailList.get(0);
        System.out.println(emailListItemFirst);
        String codeListItemLast = emailList.get(emailList.size() - 1);
        System.out.println(codeListItemLast);
        mainPage.elementToWait(By.xpath(mainPage.buttonStatus));
        mainPage.clickToButton(By.xpath(mainPage.buttonStatus));
        System.out.println(emailList.get(0));
        System.out.println(emailList.get(emailList.size() - 1));
        if(codeListItemLast == emailList.get(0) && emailListItemFirst == emailList.get(emailList.size() - 1)){
            emailValue = 1;
        }
        assertEquals(emailValue,1);
        System.out.println(emailList);

    }
    @DisplayName("Approval Dashboard (Reviewers) arrow")
    @Test
    void approvalDashboardReviewersOk() throws InterruptedException{
        mainPage.elementToWait(By.xpath(mainPage.mainPageLogo));
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath(mainPage.elementsCount));
        int elementsCount  = elements.size();
        System.out.println(elementsCount);
        List<String> emailList = new ArrayList<>();
        for(int i=1;i<=elementsCount ;i++){
            System.out.println(i);
            String newString = String.format(mainPage.elementCode, i);
            // System.out.println(newString);
            WebElement elementText = driver.findElement(By.xpath(newString));
            //System.out.println( elementText.getText());
            //dizi.add(elementText.getText());
            emailList.add(elementText.getText());
        }
        int emailValue = 0;
        String emailListItemFirst = emailList.get(0);
        System.out.println(emailListItemFirst);
        String codeListItemLast = emailList.get(emailList.size() - 1);
        System.out.println(codeListItemLast);
        mainPage.elementToWait(By.xpath(mainPage.buttonReviewers));
        mainPage.clickToButton(By.xpath(mainPage.buttonReviewers));
        System.out.println(emailList.get(0));
        System.out.println(emailList.get(emailList.size() - 1));
        if(codeListItemLast == emailList.get(0) && emailListItemFirst == emailList.get(emailList.size() - 1)){
            emailValue = 1;
        }
        assertEquals(emailValue,1);
        System.out.println(emailList);

    }
    @DisplayName("Approval Dashboard (Included Items) arrow")
    @Test
    void approvalDashboardbuttonIncludedItemsOk() throws InterruptedException{
        mainPage.elementToWait(By.xpath(mainPage.mainPageLogo));
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath(mainPage.elementsCount));
        int elementsCount  = elements.size();
        System.out.println(elementsCount);
        List<String> emailList = new ArrayList<>();
        for(int i=1;i<=elementsCount ;i++){
            System.out.println(i);
            String newString = String.format(mainPage.elementCode, i);
            // System.out.println(newString);
            WebElement elementText = driver.findElement(By.xpath(newString));
            //System.out.println( elementText.getText());
            //dizi.add(elementText.getText());
            emailList.add(elementText.getText());
        }
        int emailValue = 0;
        String emailListItemFirst = emailList.get(0);
        System.out.println(emailListItemFirst);
        String codeListItemLast = emailList.get(emailList.size() - 1);
        System.out.println(codeListItemLast);
        mainPage.elementToWait(By.xpath(mainPage.buttonIncludedItems));
        mainPage.clickToButton(By.xpath(mainPage.buttonIncludedItems));
        System.out.println(emailList.get(0));
        System.out.println(emailList.get(emailList.size() - 1));
        if(codeListItemLast == emailList.get(0) && emailListItemFirst == emailList.get(emailList.size() - 1)){
            emailValue = 1;
        }
        assertEquals(emailValue,1);
        System.out.println(emailList);

    }
    @DisplayName("Approval Dashboard NextPage")
    @Test
    void approvalDashboardbuttonNextPage() throws InterruptedException{
        mainPage.elementToWait(By.xpath(mainPage.mainPageLogo));
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath(mainPage.elementsCount));
        int elementsCount  = elements.size();
        System.out.println(elementsCount);
        List<String> codeList = new ArrayList<>();
        for(int i=1;i<=elementsCount ;i++){
            System.out.println(i);
            String newString = String.format(mainPage.elementCode, i);
            // System.out.println(newString);
            WebElement elementText = driver.findElement(By.xpath(newString));
            //System.out.println( elementText.getText());
            //dizi.add(elementText.getText());
            codeList.add(elementText.getText());
        }
        mainPage.elementToWait(By.xpath(mainPage.approvalDashboardNextPage));
        mainPage.clickToButton(By.xpath(mainPage.approvalDashboardNextPage));
        mainPage.elementToWait(By.xpath(mainPage.approvalDashboardFirstUserCode));
        WebElement approvalDashboardFirstUserCode = driver.findElement(By.xpath(mainPage.approvalDashboardFirstUserCode));
        assertFalse(codeList.contains(approvalDashboardFirstUserCode),"Aynı kod kaydı bulundu.");


    }
    @DisplayName("Approval Dashboard Search")
    //Senaryo yarım fix bekleniyor
    @Test
    void approvalDashboardSearch() throws InterruptedException{
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        Thread.sleep(2000);
        mainPage.elementToWait(By.xpath(mainPage.approvalDashboardSearch));
        mainPage.clickToButton(By.xpath(mainPage.approvalDashboardSearch));
        List<WebElement> elements = driver.findElements(By.xpath(mainPage.elementsCount));
        int elementsCount  = elements.size();
        System.out.println(elementsCount);
        List<String> codeList = new ArrayList<>();
        for(int i=1;i<=elementsCount ;i++){
            System.out.println(i);
            String newString = String.format(mainPage.elementUser, i);
            // System.out.println(newString);
            WebElement elementText = driver.findElement(By.xpath(newString));
            //System.out.println( elementText.getText());
            //dizi.add(elementText.getText());
            codeList.add(elementText.getText());
        }
        System.out.println(codeList);
        Random randomUser = new Random();
        int whichOneUser = randomUser.nextInt(elementsCount);
        System.out.println("Rastgele seçilen isim: " + codeList.get(whichOneUser));
        driver.findElement(By.xpath(mainPage.approvalDashboardSearch)).sendKeys(codeList.get(whichOneUser));
        Thread.sleep(2000);
    }
    @DisplayName("Approval  last Modified At Control")
    //Senaryo yarım devam edilecek
    @Test
    void lastModifiedAt(){
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        mainPage.elementToWait(By.xpath(mainPage.activeUserApprovalRegister));
        mainPage.clickToButton(By.xpath(mainPage.activeUserApprovalRegister));
        mainPage.elementToWait(By.xpath(mainPage.lastModifiedAt));
        String createdAtInputInputText = driver.findElement(By.xpath(mainPage.lastModifiedAt)).getAttribute("value");
        System.out.println(createdAtInputInputText);
        int createdAtInputValue =0;
        if(createdAtInputInputText.length() > 0){
            createdAtInputValue = 1;
        }
        assertEquals(createdAtInputValue,1);
    }
    @DisplayName("Approval Item Search")
    @Test
    void approvalItemSearch() throws InterruptedException{
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath(mainPage.elementsCount));
        int elementsCount  = elements.size();
        System.out.println(elementsCount);
        List<String> codeList = new ArrayList<>();
        Random randomNumber = new Random();
        int chosenValue = 1 + randomNumber.nextInt(8);
        for(int i=1;i<=elementsCount ;i++){
            System.out.println(i);
            String newString = String.format(mainPage.elementUser, i);
            // System.out.println(newString);
            WebElement elementText = driver.findElement(By.xpath(newString));
            elementText.click();
            mainPage.elementToWait(By.xpath(mainPage.approvalUserItemButton));
            mainPage.clickToButton(By.xpath(mainPage.approvalUserItemButton));
            int elementVarlığı =  driver.findElements(By.xpath(mainPage.approvalUserItemCheckBoxSize)).size();
            if(elementVarlığı >= 2){
                mainPage.elementToWait(By.xpath(mainPage.approvalUserItemSearch));
                mainPage.clickToButton(By.xpath(mainPage.approvalUserItemSearch));
            }
            //System.out.println( elementText.getText());
            //dizi.add(elementText.getText());
            codeList.add(elementText.getText());
        }
        System.out.println(codeList);
    }
    @DisplayName("Approval Dashboard (Included Items) arrow")
    @Test
    void approvalUserItemsTypeOk() throws InterruptedException{
        mainPage.elementToWait(By.xpath(mainPage.mainPageLogo));
        mainPage.elementToWait(By.xpath(mainPage.approvalButton));
        mainPage.clickToButton(By.xpath(mainPage.approvalButton));
        Thread.sleep(2000);
        List<WebElement> elements = driver.findElements(By.xpath(mainPage.elementsCount));
        int elementsCount  = elements.size();
        System.out.println(elementsCount);
        List<String> elementTextItemCodeList = new ArrayList<>();
        int emailValue = 0;
        for(int i=1;i<=elementsCount ;i++){
            System.out.println(i);
            String newString = String.format(mainPage.elementCode, i);
            // System.out.println(newString);
            WebElement elementText = driver.findElement(By.xpath(newString));
            elementText.click();
            mainPage.elementToWait(By.xpath(mainPage.approvalUserItemButton));
            mainPage.clickToButton(By.xpath(mainPage.approvalUserItemButton));
            int anyItemsSize =  driver.findElements(By.xpath(mainPage.approvalUserItemCheckBoxSize)).size();
            System.out.println(anyItemsSize+ "deneme");
            if(anyItemsSize >= 2){
                  for(int j=1;j<=anyItemsSize;j++){
                      System.out.println(j);
                      String newStringItemCodeOk = String.format(mainPage.approvalUserItemCodeOk, j);
                      WebElement elementTextItemCodeOk = driver.findElement(By.xpath(newStringItemCodeOk));
                      System.out.println(elementTextItemCodeOk.getAttribute("textContent"));
                      //System.out.println(elementTextItemCodeOk.getText()+"deneme2");
                      elementTextItemCodeList.add(elementTextItemCodeOk.getAttribute("textContent"));
                      System.out.println(elementTextItemCodeList);
                      String elementTextItemCodeListItemFirst = elementTextItemCodeList.get(0);
                      System.out.println(elementTextItemCodeListItemFirst);
                      String codeListItemLast = elementTextItemCodeList.get(elementTextItemCodeList.size() - 1);
                      System.out.println(codeListItemLast);
                      mainPage.elementToWait(By.xpath(mainPage.approvalUserItemCodeOkButton));
                      mainPage.clickToButton(By.xpath(mainPage.approvalUserItemCodeOkButton));
                      mainPage.clickToButton(By.xpath(mainPage.approvalUserItemCodeOkButton));
                      System.out.println(elementTextItemCodeList.get(0));
                      System.out.println(elementTextItemCodeList.get(elementTextItemCodeList.size() - 1));
                      if(codeListItemLast == elementTextItemCodeList.get(0) && elementTextItemCodeListItemFirst == elementTextItemCodeList.get(elementTextItemCodeList.size() - 1)){
                          emailValue = 1;
                      }
                      break;
                  }
            }
            //System.out.println( elementText.getText());
            //dizi.add(elementText.getText());
        }
        assertEquals(emailValue,1);
        System.out.println(elementTextItemCodeList);
    }
    @AfterEach
    void DriverClose(){
       driver.close();
   }
  }

    //void tearDown(){WebDriver driver = new ChromeDriver();driver.close();}}
