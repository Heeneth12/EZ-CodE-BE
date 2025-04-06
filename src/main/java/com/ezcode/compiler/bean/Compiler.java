package com.ezcode.compiler.bean;

import lombok.Data;

import java.util.UUID;

@Data
public class Compiler {

    private UUID uniqueId;
    private String code;
    private String language;
}
