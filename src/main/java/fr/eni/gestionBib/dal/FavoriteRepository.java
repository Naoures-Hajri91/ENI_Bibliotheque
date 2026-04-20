package fr.eni.gestionBib.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.gestionBib.bo.Favorite;


public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
}