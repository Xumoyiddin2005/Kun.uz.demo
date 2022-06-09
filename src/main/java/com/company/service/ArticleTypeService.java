package com.company.service;

import com.company.dto.ArticleTypeDTO;
import com.company.entity.ArticleTypeEntity;
import com.company.exp.BadRequestException;
import com.company.exp.NoPermissionException;
import com.company.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {
    @Autowired
    ArticleTypeRepository repository;

    public ArticleTypeDTO create(ArticleTypeDTO dto) {
        Optional<ArticleTypeEntity> optional =
                repository.findByKey(dto.getKey());

        if (optional.isPresent()) {
            throw new BadRequestException("Mazgi bu category oldindan bor");
        }

        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setKey(dto.getKey());
        entity.setName_en(dto.getName_en());
        entity.setName_ru(dto.getName_ru());
        entity.setName_uz(dto.getName_uz());
        entity.setCreated_date(LocalDateTime.now());
        repository.save(entity);
        return dto;
    }

    public List<ArticleTypeDTO> getList() {
        Iterable<ArticleTypeEntity> all = repository.findAll();

        List<ArticleTypeDTO> customerDTOList = new LinkedList<>();
        all.forEach(categoryEntity -> {
            ArticleTypeDTO customerDTO = new ArticleTypeDTO();

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

    public ArticleTypeDTO getById(Integer id) {

        Optional<ArticleTypeEntity> optional
                = repository.findById(id);

        if (optional.isEmpty()) {
            throw new NoPermissionException("Region not found");
        }
        ArticleTypeEntity entity = optional.get();

        ArticleTypeDTO dto = new ArticleTypeDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setName_en(entity.getName_en());
        dto.setName_ru(entity.getName_ru());
        dto.setName_uz(entity.getName_uz());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }

    public void update(Integer id, ArticleTypeDTO dto) {
        isValid(dto);
        ArticleTypeEntity entity = get(id);
        entity.setKey(dto.getKey());
        entity.setName_en(dto.getName_en());
        entity.setName_ru(dto.getName_ru());
        entity.setName_uz(dto.getName_uz());
        repository.save(entity);
    }


    private void isValid(ArticleTypeDTO dto) {
        if (dto.getKey() == null || dto.getKey().length() <= 2) {
            throw new BadRequestException("Key required.");
        }
        if (dto.getName_en() == null || dto.getName_en().length() <= 2) {
            throw new BadRequestException("name_en required.");
        }
        if (dto.getName_ru() == null || dto.getName_ru().length() <= 2) {
            throw new BadRequestException("name_ru required");
        }
        if (dto.getName_uz() == null || dto.getName_uz().length() <= 2) {
            throw new BadRequestException("name_uz required");
        }
    }

    public ArticleTypeEntity get(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoPermissionException("Key Not found");
        });
    }

}
