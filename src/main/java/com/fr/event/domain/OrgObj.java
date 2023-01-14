package com.fr.event.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@Data
@ToString
public class OrgObj {

    public Date date;
    public Integer capacity;
    public String outdoors;
    public int age_limit;
    public String property;
}
