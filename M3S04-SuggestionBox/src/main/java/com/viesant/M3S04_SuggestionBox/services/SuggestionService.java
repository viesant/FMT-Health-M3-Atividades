package com.viesant.M3S04_SuggestionBox.services;

import com.viesant.M3S04_SuggestionBox.dto.ReplyRequest;
import com.viesant.M3S04_SuggestionBox.dto.ReplyResponse;
import com.viesant.M3S04_SuggestionBox.dto.SuggestionRequest;
import com.viesant.M3S04_SuggestionBox.dto.SuggestionResponse;
import com.viesant.M3S04_SuggestionBox.entities.Reply;
import com.viesant.M3S04_SuggestionBox.entities.Suggestion;
import com.viesant.M3S04_SuggestionBox.exceptions.notfound.SuggestionNotFound;
import com.viesant.M3S04_SuggestionBox.repositories.ReplyRepository;
import com.viesant.M3S04_SuggestionBox.repositories.SuggestionRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SuggestionService {
  private final SuggestionRepository suggestionRepository;
  private final ReplyRepository replyRepository;

  // CREATE
  public SuggestionResponse createSuggestion(SuggestionRequest request) {
    log.info("Creating suggestion: {}", request.getTitle());

    Suggestion suggestion = new Suggestion(request);
    suggestion.setCreatedAt(LocalDateTime.now());
    suggestion.setUpdatedAt(LocalDateTime.now());
    SuggestionResponse response = save(suggestion);

    log.info("Suggestion created: {} (id: {})", response.getTitle(), response.getId());
    return response;
  }

  public ReplyResponse createReply(Long id, ReplyRequest replyRequest) {
    log.info("Creating reply to Suggestion with id: {}", id);
    Suggestion suggestion =
            suggestionRepository.findById(id).orElseThrow(() -> new SuggestionNotFound(id));

    Reply reply = new Reply(replyRequest);
    reply.setRepliedAt(LocalDateTime.now());
    reply.setSuggestion(suggestion);

    suggestion.setUpdatedAt(LocalDateTime.now());

    suggestionRepository.save(suggestion);
    replyRepository.save(reply);

    log.info("Reply created to Suggestion with id: {}", id);
    return new ReplyResponse(reply, id);
  }



  // READ
  public List<SuggestionResponse> findAll(String title) {
    log.info("Finding all suggestions with filter({})", title);
    List<SuggestionResponse> suggestions =
        suggestionRepository.findAllByTitleContainingIgnoreCaseOrderByUpdatedAtDesc(title).stream()
            .map(SuggestionResponse::new)
            .collect(Collectors.toList());

    log.info("{} suggestions found with filter({})", suggestions.size(), title);
    return suggestions;
  }

  public Suggestion findById(Long id) {
    log.info("Finding suggestion with id {}", id);
    Suggestion suggestion =
        suggestionRepository.findWithRepliesById(id).orElseThrow(() -> new SuggestionNotFound(id));
    log.info("Suggestion found with id {}", id);
    return suggestion;
  }

  // UPDATE

  // DELETE

  // privates
//  private Suggestion

  private SuggestionResponse save(Suggestion suggestion) {
    log.info("Saving suggestion: {}", suggestion.getTitle());

    suggestionRepository.save(suggestion);

    log.info("Suggestion saved: {} (id: {})", suggestion.getTitle(), suggestion.getId());
    return new SuggestionResponse(suggestion);
  }

}
