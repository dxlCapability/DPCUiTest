import org.openqa.selenium.WebDriver;

public class MainPage extends Page{
    //public final String mainPageLogo = "//div/span[contains(text(),\"E-SHOP\")]";
    public final String mainPageLogo = "//*[@id=\"wrapper\"]/fuse-navbar-vertical/div[1]/div/span";
    public final String approvalHead = "//div/span[contains(text(),\"Approvals\")]";
    public final String approvalButton = "//a/span[contains(text(),\"Approvals\")]";
    public final String addApprovalButton = "//*[@id=\"approvals\"]/div[2]/div[1]/button/span/mat-icon";
    public final String addApprovalPopUp = "//*[@class=\"cdk-overlay-pane\"]/snack-bar-container/simple-snack-bar";
    public final String activeUserApprovalRegister ="//mat-cell/p[contains(text(),\"amdocstozturk\")]";
    public final String closedApprovalButton ="//span/mat-icon[contains(text(),\"block\")]";
    public final String saveApprovalButton = "//span/mat-icon[contains(text(),\"save\")]";
    public final String arrowBackApprovalDashboard = "#approval > div.center > div.header.white-fg > div:nth-child(1) > button > span > mat-icon";
    public final String elementsCount = "//*[@id=\"approvals\"]/div[2]/div[2]/mat-table/mat-row";
    public final String element = "//*[@id=\"approvals\"]/div[2]/div[2]/mat-table/mat-row[%d]/mat-cell[2]/p";
    public final String elementCode = "//*[@id=\"approvals\"]/div[2]/div[2]/mat-table/mat-row[%d]/mat-cell[1]/p";
    public final String buttonCode = "//button[contains(text(),\"Code\")]";
    public final String buttonUser ="//button[contains(text(),\"User\")]";
    public final String buttonEmail ="//button[contains(text(),\"Email\")]";
    public final String buttonGroup = "//button[contains(text(),\"Group\")]";
    public final String buttonStatus ="//button[contains(text(),\"Status\")]";
    public final String buttonReviewers = "//button[contains(text(),\"Reviewers\")]";
    public final String buttonIncludedItems ="//button[contains(text(),\"Included Items\")]";
    public final String uniqueIdentifierInput ="//input[@placeholder=\"Unique Identifier\"]";
    public final String approvalStatusInput = "//input[@placeholder=\"Approval Status\"]";
    public final String ldapUserEmailInput= "//input[@placeholder=\"LDAP User Email\"]";
    public final String createdAtInput = "//input[@placeholder=\"Created At\"]";
    public final String ldapUserGroupInput = "//input[@placeholder=\"LDAP User Group\"]";
    public final String ldapUsernameInput ="//input[@placeholder=\"LDAP Username\"]";
    public final String newCreatedUserCode = "//div[starts-with(@class,\"ng-tns-\")]/div[1]/div";
    public final String closedApprovalButtonPopUp ="/html/body/div[2]";
    public final String approvalDashboardNextPage = "//*[@id=\"approvals\"]/div[2]/div[2]/mat-paginator/div/div[2]/button[2]";
    public final String approvalDashboardFirstUserCode ="//*[@id=\"approvals\"]/div[2]/div[2]/mat-table/mat-row[1]/mat-cell[1]/p";
    ////*[starts-with(@id="cdk-overlay-")]/snack-bar-container/simple-snack-bar
    public final String approvalDashboardSearch ="//input[@placeholder=\"Search\"]";
    public final String elementUser = "//*[@id=\"approvals\"]/div[2]/div[2]/mat-table/mat-row[%d]/mat-cell[2]/p";
    public final String lastModifiedAt = "//input[@placeholder=\"Last Modified At\"]";
    public final String approvalUserItemButton = "//div[starts-with(@id,\"mat-tab-label-\")][2]";
    public final String approvalUserItemCheckBoxSize ="//*[@class=\"mat-checkbox mat-accent\"]";
    public final String approvalUserItemSearch ="//*[@id=\"search\"]";
    public final String approvalUserItemCodeOk = "//*[starts-with(@id,\"mat-tab-content\")]/div/next-pc-approval-item/section/mat-table/mat-row[%d]/mat-cell[3]/p";
    public final String approvalUserItemCodeOkButton ="//*[starts-with(@id,\"mat-tab-content-\")]/div/next-pc-approval-item/section/mat-table/mat-header-row/mat-header-cell[3]/div/button";
    public MainPage(WebDriver driver) {
        super(driver);
    }
}
