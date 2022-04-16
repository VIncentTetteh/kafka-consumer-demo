package com.example.KafkaConsumerDemo.service;

import com.example.KafkaConsumerDemo.repository.EmployeeRepository;
import com.example.KafkaConsumerDemo.Entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    ObjectMapper objectMapper = new ObjectMapper();
    private EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "createEmployee", groupId = "employees")
    void listener(String data) throws JsonProcessingException {
        Employee employee = objectMapper.readValue(data, Employee.class);
        System.out.println(data);
        repository.save(employee);
        System.out.println(employee.getAge());
    }
}
