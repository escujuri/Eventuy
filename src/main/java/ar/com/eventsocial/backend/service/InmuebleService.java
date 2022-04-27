package ar.com.eventsocial.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import ar.com.eventsocial.backend.dto.InmuebleResponseDTO;
import ar.com.eventsocial.backend.model.Direccion;
import ar.com.eventsocial.backend.model.Fotos;
import ar.com.eventsocial.backend.model.GenericUtils;
import ar.com.eventsocial.backend.model.HorariosDias;
import ar.com.eventsocial.backend.model.Inmueble;
import ar.com.eventsocial.backend.model.Inmuebles;
import ar.com.eventsocial.backend.model.Servicios;
import ar.com.eventsocial.backend.model.Social;
import ar.com.eventsocial.backend.model.SocialDto;
import ar.com.eventsocial.backend.model.Usuario;
import ar.com.eventsocial.backend.repository.contract.IInmuebleRepository;
import ar.com.eventsocial.backend.repository.contract.IRegisterRepository;
import ar.com.eventsocial.backend.repository.contract.ISocialRepository;
import ar.com.eventsocial.backend.service.contract.IInmubleService;

@Service("InmuebleService")
public class InmuebleService implements IInmubleService {

	@Autowired
	IInmuebleRepository inmuebleRepository;

	@Autowired
	ISocialRepository socialRepository;

	@Autowired
	IRegisterRepository RegisteRepository;

	@Override
	public InmuebleResponseDTO getinmuebleByIdOriginante(String usuario, Long id) {

		List<Inmueble> listaInmuebles = null;

		Gson js = new Gson();

		try {
			listaInmuebles = GenericUtils.callAndThrow(() -> inmuebleRepository.inmuebleByUserId(id), ex -> {
				return (RuntimeException) ex;
			});
		} catch (RuntimeException ex) {
			throw new RuntimeException(ex.getMessage());
		}

		InmuebleResponseDTO response = new InmuebleResponseDTO();

		if (listaInmuebles != null) {
			response.setCode("200");
			response.setMessage("Se recupero con exito los inmuebles");

			Inmuebles inmuebles;
			List<Inmuebles> listaInmueblesDto = new ArrayList<Inmuebles>();

			SocialDto socialDto;
			List<SocialDto> socialDtoList;

			for (Inmueble inmueble : listaInmuebles) {

				inmuebles = new Inmuebles();
				socialDtoList = new ArrayList<SocialDto>();

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
				inmuebles.setPuntuacion(inmueble.getPuntuacion());
				inmuebles.setMaxPersonas(inmueble.getMaxPersonas());
				inmuebles.setFechaDisponibilidad((java.sql.Date) inmueble.getFechaDisponibilidad());
				inmuebles.setHorariosDias(js.fromJson(inmueble.getHorariosDias(), HorariosDias.class));
				inmuebles.setHorariosReserva(js.fromJson(inmueble.getHorariosReserva(), List.class));

				inmuebles.setFechaNoDisponibles(js.fromJson(inmueble.getFechaNoDisponibles(), List.class));
				inmuebles.setEsPrecioFijo(inmueble.getEsPrecioFijo());
				inmuebles.setDisponibilidadPersonas(inmueble.getDisponibilidadPersonas());
				inmuebles.setPublicada(inmueble.getPublicada());
				inmuebles.setIdOriginante(String.valueOf(inmueble.getIdOriginante()));
				
				
				inmuebles.setApellidoContacto(inmueble.getApellidoContacto());
				inmuebles.setNombreContacto(inmueble.getNombreContacto());
				inmuebles.setTelefonoContacto(inmueble.getTelefonoContacto());
				
					
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

				if (!servicios.isEmpty())
					inmuebles.setServicios(servicios);

				List<String> serviciosSeguridad = js.fromJson(inmueble.getServicios().getServiciosSeguridad(),
						List.class);

				if (!serviciosSeguridad.isEmpty())
					inmuebles.setServiciosDeSeguridad(serviciosSeguridad);

				List<String> fotos = js.fromJson(inmueble.getFotos().getUrlFoto(), List.class);

				if (!fotos.isEmpty())
					inmuebles.setFotos(fotos);

				listaInmueblesDto.add(inmuebles);
			}

			response.setInmuebles(listaInmueblesDto);

		} else {
			response.setCode("404");
			response.setMessage("No existen inmuebles dados de alta por el usuario: " + usuario);
		}

		return response;

	}

