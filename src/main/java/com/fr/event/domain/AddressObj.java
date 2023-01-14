package com.fr.event.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class AddressObj {

    public Integer number;
    public String street;
    public String postal_code;
    public String city;
    public String country;
}
