package com.company.controller;

import com.company.dto.ProfileDTO;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.service.ProfileService;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody ProfileDTO dto) {
        profileService.create(dto);
        ResponseEntity<String> build = ResponseEntity.ok().body("Successfully created");
        return build;
    }

    @GetMapping("/listAll")
    public List<ProfileDTO> get_post_list() {
        return profileService.getList();
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        ProfileDTO profileDTO = profileService.getById(id);
        return ResponseEntity.ok().body(profileDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody ProfileDTO dto){
        profileService.update(id, dto);
        ResponseEntity<Object> build = ResponseEntity.ok().body("Successfully updated");
        return build;
    }
    @PutMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        profileService.delete(id);
        ResponseEntity<Object> build = ResponseEntity.ok().body("Successfully deleted");
        return build;
    }

}
