package com.test.snack.bar.controller;

import com.test.snack.bar.model.Snack;
import com.test.snack.bar.service.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RequestMapping("/test")
@RestController
public class SnackController implements RequestCommads{

    @Autowired
    private SnackService service;

    @Override
    public List<Snack> getSnacks() {
        return service.getAll();
    }

    @Override
    public ResponseEntity<Snack> getSnackById(@PathVariable("id") String id) {
        try {
            Snack snack = service.getById(id);
            return new ResponseEntity<Snack>(snack, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Snack>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void saveSnack(@RequestBody Snack snack) {
        service.saveSnack(snack);
    }

    @Override
    public ResponseEntity<?> updateSnacks(@PathVariable("id") String id
            ,@RequestBody Snack snack) {
        try {
            Snack existsSnack = service.getById(id);
            service.saveSnack(snack);

            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public void deleteSnack(String id) {
        service.deleteSnackById(id);
    }
}
