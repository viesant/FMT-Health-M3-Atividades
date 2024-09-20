package com.viesant.M3S04_SuggestionBox.controllers;

import com.viesant.M3S04_SuggestionBox.dto.ErrorResponse;
import com.viesant.M3S04_SuggestionBox.dto.ReplyRequest;
import com.viesant.M3S04_SuggestionBox.dto.ReplyResponse;
import com.viesant.M3S04_SuggestionBox.dto.SuggestionRequest;
import com.viesant.M3S04_SuggestionBox.dto.SuggestionResponse;
import com.viesant.M3S04_SuggestionBox.dto.SuggestionWithRepliesResponse;
import com.viesant.M3S04_SuggestionBox.services.SuggestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Suggestions", description = "Endpoints for managing suggestions")
public class SuggestionController {
  private final SuggestionService service;

  // Create Suggestion
  @Operation(
      summary = "Create a new suggestion",
      description = "Creates a new suggestion with the given title and description.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "201",
        description = "Suggestion created successfully",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SuggestionResponse.class))),
    @ApiResponse(
        responseCode = "400",
        description = "Invalid request payload",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)))
  })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SuggestionResponse createSuggestion(
      @RequestBody
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "Suggestion details",
              required = true,
              content =
                  @Content(
                      schema = @Schema(implementation = SuggestionRequest.class),
                      examples =
                          @ExampleObject(
                              value =
                                  "{ \"title\": \"New Feature\", \"description\": \"Add a dark mode option.\" }")))
          SuggestionRequest request) {
    log.info("POST: /suggestions -> Begin");

    SuggestionResponse response = service.createSuggestion(request);

    log.info("POST: /suggestions -> End");

    return response;
  }

  // Read all
  @Operation(
      summary = "List all suggestions",
      description = "Returns a list of all suggestions, with optional filtering by title.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "List of suggestions",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SuggestionResponse[].class))),
    @ApiResponse(
        responseCode = "500",
        description = "Internal server error",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<SuggestionResponse> findAll(
      @Parameter(description = "Optional filter by title") @RequestParam(defaultValue = "")
          String titleFilter) {

    log.info("GET: /suggestions -> Begin");

    List<SuggestionResponse> suggestions = service.findAll(titleFilter);

    log.info("GET: /suggestions -> End");
    return suggestions;
  }

  // Read Suggestion by id with replies
  @Operation(
      summary = "Get a suggestion by ID",
      description = "Fetch a specific suggestion by its ID, including replies.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Suggestion found",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SuggestionWithRepliesResponse.class))),
    @ApiResponse(
        responseCode = "404",
        description = "Suggestion not found",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)))
  })
  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public SuggestionWithRepliesResponse findSuggestionByIdWithReplies(
      @Parameter(description = "ID of the suggestion to retrieve", required = true) @PathVariable
          Long id) {
    log.info("GET: /suggestions/{} -> Begin", id);
    SuggestionWithRepliesResponse suggestion = service.findById(id);

    log.info("GET: /suggestions/{} -> End", id);
    return suggestion;
  }

  // Post Reply to Suggestion
  @Operation(
      summary = "Add a reply to a suggestion",
      description = "Creates a new reply for a specific suggestion.")
  @ApiResponses({
    @ApiResponse(
        responseCode = "201",
        description = "Reply created successfully",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ReplyResponse.class))),
    @ApiResponse(
        responseCode = "400",
        description = "Invalid request payload",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class))),
    @ApiResponse(
        responseCode = "404",
        description = "Suggestion not found",
        content =
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)))
  })
  @PostMapping("{id}/replies")
  @ResponseStatus(HttpStatus.CREATED)
  public ReplyResponse createReply(
      @Parameter(description = "ID of the suggestion to add the reply to", required = true)
          @PathVariable
          Long id,
      @RequestBody
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "Reply details",
              required = true,
              content =
                  @Content(
                      schema = @Schema(implementation = ReplyRequest.class),
                      examples =
                          @ExampleObject(
                              value = "{ \"message\": \"Great idea, I support this feature!\" }")))
          ReplyRequest replyRequest) {
    log.info("POST: /suggestions/{}/replies -> Begin", id);

    ReplyResponse response = service.createReply(id, replyRequest);

    log.info("POST: /suggestions/{}/replies -> End", id);
    return response;
  }
}
