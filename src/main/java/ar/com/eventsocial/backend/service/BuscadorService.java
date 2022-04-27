package ar.com.eventsocial.backend.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import ar.com.eventsocial.backend.dto.InmuebleReducidoResponseDTO;
import ar.com.eventsocial.backend.dto.InmuebleResponseDTO;
import ar.com.eventsocial.backend.model.Direccion;
import ar.com.eventsocial.backend.model.HorariosDias;
import ar.com.eventsocial.backend.model.Inmueble;
import ar.com.eventsocial.backend.model.Inmuebles;
import ar.com.eventsocial.backend.model.InmueblesReducido;
import ar.com.eventsocial.backend.model.Social;
import ar.com.eventsocial.backend.model.SocialDto;
import ar.com.eventsocial.backend.model.Transacciones;
import ar.com.eventsocial.backend.model.Usuario;
import ar.com.eventsocial.backend.repository.contract.IBuscadorRepository;
import ar.com.eventsocial.backend.repository.contract.IRegisterRepository;
import ar.com.eventsocial.backend.repository.contract.ISocialRepository;
import ar.com.eventsocial.backend.repository.contract.ITransactionRepository;
import ar.com.eventsocial.backend.service.contract.IBuscadorService;

@Service("BuscadorService")
public class BuscadorService implements IBuscadorService {

	@Autowired
	IBuscadorRepository buscadorRepository;

	@Autowired
	ITransactionRepository transactionRepository;
	
	@Autowired
	ISocialRepository socialRepository;
	
	@Autowired
	IRegisterRepository RegisteRepository;


	@Override
	public InmuebleReducidoResponseDTO BuscadorByWord(String keyWord) {

		InmuebleReducidoResponseDTO response = new InmuebleReducidoResponseDTO();

		List<Inmueble> busquedaInmuebles = buscadorRepository.getResultByWord(keyWord);

		SocialDto socialDto;
		List<SocialDto> socialDtoList = new ArrayList<>();
		
		if (busquedaInmuebles != null && !busquedaInmuebles.isEmpty()) {

			InmueblesReducido inmuebles;
			List<InmueblesReducido> listaInmueblesDto = new ArrayList<InmueblesReducido>();
			Gson js = new Gson();

			for (Inmueble inmueble : busquedaInmuebles) {

				if (inmueble.getPublicada()) {

					inmuebles = new InmueblesReducido();

					inmuebles.setIdInmueble(inmueble.getIdInmueble());
					inmuebles.setTitulo(inmueble.getTitulo());
					inmuebles.setTipoLugar(inmueble.getTipoLugar());

					Direccion direccion = new Direccion();

					direccion.setAltura(inmueble.getAltura());
					direccion.setCalle(inmueble.getCalle());
					direccion.setLocalidad(inmueble.getLocalidad());
					direccion.setCiudad(inmueble.getCiudad());
					direccion.setProvincia(inmueble.getProvincia());
					direccion.setPais(inmueble.getPais());
					direccion.setCp(inmueble.getCp());

					inmuebles.setDireccion(direccion);
					inmuebles.setDescripcion(inmueble.getDescripcion());
					inmuebles.setPrecio(inmueble.getPrecio());
					inmuebles.setFechaDisponibilidad((java.sql.Date) inmueble.getFechaDisponibilidad());
					inmuebles.setIdOriginante(String.valueOf(inmueble.getIdOriginante()));
					
					
					List<Social> social = socialRepository.getSocial(inmueble.getIdInmueble().toString());

					if (social != null) {
						for (int i = 0; i < social.size(); i++) {
							
							Usuario userInfo = RegisteRepository.getUsuario(social.get(i).getUsuario_id());
							socialDto = new SocialDto();

							socialDto.setComment(social.get(i).getComment());
							socialDto.setCreated_at(social.get(i).getCreated_at());
							socialDto.setRanking(social.get(i).getRanking());

							socialDto.setUrlAvatar(userInfo.getUrlAvatar());
							socialDto.setNombreUsuario(userInfo.getNombre());

							socialDtoList.add(socialDto);

						}
						inmuebles.setSocial(socialDtoList);
					}
					
					
					List<String> servicios = new ArrayList<String>();

					if (inmueble.getServicios() != null) {
						servicios = js.fromJson(inmueble.getServicios().getServicios(), List.class);
					}

					if (!servicios.isEmpty())
						inmuebles.setServicios(servicios);

					List<String> fotos = new ArrayList<String>();

					if (inmueble.getFotos() != null) {
						fotos = js.fromJson(inmueble.getFotos().getUrlFoto(), List.class);
					}

					if (!fotos.isEmpty())
						inmuebles.setFotos(fotos);

					if (inmueble.getPublicada()) {
						listaInmueblesDto.add(inmuebles);
					}

				}
			}

			response.setCode("200");
			response.setMessage("Se encontraron los siguientes inmuebles");
			response.setInmuebles(listaInmueblesDto);
			return response;

		}

		response.setCode("200");
		response.setMessage("No se encontraron resultados");
		response.setInmuebles(null);

		return response;
	}

