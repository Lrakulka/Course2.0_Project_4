package com.homework.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="actor")
public class Actor {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "act_name")
    private String name;
    @Column(name = "pass")    
    private String pass;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "actor", cascade = CascadeType.ALL)
    private List<Card> cards;
    
    public Actor() {}
    
    public Actor(String name, String pass) {
	this.name = name;
	this.pass = pass;
    }
    
    public List<Card> getCards() {
        return cards;
    }
    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPwd() {
        return pass;
    }
    public void setPwd(String pwd) {
        this.pass = pwd;
    }
    
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((pass == null) ? 0 : pass.hashCode());
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
	Actor other = (Actor) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (pass == null) {
	    if (other.pass != null)
		return false;
	} else if (!pass.equals(other.pass))
	    return false;
	return true;
    }
    @Override
    public String toString() {
	return "User [id=" + id + ", name=" + name + ", pass=" + pass + "]";
    }
}
