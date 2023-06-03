package exam.demo.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "seat_numbers")
    private String seatNumbers;

    @Column(name = "theater_id")
    private Long theaterId;

    @Column(name = "screen_id")
    private Long screenId;

    @Column(name = "user_id")
    private Long userId;


}
