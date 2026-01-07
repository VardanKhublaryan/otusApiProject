package services;

import io.restassured.response.Response;

public class InventoryService extends BaseService {

   public Response getInventory() {
      return get(getUrl());
   }

   public String getUrl() {
      return "/inventory";
   }
}
