package fr.eni.gestionBib.bll;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.eni.gestionBib.bo.Book;
import fr.eni.gestionBib.dal.BookRepository;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    // 📚 ALL BOOKS
    public Page<Book> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // 🔍 SEARCH
    public Page<Book> searchBooks(String keyword,
            Pageable pageable) {

// 🔥 1. GLOBAL SEARCH (priorité haute)
       if (keyword != null && !keyword.isBlank()) {

return repository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrIsbnContainingIgnoreCase(
keyword,
keyword,
keyword,
pageable
);
}



// 🔥 5. DEFAULT (ALL BOOKS)
return repository.findAll(pageable);
}


    // 📌 BY ID
    public Book getById(Long id) {
    	
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // ➕ CREATE
    public Book create(Book book) {
        return repository.save(book);
    }

    // ✏️ UPDATE
    public Book update(Long id, Book book) {
        Book b = getById(id);

        b.setTitle(book.getTitle());
        b.setAuthor(book.getAuthor());
        b.setIsbn(book.getIsbn());
        b.setCategory(book.getCategory());
        b.setCoverUrl(book.getCoverUrl());

        return repository.save(b);
    }

    // ❌ DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
