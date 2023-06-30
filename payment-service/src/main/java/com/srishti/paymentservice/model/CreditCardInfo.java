package com.srishti.paymentservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class CreditCardInfo {
    private String firstName;
    private String lastName;
    private int expiredMonth;
    private int expiredYear;
    private int securityCode;
}
