package com.company.controller;

import com.company.dto.CategoryDTO;
import com.company.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article_type")
public class ArticleTypeController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CategoryDTO dto) {
        categoryService.create(dto);
        ResponseEntity<String> build = ResponseEntity.ok().body("Successfully created");
        return build;
    }

    @GetMapping("/listAll")
    public List<CategoryDTO> get_post_list() {
        return categoryService.getList();
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        CategoryDTO categoryDTO = categoryService.getById(id);
        return ResponseEntity.ok().body(categoryDTO);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CategoryDTO dto){
        categoryService.update(id, dto);
        ResponseEntity<Object> build = ResponseEntity.ok().body("Successfully updated");
        return build;
    }

}
