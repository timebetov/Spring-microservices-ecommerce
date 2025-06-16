package com.github.timebetov.ordersservice.controller;

import com.github.timebetov.ordersservice.dto.OrderContactInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class OrdersController {

    @Autowired
    private OrderContactInfoDto infoDto;

    @GetMapping("/contact-info")
    public ResponseEntity<OrderContactInfoDto> getContactInfo() {

        return ResponseEntity.ok(infoDto);
    }
}
