package rough;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class apiTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Get Request
		String resourcePath = "https://reqres.in/api/users";
		String token = null;
		Response res = given().log().all().header("Authorization", "Bearer " + token).get(resourcePath);
		//res.prettyPrint();
		System.out.println(res.asString());
		//System.out.println(jp.getString("Headers"));
		
		
		//Post Request
//		String resourcePath = "https://reqres.in/api/users";
//		String JWTtoken = null;
//		String Requestbody = null;
//		Response res = given().log().all().header("Authorization", "Bearer " + JWTtoken).body(Requestbody).post(resourcePath);
//		res.prettyPrint()
	}

}
