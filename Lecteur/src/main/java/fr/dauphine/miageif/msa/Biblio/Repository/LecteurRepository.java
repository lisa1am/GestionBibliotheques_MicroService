package fr.dauphine.miageif.msa.Biblio.Repository;

import fr.dauphine.miageif.msa.Biblio.Lecteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LecteurRepository extends JpaRepository<Lecteur, Long> {
    Lecteur findByIdf(Long idf);

    List<Lecteur> findAllByNom(String Nom);

    void deleteById(Long id);

    boolean existsById(Long id);


}
