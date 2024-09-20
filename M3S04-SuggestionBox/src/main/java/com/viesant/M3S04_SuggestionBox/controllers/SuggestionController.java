package com.viesant.M3S04_SuggestionBox.controllers;

import com.viesant.M3S04_SuggestionBox.dto.SuggestionRequest;
import com.viesant.M3S04_SuggestionBox.dto.SuggestionResponse;
import com.viesant.M3S04_SuggestionBox.services.SuggestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("suggestions")
@RequiredArgsConstructor
@Slf4j
public class SuggestionController {
  private final SuggestionService service;

  // CREATE
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SuggestionResponse create(@RequestBody SuggestionRequest request) {
    log.info("POST: /reminder -> Begin");

    SuggestionResponse response = service.create(request);

    log.info("POST: /reminder -> End");

    return response;
  }

  // READ
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<SuggestionResponse> findAll(
          @RequestParam(defaultValue = "") String title
  ) {
    log.info("GET: /reminder -> Begin");

    List<SuggestionResponse> suggestions = service.findAll(title);

    log.info("GET: /reminder -> End");
    return suggestions;
  }
  // UPDATE

  // DELETE
}
