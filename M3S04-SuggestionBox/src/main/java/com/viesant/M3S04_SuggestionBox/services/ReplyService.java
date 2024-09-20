package com.viesant.M3S04_SuggestionBox.services;

import com.viesant.M3S04_SuggestionBox.repositories.repositories.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {
  private final ReplyRepository replyRepository;
}
