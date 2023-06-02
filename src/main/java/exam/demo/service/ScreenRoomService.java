package exam.demo.service;

import exam.demo.dto.ScreenRoomDto;
import exam.demo.entity.ScreenRoom;
import exam.demo.repository.ScreenRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScreenRoomService {
    private final ScreenRoomRepository screenRoomRepository;



    public List<ScreenRoom> getAllScreenRooms() {
        return screenRoomRepository.findAll();
    }

    public ScreenRoom getScreenRoomById(Long screenRoomId) {
        return screenRoomRepository.findById(screenRoomId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Screen Room ID: " + screenRoomId));
    }

    public ScreenRoom createScreenRoom(ScreenRoom screenRoom) {
        return screenRoomRepository.save(screenRoom);
    }

    public void updateScreenRoom(ScreenRoom screenRoom, ScreenRoomDto screenRoomDto) {
        screenRoom.updateScreenroom(screenRoomDto);
        screenRoomRepository.save(screenRoom);
    }

    public void deleteScreenRoom(ScreenRoom screenRoom) {
        screenRoomRepository.delete(screenRoom);
    }
}
