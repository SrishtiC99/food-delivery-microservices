package com.srishti.deliveryservice.controller;

import com.srishti.deliveryservice.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/{orderId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String deliverOrder(@PathVariable String orderId) {
        return deliveryService.deliverOrder(orderId);
    }

    public void callCustomer() {
        deliveryService.callCustomer();
    }
}
