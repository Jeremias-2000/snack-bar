package com.test.snack.bar.service;

import com.test.snack.bar.model.Snack;
import com.test.snack.bar.repository.SnackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnackService {
    @Autowired
    private SnackRepository repository;

    public List<Snack> getAll(){
        return repository.findAll();
    }

    public Snack getById(Long id){
        return repository.findById(id).orElse(null);
    }

    public void saveSnack(Snack snack){
        repository.save(snack);
    }

    public void update(Long id, Snack update){
        Snack exist = repository.findById(id).orElse(update);
        exist.setName(update.getName());
        exist.setQuantity(exist.getQuantity());
        exist.setPrice(exist.getPrice());
        repository.save(exist);
    }
    public void deleteSnackById(Long id){
        repository.deleteById(id);
    }
}
