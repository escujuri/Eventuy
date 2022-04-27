package ar.com.eventsocial.backend.repository;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import ar.com.eventsocial.backend.model.Consult;
import ar.com.eventsocial.backend.repository.contract.ISengridRepository;

@Repository("SengridRepository")
public class SengridRepository implements ISengridRepository {

	final String SENDGRID_API_KEY = "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";

	final String TEMPLATE_ID_CONSULT = "d-f0aa934a3b4c41508e535e913d56cb0b";

	final String TEMPLATE_ID_WELCOME = "d-48ea7aba25784e3dbe87cf79914e0b85";

	@Override
	public void postSendEmail(String email) throws IOException {

		Email from = new Email("eventuy.app@gmail.com");
		Email to = new Email(email.trim());

		Personalization personalization = new Personalization();
		personalization.addBcc(from);
		personalization.addTo(to);

		HashMap<String, String> map = new HashMap<String, String>();

		// map.put("body", consultRequest.getMensaje());
		// map.put("subject", consultRequest.getAsunto());
		// map.put("nombre", consultRequest.getNombre());

		personalization.addDynamicTemplateData("data", map);

		Mail mail = new Mail();
		mail.setFrom(from);
		mail.setReplyTo(to);
		mail.setTemplateId(TEMPLATE_ID_WELCOME);
		mail.addPersonalization(personalization);

		SendGrid sg = new SendGrid(SENDGRID_API_KEY);
		Request request = new Request();

		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());

			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());

		} catch (IOException ex) {
			throw ex;
		}

	}

	@Override
	public void postSendEmailConsult(Consult consultRequest) throws IOException {

		Email from = new Email("eventuy.app@gmail.com");
		Email to = new Email(consultRequest.getEmail().trim());

		Personalization personalization = new Personalization();
		personalization.addBcc(from);
		personalization.addTo(to);

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("body", consultRequest.getMensaje());
		map.put("subject", consultRequest.getAsunto());
		map.put("nombre", consultRequest.getNombre());

		personalization.addDynamicTemplateData("data", map);

		Mail mail = new Mail();
		mail.setFrom(from);
		mail.setReplyTo(to);
		mail.setTemplateId(TEMPLATE_ID_CONSULT);
		mail.addPersonalization(personalization);

		SendGrid sg = new SendGrid(SENDGRID_API_KEY);
		Request request = new Request();

		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());

			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());

		} catch (IOException ex) {
			throw ex;
		}
	}

}
