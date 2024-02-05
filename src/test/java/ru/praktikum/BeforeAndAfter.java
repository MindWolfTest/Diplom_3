package ru.praktikum;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import static ru.praktikum.driver.WebDriverCreator.createWebDriver;

public class BeforeAndAfter
{
    protected WebDriver driver;

    @Before
    public void setUp()
    {
        driver = createWebDriver();
    }

    @After
    public void tearDown()
    {
        driver.close();
    }
}
