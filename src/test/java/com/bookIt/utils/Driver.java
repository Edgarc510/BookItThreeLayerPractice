package com.bookIt.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {
        /*
           Creating the private constructor so this class' object
           is not reachable from outside
            */
        private Driver(){

        }
        /*
        Making our 'driver' instance private so that it is not reachable from
        outside of the class.Also, we make static we also need the functionality
        of static of running before everything else, and also we will use in a
        static method
         */
        private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>(); // this is null by default
        /*
        Creating re-usable utility method that will return same 'driver' instance
        everytime we call it.
         */
        public static WebDriver get() {

            if(driverPool.get() == null){

                synchronized (Driver.class){


                    String browserType = ConfigurationReader.get("browser");

                    switch (browserType){
                        case "chrome":
                            WebDriverManager.chromedriver().setup();
                            driverPool.set(new ChromeDriver());
                            driverPool.get().manage().window().maximize();
                            driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                            break;
                        case "firefox":
                            WebDriverManager.firefoxdriver().setup();
                            driverPool.set(new FirefoxDriver());
                            driverPool.get().manage().window().maximize();
                            driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                            break;
                    }
                }
            }
 /*
        Same driver instance will be returned every time we call Driver.getDriver(); method
         */
            return driverPool.get();

        }
        /*
         This method makes sure we have some form of driver session or driver id has.
            Either null or not null it must exist.
         */
        public static void closeDriver() {
            if(driverPool.get()!=null) {
                driverPool.get().quit();
                driverPool.remove();
            }


        }
}
