package com.completeTimeFlow.core.server.controller;

import com.completeTimeFlow.core.repository.ClockingRepository;
import com.completeTimeFlow.core.repository.PersonRepository;
import com.completeTimeFlow.core.server.domain.Clocking;
import com.completeTimeFlow.core.server.domain.Person;
import com.completeTimeFlow.core.server.service.ClockingService;
import com.completeTimeFlow.core.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

///@CrossOrigin(origins = "*")
@RestController
public class ClockingController {
    @Autowired
    ClockingRepository clockingRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    ClockingService clockingService;

    @RequestMapping("/testCreateTablesAndSave")
    @GetMapping
    public ResponseEntity<String> creating() {
        Person person = new Person();

        person.setName("Salomão");
        person.setRegisterDate(TimeUtil.getConvertedDate(new Date()));
        personRepository.save(person);

        Clocking clocking = new Clocking();
        clocking.setGrade(9.9);
        clocking.setClockingStartDate(new Date());
        clocking.setPerson(person);
        clocking.setSubject("Programação deep");
        clockingService.saveDateFormatted(clocking);
        return new ResponseEntity<String>("Saved again!!!", HttpStatus.OK);
    }

    @RequestMapping("/sayHello")
    @GetMapping
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<String>("Hello!!!", HttpStatus.OK);
    }

    @RequestMapping("/getClocks")
    @GetMapping
    public ResponseEntity<List<Clocking>> getClocks() {
        List<Clocking> listOfClocks = new ArrayList<>();
        listOfClocks = clockingRepository.getEveryBody();
        return new ResponseEntity<>(listOfClocks, HttpStatus.OK);
    }

}