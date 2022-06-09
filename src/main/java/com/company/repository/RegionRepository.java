package com.company.repository;

import com.company.entity.RegionEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface RegionRepository extends CrudRepository<RegionEntity, Integer> {
    Optional<RegionEntity> findByKey (String key);

    @Transactional
    @Modifying
    @Query("Update RegionEntity set key=:key, name_en=:name_en, name_ru=:name_ru, name_uz=:name_uz where id =:id")
    int update_profile(@Param("key") String key, @Param("name_en") String name_en, @Param("name_ru") String name_ru, @Param("name_uz") String name_uz, @Param("id") Integer id);
}
