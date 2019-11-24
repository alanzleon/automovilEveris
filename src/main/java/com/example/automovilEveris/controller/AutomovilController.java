package com.example.automovilEveris.controller;

import com.example.automovilEveris.entity.Automovil;
import com.example.automovilEveris.service.AutomovilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/automovil")
@CrossOrigin(value = {})
            public class AutomovilController {

                @Autowired
                private AutomovilService service;

                @GetMapping("/getAll")
                public ResponseEntity<?> obtenerTodos(){
                    List<Automovil> autos = this.service.obtenerAutomoviles();
                    return new ResponseEntity<>(autos,HttpStatus.OK);
                }

                @GetMapping("/getByPatente/{patente}")
                public ResponseEntity<?> getAutomovilByPatente(@PathVariable(value="patente") String patente){
                    ResponseEntity<?> response;
                    try{
                        Automovil auto = this.service.findAutoByPatente(patente);
                        response = new ResponseEntity<>(auto, HttpStatus.OK);
                    }catch (Exception ex) {
                        System.out.println(ex);
                        response = new ResponseEntity<>("{\"Error\":\"Sin resultado, verifique formato patente\"}",HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                    return response;
                }

                @GetMapping("/getAllByEstadoArriendo/{estadoArriendo}")
                public ResponseEntity<?> getAutosByStatus(@PathVariable(value="estadoArriendo") String status) {
                    ResponseEntity<?> response;

                        List<Automovil> autos = this.service.findAutosByEstadoArriendo(status);
                        if (autos.size()>0) {
                            response = new ResponseEntity<>(autos, HttpStatus.OK);
                        } else {
                            response = mensajeHttp("{\"Error\":\"Sin resultados\"}", HttpStatus.INTERNAL_SERVER_ERROR);

                        }
                        return response;


                }
                    @DeleteMapping("/eliminarAuto/{patente}")
                    public ResponseEntity<?> eliminarAuto (@PathVariable(value = "patente") String patente){
                        ResponseEntity<?> response;
                        try {
                            this.service.eliminarAutomovil(patente);
                            return response = new ResponseEntity<>("{\"registro eliminado\"}", HttpStatus.OK);
                        } catch (Exception e) {

                            return response = new ResponseEntity<>("{\"Error\":\"Sin resultado, verifique formato patente\"}", HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    }

    //@RequestMapping(value = "/actualizar/{patente}", method = RequestMethod.PUT)
    @PutMapping("/actualizar/{patente}")
    public ResponseEntity<?> actualizar(@RequestBody Automovil auto, @PathVariable(value = "patente") String patente){
        ResponseEntity<?> response;
        String respuestaService = this.service.actualizarAuto(auto,patente);
        try {
            switch(respuestaService) {
                case "registro actualizado":
                    response = new ResponseEntity<>("{\"Cliente actualizado\"}", HttpStatus.OK);
                    break;
                case "no encontrado":
                    response = new ResponseEntity<>("{\"Cliente No existe\"}",HttpStatus.NOT_FOUND);
                    break;
                case "año fabricacion no valido":
                    response = new ResponseEntity<>("{\"Año de fabricacion no valido\"}",HttpStatus.BAD_REQUEST);
                    break;
                case "tipo no valido":
                    response = new ResponseEntity<>("{\"Tipo de automovil no valido\"}",HttpStatus.BAD_REQUEST);
                    break;
                case "numero de asientos no valido":
                    response = new ResponseEntity<>("{\"Cantidad de asientos no valida\"}",HttpStatus.BAD_REQUEST);
                    break;
                case "estado no valido":
                    response = new ResponseEntity<>("{\"Estado de automovil no valido\"}",HttpStatus.BAD_REQUEST);
                    break;
                default:
                    response = new ResponseEntity<>("{\"Algo salio mal\"}",HttpStatus.INTERNAL_SERVER_ERROR);
                    break;
            }
        } catch (Exception ex){
            response = new ResponseEntity<>(ex.toString(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
/*
                    @PutMapping(value = "/actualizarAuto/{patente}/{estado}")
                    public ResponseEntity<?> actualizarAuto (@PathVariable(value = "patente") String
                    patente, @PathVariable(value = "estado") String estadoArriendo)
                    {
                        ResponseEntity<?> response;
                        try {
                            String respuesta = this.service.actualizarAuto(patente, estadoArriendo);
                            response = new ResponseEntity<>("{\"Correctamente actualizado\"}", HttpStatus.OK);

                        } catch (Exception ex) {

                            response = new ResponseEntity<>("{\"Error\":\"problemas\"}", HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                        return response;
                    }
*/
                    @PostMapping("/addAutomovil")
                    public ResponseEntity<?> addAutomovil (@RequestBody Automovil auto){
                        ResponseEntity<?> response;
                        String respuestaService = this.service.guardarAutomovil(auto);
                        try {
                            if (respuestaService.equals("Automovil agregado"))
                            {
                                response = mensajeHttp("{\"Mensaje\":\"Automovil ingresado correctamente\"}", HttpStatus.CREATED);
                                String response1 = this.service.guardarAutomovil(auto);
                            }
                            else if (respuestaService.equals("Formato de patente invalido"))
                            {
                                response = new ResponseEntity<>("{\"Error\":\"El formato de patente ingresado no corresponde segun formato vigente para el año de fabricacion\"}", HttpStatus.BAD_REQUEST);
                            }
                            else if (respuestaService.equals("tipo de auto no valido"))
                            {
                                response = new ResponseEntity<>("{\"Error\":\"los tipos de vehiculos validos son:City car, Descapotable, " +
                                        "SUV y Alta gama\"}", HttpStatus.BAD_REQUEST);
                            }
                            else if (respuestaService.equals("numero de asientos entre 2 - 9"))
                            {
                                response = new ResponseEntity<>("{\"Error\":\"Cantidad de asientos invalida\"}", HttpStatus.BAD_REQUEST);
                            }
                            else if (respuestaService.equals("estado de automovil no valido"))
                            {
                                response = new ResponseEntity<>("{\"Error\":\"Estado de automovil no valido\"}", HttpStatus.BAD_REQUEST);
                            }
                            else if (respuestaService.equals("No se puede ingresar un vehiculo anterior al 2000"))
                            {
                                response = new ResponseEntity<>("{\"Error\":\"No es posible ingresar un vehiculo anterior al año 2000\"}", HttpStatus.BAD_REQUEST);
                            }
                            else
                                {
                                response = new ResponseEntity<>("{\"Error\":\"No fue posible ingresar automovil, todos los campos son obligatorios,revise condiciones de ingreso\"}", HttpStatus.BAD_REQUEST);
                                System.out.println(respuestaService);
                            }
                        } catch (Exception ex) {
                            response = new ResponseEntity<>("{\"Error\":\"Algo salio mal :c\"}", HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                        return response;
                    }

                    ResponseEntity<?> mensajeHttp (String body, HttpStatus status){
                        ResponseEntity<?> msj = new ResponseEntity<>(body, status);
                        return msj;
                    }



            }

