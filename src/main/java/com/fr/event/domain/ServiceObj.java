package com.fr.event.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class ServiceObj {

    public String title;
    public Integer eventId;
    public String description;
    public Integer percentage;
}
