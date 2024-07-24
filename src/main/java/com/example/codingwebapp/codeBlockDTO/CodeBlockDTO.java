package com.example.codingwebapp.codeBlockDTO;

public class CodeBlockDTO {
    private String id;
    private String title;
    private String code;

    public CodeBlockDTO() {}

    public CodeBlockDTO(String id, String title, String code) {
        this.id = id;
        this.title = title;
        this.code = code;
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
}
