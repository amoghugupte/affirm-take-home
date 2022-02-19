package com.amogh.affirm.facilityservice.controller;

import com.amogh.affirm.facilityservice.model.Bank;
import com.amogh.affirm.facilityservice.model.Facility;
import com.amogh.affirm.facilityservice.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/facility/v1")
public class FacilityController {
    @Autowired
    private FacilityService facilityService;

    @GetMapping ("current")
    public List<Facility> getCurrentFacilities () {
        return facilityService.getCurrentFacilities();
    }


    @PostMapping("save")
    public String save (Facility facility) {
        facilityService.save(facility);
        return "Saved";
    }

    @PostMapping ("load")
    public String uploadFacilities (MultipartFile file) throws IOException {
        facilityService.load(file.getInputStream());
        return "done!";
    }
}
