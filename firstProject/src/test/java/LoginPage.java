import org.openqa.selenium.WebDriver;

public class LoginPage extends Page{
    public final String username ="//input[contains(@id,'username')]";
    public final String password = "//input[contains(@id,'password')]";
    public final String loginButton ="//input[contains(@id,'kc-login')]";
    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
