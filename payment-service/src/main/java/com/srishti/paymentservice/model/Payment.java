package com.srishti.paymentservice.model;

import com.srishti.paymentservice.model.CreditCardInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "payments")
public class Payment {
    @Id
    private String id;
    private Long timestamp;
    private BigDecimal amount;
    private CreditCardInfo creditCardInfo;
    private String orderId;

}
