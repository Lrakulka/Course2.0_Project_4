package com.homework.service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.homework.dao.ActorDAO;
import com.homework.entities.Actor;
import com.homework.entities.ActorRole;

@Service("myActorDetailsService")
public class MyActorDetailsService implements UserDetailsService {
    private static final Logger logger = Logger.getLogger(MyActorDetailsService.class);
    
    private ActorDAO actorDAO;

    @Autowired(required=true)
    public MyActorDetailsService(ActorDAO actorDAO) {
	this.actorDAO = actorDAO;
    }

    @Override
    public UserDetails loadUserByUsername(final String actorName) 
        throws UsernameNotFoundException {
	logger.info(new StringBuilder("Entering name=").
		append(actorName));
	Actor actor = actorDAO.findByActorName(actorName);
	List<GrantedAuthority> authorities = buildActorAuthority(actor.getActorRole());
	logger.info(new StringBuilder("Leaving actorName=").
		append(actor.getName().charAt(0)).
		append("*** ActorRoleCount=").append(authorities.size()));
	return buildActorForAuthentication(actor, authorities);
    }

    // Converts com.homework.Actor to
    // org.springframework.security.core.userdetails.User
    private User buildActorForAuthentication(Actor actor, 
	List<GrantedAuthority> authorities) {
	logger.info("Convertion com.homework.Actor to" +
		"org.springframework.security.core.userdetails.User");
	return new User(actor.getName(), 
			actor.getPass(), true,
                        true, true, true, authorities);
    }

    private List<GrantedAuthority> buildActorAuthority(List<ActorRole> actorRoles) {
	logger.info(new StringBuilder("Entering actorRolesCount=").
		append(actorRoles.size()));
	Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
	// Build actor's authorities
	for (ActorRole actorRole : actorRoles) {
	    setAuths.add(new SimpleGrantedAuthority(actorRole.getRole()));
	}
	List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
	logger.info(new StringBuilder("Leaving ResultCount=").
		append(result.size()));
	return result;
    }
}