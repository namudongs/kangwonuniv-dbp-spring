package exam.demo.repository;

import exam.demo.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByScreenRoomId(Long screenRoomId);
}
