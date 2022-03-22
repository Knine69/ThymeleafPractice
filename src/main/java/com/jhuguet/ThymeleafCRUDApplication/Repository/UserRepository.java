package com.jhuguet.ThymeleafCRUDApplication.Repository;

import com.jhuguet.ThymeleafCRUDApplication.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
