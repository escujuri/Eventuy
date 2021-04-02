package ar.com.eventsocial.backend.service_;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.eventsocial.backend.dto.InmuebleResponseDTO;
import ar.com.eventsocial.backend.logs.LogMaker;
import ar.com.eventsocial.backend.model.GenericUtils;
import ar.com.eventsocial.backend.model.Inmueble;
import ar.com.eventsocial.backend.model.Inmuebles;
import ar.com.eventsocial.backend.model.Servicios;
import ar.com.eventsocial.backend.repository.contract.IInmuebleRepository;
import ar.com.eventsocial.backend.repository_.StorageRepository;
import ar.com.eventsocial.backend.service.contract.IInmubleService;

@Service("InmuebleService")
public class InmuebleService implements IInmubleService {

	private static final LogMaker log = new LogMaker();

	@Autowired
	IInmuebleRepository inmuebleRepository;

	@Override
	public InmuebleResponseDTO inmuebleByIdOriginante(String usuario, Long id) {

		List<Inmueble> listaInmuebles = null;

		try {
			listaInmuebles = GenericUtils.callAndThrow(() -> inmuebleRepository.inmuebleByUserId(id), ex -> {
				log.ErrorRecibe("solicitudService", "getCreditoHipotecarioProcrearByUser", ex.getMessage());
				return (RuntimeException) ex;
			});
		} catch (RuntimeException ex) {
			throw new RuntimeException(ex.getMessage());
		}

		InmuebleResponseDTO response = new InmuebleResponseDTO();

		if (listaInmuebles != null) {
			response.setCode("200");
			response.setMessage("Se recupero con exito los inmuebles");
			response.setInmuebles(listaInmuebles);

		} else {
			response.setCode("404");
			response.setMessage("No existen inmuebles dados de alta por el usuario: " + usuario);
		}

		return response;

	}

