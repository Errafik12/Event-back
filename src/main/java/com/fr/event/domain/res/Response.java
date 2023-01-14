package com.fr.event.domain.res;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class Response {

    private Integer outMsgCode;
    private String outMsgDesc;
}
