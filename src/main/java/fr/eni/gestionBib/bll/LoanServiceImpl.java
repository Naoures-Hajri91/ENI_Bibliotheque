package fr.eni.gestionBib.bll;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.eni.gestionBib.bo.Book;
import fr.eni.gestionBib.bo.Loan;
import fr.eni.gestionBib.bo.UserInfo;
import fr.eni.gestionBib.bo.enumeration.LoanStatus;
import fr.eni.gestionBib.dal.BookRepository;
import fr.eni.gestionBib.dal.LoanRepository;
import fr.eni.gestionBib.dal.UserRepository;
import fr.eni.gestionBib.web.dto.LoanDTO;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public Loan borrowBook(Long bookId, String email) {

        // 👤 user connecté
        UserInfo user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 📚 livre
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // 🔴 RG-LOAN-01 : max 3 emprunts actifs
        long activeLoans = loanRepository.countByUserAndStatus(user, LoanStatus.ACTIVE);
        if (activeLoans >= 3) {
            throw new RuntimeException("Max 3 emprunts atteints");
        }

        // 🔴 RG-LOAN-03 : interdit si retard
        boolean hasOverdue = loanRepository.existsByUserAndStatus(user, LoanStatus.OVERDUE);
        if (hasOverdue) {
            throw new RuntimeException("Vous avez un emprunt en retard !");
        }

        // 📦 disponibilité
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Livre non disponible");
        }

        // 📅 création emprunt
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setLoanDate(LocalDateTime.now());
        loan.setDueDate(LocalDateTime.now().plusDays(14)); // RG-LOAN-02
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setReturnDate(null);

        // 🔽 décrément exemplaires
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    @Override
    public List<LoanDTO> getMyLoans(String email) {

        UserInfo user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return loanRepository.findByUser(user)
                .stream()
                .map(loan -> {

                    LoanDTO dto = new LoanDTO();

                    dto.setId(loan.getId());
                    dto.setBookTitle(loan.getBook().getTitle());
                    dto.setAuthor(loan.getBook().getAuthor());
                    dto.setCoverUrl(loan.getBook().getCoverUrl());

                    dto.setLoanDate(loan.getLoanDate());
                    dto.setDueDate(loan.getDueDate());
                    dto.setReturnDate(loan.getReturnDate());

                    // ✔ enum direct (propre)
                    dto.setStatus(loan.getStatus());

                    return dto;
                })
                .collect(Collectors.toList());
    }
}