package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.service.MerchandiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchandise")
public class MerchandiseController {
    private final MerchandiseService merchandiseService;

    @Autowired
    public MerchandiseController(MerchandiseService merchandiseService) {
        this.merchandiseService = merchandiseService;
    }
}
