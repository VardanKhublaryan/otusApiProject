package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderRequest {

   private long id;
   private long petId;
   private int quantity;
   private String shipDate;
   private String status;
   private boolean complete;

   public static OrderRequest buildOrderRequest(
       long id,
       long petId,
       int quantity,
       String shipDate,
       String status,
       boolean complete
   ) {
      return OrderRequest.builder()
          .id(id)
          .petId(petId)
          .quantity(quantity)
          .shipDate(shipDate)
          .status(status)
          .complete(complete)
          .build();
   }
}
