package ar.com.eventsocial.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.com.eventsocial.backend.model.Login;
import ar.com.eventsocial.backend.repository.contract.ILoginRepository;




@Service
public class JwtUserDetailsService implements UserDetailsService /* interface UserDetailsService */{

	@Autowired
	private ILoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		List<Login> usuarios = loginRepository.logginAccess(username);
		
		if (usuarios == null || (usuarios != null && usuarios.size() == 0)) {
			throw new UsernameNotFoundException("Usuario no encontrado: " + username);
		}
		
		Optional<Login> user = usuarios.stream() 
                .filter(u -> u.getEmail()!= null 
                			&& u.getEmail().equals(username))
                .findAny();
			
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("Usuario no encontrado: " + username);
		}
				
		Login persona = user.get(); // usuarios.get(0);


    	return User.withUsername(persona.getEmail())
    	                   .password(persona.getPassword_())
    	                   .roles("USER").build();
    	
    		}
}