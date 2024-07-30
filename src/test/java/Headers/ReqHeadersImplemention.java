package Headers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ReqHeadersImplemention {

	@Test(enabled=false)
	public void simpleHeader() {
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://reqres.in/api").basePath("/users")
		.header("Content-Type","application/json")
		.header("sample","header");
		reqSpec.log().headers();
		
		Response res = reqSpec.get("/2");
		
		res.prettyPrint();
		Assert.assertEquals(res.statusCode(), 200, "The status code is not matching");
		
	}
	@Test(enabled=false)
	public void hadersMethod() {
		
		Map<String , String> map = new HashMap<String, String>();
		map.put("Content-Type", "application/json");
		map.put("new header", "sample");
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://reqres.in/api").basePath("/users")
		.headers(map).log().headers();
		 
		Response res = reqSpec.get("/2");
		res.prettyPrint();
		Assert.assertEquals(res.statusCode(), 200,"Status code is not matching");
	}
	
	@Test
	public void usingHeaderClass() {
		
		//create multiple header object with key value pairs
		Header header1 = new Header("Content-Type","application/json");
		Header header2 = new Header("haedernew","newvalue");
		
		//Need to create list of headers
		List<Header> list = new ArrayList<Header>();
		list.add(header1);
		list.add(header2);
		
		
		//Add the list of headers into the Headers object
		Headers headers = new Headers(list);
		
		//Use the headers object in the headers method
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://reqres.in/api").basePath("/users")
		.headers(headers).log().headers();
		
		Response res = reqSpec.get("/2");
		res.prettyPrint();
		Assert.assertEquals(res.statusCode(), 200, "Status code is not matching");
		
	}
}
