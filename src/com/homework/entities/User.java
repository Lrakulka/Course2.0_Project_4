package com.homework.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "pwd")    
    private String pwd;
    @OneToMany(mappedBy="user")
    private Set<Card> cards;
    
    public Set<Card> getCrds() {
        return cards;
    }
    public void setCrds(Set<Card> crds) {
        this.cards = crds;
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
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cards == null) ? 0 : cards.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
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
	User other = (User) obj;
	if (cards == null) {
	    if (other.cards != null)
		return false;
	} else if (!cards.equals(other.cards))
	    return false;
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
	if (pwd == null) {
	    if (other.pwd != null)
		return false;
	} else if (!pwd.equals(other.pwd))
	    return false;
	return true;
    }
    @Override
    public String toString() {
	return "User [id=" + id + ", name=" + name + ", pwd=" + pwd + ", cards=" + cards + "]";
    }
}
