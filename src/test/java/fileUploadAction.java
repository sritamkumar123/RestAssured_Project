import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class fileUploadAction {
	
	static String authToken= "eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MTAxLCJleHAiOjE3MjIyNDgxMDUsInRva2VuX3R5cGUiOiJsb2dpbiJ9.H_XwD8U_Cg8YlIH5X2TdCsC9zWbE9vhqgypwfAmfTi-SD3_jJtt3_Wp1WQHCX06I0iUc7FKhzyxC3Yig9bOjpw";

	@Test(enabled=false)
	public void fileUploadActions() {
		
		//create file object
		File filepath = new File ("C:\\Users\\SRITAMLINKAN\\Downloads\\Sritam_3Years_Resume.pdf");
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("http://httpbin.org").basePath("/post")
		.multiPart("file",filepath)
		.contentType(ContentType.MULTIPART); // interview qn asked >>> content type is multipart/formdata
		
		Response res = reqSpec.post();
		
		res.prettyPrint();
		
		Assert.assertEquals(res.statusCode(), 200,"Status code is not matching");
		
	}
	
	@Test(enabled=false)
	
	public void uploadFileReal() {
		File filepath = new File ("C:\\Users\\SRITAMLINKAN\\Downloads\\sample_jpg_file.jpg");

		RequestSpecification reqSpec = RestAssured.given();
		
		reqSpec.baseUri("https://pet-cure-api-qa.green-apex.com").basePath("/spree/api/update_image")
		.queryParam("store_name", "pet cure zone") // query param is most important to separate from the uri of a request
		.multiPart("profile_image",filepath)
		.contentType(ContentType.MULTIPART)
		.header("Authorization","Bearer "+ authToken)
		.header("store_name","pet cure zone");
		
		Response res = reqSpec.put();
		
		res.prettyPrint();
		Assert.assertEquals(res.statusCode(), 200,"Code is not matching");
		
	}
	@Test
	void uploadFileAgain() {
		File newFile = new File("C:\\Users\\SRITAMLINKAN\\Downloads\\pexels-mike-468229-1181772.jpg");
		
		RequestSpecification reqSpec = RestAssured.given();
		reqSpec.baseUri("https://pet-cure-api-qa.green-apex.com").basePath("/spree/api/update_image")
		.queryParam("store_name", "pet cure zone") // must add query param with the url
		.auth().oauth2(authToken)
		.multiPart("profile_image", newFile)
		//.header("Content-Type","multipart/form-data"); // most importnat interview question
		.contentType("multipart/form-data");
		
		Response res = reqSpec.put();
		
		res.prettyPeek(); // to print header with body
		Assert.assertEquals(res.statusCode(), 200, "Status is not as expected");
		
	}
	
}
