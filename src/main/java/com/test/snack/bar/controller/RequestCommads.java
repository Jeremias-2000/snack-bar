package com.test.snack.bar.controller;

import com.test.snack.bar.model.Snack;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface RequestCommads {

    @GetMapping("/snacks")
    List<Snack> getSnacks();


    @GetMapping(path = "/search/{id}")
    ResponseEntity<Snack> getSnackById(@PathVariable("id") Long id);

    @PostMapping("/save")
    void saveSnack(@RequestBody @Valid  Snack snack);

    @PutMapping("/update/{id}")
    ResponseEntity<?> updateSnacks (@PathVariable("id") Long id
            ,@RequestBody Snack snack);


    @DeleteMapping(path = "/delete/{id}")
    void deleteSnack(@PathVariable("id") Long id);

}
