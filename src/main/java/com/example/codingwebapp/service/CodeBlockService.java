package com.example.codingwebapp.service;

import com.example.codingwebapp.codeBlockDTO.CodeBlockDTO;
import com.example.codingwebapp.model.Client;
import com.example.codingwebapp.model.CodeBlock;
import com.example.codingwebapp.repository.CodeBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CodeBlockService {

    private Map<String, String> clients = new HashMap<>();
    private String mentorId = null;
    private boolean isMentorConnected = false;


    @Autowired
    private CodeBlockRepository codeBlockRepository;

    public Client assignRole(String id) {
        //check who is the mentor
        System.out.println(id);
        if (!isMentorConnected) {
            isMentorConnected = true;
            Client mentor = new Client(id, "mentor");
            mentorId = id;
            clients.put(id, "mentor");
            return mentor;
        } else if (id.equals(mentorId)) {
            Client mentor = new Client(mentorId, "mentor");
            return mentor;
        } else {
            Client student = new Client(id, "student");
            clients.put(id, "student");
            return student;
        }
    }

    public CodeBlockDTO updateCode (String id, String title, String code){
        try{
            Optional<CodeBlock> codeBlock = codeBlockRepository.findById(id);
            codeBlock.get().setCode(code);
            codeBlockRepository.save(codeBlock.get());
            System.out.println("Updating code: " + id);
            CodeBlockDTO codeBlockDTO = new CodeBlockDTO(id, title, code);
            return codeBlockDTO;
        }
        catch (Exception e){
            System.err.println("Error updating code: " + e.getMessage());
            CodeBlockDTO errorDTO = new CodeBlockDTO();
            errorDTO.setId(id);
            errorDTO.setTitle(title);
            errorDTO.setCode("Failed to update code: " + e.getMessage());
            return errorDTO;
        }
    }

    public ResponseEntity<List<CodeBlockDTO>> getCodeBlocks() {
        try {
            List<CodeBlock> codeBlocks = codeBlockRepository.findAll();
            List<CodeBlockDTO> response = codeBlocks.stream().map(codeBlock -> {
                CodeBlockDTO dto = new CodeBlockDTO();
                dto.setId(codeBlock.getId());
                dto.setCode(codeBlock.getCode());
                dto.setTitle(codeBlock.getTitle());
                return dto;
            }).collect(Collectors.toList());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("Error retrieving code blocks: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    public boolean checkSolution (String id, String title, String solutionCode){
        // Todo: check if the code is compiling - not just equal
        Optional<CodeBlock> codeBlock = codeBlockRepository.findById(id);
        if (codeBlock.get().getSolution().equals(solutionCode)){
            return true;
        }
        return false;
    }

    public void unAssignRole(String id){
        if (id.equals(mentorId)){
            clients.remove(mentorId);
            mentorId = null;
            isMentorConnected = false;
        }
    }

}
