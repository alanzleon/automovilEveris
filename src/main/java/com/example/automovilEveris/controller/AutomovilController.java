package com.example.automovilEveris.controller;

import com.example.automovilEveris.entity.Automovil;
import com.example.automovilEveris.service.AutomovilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/automovil")
@CrossOrigin(value = {})
public class AutomovilController {

    @Autowired
    private AutomovilService service;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.service.findAutomoviles());
    }

    @PostMapping("/addAutomovil")
    public ResponseEntity<?> addAutomovil (@RequestBody Automovil auto){
        ResponseEntity<?> response;
        String respuestaService =this.service.saveAutomovil(auto);
        try{
            if(respuestaService.equals("Automovil agregado!")){
                response = new ResponseEntity<>("{\"Mensaje\":\"Automovil ingresado correctamente\"}", HttpStatus.CREATED);
            }
            else if (respuestaService.equals("Formato de patente invalido")){
                response = new ResponseEntity<>("{\"Error\":\"El formato de patente ingresado no corresponde segun formato vigente para el año de fabricacion\"}",HttpStatus.BAD_REQUEST);
            }
            else{
                response = new ResponseEntity<>("{\"Error\":\"No fue posible ingresar automovil, revise año de fabricacion\"}",HttpStatus.BAD_REQUEST);

            }
        }
        catch (Exception ex){
            response = new ResponseEntity<>("{\"Error\":\"Algo salio mal :c\"}",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @RequestMapping(value = "/actualizarAuto/{id}", method = RequestMethod.PUT)
    public Automovil actualizar(@RequestBody Automovil automovil, @PathVariable(value = "id") String id){
        this.service.updateAuto(automovil,id);
        return automovil;
    }



}
