import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import org.hamcrest.Matchers;

public class xml_req_res_handling {
	@Test(enabled=false)
	void json_req_res_handle() {
		
		String reqBody = "{\r\n"
				+ "  \"id\": 0,\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 0,\r\n"
				+ "    \"name\": \"sritam\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"sritam\",\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"string\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 0,\r\n"
				+ "      \"name\": \"string\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"status\": \"available\"\r\n"
				+ "}";
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://petstore.swagger.io")
		.basePath("/v2/pet")
		.header("accept","application/json")
		.header("Content-Type","application/json")
		.body(reqBody);
		
		Response res = reqSpec.post();
		res.prettyPrint();
		Assert.assertEquals(res.statusCode(), 200,"Code is not matching");
	}
	@Test(enabled=false)
	void xml_req_res_handle() {
	     String xmlBody= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
	     		+ "<Pet>\r\n"
	     		+ "	<id>0</id>\r\n"
	     		+ "	<Category>\r\n"
	     		+ "		<id>0</id>\r\n"
	     		+ "		<name>string</name>\r\n"
	     		+ "	</Category>\r\n"
	     		+ "	<name>SritamXml</name>\r\n"
	     		+ "	<photoUrls>\r\n"
	     		+ "		<photoUrl>SritamXml1</photoUrl>\r\n"
	     		+ "	</photoUrls>\r\n"
	     		+ "	<tags>\r\n"
	     		+ "		<Tag>\r\n"
	     		+ "			<id>0</id>\r\n"
	     		+ "			<name>string</name>\r\n"
	     		+ "		</Tag>\r\n"
	     		+ "	</tags>\r\n"
	     		+ "	<status>available</status>\r\n"
	     		+ "</Pet>";
	     
	     RequestSpecification reqSpec = RestAssured.given();
	     reqSpec.baseUri("https://petstore.swagger.io")
			.basePath("/v2/pet")
			.header("accept","application/xml")
			.header("Content-Type","application/xml")
			.body(xmlBody);
			
			Response res = reqSpec.post();
			res.prettyPrint();
			Assert.assertEquals(res.statusCode(), 200,"Code is not matching");
			
			res.then().body("Pet.name", Matchers.equalTo("SritamXml"));
	}
	
	@Test
	void xml_validation() {
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://petstore.swagger.io")
		.basePath("/v2/pet/findByStatus")
		.queryParam("status", "sold")
       	.header("accept","application/xml");
		
		Response res = reqSpec.get();
		res.prettyPrint();
		Assert.assertEquals(res.statusCode(), 200,"Code is not matching");
		//Approach 1 
		res.then().body("pets.Pet[1].category.id", Matchers.equalTo("0"));
		
		//Approach2
		
		XmlPath xmlPath = new XmlPath(res.asString());
		String id = xmlPath.get("pets.Pet[0].id");
		System.out.println(id);
		Assert.assertEquals(id, "9223372036854591716","id are not matching");
		
		List<String> pets = xmlPath.getList("pets.Pet");
		System.out.println("Total pets : "+ pets.size());
		
		
		List<String> ids=xmlPath.getList("pets.Pet.category.id");
		System.out.println(ids.size());
		
		for(String get_id : ids){
			System.out.println(id);
		}
		
		
	

}
}
