package com.company.service;

import com.company.dto.ProfileDTO;
import com.company.dto.RegionDTO;
import com.company.entity.ProfileEntity;
import com.company.entity.RegionEntity;
import com.company.exp.BadRequestException;
import com.company.exp.NoPermissionException;
import com.company.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    RegionRepository repository;

    public RegionDTO create(RegionDTO dto){
        Optional<RegionEntity> optional = repository.findByKey(dto.getKey());

        if (optional.isPresent()){
            throw new BadRequestException("Mazgi bu region oldindan bor");
        }

        RegionEntity entity = new RegionEntity();
        entity.setKey(dto.getKey());
        entity.setName_en(dto.getName_en());
        entity.setName_ru(dto.getName_ru());
        entity.setName_uz(dto.getName_uz());
        entity.setCreated_date(LocalDateTime.now());
        repository.save(entity);
        return dto;
    }

    public List<RegionDTO> getList() {
        Iterable<RegionEntity> all = repository.findAll();

        List<RegionDTO> customerDTOList = new LinkedList<>();
        all.forEach(customerEntity -> {
            RegionDTO customerDTO = new RegionDTO();

            customerDTO.setId(customerEntity.getId());
            customerDTO.setKey(customerEntity.getKey());
            customerDTO.setName_en(customerEntity.getName_en());
            customerDTO.setName_ru(customerEntity.getName_ru());
            customerDTO.setName_uz(customerEntity.getName_uz());
            customerDTO.setCreated_date(customerEntity.getCreated_date());
            customerDTOList.add(customerDTO);
        });

        return customerDTOList;
    }

    public RegionDTO getById(Integer id) {

        Optional<RegionEntity> optional
                = repository.findById(id);

        if (optional.isEmpty()) {
            throw new NoPermissionException("Region not found");
        }
        RegionEntity entity = optional.get();

        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        dto.setName_en(entity.getName_en());
        dto.setName_ru(entity.getName_ru());
        dto.setName_uz(entity.getName_uz());
        dto.setCreated_date(entity.getCreated_date());
        return dto;
    }

    public void update(Integer id, RegionDTO dto) {
        isValid(dto);
        RegionEntity entity = get(id);
        entity.setKey(dto.getKey());
        entity.setName_en(dto.getName_en());
        entity.setName_ru(dto.getName_ru());
        entity.setName_uz(dto.getName_uz());
        repository.save(entity);
    }


    private void isValid(RegionDTO dto) {
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
    public RegionEntity get(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new NoPermissionException("Key Not found");
        });
    }

}
