package ar.com.eventsocial.backend.service_;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.eventsocial.backend.dto.RegisterDTO;
import ar.com.eventsocial.backend.dto.RegisterResponseDTO;
import ar.com.eventsocial.backend.model.Login;
import ar.com.eventsocial.backend.model.Usuario;
import ar.com.eventsocial.backend.repository.contract.ILoginRepository;
import ar.com.eventsocial.backend.repository.contract.IRegisterRepository;
import ar.com.eventsocial.backend.service.contract.IRegisterService;

@Service("RegisterService")
public class RegisterService implements IRegisterService {

	@Autowired
	IRegisterRepository registerRepository;

	@Autowired
	private ILoginRepository loginRepository;

	@Override
	public RegisterResponseDTO Step1(String username, String pass, String email) {

		int userSave = 0;

		RegisterResponseDTO response = new RegisterResponseDTO();

		Login login = new Login();
		
		Usuario user = new Usuario();

		login.setUser_(username);
		login.setPassword_(pass);
		login.setFecha_creacion(new Date());
		user.setEmail(email);
		login.setUsuario(user);
		user.setLogin(login);

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

		return response;

	}

}
