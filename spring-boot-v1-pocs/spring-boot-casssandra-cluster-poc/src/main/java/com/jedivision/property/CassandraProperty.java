package com.jedivision.property;

import lombok.Data;

@Data
public class CassandraProperty {
    private String contactpoints;
    private int port;
    private String keyspace;
}
