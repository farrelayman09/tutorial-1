package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution. * Set automatically during each test run by Spring Framework's test context. */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void createProduct_isCorrect (ChromeDriver driver) throws Exception {
        driver.get(baseUrl);

        driver.findElement(By.id("check-product")).click();
        driver.findElement(By.id("create-product")).click();

        driver.findElement(By.id("nameInput")).sendKeys("foobar");
        driver.findElement(By.id("quantityInput")).clear();
        driver.findElement(By.id("quantityInput")).sendKeys("500");

        driver.findElement(By.id("submit")).click();

        // Verify
        WebElement product = driver.findElement(By.id("name-1"));
        assertEquals(product.getText(), "foobar");

        product = driver.findElement(By.id("quantity-1"));
        assertEquals(product.getText(), "500");
    }
}
