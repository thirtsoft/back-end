package com.spring.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ligneVente")
public class LigneVente extends BaseEntity {

    private Long numero;

    private String code;

    private int quantite;

    private double prixVente;

    @ManyToOne
    @JoinColumn(name = "vente_id")
    @JsonIgnoreProperties(value = {"ligneVentes"})
    private Vente vente;

    @ManyToOne
    @JoinColumn(name = "prod_id")
    private Order order;

    public LigneVente() {
    }

    public LigneVente(Long numero, String code, int quantite, double prixVente, Vente vente, Order order) {
        this.numero = numero;
        this.code = code;
        this.quantite = quantite;
        this.prixVente = prixVente;
        this.vente = vente;
        this.order = order;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
