package com.example.demo.service;

import com.example.demo.entity.Operation;

public interface OperationService {
    
    default String process(Operation operation) {

        //create a transaction of type withdraw
        persistData(operation);

        //Return reference
        return operation.getReference();
    }

    void persistData(Operation operation);

}
