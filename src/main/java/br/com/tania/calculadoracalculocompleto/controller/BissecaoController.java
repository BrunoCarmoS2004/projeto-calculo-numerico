package br.com.tania.calculadoracalculocompleto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tania.calculadoracalculocompleto.models.bi.InfosCalculoBiDTO;
import br.com.tania.calculadoracalculocompleto.service.BissecaoService;

@RestController
@RequestMapping("/bissecao")
public class BissecaoController {
  @Autowired
  private BissecaoService bissecaoService;

  @PostMapping
  public ResponseEntity<?> calculoBissecao(@RequestBody InfosCalculoBiDTO infosCalculoBiDTO){
    return bissecaoService.calculoBissecao(infosCalculoBiDTO);
  }
}
