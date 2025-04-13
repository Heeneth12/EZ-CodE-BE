package com.ezcode.compiler.service;

import com.ezcode.compiler.entity.Compiler;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class CompilerService {

    public String runCode(Compiler compiler) {
        String language = compiler.getLanguage().toLowerCase();
        String code = compiler.getCode();
        String result;

        try {
            File tempFile;
            ProcessBuilder builder;

            switch (language) {
                case "python":
                    tempFile = File.createTempFile("code", ".py");
                    writeToFile(tempFile, code);
                    builder = new ProcessBuilder("python3", tempFile.getAbsolutePath());
                    break;

                case "java":
                    tempFile = File.createTempFile("Main", ".java");
                    writeToFile(tempFile, code);
                    File parent = tempFile.getParentFile();
                    Process compileJava = new ProcessBuilder("javac", tempFile.getAbsolutePath())
                            .directory(parent).start();
                    compileJava.waitFor();
                    builder = new ProcessBuilder("java", "-cp", parent.getAbsolutePath(), "Main");
                    break;

                case "c":
                    tempFile = File.createTempFile("code", ".c");
                    writeToFile(tempFile, code);
                    File cExe = new File(tempFile.getParent(), "c_output");
                    Process compileC = new ProcessBuilder("gcc", tempFile.getAbsolutePath(), "-o", cExe.getAbsolutePath()).start();
                    compileC.waitFor();
                    builder = new ProcessBuilder(cExe.getAbsolutePath());
                    break;

                case "cpp":
                    tempFile = File.createTempFile("code", ".cpp");
                    writeToFile(tempFile, code);
                    File cppExe = new File(tempFile.getParent(), "cpp_output");
                    Process compileCpp = new ProcessBuilder("g++", tempFile.getAbsolutePath(), "-o", cppExe.getAbsolutePath()).start();
                    compileCpp.waitFor();
                    builder = new ProcessBuilder(cppExe.getAbsolutePath());
                    break;

                case "go":
                    tempFile = File.createTempFile("code", ".go");
                    writeToFile(tempFile, code);
                    builder = new ProcessBuilder("go", "run", tempFile.getAbsolutePath());
                    break;

                default:
                    return "Unsupported language: " + language;
            }

            builder.redirectErrorStream(true);
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            process.waitFor();
            result = output.toString();

        } catch (Exception e) {
            result = "Error: " + e.getMessage();
        }

        return result;
    }

    private void writeToFile(File file, String code) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(code);
        }
    }
}

