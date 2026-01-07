package services;

import dto.OrderRequest;
import io.restassured.response.Response;

public class OrderService extends BaseService{

   public Response createOrder(OrderRequest request){
      return post(request,getUrl());
   }

   public Response deleteOrder(long id){
      return delete(getUrl(),id);
   }

   public Response getOrder(long id){
      return get(getUrl(),id);
   }

   public String getUrl(){
      return "/order";
   }

}
