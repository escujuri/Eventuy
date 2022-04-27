package ar.com.eventsocial.backend.service;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.eventsocial.backend.dto.RegisterResponseDTO;
import ar.com.eventsocial.backend.model.Login;
import ar.com.eventsocial.backend.model.Usuario;
import ar.com.eventsocial.backend.repository.contract.ILoginRepository;
import ar.com.eventsocial.backend.repository.contract.ISengridRepository;
import ar.com.eventsocial.backend.security.jwt.JwtRequestStep1;
import ar.com.eventsocial.backend.service.contract.IRegisterService;

@Service("RegisterService")
public class RegisterService implements IRegisterService {

	@Autowired
	private ILoginRepository loginRepository;

	@Autowired
	private ISengridRepository sengridRepository;

	@Override
	public RegisterResponseDTO Step1(JwtRequestStep1 jwtRequestStep1) throws IOException {

		int userSave = 0;

		RegisterResponseDTO response = new RegisterResponseDTO();

		Login login = new Login();

		Usuario user = new Usuario();

		login.setPassword_(jwtRequestStep1.getPassword());
		login.setEmail(jwtRequestStep1.getEmail());
		login.setFecha_creacion(new Date());
		login.setUsuario(user);

		user.setLogin(login);
		user.setNombre(jwtRequestStep1.getUsername());

		try {
			userSave = loginRepository.RegisterUser(login);

		} catch (Exception e) {
			response.setCode("500");
			response.setMessage(e.getMessage());
			response.setUserCaseId("error_registrando_usuario");
			return response;
		}

		if (userSave == 2) {
			response.setCode("200");
			response.setMessage("El usuario ya se encuentra registrado");
			response.setUserCaseId("usuario_existente");
			return response;
		}

		response.setCode("200");
		response.setMessage("Se registro correctamente");
		response.setUserCaseId("usuario_guardado");

		try {
			sengridRepository.postSendEmail(jwtRequestStep1.getEmail());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return response;

	}

}
