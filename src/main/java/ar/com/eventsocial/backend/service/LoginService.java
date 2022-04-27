package ar.com.eventsocial.backend.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ar.com.eventsocial.backend.dto.RegisterResponseDTO;
import ar.com.eventsocial.backend.model.Consult;
import ar.com.eventsocial.backend.model.ConsultTable;
import ar.com.eventsocial.backend.model.Inmueble;
import ar.com.eventsocial.backend.model.Login;
import ar.com.eventsocial.backend.repository.contract.IInmuebleRepository;
import ar.com.eventsocial.backend.repository.contract.ILoginRepository;
import ar.com.eventsocial.backend.repository.contract.ISengridRepository;
import ar.com.eventsocial.backend.security.jwt.JwtTokenUtil;
import ar.com.eventsocial.backend.service.contract.ILoginService;

@Service("LoginService")
public class LoginService implements ILoginService {

	@Autowired
	private ILoginRepository loginRepository;

	@Autowired
	private IInmuebleRepository inmmubleRepository;

	@Autowired
	private ISengridRepository sengridRepository;
	
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@Override
	public RegisterResponseDTO authenticate(String email, String password, String ipFromRequest) {

		List<Login> usuario = loginRepository.logginAccess(email);

		if (usuario == null || usuario.isEmpty()) {
			RegisterResponseDTO response_ = new RegisterResponseDTO();
			response_.setCode("404");
			response_.setMessage("Email no encontrado");
			response_.setUserCaseId("email_inexistente");
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
		Map<String, Object> claims = new HashMap<String, Object>();

		try {
			userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(email);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		claims.put("email",  usuario.get(0).getEmail());
		claims.put("userId", usuario.get(0).getId());
		claims.put("nombre", usuario.get(0).getUsuario().getNombre());
		claims.put("urlAvatar", usuario.get(0).getUsuario().getUrlAvatar());
		claims.put("edad", usuario.get(0).getUsuario().getEdad());
		claims.put("fechaNacimiento", usuario.get(0).getUsuario().getFechaNacimiento());
		
		List<Inmueble> tipoUsuario = null;

		tipoUsuario = inmmubleRepository.inmuebleByUserId(usuario.get(0).getId());

		if (tipoUsuario == null || tipoUsuario.isEmpty()) {
			claims.put("anfitrion", false);
		} else {
			claims.put("anfitrion", true);
		}

		final String token = jwtTokenUtil.generateToken(userDetails, claims);

		RegisterResponseDTO response_ = new RegisterResponseDTO();
		response_.setCode("200");
		response_.setMessage("Se logeo correctamente");
		response_.setSessionEnc(token);

		return response_;
	}

	@Override
	public RegisterResponseDTO postConsult(Consult consultRequest, String ip) throws IOException {
	
		ConsultTable consult = new ConsultTable();
		
		consult.setAsunto(consultRequest.getAsunto());
		consult.setEmail(consultRequest.getEmail());
		consult.setFecha_creacion (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date()));
		consult.setMensaje(consultRequest.getMensaje());
		consult.setNombre(consultRequest.getAsunto());
		consult.setIp(ip);
		
		loginRepository.postConsult(consult);
		
		RegisterResponseDTO response_ = new RegisterResponseDTO();
		response_.setCode("200");
		response_.setMessage("Consulta guardada correctamente");
	
		
		sengridRepository.postSendEmailConsult(consultRequest);
		
		
		return response_;
	}

}
