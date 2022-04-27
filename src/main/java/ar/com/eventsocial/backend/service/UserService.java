package ar.com.eventsocial.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.eventsocial.backend.dto.RegisterResponseDTO;
import ar.com.eventsocial.backend.dto.UserInfoResponseDTO;
import ar.com.eventsocial.backend.model.Inmueble;
import ar.com.eventsocial.backend.model.Transacciones;
import ar.com.eventsocial.backend.model.UserInfo;
import ar.com.eventsocial.backend.model.Usuario;
import ar.com.eventsocial.backend.model.UsuarioDto;
import ar.com.eventsocial.backend.repository.contract.IInmuebleRepository;
import ar.com.eventsocial.backend.repository.contract.ILoginRepository;
import ar.com.eventsocial.backend.repository.contract.IRegisterRepository;
import ar.com.eventsocial.backend.repository.contract.ITransactionRepository;
import ar.com.eventsocial.backend.service.contract.IUserService;

@Service("IUserService")
public class UserService implements IUserService {

	@Autowired
	IRegisterRepository registerRepository;

	@Autowired
	IInmuebleRepository inmmubleRepository;

	@Autowired
	ILoginRepository loginRepository;
	
	@Autowired
	ITransactionRepository transactionRepository;

	@Override
	public UserInfoResponseDTO getInfoUser(String userId) {

		Usuario user = new Usuario();
		UserInfoResponseDTO response = new UserInfoResponseDTO();
		UsuarioDto usuarioDto = new UsuarioDto();

		String email = null;

		user = registerRepository.getUsuario(userId);

		if (user == null) {
			response.setCode("404");
			response.setMessage("usuario sin datos");
			return response;
		}

		List<Inmueble> tipoUsuario = inmmubleRepository.inmuebleByUserId(Long.valueOf(userId));

		email = loginRepository.getEmailByUserId(userId);

		usuarioDto.setDocumento(user.getDocumento());
		usuarioDto.setEdad(user.getEdad());
		usuarioDto.setFechaNacimiento(user.getFechaNacimiento());
		usuarioDto.setId(user.getId());
		usuarioDto.setNombre(user.getNombre());
		usuarioDto.setTelefono(user.getTelefono());
		usuarioDto.setUrlAvatar(user.getUrlAvatar());
		usuarioDto.setEmail(email);

		if (tipoUsuario == null || tipoUsuario.isEmpty()) {
			usuarioDto.setAnfitrion(false);
		} else {
			usuarioDto.setAnfitrion(true);
		}

		response.setCode("200");
		response.setMessage("se recuperaron los datos del usuario: " + userId);
		response.setUsuario(usuarioDto);

		return response;

	}

	@Override
	public UserInfoResponseDTO postInfoUser(String userId, UserInfo userInfo) {

		UserInfoResponseDTO response = new UserInfoResponseDTO();

		Long update = null;

		Usuario userRecovery = new Usuario();
		userRecovery = registerRepository.getUsuario(userId);

		if (userRecovery != null) {
			Usuario user = new Usuario();
			user = registerRepository.getUsuario(userId);

			user.setDocumento(userInfo.getDocumento());
			user.setEdad(userInfo.getEdad());
			user.setFechaNacimiento(userInfo.getFechaNacimiento());
			user.setNombre(userInfo.getNombre());
			user.setId(userRecovery.getId());
			user.setTelefono(userInfo.getTelefono());
			user.setUrlAvatar(userInfo.getUrlAvatar());

			update = registerRepository.postUsuario(user);

			if (update == null) {
				response.setCode("404");
				response.setMessage("no es posible actualizar los datos de este usuario");
				return response;
			}
		} else {
			response.setCode("404");
			response.setMessage("no es posible actualizar los datos de este usuario");
			return response;
		}

		response.setCode("200");
		response.setMessage("se cargaron los datos del usuario: " + userId);

		return response;
	}

	@Override
	public UserInfoResponseDTO getListInfoUser(String userId) {

		UserInfoResponseDTO response = new UserInfoResponseDTO();

		UsuarioDto usuarioDto = null;

		List<UsuarioDto> listUserDto = new ArrayList<>();

		List<Transacciones> listaTransactions = transactionRepository.getBookingForChatByUserId(userId);
	
		if (listaTransactions == null && listaTransactions.size() > 0) {
			response.setCode("404");
			response.setMessage("usuario sin datos");
			return response;
		}

		for (int i = 0; i < listaTransactions.size(); i++) {
			try {
				Usuario userInfo = registerRepository.getUsuario(listaTransactions.get(i).getUserId().toString());
				usuarioDto = new UsuarioDto();
				//usuarioDto.setId(userInfo.get(i).getId());
				//usuarioDto.setNombre(listUser.get(i).getNombre());
				//usuarioDto.setUrlAvatar(listUser.get(i).getUrlAvatar());
				listUserDto.add(usuarioDto);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		response.setCode("200");
		response.setMessage("se recuperaron todos los usuarios");
		response.setListUsuario(listUserDto);

		return response;
	}
}
