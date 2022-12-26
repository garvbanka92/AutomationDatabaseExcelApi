package utilities;

import static io.restassured.RestAssured.given;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class ApiReader {

	public Response res = null;
	public String requestbody = null;


	public Response getRequest(String resourcePath) {
		res = given().get(resourcePath);
		return res;
	}

	public Response getRequest(String resourcePath, Headers headersParams) {
		res = given().headers(headersParams).get(resourcePath);
		return res;
	}

	public Response getRequest(String resourcePath, Header... header) {
		Headers headersParams = new Headers();
		for (Header h : header) {
			headersParams = Headers.headers(h);
		}

		res = given().headers(headersParams).get(resourcePath);
		return res;
	}

	public Response getRequest(String resourcePath, String headerParamName, String headerParamValue) {
		res = given().header(headerParamName, headerParamValue).get(resourcePath);
		return res;
	}

	public Response postRequest(String resourcePath, String body, String headerParamName, String headerParamValue) {
		res = given().body(body).header(headerParamName, headerParamValue).post(resourcePath);
		return res;
	}

	public Response postRequest(String resourcePath, Object body, Headers headersParams) {
		res = given().body(body).headers(headersParams).post(resourcePath);
		return res;
	}

}