package psdpal.base;

import org.testng.annotations.BeforeSuite;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import psdpal.common.ConfigReader;
import psdpal.common.TokenManager;

public class BaseTest {

	protected static String token;
	protected RequestSpecification requestSpec;
	protected RequestSpecification publicSpec;
	protected RequestSpecification authSpec;

	@BeforeSuite(alwaysRun = true)
	public void setup() {

		token = TokenManager.getToken();

		publicSpec = new RequestSpecBuilder().setBaseUri(ConfigReader.getBaseUrlFor("restfulbooker"))
				.setContentType(ContentType.JSON).build();

		authSpec = new RequestSpecBuilder().setBaseUri(ConfigReader.getBaseUrlFor("restfulbooker"))
				.setContentType(ContentType.JSON).addHeader("Cookie", "token=" + token).build();
	}

}
