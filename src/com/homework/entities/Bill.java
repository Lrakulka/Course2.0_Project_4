package com.homework.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "bill", cascade = CascadeType.ALL)
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
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((isBlocked == null) ? 0 : isBlocked.hashCode());
	result = prime * result + ((score == null) ? 0 : score.hashCode());
	return result;
    }
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Bill other = (Bill) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (isBlocked == null) {
	    if (other.isBlocked != null)
		return false;
	} else if (!isBlocked.equals(other.isBlocked))
	    return false;
	if (score == null) {
	    if (other.score != null)
		return false;
	} else if (!score.equals(other.score))
	    return false;
	return true;
    }
    @Override
    public String toString() {
	return "Bill [id=" + id + ", isBlocked=" + isBlocked + ", score=" + score + "]";
    }
}
