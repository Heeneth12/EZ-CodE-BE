package com.ezcode.compiler.controller;


import com.ezcode.compiler.entity.Compiler;
import com.ezcode.compiler.service.CompilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/compiler")
@Controller
public class CompilerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompilerController.class);


    @Autowired
    private CompilerService compilerService;

    @PostMapping("/run")
    public String runCode(@RequestBody Compiler compiler) {
        LOGGER.info("Running code for language: {}", compiler.getLanguage());
        return compilerService.runCode(compiler);
    }


}
