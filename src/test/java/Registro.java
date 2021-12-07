import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.util.concurrent.TimeUnit;

public class Registro {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir").concat("\\src\\test\\resources\\drivers\\chromedriver.exe"));
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
    }

    @Test
    void envioCredencialesValidas() {
        /**Ingreso a Sing In*/
        WebElement btnSingIn = driver.findElement(By.className("login"));
        btnSingIn.click();
        WebElement tittleSingIn = driver.findElement(By.className("page-heading"));

        /**VALIDACION DE INGRESO A LA PANTALLA DE LOGIN*/
        Assertions.assertEquals("AUTHENTICATION", tittleSingIn.getText());

        //INGRESO CREACIÓN DE CUENTA
        WebElement email = driver.findElement(By.id("email_create"));
        WebElement btnCrearCuenta = driver.findElement(By.id("SubmitCreate"));
        WebElement tittleNewAccount = driver.findElement(By.className("page-subheading"));
        email.sendKeys("pepito010@gmail.com");
        btnCrearCuenta.click();

        //VALIDACION INGRESO A LA PANTALLA CREAR CUENTA
        Assertions.assertEquals("CREATE AN ACCOUNT", tittleNewAccount.getText()); //ERROR 1 NO COINCIDEN LOS TITULOS

        //INGRESO DE DATOS PARA LA CREACIÓN DE CUENTA

        WebElement gender1 = driver.findElement(By.id("id_gender1"));
        WebElement name = driver.findElement(By.name("customer_firstname"));
        WebElement lastName = driver.findElement(By.id("customer_lastname"));
        WebElement password = driver.findElement(By.id("passwd"));
        Select days = new Select(driver.findElement(By.id("days")));
        Select month = new Select(driver.findElement(By.id("months")));
        Select year = new Select(driver.findElement(By.id("years")));

        WebElement name1 = driver.findElement(By.name("firstname"));
        WebElement lastName1 = driver.findElement(By.name("lastname"));
        WebElement company = driver.findElement(By.id("company"));
        WebElement addres = driver.findElement(By.id("address1"));
        WebElement city = driver.findElement(By.id("city"));
        Select state = new Select(driver.findElement(By.id("id_state")));
        WebElement postal = driver.findElement(By.id("postcode"));
        WebElement phone = driver.findElement(By.id("phone_mobile"));
        WebElement btnRegistrar = driver.findElement(By.id("submitAccount"));

        gender1.click();
        name.sendKeys("Pepito");
        lastName.sendKeys("Perez");
        password.sendKeys("123456");
        days.selectByValue("2");
        month.selectByValue("6");
        year.selectByValue("1998");
        name1.sendKeys("Pepito");
        lastName1.sendKeys("Perez");
        company.sendKeys("ABCDE");
        addres.sendKeys(" 2375 Pennsylvania Av. NW, 20037 Washington DC");
        city.sendKeys("Alabahama");
        state.selectByValue("1");
        postal.sendKeys("36523");
        phone.sendKeys("123456789");
        btnRegistrar.click();

        //VALIDACION DE CORRECTO REGISTROS
        WebElement userRegistered = driver.findElement(By.className("account"));
        Assertions.assertEquals("Pepito Perez", userRegistered.getText());

    }

    @AfterEach
    void tearDown() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }
}