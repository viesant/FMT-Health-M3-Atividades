package com.viesant.M3S04_SuggestionBox.repositories;

import com.viesant.M3S04_SuggestionBox.dto.ReplyResponse;
import com.viesant.M3S04_SuggestionBox.entities.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
  List<Reply> findAllBySuggestionIdOrderByRepliedAtDesc(Long suggestionId);

}
