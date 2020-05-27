package fr.dauphine.miageif.msa.Biblio.Controller;

import fr.dauphine.miageif.msa.Biblio.Lecteur;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import fr.dauphine.miageif.msa.Biblio.Repository.LecteurRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
public class LecteurController {

    @Autowired
    private Environment environment;

    @Autowired
    private LecteurRepository repository;

    @GetMapping("lecteurs/idf/{idf}")
    public Lecteur findById(@PathVariable Long idf){
        Lecteur lecteur = repository.findByIdf(idf);
        return lecteur;
    }

    @GetMapping("lecteurs/nom/{nom}")
    public List<Lecteur> findByNomPrenom(@PathVariable String nom){
        List<Lecteur> lecteurs = repository.findAllByNom(nom);
        return lecteurs;
    }

    @DeleteMapping("lecteurs/idf/{idf}")
    public boolean deleteById(@PathVariable Long idf){
        repository.deleteById(idf);
        System.out.println("Le lecteur ayant l'idf "+idf+" a été supprimé de la base de données");
        return true;
    }


    //HEADER : 'Content-type: application/json'
    @PutMapping("lecteurs/idf/{idf}")
    public boolean updateLecteur(@RequestBody Lecteur lecteur, @PathVariable Long idf) {
        if (!repository.existsById(idf)){
            System.out.println("Le lecteur n'existe pas dans la base de données !");
            return false;
        }else{
            Lecteur lecteurEnBase = repository.findByIdf(idf);
            if(lecteur.getGenre() == null){
                lecteur.setGenre(lecteurEnBase.getGenre());
            }
            if(lecteur.getNom() == null){
                lecteur.setNom(lecteurEnBase.getNom());
            }
            if(lecteur.getPrenom() == null){
                lecteur.setPrenom(lecteurEnBase.getPrenom());
            }
            if(lecteur.getDate_naissance() == null){
                lecteur.setDate_naissance(lecteurEnBase.getDate_naissance());
            }
            if(lecteur.getAdresse() == null){
                lecteur.setAdresse(lecteurEnBase.getAdresse());
            }
            repository.save(lecteur);
            System.out.println("Le lecteur ayant l'idf "+idf+" a été mis à jour avec succès");
            return true;
        }

    }

    @PostMapping("lecteurs/")
    public boolean addLecteur(@RequestBody Lecteur lecteur){
        if (repository.existsById(lecteur.getIdf())) {
            System.out.println("Le lecteur existe déjà dans la base de données !");
            return false;
        }

        repository.save(lecteur);
        System.out.println("Le lecteur a été enregistré avec succès");
        return true;
    }
}
