package com.ezcode.compiler.controller;

import com.ezcode.compiler.bean.Compiler;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compiler")
public class CompilerController {

    @PostMapping
    public void codeOutputGenerator(@RequestBody Compiler requestBody){

    }
}