	@Override
	public InmuebleResponseDTO saveByIdOriginante(String usuario, String userId, Inmuebles datosInmueble)
			throws Exception {

		Long id = null;

		Inmueble nuevoInmueble = new Inmueble();

		Gson js = new Gson();

		/* Se carga los valores del inmueble */
		nuevoInmueble.setCalle(datosInmueble.getDireccion().getCalle());
		nuevoInmueble.setAltura(datosInmueble.getDireccion().getAltura());
		nuevoInmueble.setCiudad(datosInmueble.getDireccion().getCiudad());
		nuevoInmueble.setDescripcion(datosInmueble.getDescripcion());
		nuevoInmueble.setFechaDisponibilidad(datosInmueble.getFechaDisponibilidad());
		nuevoInmueble.setFechaPublicacion(new Date());
		nuevoInmueble.setIdOriginante(Long.valueOf(userId));
		nuevoInmueble.setLocalidad(datosInmueble.getDireccion().getLocalidad());
		nuevoInmueble.setMaxPersonas(datosInmueble.getMaxPersonas());
		nuevoInmueble.setPais(datosInmueble.getDireccion().getPais());
		nuevoInmueble.setPrecio(datosInmueble.getPrecio());
		nuevoInmueble.setProvincia(datosInmueble.getDireccion().getProvincia());
		nuevoInmueble.setPuntuacion(datosInmueble.getPuntuacion());
		nuevoInmueble.setTipoLugar(datosInmueble.getTipoLugar());
		nuevoInmueble.setTitulo(datosInmueble.getTitulo());
		nuevoInmueble.setHabilitado(true);
		nuevoInmueble.setCp(datosInmueble.getDireccion().getCp());
		nuevoInmueble.setHorariosReserva(js.toJson(datosInmueble.getHorariosReserva()));
		nuevoInmueble.setHorariosDias(js.toJson(datosInmueble.getHorariosDias()));
		nuevoInmueble.setFechaNoDisponibles(js.toJson(datosInmueble.getFechaNoDisponibles()));
		nuevoInmueble.setEsPrecioFijo(datosInmueble.getEsPrecioFijo());
		nuevoInmueble.setDisponibilidadPersonas(datosInmueble.getMaxPersonas());
		nuevoInmueble.setPublicada(datosInmueble.getPublicada());
		

		nuevoInmueble.setApellidoContacto(datosInmueble.getApellidoContacto());
		nuevoInmueble.setNombreContacto(datosInmueble.getNombreContacto());
		nuevoInmueble.setTelefonoContacto(datosInmueble.getTelefonoContacto());
		
		Servicios servicio = new Servicios();

		String serviciosUrlJson = js.toJson(datosInmueble.getServicios().toArray());

		String serviciosSeguridadUrlJson = js.toJson(datosInmueble.getServiciosDeSeguridad().toArray());

		servicio.setServicios(serviciosUrlJson);
		servicio.setServiciosSeguridad(serviciosSeguridadUrlJson);
		servicio.setInmueble(nuevoInmueble);
		nuevoInmueble.setServicios(servicio);

		// -- Carga de url de fotos a la bd --//
		Fotos fotoUrl = new Fotos();
		String fotosUrlString = js.toJson(datosInmueble.getFotos().toArray());
		fotoUrl.setUrlFoto(fotosUrlString);
		nuevoInmueble.setFotos(fotoUrl);
		fotoUrl.setInmueble(nuevoInmueble);

		try {
			id = GenericUtils.callAndThrow(() -> inmuebleRepository.guardarInmueble(nuevoInmueble), ex -> {
				return (RuntimeException) ex;
			});
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}

		InmuebleResponseDTO response = new InmuebleResponseDTO();
		response.setCode("200");
		response.setMessage("Se guardo con exito el inmueble");

		return response;
	}

