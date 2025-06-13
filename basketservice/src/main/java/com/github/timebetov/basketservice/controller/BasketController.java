package com.github.timebetov.basketservice.controller;

import com.github.timebetov.basketservice.dto.BasketContactInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/basket", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BasketController {

    @Autowired
    private BasketContactInfoDto infoDto;

    @GetMapping("/contact-info")
    public ResponseEntity<BasketContactInfoDto> getContactInfo() {

        return ResponseEntity.ok(infoDto);
    }
}
