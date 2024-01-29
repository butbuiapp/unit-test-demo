package miu.waa.productapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData {
    private boolean success;
    private String message;
    private Object data;
}
