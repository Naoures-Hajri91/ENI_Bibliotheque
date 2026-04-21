package fr.eni.gestionBib.bll;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.Repositories;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.eni.gestionBib.bo.Book;
import fr.eni.gestionBib.bo.enumeration.Category;
import fr.eni.gestionBib.dal.BookRepository;

import fr.eni.gestionBib.web.dto.BookRequest;

@Service
public class BookService {

    private final BookRepository repository;
    // private final  BookMapper bookMapper;
    
    private final ObjectMapper objectMapper;

    public BookService(BookRepository repository) {
        this.repository = repository;
		
		this.objectMapper = new ObjectMapper();
    }

    
    public void validateBusiness(BookRequest req, Long bookId) {

        if (req.getAvailableCopies() > req.getTotalCopies()) {
            throw new RuntimeException("availableCopies > totalCopies");
        }

        if (req.getTotalCopies() <= 0) {
            throw new RuntimeException("totalCopies doit être > 0");
        }

        // ISBN unique (gestion update)
        repository.findByIsbn(req.getIsbn()).ifPresent(b -> {
            if (bookId == null || !b.getId().equals(bookId)) {
                throw new RuntimeException("ISBN déjà existant");
            }
        });
    }
    
    
    
    
    
    public Book create(BookRequest req, MultipartFile file) throws IOException {

        // ✅ validation métier
        validateBusiness(req, null);

        // 🖼️ upload image
        String imageUrl = saveImage(file);
        req.setCoverUrl(imageUrl);
        // 🧠 mapping DTO → entity
        // Book book = bookMapper.toEntity(req);
        
     // 🧠 mapping DTO → Entity avec ObjectMapper
        Book book = objectMapper.convertValue(req, Book.class);

        return repository.save(book);
    }
    
    
    private String saveImage(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Image obligatoire");
        }

        // 🔒 validation type fichier
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new RuntimeException("Fichier doit être une image");
        }

        String folder = "uploads/";
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        Path path = Paths.get(folder + filename);

        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        // ✅ URL accessible
        return "uploads/" + filename;
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
    
    // 📌 BY Categorie
   public Page<Book> findBookByCategory(Category category,Pageable pageable){
 	   Page<Book> books = repository.findByCategory(category, pageable);
 		return books;
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
   
	public void delete(Long isbn) {
		if (repository.findById(isbn).get()==null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found");
		repository.deleteById(isbn);
	}
}
