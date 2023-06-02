package exam.demo.entity;

import exam.demo.dto.ScheduleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long scheduleId;
    private Long screenRoomId;
    private Long movieId;
    private String startTime;
    private String endTime;
    @ElementCollection
    private List<Boolean> seatStatus;

    public void initSeatStatus(ScreenRoom screenRoom) {
        int seatCount = screenRoom.getSeatCount();
        seatStatus = new ArrayList<>(Collections.nCopies(seatCount, false));
    }

    public Schedule(ScheduleDto scheduleDto){
        this.screenRoomId = scheduleDto.getScreenRoomId();
        this.movieId = scheduleDto.getMovieId();
        this.startTime = scheduleDto.getStartTime();
        this.endTime = scheduleDto.getEndTime();
    }

    public void updateScheduleTime(ScheduleDto scheduleDto){

        this.startTime = scheduleDto.getStartTime();
        this.endTime = scheduleDto.getEndTime();
    }




}