	@Override
	public InmuebleResponseDTO BuscadorByInmuebleId(String inmuebleId) {

		InmuebleResponseDTO response = new InmuebleResponseDTO();

		Inmueble inmueble = buscadorRepository.getResultByIdInmueble(inmuebleId);

		if (inmueble != null) {

			Inmuebles inmuebles;
			Gson js = new Gson();

			SocialDto socialDto;
			List<SocialDto> socialDtoList = new ArrayList<>();
			
			inmuebles = new Inmuebles();
			inmuebles.setIdInmueble(inmueble.getIdInmueble());
			inmuebles.setTitulo(inmueble.getTitulo());
			inmuebles.setTipoLugar(inmueble.getTipoLugar());

			Direccion direccion = new Direccion();

			direccion.setAltura(inmueble.getAltura());
			direccion.setCalle(inmueble.getCalle());
			direccion.setLocalidad(inmueble.getLocalidad());
			direccion.setCiudad(inmueble.getCiudad());
			direccion.setProvincia(inmueble.getProvincia());
			direccion.setPais(inmueble.getPais());
			direccion.setCp(inmueble.getCp());
			inmuebles.setDireccion(direccion);
			inmuebles.setDescripcion(inmueble.getDescripcion());
			inmuebles.setPrecio(inmueble.getPrecio());
			inmuebles.setHorariosDias(js.fromJson(inmueble.getHorariosDias(), HorariosDias.class));
			inmuebles.setIdOriginante(String.valueOf(inmueble.getIdOriginante()));
			
			
			inmuebles.setFechaDisponibilidad((java.sql.Date) inmueble.getFechaDisponibilidad());
			inmuebles.setEsPrecioFijo(inmueble.getEsPrecioFijo());
			inmuebles.setDisponibilidadPersonas(inmueble.getDisponibilidadPersonas());
			inmuebles.setPublicada(inmueble.getPublicada());
			inmuebles.setMaxPersonas(inmueble.getMaxPersonas());
			inmuebles.setPuntuacion(inmueble.getPuntuacion());

			if (!inmueble.getHorariosReserva().isBlank()) {

				List<String> booking = js.fromJson(inmueble.getHorariosReserva(), List.class);

				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				String formatDate = dateFormat.format(date);

				List<Transacciones> transactions = transactionRepository.getBookingByInmuebleId(inmuebleId);

				for (int i = 0; i < transactions.size(); i++) {

					if (dateFormat.format(transactions.get(i).getFechaReserva()).equals(formatDate)) {
						booking.remove(transactions.get(i).getHorarioReserva());
					}
				}

				inmuebles.setHorariosReserva(booking);

			}
			
			List<Social> social = socialRepository.getSocial(inmueble.getIdInmueble().toString());

			if (social != null) {
				for (int i = 0; i < social.size(); i++) {
					
					Usuario userInfo = RegisteRepository.getUsuario(social.get(i).getUsuario_id());
					socialDto = new SocialDto();

					socialDto.setComment(social.get(i).getComment());
					socialDto.setCreated_at(social.get(i).getCreated_at());
					socialDto.setRanking(social.get(i).getRanking());

					socialDto.setUrlAvatar(userInfo.getUrlAvatar());
					socialDto.setNombreUsuario(userInfo.getNombre());

					socialDtoList.add(socialDto);

				}
				inmuebles.setSocial(socialDtoList);
			}

			List<String> servicios = js.fromJson(inmueble.getServicios().getServicios(), List.class);

			List<String> serviciosSeguridad = js.fromJson(inmueble.getServicios().getServiciosSeguridad(), List.class);

			if (!servicios.isEmpty())
				inmuebles.setServicios(servicios);

			if (!serviciosSeguridad.isEmpty())
				inmuebles.setServiciosDeSeguridad(serviciosSeguridad);

			List<String> fotos = js.fromJson(inmueble.getFotos().getUrlFoto(), List.class);

			if (!fotos.isEmpty())
				inmuebles.setFotos(fotos);

			response.setCode("200");
			response.setMessage("El resultado es:");
			response.setInmueble(inmuebles);

			return response;
		}

		response.setCode("400");
		response.setInmuebles(null);
		response.setMessage("No se encontraron resultados");

		return response;
	}
}
