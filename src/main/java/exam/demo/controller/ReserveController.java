package exam.demo.controller;

import exam.demo.entity.ScreenRoom;
import exam.demo.entity.Schedule;
import exam.demo.entity.Theater;
import exam.demo.service.ScreenRoomService;
import exam.demo.service.ScheduleService;
import exam.demo.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
public class ReserveController {

    private final TheaterService theaterService;
    private final ScreenRoomService screenRoomService;
    private final ScheduleService scheduleService;


    @GetMapping("/reserve/{movieId}")
    public String showReservationPage(@PathVariable Long movieId, Model model) {
        List<Theater> movieTheaters = theaterService.getTheatersByMovieId(movieId);

        model.addAttribute("movieId", movieId);
        model.addAttribute("movieTheaters", movieTheaters);

        return "reserve";
    }

    @GetMapping("/theaters/{theaterId}/screens")
    @ResponseBody
    public List<ScreenRoom> getScreensByTheaterId(@PathVariable Long theaterId) {
        return screenRoomService.getScreensByTheater(theaterId);
    }

    @GetMapping("/screens/{screenId}/schedules")
    @ResponseBody
    public List<Schedule> getSchedulesByScreenId(@PathVariable Long screenId) {
        return scheduleService.getSchedulesByScreenRoom(screenId);
    }

    @GetMapping("/schedules/{scheduleId}/seatstatus")
    @ResponseBody
    public List<Boolean> getSeatStatusByScheduleId(@PathVariable Long scheduleId) {
        return scheduleService.getSeatStatusByScheduleId(scheduleId);
    }


}
