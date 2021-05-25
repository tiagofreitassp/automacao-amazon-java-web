package br.com.amazon.driver;

import br.com.amazon.utils.InfraUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverWeb {
    public WebDriver driver;

    public WebDriver getCurrentRunningDriver() {
        return driver;
    }

    public void criarDriverWeb(String browser, String url) throws Exception {
        if(browser.equalsIgnoreCase("chrome")){
            criarDriverChrome(url);
        }else{
            criarDriverChrome(url);
        }
    }

    public void criarDriverChrome(String url){
        String os = InfraUtils.getOsName();
        System.out.println("Sistema Operacional: "+os);

        if (os.equalsIgnoreCase("Mac") || os.equalsIgnoreCase("Unix") ||
                os.equalsIgnoreCase("Mac OS X")) {
            System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
        }else if (os.equalsIgnoreCase("Windows") || os.equalsIgnoreCase("Windows 10")){
            System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        }
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void fecharDriverWeb(){
        if (driver != null){
            driver.quit();
            System.out.println("Driver encerrado com sucesso!");
        }
    }
}
