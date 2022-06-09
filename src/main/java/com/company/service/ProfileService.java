package com.company.service;

import com.company.dto.ProfileDTO;
import com.company.entity.ProfileEntity;
import com.company.entity.RegionEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exp.BadRequestException;
import com.company.exp.InformationNotFound;
import com.company.exp.NoPermissionException;
import com.company.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    public ProfileDTO create(ProfileDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            throw new InformationNotFound("Mazgi bunaqa login bor");
        }
        if (!dto.getEmail().endsWith("@gmail.com")) {
            throw new BadRequestException("Mazgi email yoki parol xato");
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRole(ProfileRole.USER);
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setCreated_date(LocalDateTime.now());
        entity.setVisible(Boolean.TRUE);

        profileRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<ProfileDTO> getList() {
        Iterable<ProfileEntity> all = profileRepository.findAll();

        List<ProfileDTO> customerDTOList = new LinkedList<>();
        all.forEach(customerEntity -> {
            ProfileDTO customerDTO = new ProfileDTO();

            customerDTO.setName(customerEntity.getName());
            customerDTO.setSurname(customerEntity.getSurname());
            customerDTO.setEmail(customerEntity.getEmail());
            customerDTO.setPassword("Senga password ko'rish mumkin emas!");
            customerDTO.setStatus(customerEntity.getStatus());
            customerDTO.setVisible(customerEntity.getVisible());
            customerDTO.setRole(customerEntity.getRole());
            customerDTO.setCreated_date(customerEntity.getCreated_date());
            customerDTO.setJwt_token("Bu ham senga mumkin emas");
            customerDTOList.add(customerDTO);
        });

        return customerDTOList;
    }

    public ProfileDTO getById(Integer id) {

        Optional<ProfileEntity> optional
                = profileRepository.findById(id);

        if (optional.isEmpty()) {
            throw new NoPermissionException("Profile not fount");
        }
        ProfileEntity entity = optional.get();

        ProfileDTO dto = new ProfileDTO();
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPassword("Senga password ko'rish mumkin emas!");
        dto.setStatus(entity.getStatus());
        dto.setVisible(entity.getVisible());
        dto.setRole(entity.getRole());
        dto.setCreated_date(entity.getCreated_date());
        dto.setJwt_token("Bu ham senga mumkin emas");
        return dto;
    }

    public void update(Integer id, ProfileDTO dto) {
        isValid(dto);

        ProfileEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        profileRepository.save(entity);
    }

    public void delete(Integer id) {
        ProfileEntity entity = get(id);
        profileRepository.save(entity);
    }

    private void isValid(ProfileDTO dto) {
        if (dto.getName() == null || dto.getName().length() <= 2) {
            throw new BadRequestException("Name  xato.");
        }
        if (dto.getSurname() == null || dto.getSurname().length() <= 3) {
            throw new BadRequestException("Surname required.");
        }
    }

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> {
            throw new NoPermissionException("Profile Not found");
        });
    }


}
