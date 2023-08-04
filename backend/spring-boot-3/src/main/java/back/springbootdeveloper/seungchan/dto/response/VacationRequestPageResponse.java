package back.springbootdeveloper.seungchan.dto.response;

import back.springbootdeveloper.seungchan.domain.UserUtill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VacationRequestPageResponse extends VacationsResponce {
    private int cntVacation;

    public VacationRequestPageResponse(VacationsResponce vacationsResponce, UserUtill userUtill) {
        super(vacationsResponce.getAbsences(), vacationsResponce.getBeforeVacationDate(), vacationsResponce.getPreVacationDate());
        this.cntVacation = userUtill.getCntVacation();
    }
}
