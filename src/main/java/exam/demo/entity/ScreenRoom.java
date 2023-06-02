package exam.demo.entity;


import exam.demo.dto.ScreenRoomDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Entity
public class ScreenRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long screenroomId;
    private Long theaterId;
    private String screenroomName;
    private int seatCount;

    public ScreenRoom(ScreenRoomDto screenRoomDto){
        this.theaterId = screenRoomDto.getTheaterId();
        this.screenroomName = screenRoomDto.getScreenroomName();
        this.seatCount = screenRoomDto.getSeatCount();
    }

    public void updateScreenroom(ScreenRoomDto screenRoomDto){
        this.theaterId = screenRoomDto.getTheaterId();
        this.screenroomName = screenRoomDto.getScreenroomName();
        this.seatCount = screenRoomDto.getSeatCount();
    }


}
