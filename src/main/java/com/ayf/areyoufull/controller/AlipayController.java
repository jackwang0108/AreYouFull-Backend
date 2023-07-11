package com.ayf.areyoufull.controller;

import com.ayf.areyoufull.entity.Result;
import com.ayf.areyoufull.utils.Constant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlipayController {
    public static final String RETURL = Constant.SERVER_URL + "/ayf/retUrl";
    @PostMapping(RETURL)
    public Result handleReturn(@RequestBody Integer integer){
        return Result.ok();
    }
}
