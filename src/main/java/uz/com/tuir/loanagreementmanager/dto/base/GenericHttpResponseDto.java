package uz.com.tuir.loanagreementmanager.dto.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericHttpResponseDto <T>{
    private Boolean isFinish;
    private T data;
    private ErrorResultDto error;
}
