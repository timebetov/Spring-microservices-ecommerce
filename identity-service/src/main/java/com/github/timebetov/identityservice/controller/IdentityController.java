package com.github.timebetov.identityservice.controller;

import com.github.timebetov.identityservice.dto.IdentityContactInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class IdentityController {

    @Autowired
    private IdentityContactInfoDto infoDto;

    @GetMapping("/contact-info")
    public ResponseEntity<IdentityContactInfoDto> getContactInfo() {

        return ResponseEntity.ok(infoDto);
    }
}
