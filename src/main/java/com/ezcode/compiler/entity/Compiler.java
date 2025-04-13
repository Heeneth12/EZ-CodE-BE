package com.ezcode.compiler.entity;


public class Compiler {

    private String language;
    private String code;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Compiler(String language, String code) {
        this.language = language;
        this.code = code;
    }
}
