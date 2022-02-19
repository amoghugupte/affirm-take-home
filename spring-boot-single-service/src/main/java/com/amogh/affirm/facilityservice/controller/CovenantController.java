package com.amogh.affirm.facilityservice.controller;

import com.amogh.affirm.facilityservice.service.CovenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/covenant/v1")
public class CovenantController {

    @Autowired
    private CovenantService covenantService;

    @PostMapping("load")
    public String uploadCovenants (MultipartFile file) throws IOException {
        covenantService.load(file.getInputStream());
        return "done!";
    }
}
