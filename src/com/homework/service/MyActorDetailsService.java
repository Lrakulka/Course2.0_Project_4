package com.homework.service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private ActorDAO actorDAO;

	@Autowired(required=true)
	public MyActorDetailsService(ActorDAO actorDAO) {
	    this.actorDAO = actorDAO;
	}

	@Override
	public UserDetails loadUserByUsername(final String actorName) 
               throws UsernameNotFoundException {
		Actor actor = actorDAO.findByActorName(actorName);
		List<GrantedAuthority> authorities = buildActorAuthority(actor.getActorRole());
		return buildActorForAuthentication(actor, authorities);
	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildActorForAuthentication(Actor actor, 
		List<GrantedAuthority> authorities) {
		return new User(actor.getName(), 
			actor.getPass(), true,
                        true, true, true, authorities);
	}

	private List<GrantedAuthority> buildActorAuthority(List<ActorRole> actorRoles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		// Build actor's authorities
		for (ActorRole actorRole : actorRoles) {
			setAuths.add(new SimpleGrantedAuthority(actorRole.getRole()));
		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}
}