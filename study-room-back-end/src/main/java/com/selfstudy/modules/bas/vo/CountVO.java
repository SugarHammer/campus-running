package com.selfstudy.modules.bas.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class CountVO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long count;

    private String roomName;

    private String floor;

}
