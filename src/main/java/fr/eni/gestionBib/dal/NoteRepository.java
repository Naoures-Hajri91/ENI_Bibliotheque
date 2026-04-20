package fr.eni.gestionBib.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.gestionBib.bo.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {}