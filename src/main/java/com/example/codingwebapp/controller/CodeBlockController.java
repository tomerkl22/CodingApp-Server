package com.example.codingwebapp.controller;
import com.example.codingwebapp.codeBlockDTO.CodeBlockDTO;
import com.example.codingwebapp.model.Client;
import com.example.codingwebapp.codeBlockDTO.ClientDTO;
import com.example.codingwebapp.service.CodeBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CodeBlockController {

    @Autowired
    private CodeBlockService codeBlockService;

    @MessageMapping("/assignRole")
    @SendTo("/topic/assignRole")
    public Client assignRole(ClientDTO clientRequest) {
        return codeBlockService.assignRole(clientRequest.getClientId());
    }

    @MessageMapping("/unAssignRole")
    public void unAssignRole(ClientDTO clientRequest){
        codeBlockService.unAssignRole(clientRequest.getClientId());
    }


    @MessageMapping("/code")
    @SendTo("/topic/code")
    public CodeBlockDTO updateCode(CodeBlockDTO codeBlockDTO) {
       return codeBlockService.updateCode(codeBlockDTO.getId(),codeBlockDTO.getTitle(),codeBlockDTO.getCode());
    }

    @MessageMapping("/submit")
    @SendTo("/topic/submit")
    public boolean codeSubmitted(CodeBlockDTO codeBlockDTO){
        return codeBlockService.checkSolution(codeBlockDTO.getId(),codeBlockDTO.getTitle(),codeBlockDTO.getCode());
    }


    @CrossOrigin(origins = "https://onlinecodingwebapp-client.onrender.com:10000")
    @GetMapping("/codeblocks")
    @ResponseBody
    public ResponseEntity<List<CodeBlockDTO>> getCodeBlocks() {
        return codeBlockService.getCodeBlocks();
    }

}
