package com.test.snack.bar.controller;

import com.test.snack.bar.model.Snack;
import com.test.snack.bar.service.SnackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<Snack> getSnackById( Long id) {
        try {
            Snack snack = service.getById(id);
            return new ResponseEntity<Snack>(snack, HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Snack>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void saveSnack( Snack snack) {
        service.saveSnack(snack);
    }

    @Override
    public ResponseEntity<?> updateSnacks( Long id
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
    public void deleteSnack(Long id) {
        service.deleteSnackById(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
