package com.viesant.M3S04_SuggestionBox.exceptions.notfound;

public class SuggestionNotFound extends NotFoundException {
  public SuggestionNotFound(Long id) {
    super("Suggestion", id);
  }
}
