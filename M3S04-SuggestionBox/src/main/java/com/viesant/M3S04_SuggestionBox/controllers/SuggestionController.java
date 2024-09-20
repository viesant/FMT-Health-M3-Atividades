package com.viesant.M3S04_SuggestionBox.controllers;

import com.viesant.M3S04_SuggestionBox.dto.ReplyRequest;
import com.viesant.M3S04_SuggestionBox.dto.ReplyResponse;
import com.viesant.M3S04_SuggestionBox.dto.SuggestionRequest;
import com.viesant.M3S04_SuggestionBox.dto.SuggestionResponse;
import com.viesant.M3S04_SuggestionBox.entities.Suggestion;
import com.viesant.M3S04_SuggestionBox.services.SuggestionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("suggestions")
@RequiredArgsConstructor
@Slf4j
public class SuggestionController {
  private final SuggestionService service;

  // Create Suggestion
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SuggestionResponse createSuggestion(@RequestBody SuggestionRequest request) {
    log.info("POST: /suggestions -> Begin");

    SuggestionResponse response = service.createSuggestion(request);

    log.info("POST: /suggestions -> End");

    return response;
  }

  // Read all
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<SuggestionResponse> findAll(@RequestParam(defaultValue = "") String title) {
    log.info("GET: /suggestions -> Begin");

    List<SuggestionResponse> suggestions = service.findAll(title);

    log.info("GET: /suggestions -> End");
    return suggestions;
  }

  //Read Suggestion by id with replies
  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public Suggestion findSuggestionByIdWithReplies(@PathVariable Long id){
    log.info("GET: /suggestions/{} -> Begin", id);
    Suggestion suggestion = service.findById(id);

    log.info("GET: /suggestions/{} -> End", id);
    return suggestion;
  }


  //Post Reply to Suggestion
  @PostMapping("{id}/replies")
  @ResponseStatus(HttpStatus.CREATED)
  public ReplyResponse createReply(@PathVariable Long id, @RequestBody ReplyRequest replyRequest){
    log.info("POST: /suggestions/{}/replies -> Begin", id);

    ReplyResponse response = service.createReply(id, replyRequest);

    log.info("POST: /suggestions/{}/replies -> End", id);
    return response;
  }



}
