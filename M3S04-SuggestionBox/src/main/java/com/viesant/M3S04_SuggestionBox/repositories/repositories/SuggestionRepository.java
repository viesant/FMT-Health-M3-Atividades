package com.viesant.M3S04_SuggestionBox.repositories.repositories;

import com.viesant.M3S04_SuggestionBox.entities.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {}
