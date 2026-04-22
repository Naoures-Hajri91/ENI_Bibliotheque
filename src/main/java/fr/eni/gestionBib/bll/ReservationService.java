package fr.eni.gestionBib.bll;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import fr.eni.gestionBib.bo.Book;
import fr.eni.gestionBib.bo.Reservation;
import fr.eni.gestionBib.bo.UserInfo;
import fr.eni.gestionBib.bo.enumeration.ReservationStatus;
import fr.eni.gestionBib.dal.BookRepository;
import fr.eni.gestionBib.dal.ReservationRepository;
import fr.eni.gestionBib.dal.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class ReservationService {
	
	private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ReservationRepository reservationRepository;
    
    public ReservationService(UserRepository userRepository, ReservationRepository reservationRepository, BookRepository bookRepository) {
		this.userRepository = userRepository;
		this.bookRepository = bookRepository;
		this.reservationRepository = reservationRepository;
    	
    }

	@Transactional
	public Reservation createReservation(Long bookId , Long userId) {

	    UserInfo user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User introuvable"));

	    Book book = bookRepository.findById(bookId)
	            .orElseThrow(() -> new RuntimeException("Book introuvable"));

	    boolean alreadyReserved =
	            reservationRepository.existsByUserAndBook(user, book);

	    if (alreadyReserved) {
	        throw new RuntimeException("Déjà réservé");
	    }

	    int rank = reservationRepository.countByBook(book) + 1;


	    Reservation reservation = new Reservation(null, LocalDateTime.now(), rank, ReservationStatus.PENDING, user, book);

	    return reservationRepository.save(reservation);
	}
}
