package businessLogic;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import pojo.Jwt;
import utilities.ApiReader;

public class RequestResponse {

	private String jwtToken = null;
	public String body = null;
	public ApiReader getResponse = new ApiReader();
	public Response resp = null;

	public RequestResponse(String ResourcePath, String UserName, String Password, String ClientID, String ClientSecret) {

		Jwt jwtObj = new Jwt(UserName, Password);

		Header clientSecret = new Header("ClientSecret", ClientSecret);
		Header clientId = new Header("ClientId", ClientID);

		resp = getResponse.postRequest(ResourcePath, ((Object) jwtObj), Headers.headers(clientId, clientSecret));
		String jwt = resp.jsonPath().get("jwt_token");
		this.jwtToken = jwt;
	}
	
	public String postReq(String ResourcePath, String body) {
		resp = getResponse.postRequest(ResourcePath, body, "Authorization", "Bearer " + jwtToken);
		return resp.asString();
	}
	
	public String getReq(String ResourcePath, String body) {
		resp = getResponse.getRequest(ResourcePath, "Authorization", "Bearer " + jwtToken);
		return resp.asString();
	}
	
/*	
	public String getBDMPlan(String env, int planVer, String domain) {
		String ResourcePath = "SamplePath/..../.dev6../../..../".replaceAll("dev6", env) + String.valueOf(planVer).trim() + "?domain=" + domain;
		resp = getResponse.getRequest(ResourcePath, "Authorization", "Bearer " + jwtToken);
		return Env;
	}
	
	public String getBDMPlanGrpSec(String env, int planVer, String domain) {
		String ResourcePath = "SamplePath/..../.dev6../../..../".replaceAll("dev6", env) + String.valueOf(planVer).trim() + "?domain=" + domain;
		resp = getResponse.getRequest(ResourcePath, "Authorization", "Bearer " + jwtToken);
		return Env;
	}
	
	public String executeMedClaims(String Env, String req, String domains) {
		String ResourcePath = null;	
		return Env;
	}
*/
	
	
}
