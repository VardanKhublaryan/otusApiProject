import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.InventoryService;

public class InventoryTest {

   @Test
   @DisplayName("Check inventory response: status 200 and all fields are integers")
   void validateInventorySchema() {
      new InventoryService()
          .getInventory()
          .then()
          .statusCode(200)
          .body(
              matchesJsonSchemaInClasspath(
                  "schemas/inventory-schema.json"
              )
          );
   }

}
