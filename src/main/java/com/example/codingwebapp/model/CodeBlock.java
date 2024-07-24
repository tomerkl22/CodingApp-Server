package com.example.codingwebapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection  = "codeBlocks")
public class CodeBlock {
    @Id
    private String id;
    private String title;
    private String code;
    private String solution;

    public CodeBlock() {}

    public CodeBlock(String title, String code) {
        this.title = title;
        this.code = code;
    }

    public boolean solutionIsCorrect(String newCode){
        if (newCode.equals(this.solution)){
            return true;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
