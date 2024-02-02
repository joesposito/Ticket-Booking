package com.trainer.ticketbooking.dao;
import com.trainer.ticketbooking.entity.LocalUser;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface LocalUserDao extends CrudRepository<LocalUser, Long> {
    Optional<LocalUser> findByUsernameIgnoreCase(String username);
    Optional<LocalUser> findByUserID(long userID);
}
