package ar.com.eventsocial.backend.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.eventsocial.backend.dto.BookingResponseDTO;
import ar.com.eventsocial.backend.dto.PaymentResponseDTO;
import ar.com.eventsocial.backend.model.GenericUtils;
import ar.com.eventsocial.backend.model.MercadoPagoRequest;
import ar.com.eventsocial.backend.model.StatusChangePaymentMP;
import ar.com.eventsocial.backend.security.jwt.ITokenExtractor;
import ar.com.eventsocial.backend.security.jwt.JwtTokenUtil;
import ar.com.eventsocial.backend.service.PaymentsService.StatusOwnerApproval;
import ar.com.eventsocial.backend.service.contract.IPagoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@PreAuthorize("authenticated")
@CrossOrigin
@RestController
@RequestMapping(value = "v1/payments", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(value = "", tags = "Estos metodos realizan un ABM de los inmuebles", protocols = "https")
public class JwtPayments {

	@Autowired
	private IPagoService IpagoService;

	@Autowired
	@Qualifier("jwtHeaderTokenExtractor")
	private ITokenExtractor tokenExtractor;

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/MP", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Se realiza el pago via mercado pago devolviendo la url de re direccionamiento")
	@ResponseBody
	public ResponseEntity<PaymentResponseDTO> paymentMP(HttpServletRequest request,
			@RequestBody(required = true) @Validated MercadoPagoRequest dataPayment) throws Exception {

		String userId = "";
		String email = "";

		String tokenPayload = tokenExtractor.Extract(request.getHeader(JwtTokenUtil.TOKEN_HEADER));
		Map<String, Object> claims = tokenExtractor.ReadToken(tokenPayload);

		if (claims != null && !claims.isEmpty()) {
			if (GenericUtils.getValueFromKeyValue(claims, "userId") != null) {
				userId = GenericUtils.getValueFromKeyValue(claims, "userId").toString();
			}
			if (GenericUtils.getValueFromKeyValue(claims, "email") != null) {
				email = GenericUtils.getValueFromKeyValue(claims, "email").toString();
			}
		}

		try {
			final PaymentResponseDTO _response = IpagoService.paymentsMPService(dataPayment, userId, email);
			return new ResponseEntity<PaymentResponseDTO>(_response, HttpStatus.OK);

		} catch (Exception e) {
			PaymentResponseDTO _response = new PaymentResponseDTO();
			_response.setCode("400");
			_response.setMessage(e.getMessage());
			return new ResponseEntity<PaymentResponseDTO>(_response, HttpStatus.BAD_REQUEST);
		}

	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/booking", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Devuelve todas las reservas realizadas por el usuario")
	@ResponseBody
	public ResponseEntity<BookingResponseDTO> getBookings(HttpServletRequest request) throws Exception {

		String userId = "";

		String tokenPayload = tokenExtractor.Extract(request.getHeader(JwtTokenUtil.TOKEN_HEADER));
		Map<String, Object> claims = tokenExtractor.ReadToken(tokenPayload);

		if (claims != null && !claims.isEmpty()) {
			if (GenericUtils.getValueFromKeyValue(claims, "userId") != null) {
				userId = GenericUtils.getValueFromKeyValue(claims, "userId").toString();
			}
		}

		try {
			final BookingResponseDTO _response = IpagoService.getBookingsService(userId);
			return new ResponseEntity<BookingResponseDTO>(_response, HttpStatus.OK);

		} catch (Exception e) {
			BookingResponseDTO _response = new BookingResponseDTO();
			_response.setCode("400");
			_response.setMessage(e.getMessage());
			return new ResponseEntity<BookingResponseDTO>(_response, HttpStatus.BAD_REQUEST);
		}

	}
	
	
	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/booking/owner", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Devuelve todas las reservas realizadas en mis inmuebles")
	@ResponseBody
	public ResponseEntity<BookingResponseDTO> getBookingsOwner(HttpServletRequest request) throws Exception {

		String userId = "";

		String tokenPayload = tokenExtractor.Extract(request.getHeader(JwtTokenUtil.TOKEN_HEADER));
		Map<String, Object> claims = tokenExtractor.ReadToken(tokenPayload);

		if (claims != null && !claims.isEmpty()) {
			if (GenericUtils.getValueFromKeyValue(claims, "userId") != null) {
				userId = GenericUtils.getValueFromKeyValue(claims, "userId").toString();
			}
		}

		try {
			final BookingResponseDTO _response = IpagoService.getBookingsOwnerUserIdService(userId);
			return new ResponseEntity<BookingResponseDTO>(_response, HttpStatus.OK);

		} catch (Exception e) {
			BookingResponseDTO _response = new BookingResponseDTO();
			_response.setCode("400");
			_response.setMessage(e.getMessage());
			return new ResponseEntity<BookingResponseDTO>(_response, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "/statusChange/MP", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Se realiza el pago via mercado pago devolviendo la url de re direccionamiento")
	@ResponseBody
	public ResponseEntity<Object> statuChangePayment(HttpServletRequest request,
			@RequestBody(required = true) @Validated StatusChangePaymentMP statusChangeInfo) throws Exception {

		try {

			IpagoService.statusChangeMPService(statusChangeInfo);

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			PaymentResponseDTO _response = new PaymentResponseDTO();
			_response.setCode("400");
			_response.setMessage(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/statusChange/approval/{paymentId}/status/{status}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Se realiza el pago via mercado pago devolviendo la url de re direccionamiento")
	@ResponseBody
	public ResponseEntity<Object> statuChangeApproval(HttpServletRequest request,
			@PathVariable("paymentId") String paymentId,
			@PathVariable("status") StatusOwnerApproval status
			) throws Exception {

		try {

			IpagoService.statusChangeApprovalService(paymentId, status);

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			PaymentResponseDTO _response = new PaymentResponseDTO();
			_response.setCode("400");
			_response.setMessage(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
