package com.coffee.ordering.system.connectors.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEventModel {
    private String customerId;
    private String username;
    private String firstName;
    private String lastName;

}
