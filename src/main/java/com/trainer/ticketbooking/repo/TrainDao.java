package com.trainer.ticketbooking.repo;
import com.trainer.ticketbooking.entity.Train;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface TrainDao extends CrudRepository<Train, Long> {
    Optional<Train> findByTrainID(Long trainID);
}
