package com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Persistence.Repository;

import com.jhuguet.ThymeleafCRUDApplication.Infrastructure.Persistence.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
