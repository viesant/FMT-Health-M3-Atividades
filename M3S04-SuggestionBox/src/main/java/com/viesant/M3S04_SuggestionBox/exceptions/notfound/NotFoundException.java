package com.viesant.M3S04_SuggestionBox.exceptions.notfound;

public abstract class NotFoundException extends RuntimeException {
  public NotFoundException(String entityName, Long id) {
    super("Not found entity " + entityName + " with id " + id);
  }
}
