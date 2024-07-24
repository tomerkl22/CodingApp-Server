package com.example.codingwebapp.repository;
import com.example.codingwebapp.model.CodeBlock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CodeBlockRepository extends MongoRepository<CodeBlock, String> {}
