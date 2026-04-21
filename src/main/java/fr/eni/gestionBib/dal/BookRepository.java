package fr.eni.gestionBib.dal;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.gestionBib.bo.Book;
import fr.eni.gestionBib.bo.enumeration.Category;

	 public interface BookRepository extends JpaRepository<Book, Long> {

	     // 🔍 recherche par titre (insensible casse)
	     Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

	     // 🔍 recherche par auteur
	     Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);

	     // 🔍 recherche par ISBN
	     Page<Book> findByIsbnContainingIgnoreCase(String isbn, Pageable pageable);

	     // 📚 filtre catégorie
	     Page<Book> findByCategory(Category category, Pageable pageable);

	     // 📦 disponibilité
	     //  Page<Book> findByAvailable(boolean available, Pageable pageable);

	     // 🔥 combinaison simple (catégorie + disponibilité)
	     //  Page<Book> findByCategoryAndAvailable(String category, boolean available, Pageable pageable);

	     // 🔥 recherche globale (titre OU auteur OU isbn)
	     Page<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrIsbnContainingIgnoreCase(
	             String title,
	             String author,
	             String isbn,
	             Pageable pageable
	     );

		Optional<Book> findByIsbn(String isbn);
	 }
	 




