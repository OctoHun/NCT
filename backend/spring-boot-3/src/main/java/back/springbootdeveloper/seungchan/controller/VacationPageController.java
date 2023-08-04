package back.springbootdeveloper.seungchan.controller;

import back.springbootdeveloper.seungchan.domain.UserUtill;
import back.springbootdeveloper.seungchan.dto.request.VacationRequest;
import back.springbootdeveloper.seungchan.dto.response.BaseResponseBody;
import back.springbootdeveloper.seungchan.dto.response.VacationRequestPageResponse;
import back.springbootdeveloper.seungchan.dto.response.VacationsResponce;
import back.springbootdeveloper.seungchan.service.AttendanceService;
import back.springbootdeveloper.seungchan.service.UserUtillService;
import back.springbootdeveloper.seungchan.util.BaseResponseBodyUtiil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@ResponseBody
public class VacationPageController {
    private final UserUtillService userUtillService;
    private final AttendanceService attendanceService;

    @PostMapping("/vacations/request")
    public ResponseEntity<BaseResponseBody> applyVacation(@RequestBody VacationRequest vacationRequest) {
        Long userId = 1L;
        userUtillService.subVacationCount(userId, vacationRequest);
        attendanceService.updateVacationDate(userId, vacationRequest);

        return BaseResponseBodyUtiil.BaseResponseBodySuccess();
    }

    @GetMapping("/vacations/request")
    public ResponseEntity<VacationRequestPageResponse> findVacationRequestPage() {
        // TODO: 8/3/23
        // 토큰을 이용해 유저 id 가져오기
        Long userId = 1L;
        UserUtill userUtill = userUtillService.findUserByUserId(userId);

        VacationsResponce vacationsResponce = attendanceService.findVacations(userId);
        VacationRequestPageResponse vacationRequestPageResponse = new VacationRequestPageResponse(vacationsResponce, userUtill);
        return ResponseEntity.ok().body(vacationRequestPageResponse);
    }

    @GetMapping("/vacations")
    public ResponseEntity<VacationsResponce> findVacation() {

        // TODO Token find userId from token
        Long userId = 1L;

        VacationsResponce vacationsResponce = attendanceService.findVacations(userId);
        return ResponseEntity.ok().body(vacationsResponce);
    }
}
