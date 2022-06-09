package com.company.repository;

import com.company.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {

    Optional<ProfileEntity> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("Update ProfileEntity set name=:name, surname=:sur where id =:id")
    int update_profile(@Param("name") String name, @Param("sur") String surname, @Param("id") Integer id);


    @Transactional
    @Modifying
    @Query("UPDATE ProfileEntity set visible = FALSE where id = :id")
    int delete_profile(@Param("id") Integer id);
}