	@Override
	public InmuebleResponseDTO actualizarByIdOriginante(String usuario, String userId, String inmuebleId,
			Inmuebles datosInmueble) throws Exception {

		Long id = null;

		Gson js = new Gson();

		/* Se carga los valores del inmueble */
		Inmueble nuevoInmueble = new Inmueble();
		nuevoInmueble.setIdInmueble(Long.valueOf(inmuebleId));
		nuevoInmueble.setCalle(datosInmueble.getDireccion().getCalle());
		nuevoInmueble.setAltura(datosInmueble.getDireccion().getAltura());
		nuevoInmueble.setCiudad(datosInmueble.getDireccion().getCiudad());
		nuevoInmueble.setDescripcion(datosInmueble.getDescripcion());
		nuevoInmueble.setFechaDisponibilidad(datosInmueble.getFechaDisponibilidad());
		nuevoInmueble.setFechaPublicacion(new Date());
		nuevoInmueble.setIdOriginante(Long.valueOf(userId));
		nuevoInmueble.setLocalidad(datosInmueble.getDireccion().getLocalidad());
		nuevoInmueble.setMaxPersonas(datosInmueble.getMaxPersonas());
		nuevoInmueble.setPais(datosInmueble.getDireccion().getPais());
		nuevoInmueble.setPrecio(datosInmueble.getPrecio());
		nuevoInmueble.setProvincia(datosInmueble.getDireccion().getProvincia());
		nuevoInmueble.setPuntuacion(datosInmueble.getPuntuacion());
		nuevoInmueble.setTipoLugar(datosInmueble.getTipoLugar());
		nuevoInmueble.setTitulo(datosInmueble.getTitulo());
		nuevoInmueble.setHabilitado(true);
		nuevoInmueble.setCp(datosInmueble.getDireccion().getCp());
		nuevoInmueble.setHorariosReserva(js.toJson(datosInmueble.getHorariosReserva()));
		nuevoInmueble.setHorariosDias(js.toJson(datosInmueble.getHorariosDias()));
		nuevoInmueble.setFechaNoDisponibles(js.toJson(datosInmueble.getFechaNoDisponibles()));
		nuevoInmueble.setEsPrecioFijo(datosInmueble.getEsPrecioFijo());
		nuevoInmueble.setDisponibilidadPersonas(datosInmueble.getMaxPersonas());
		nuevoInmueble.setPublicada(datosInmueble.getPublicada());
		
		nuevoInmueble.setApellidoContacto(datosInmueble.getApellidoContacto());
		nuevoInmueble.setNombreContacto(datosInmueble.getNombreContacto());
		nuevoInmueble.setTelefonoContacto(datosInmueble.getTelefonoContacto());

		/* Se carga los servicios del inmueble */
		Servicios servicio = new Servicios();
		String serviciosUrlJson = js.toJson(datosInmueble.getServicios().toArray());
		String serviciosSeguridadUrlJson = js.toJson(datosInmueble.getServiciosDeSeguridad().toArray());
		servicio.setServicios(serviciosUrlJson);
		servicio.setServiciosSeguridad(serviciosSeguridadUrlJson);

		// -- Carga de url de fotos a la bd --//
		Fotos fotoUrl = new Fotos();
		String fotosUrlString = js.toJson(datosInmueble.getFotos().toArray());
		fotoUrl.setUrlFoto(fotosUrlString);

		int updateService = 0;
		int updatePhoto = 0;

		try {
			updateService = inmuebleRepository.updateService(inmuebleId, servicio);
			updatePhoto = inmuebleRepository.updatePhoto(inmuebleId, fotoUrl);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		if (updateService != 1 || updatePhoto != 1) {
			InmuebleResponseDTO response = new InmuebleResponseDTO();
			response.setCode("400");
			response.setMessage("servicios o fotos inexistente");
			return response;
		}

		try {
			id = GenericUtils.callAndThrow(
					() -> inmuebleRepository.actualizarInmueble(userId, inmuebleId, nuevoInmueble), ex -> {
						return (RuntimeException) ex;
					});
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}

		if (id != null) {
			InmuebleResponseDTO response = new InmuebleResponseDTO();
			response.setCode("200");
			response.setMessage("Se actualizo con exito el inmueble");
			return response;
		}

		InmuebleResponseDTO response = new InmuebleResponseDTO();
		response.setCode("400");
		response.setMessage("No se encuentra el inmueble seleccionado");

		return response;
	}

	@Override
	public InmuebleResponseDTO deleteByIdOriginante(String userId, String idInmueble) {

		int response_;

		try {
			response_ = GenericUtils.callAndThrow(() -> inmuebleRepository.bajaInmueble(userId, idInmueble), ex -> {

				return (RuntimeException) ex;
			});
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}

		if (response_ == 1) {
			InmuebleResponseDTO response = new InmuebleResponseDTO();
			response.setCode("200");
			response.setMessage("Se deshabilito con exito el inmueble: " + userId);
			return response;
		}
		InmuebleResponseDTO response = new InmuebleResponseDTO();
		response.setCode("400");
		response.setMessage("No se encuentra el inmueble seleccionado");

		return response;
	}
}
