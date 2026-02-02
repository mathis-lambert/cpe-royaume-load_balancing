package com.cpe.royaume.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "quests")
public class Quest {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "kind")
    private String kind;

    @Column(name = "titre")
    private String titre;

    @Column(name = "description")
    private String description;

    @Column(name = "lieu")
    private String lieu;

    @Column(name = "ennemi")
    private String ennemi;

    @Column(name = "priorite")
    private String priorite; //todo: enum

    @Column(name = "recompense")
    private String recompense;
    // private object durée estimée

    @Column(name = "delai_limite")
    private LocalDateTime delaiLimite;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "longitude")
    private float longitude;

    public Quest() {
        // Default constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getEnnemi() {
        return ennemi;
    }

    public void setEnnemi(String ennemi) {
        this.ennemi = ennemi;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getRecompense() {
        return recompense;
    }

    public void setRecompense(String recompense) {
        this.recompense = recompense;
    }

    public LocalDateTime getDelaiLimite() {
        return delaiLimite;
    }

    public void setDelaiLimite(LocalDateTime delaiLimite) {
        this.delaiLimite = delaiLimite;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
