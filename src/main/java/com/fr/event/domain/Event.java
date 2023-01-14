package com.fr.event.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class Event {

    public Integer eventId;
    public String name;
    public String status;
    public OrgObj orgObj;
    public AddressObj addressObj;
}
