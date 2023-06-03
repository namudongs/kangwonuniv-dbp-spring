package exam.demo.repository;

import exam.demo.entity.ScreenRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreenRoomRepository extends JpaRepository<ScreenRoom, Long> {
    List<ScreenRoom> findByTheaterId(Long theaterId);
}
