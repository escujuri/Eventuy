package ar.com.eventsocial.backend.service_;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ar.com.eventsocial.backend.dto.RegisterResponseDTO;
import ar.com.eventsocial.backend.model.Login;
import ar.com.eventsocial.backend.repository.contract.ILoginRepository;
import ar.com.eventsocial.backend.repository_.StorageRepository;
import ar.com.eventsocial.backend.security.jwt.JwtTokenUtil;
import ar.com.eventsocial.backend.service.contract.ILoginService;

@Service("LoginService")
public class LoginService implements ILoginService {

	@Autowired
	private ILoginRepository loginRepository;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired /* interface UserDetailsService */
	private UserDetailsService jwtInMemoryUserDetailsService;

	@Override
	public RegisterResponseDTO authenticate(String username, String password, String ipFromRequest) {

		List<Login> usuario = loginRepository.logginAccess(username);

		if (usuario == null || usuario.isEmpty()) {
			RegisterResponseDTO response_ = new RegisterResponseDTO();
			response_.setCode("404");
			response_.setMessage("Usuario no encontrado");
			response_.setUserCaseId("usuario_inexistente");
			return response_;
		}

		String pass = usuario.get(0).getPassword_();

		if (!pass.equals(password)) {
			RegisterResponseDTO response_ = new RegisterResponseDTO();
			response_.setCode("404");
			response_.setMessage("Contraseña invalida");
			response_.setUserCaseId("invalid_password");
			return response_;
		}

		/*
		 * Etapa de completar el JWT
		 */

		UserDetails userDetails = null;
		Map<String, Object> claims = new HashMap();

		try {
			userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(username);
		} catch (Exception e) {
			System.out.println("lala");
		}
		// recuperamos nuevamente, desde la base de datos.. la info del usuario asociado
		// al username

		claims.put("usuario", username);
		claims.put("email", usuario.get(0).getUsuario().getEmail());
		claims.put("userId", usuario.get(0).getId());

		// Generamos el token con los claims necesarios.
		final String token = jwtTokenUtil.generateToken(userDetails, claims);

		RegisterResponseDTO response_ = new RegisterResponseDTO();
		response_.setCode("200");
		response_.setMessage("Se logeo correctamente");
		response_.setSessionEnc(token);

		
		StorageRepository test = new StorageRepository();
		
		try {
			test.Connection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		return response_;
	}

}
