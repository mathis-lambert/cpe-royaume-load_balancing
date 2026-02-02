package com.cpe.royaume.entity;

import java.util.Date;

public class Quest {
    private String id;
    private String kind;
    private String titre;
    private String description;
    private String lieu;
    private String ennemi;
    private String priorite; //todo: enum
    private String recompense;
    // private object durée estimée
    private Date delaiLimite;
    private float latitude;
    private float longitude;

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

    public Date getDelaiLimite() {
        return delaiLimite;
    }

    public void setDelaiLimite(Date delaiLimite) {
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
