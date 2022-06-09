package com.company.service;

import com.company.dto.CategoryDTO;
import com.company.dto.RegionDTO;
import com.company.entity.CategoryEntity;
import com.company.entity.RegionEntity;
import com.company.exp.BadRequestException;
import com.company.exp.NoPermissionException;
import com.company.repository.CategoryRepository;
import com.company.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;

    public CategoryDTO create(CategoryDTO dto){
        Optional<CategoryEntity> optional = repository.findByKey(dto.getKey());

        if (optional.isPresent()){
            throw new BadRequestException("Mazgi bu category oldindan bor");
        }

        CategoryEntity entity = new CategoryEntity();
        entity.setKey(dto.getKey());
        entity.setName_en(dto.getName_en());
        entity.setName_ru(dto.getName_ru());
        entity.setName_uz(dto.getName_uz());
        entity.setCreated_date(LocalDateTime.now());
        repository.save(entity);
        return dto;
    }

    public List<CategoryDTO> getList() {
        Iterable<CategoryEntity> all = repository.findAll();

        List<CategoryDTO> customerDTOList = new LinkedList<>();
        all.forEach(categoryEntity -> {
            CategoryDTO customerDTO = new CategoryDTO();

            customerDTO.setId(categoryEntity.getId());
            customerDTO.setKey(categoryEntity.getKey());
            customerDTO.setName_en(categoryEntity.getName_en());
            customerDTO.setName_ru(categoryEntity.getName_ru());
            customerDTO.setName_uz(categoryEntity.getName_uz());
            customerDTO.setCreated_date(categoryEntity.getCreated_date());
            customerDTOList.add(customerDTO);
        });

        return customerDTOList;
    }

    public CategoryDTO getById(Integer id) {

        Optional<CategoryEntity> optional
                = repository.findById(id);

        if (optional.isEmpty()) {
            throw new NoPermissionException("Region not found");
        }
        CategoryEntity entity = optional.get();

        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setName_en(entity.getName_en());
        dto.setName_ru(entity.getName_ru());
        dto.setName_uz(entity.getName_uz());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }

    public void update(Integer id, CategoryDTO dto) {
        isValid(dto);
        CategoryEntity entity = get(id);
        entity.setKey(dto.getKey());
        entity.setName_en(dto.getName_en());
        entity.setName_ru(dto.getName_ru());
        entity.setName_uz(dto.getName_uz());
        repository.save(entity);
    }


    private void isValid(CategoryDTO dto) {
        if (dto.getKey() == null || dto.getKey().length() <= 2) {
            throw new BadRequestException("Key required.");
        }
        if (dto.getName_en() == null || dto.getName_en().length() <= 2) {
            throw new BadRequestException("name_en required.");
        }
        if (dto.getName_ru() == null || dto.getName_ru().length() <= 2){
            throw new BadRequestException("name_ru required");
        }
        if (dto.getName_uz() == null || dto.getName_uz().length() <= 2){
            throw new BadRequestException("name_uz required");
        }
    }
    public CategoryEntity get(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoPermissionException("Key Not found");
        });
    }

}
