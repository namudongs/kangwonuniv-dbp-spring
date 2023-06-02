package exam.demo.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleDto {
    private Long screenRoomId;
    private Long movieId;
    private String startTime;
    private String endTime;
}
