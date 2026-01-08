package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class BaseService {

   private final String baseUrl = System.getProperty("baseUrl",
       "https://petstore.swagger.io/v2/store");

   private final RequestSpecification requestSpecification = RestAssured.given()
       .baseUri(baseUrl)
       .contentType(ContentType.JSON);

   protected Response get(String path) {
      return requestSpecification.get(path);
   }

   protected Response get(String path, long id) {
      return requestSpecification.pathParam("orderId", id)
          .get(path + "/{orderId}");
   }

   protected Response post(Object object, String path) {
      return requestSpecification.body(object).post(path);
   }

   protected Response delete(String path, long orderId) {
      return requestSpecification
          .pathParam("orderId", orderId)
          .delete(path + "/{orderId}");
   }

   abstract String getUrl();

}
