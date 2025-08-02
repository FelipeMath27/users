package com.pragma.users.infrastructure.output.jpa.repository;

import com.pragma.users.infrastructure.output.jpa.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IRolRepository extends JpaRepository<RolEntity, Long> {
    @Query("SELECT r FROM RolEntity r WHERE UPPER(r.nameRol) = UPPER(:nameRol)")
    Optional<RolEntity> findByNameRol(@Param("nameRol")String nameRol);

    Optional<RolEntity> findByIdRol(@Param("idRol") Long idRol);
} 