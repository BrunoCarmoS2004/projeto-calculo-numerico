package br.com.tania.calculadoracalculocompleto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tania.calculadoracalculocompleto.models.gauss.InfosCalculoGauss;
import br.com.tania.calculadoracalculocompleto.service.GaussService;

@RestController
@RequestMapping("/gauss")
public class GaussController {
  @Autowired
  private GaussService gaussService;

  @PostMapping
  public ResponseEntity<?> calcularGauss(@RequestBody InfosCalculoGauss infosCalculoGauss){
    return gaussService.calcularGauss(infosCalculoGauss);
  }
}
