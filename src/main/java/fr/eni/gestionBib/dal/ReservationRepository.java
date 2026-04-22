package fr.eni.gestionBib.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.gestionBib.bo.Book;
import fr.eni.gestionBib.bo.Reservation;
import fr.eni.gestionBib.bo.UserInfo;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	boolean existsByUserAndBook(UserInfo user, Book book);

	int countByBook(Book book);
	
	
	
}