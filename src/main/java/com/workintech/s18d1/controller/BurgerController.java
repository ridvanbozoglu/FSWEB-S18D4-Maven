package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.exceptions.BurgerException;
import com.workintech.s18d1.util.BurgerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/burger")
public class BurgerController {

    private BurgerDao burgerDao;

    @Autowired
    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @GetMapping
    public List<Burger> getAllBurgers(){
        return burgerDao.findAll();
    }

    @GetMapping("/{id}")
    public Burger findById(@PathVariable long id) {
        if (!BurgerValidation.isIdValid(id)) {
            throw new BurgerException("ID is not valid", HttpStatus.BAD_REQUEST);
        }
        Burger burger = burgerDao.findById(id);
        if (burger == null) {
            throw new BurgerException("Burger with id " + id + " cannot be found", HttpStatus.NOT_FOUND);
        }
        return burger;
    }


    @PostMapping
    public Burger saveBurger(@RequestBody Burger burger){
        if(!BurgerValidation.burgerIsValid(burger)){
            throw new BurgerException("Burger is not valid", HttpStatus.BAD_REQUEST);
        }
        return burgerDao.save(burger);
    }

    @PutMapping
    public Burger update(@RequestBody Burger burger){
        Burger burger1 = burgerDao.update(burger);
        if(burger1 == null){
            throw new BurgerException("Burger cannot be found", HttpStatus.NOT_FOUND);
        }
        return burger1;
    }

    @PutMapping("/{id}")
    public Burger update(@PathVariable long id,@RequestBody Burger burger){
        if (!BurgerValidation.isIdValid(id)) {
            throw new BurgerException("ID is not valid", HttpStatus.BAD_REQUEST);
        }
        Burger burger1 = burgerDao.update(burger);
        if(burger1 == null){
            throw new BurgerException("Burger with id " + id + " cannot be found", HttpStatus.NOT_FOUND);
        }
        return burger1;
    }

    @DeleteMapping("/{id}")
    public Burger deleteBurger(@PathVariable long id){
        if (!BurgerValidation.isIdValid(id)) {
            throw new BurgerException("ID is not valid", HttpStatus.BAD_REQUEST);
        }
        Burger burger = burgerDao.remove(id);
        if(burger == null){
            throw new BurgerException("Burger with id " + id + " cannot be found", HttpStatus.NOT_FOUND);
        }
        return burgerDao.remove(id);
    }

    @GetMapping("/price/{price}")
    public List<Burger> findByPrice(@PathVariable double price){
        if(!BurgerValidation.isPriceValid(price)){
            throw new BurgerException("Price must be greater than 0", HttpStatus.BAD_REQUEST);
        }
        return burgerDao.findByPrice(price);
    }

    @GetMapping("/breadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable BreadType breadType){
        return burgerDao.findByBreadType(breadType);
    }

    @GetMapping("/content/{content}")
    public List<Burger> findByContent(@PathVariable String content){
        if(!BurgerValidation.isContentValid(content)){
            throw new BurgerException("Content can not be null", HttpStatus.BAD_REQUEST);
        }
        return burgerDao.findByContent(content);
    }
}
