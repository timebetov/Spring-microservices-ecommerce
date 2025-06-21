package com.github.timebetov.productservice.controller;

import com.github.timebetov.productservice.dto.ProductsContactInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController {

    @Autowired
    private ProductsContactInfoDto infoDto;

    @GetMapping("/contact-info")
    public ResponseEntity<ProductsContactInfoDto> getContactInfo() {

        log.info("INVOKED::PRODUCT/contact-info");
        return ResponseEntity.ok(infoDto);
    }

}
