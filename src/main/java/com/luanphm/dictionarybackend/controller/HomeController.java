package com.luanphm.dictionarybackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "home")
public class HomeController {

  @GetMapping
  public String helloWorld() {
      return "Hello 123123dfgdfgdfg";
}
}
