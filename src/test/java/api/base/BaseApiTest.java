package api.base;

import io.restassured.RestAssured;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseApiTest {

    @BeforeMethod
    public void beforeTest(Method method) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("STARTING TEST: " + method.getName());
        System.out.println("==================================================");
    }

    @AfterMethod
    public void afterTest(Method method) {
        System.out.println("==================================================");
        System.out.println("FINISHED TEST: " + method.getName());
        System.out.println("==================================================");
        System.out.println();
    }

    static {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }
}
