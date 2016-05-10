package com.homework.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="bill")
public class Bill {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "isBlocked")
    @Type(type = "boolean")
    private Boolean isBlocked;
    @Column(name = "score")
    private Double score;
    @OneToOne
    @JoinColumn(name="id_bill")
    private Card card;
    
    public Card getCard() {
        return card;
    }
    public void setCard(Card card) {
        this.card = card;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Boolean getIsBlocked() {
        return isBlocked;
    }
    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }
}
