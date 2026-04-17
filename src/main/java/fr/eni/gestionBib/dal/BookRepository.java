package fr.eni.gestionBib.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.gestionBib.bo.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	
	
	
}

