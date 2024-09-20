package com.viesant.M3S04_SuggestionBox.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtil {
  private static final ObjectMapper om = new ObjectMapper().registerModule(new JavaTimeModule());

  private JsonUtil() {}

  public static String toJson(Object object) {
    try {
      ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
      return ow.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      return ("Erro ao converter o RequestBody para JSON" + e);
    }
  }
}
