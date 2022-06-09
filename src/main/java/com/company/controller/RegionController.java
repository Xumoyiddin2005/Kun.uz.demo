package com.company.controller;

import com.company.dto.ProfileDTO;
import com.company.dto.RegionDTO;
import com.company.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {

    @Autowired
    RegionService regionService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody RegionDTO dto) {
        regionService.create(dto);
        ResponseEntity<String> build = ResponseEntity.ok().body("Successfully created");
        return build;
    }

    @GetMapping("/listAll")
    public List<RegionDTO> get_post_list() {
        return regionService.getList();
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        RegionDTO regionDTO = regionService.getById(id);
        return ResponseEntity.ok().body(regionDTO);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody RegionDTO dto){
        regionService.update(id, dto);
        ResponseEntity<Object> build = ResponseEntity.ok().body("Successfully updated");
        return build;
    }

}