	@Override
	public InmuebleResponseDTO guardarByIdOriginante(String usuario, String userId, Inmuebles datosInmueble)
			throws Exception {

		Long id = null;

		Inmueble nuevoInmueble = new Inmueble();

		/* Se carga los valores del inmueble */
		nuevoInmueble.setCalle(datosInmueble.getCalle());
		nuevoInmueble.setAltura(datosInmueble.getAltura());
		nuevoInmueble.setCiudad(datosInmueble.getCiudad());
		nuevoInmueble.setDescripcion(datosInmueble.getDescripcion());
		nuevoInmueble.setFechaDisponibilidad(datosInmueble.getFechaDisponibilidad());
		nuevoInmueble.setFechaPublicacion(new Date());
		nuevoInmueble.setIdOriginante(Long.valueOf(userId)); // Se guarda con el id del usuario para vincularlo
		nuevoInmueble.setLocalidad(datosInmueble.getLocalidad());
		nuevoInmueble.setMaxPersonas(datosInmueble.getMaxPersonas());
		nuevoInmueble.setPais(datosInmueble.getPais());
		nuevoInmueble.setPrecio(datosInmueble.getPrecio());
		nuevoInmueble.setProvincia(datosInmueble.getProvincia());
		nuevoInmueble.setPuntuacion(datosInmueble.getPuntuacion());
		nuevoInmueble.setTipoLugar(datosInmueble.getTipoLugar());
		nuevoInmueble.setTitulo(datosInmueble.getTitulo());
		nuevoInmueble.setUbicacion(datosInmueble.getUbicacion());
		nuevoInmueble.setHabilitado(true);

		Servicios servicio = new Servicios();

		/* Se carga los servicios del inmueble */
		servicio.setCatering(datosInmueble.getCatering());
		servicio.setDj(datosInmueble.getDj());
		servicio.setInmueble(nuevoInmueble);
		servicio.setMascotas(datosInmueble.getMascotas());
		servicio.setParrilla(datosInmueble.getParrilla());
		servicio.setQuincho(datosInmueble.getQuincho());
		servicio.setPiscina(datosInmueble.getPiscina());
		servicio.setWifi(datosInmueble.getQuincho());

		nuevoInmueble.setServicios(servicio);

		try {
			id = GenericUtils.callAndThrow(() -> inmuebleRepository.guardarInmueble(nuevoInmueble), ex -> {
				log.ErrorRecibe("InmuebleService", "guardarByIdOriginante", ex.getMessage());
				return (RuntimeException) ex;
			});
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}

		InmuebleResponseDTO response = new InmuebleResponseDTO();
		response.setCode("200");
		response.setMessage("Se guardo con exito el inmueble");

		
		StorageRepository test =new StorageRepository();
		
		
		
		
		try {
			File a = test.convertToFile(datosInmueble.getMultipartFile(), "test");
			test.subirImagen(a, "test");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
		
		
		
		
		
		
		
		
		
		return response;
	}

	@Override
	public InmuebleResponseDTO actualizarByIdOriginante(String usuario, String userId, String inmuebleId,
			String idServicio, Inmuebles datosInmueble) throws Exception {

		Long id = null;

		/* Se carga los valores del inmueble */
		Inmueble nuevoInmueble = new Inmueble();
		nuevoInmueble.setCalle(datosInmueble.getCalle());
		nuevoInmueble.setAltura(datosInmueble.getAltura());
		nuevoInmueble.setCiudad(datosInmueble.getCiudad());
		nuevoInmueble.setDescripcion(datosInmueble.getDescripcion());
		nuevoInmueble.setFechaDisponibilidad(datosInmueble.getFechaDisponibilidad());
		nuevoInmueble.setFechaPublicacion(new Date());
		nuevoInmueble.setIdOriginante(Long.valueOf(userId)); // Se guarda con el id del usuario para vincularlo
		nuevoInmueble.setLocalidad(datosInmueble.getLocalidad());
		nuevoInmueble.setMaxPersonas(datosInmueble.getMaxPersonas());
		nuevoInmueble.setPais(datosInmueble.getPais());
		nuevoInmueble.setPrecio(datosInmueble.getPrecio());
		nuevoInmueble.setProvincia(datosInmueble.getProvincia());
		nuevoInmueble.setPuntuacion(datosInmueble.getPuntuacion());
		nuevoInmueble.setTipoLugar(datosInmueble.getTipoLugar());
		nuevoInmueble.setTitulo(datosInmueble.getTitulo());
		nuevoInmueble.setUbicacion(datosInmueble.getUbicacion());
		nuevoInmueble.setHabilitado(true);

		/* Se carga los servicios del inmueble */
		Servicios servicio = new Servicios();
		servicio.setId(Long.valueOf(idServicio));
		servicio.setCatering(datosInmueble.getCatering());
		servicio.setDj(datosInmueble.getDj());
		servicio.setMascotas(datosInmueble.getMascotas());
		servicio.setParrilla(datosInmueble.getParrilla());
		servicio.setQuincho(datosInmueble.getQuincho());
		servicio.setPiscina(datosInmueble.getPiscina());
		servicio.setWifi(datosInmueble.getQuincho());

		nuevoInmueble.setId(Long.valueOf(inmuebleId));
		nuevoInmueble.setServicios(servicio);
		servicio.setInmueble(nuevoInmueble);

		try {
			id = GenericUtils.callAndThrow(
					() -> inmuebleRepository.actualizarInmueble(userId, inmuebleId, nuevoInmueble, servicio), ex -> {
						log.ErrorRecibe("InmuebleService", "actualizarByIdOriginante", ex.getMessage());
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
	public InmuebleResponseDTO bajayIdOriginante(String userId, String idInmueble) {

		int response_;
		
		try {
			response_ = GenericUtils.callAndThrow(
					() -> inmuebleRepository.bajaInmueble(userId, idInmueble), ex -> {
						log.ErrorRecibe("InmuebleService", "actualizarByIdOriginante", ex.getMessage());
						return (RuntimeException) ex;
					});
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
		

		if (response_== 1) {
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
