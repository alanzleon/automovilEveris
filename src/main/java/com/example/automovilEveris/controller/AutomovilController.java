package com.example.automovilEveris.controller;

import com.example.automovilEveris.entity.Automovil;
import com.example.automovilEveris.service.AutomovilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/cliente")
@CrossOrigin(value = {})
public class AutomovilController {

    @Autowired
    private AutomovilService service;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.service.findAutomoviles());
    }
    @PostMapping("/addAutomovil")
    public Automovil addAutomovil (@RequestBody Automovil auto){
        this.service.saveAutomovil(auto);
        return auto;
    }
    @RequestMapping(value = "/actualizarAuto/{id}", method = RequestMethod.PUT)
    public Automovil actualizar(@RequestBody Automovil automovil, @PathVariable(value = "id") String id){
        this.service.updateAuto(automovil,id);
        return automovil;
    }



}
