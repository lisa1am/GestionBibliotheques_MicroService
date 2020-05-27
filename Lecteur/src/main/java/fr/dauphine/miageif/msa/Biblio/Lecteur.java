package fr.dauphine.miageif.msa.Biblio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Lecteur {

    @Id
    private Long idf;

    @Column(name="genre")
    private String genre;

    @Column(name="nom")
    private String nom;

    @Column(name="prenom")
    private String prenom;

    @Column(name="adresse")
    private String adresse;

    @Column(name="date_naissance")
    private Date date_naissance;

    public Lecteur(){}

    public Lecteur(Long idf, String genre, String nom, String prenom, String adresse, Date date_naissance){
        this.idf=idf;
        this.genre=genre;
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.date_naissance=date_naissance;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecteur lecteur = (Lecteur) o;
        return idf.equals(lecteur.idf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idf);
    }


    public Long getIdf() {
        return idf;
    }

    public void setIdf(Long idf) {
        this.idf = idf;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }
}
