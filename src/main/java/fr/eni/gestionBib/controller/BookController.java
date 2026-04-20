package fr.eni.gestionBib.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import fr.eni.gestionBib.bll.BookService;
import fr.eni.gestionBib.bo.Book;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // ✅ GET ALL + PAGINATION 
    @GetMapping
    public Page<Book> getAll(
            @RequestParam(defaultValue = "0", name = "page") Integer page,
            @RequestParam(defaultValue = "20",name = "size") Integer size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return service.getAll(pageable);
    }
    
    
    
    @GetMapping("/search")
    public Page<Book> getBooks(
            @RequestParam(required = false,name="keyword") String keyword,
            @RequestParam(required = false,name="category") String category,
            @RequestParam(required = false,name="available") Boolean available,
            Pageable pageable
    ) {
        return service.searchBooks(keyword, pageable);
    }
    
    
    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {

      //  Long bookId = Long.parseLong(id);

        return service.getById(id);
    }
    
    
    
}