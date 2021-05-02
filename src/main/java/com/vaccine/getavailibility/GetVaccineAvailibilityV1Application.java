package com.vaccine.getavailibility;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Timer;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.vaccine.getavailibility.configuration.PropertiesCache;
import com.vaccine.getavailibility.model.Center;
import com.vaccine.getavailibility.model.MailMessageRequest;
import com.vaccine.getavailibility.model.Response;
import com.vaccine.getavailibility.model.Root;
import com.vaccine.getavailibility.model.Session;
import com.vaccine.getavailibility.service.MailServiceImpl;

@SpringBootApplication
public class GetVaccineAvailibilityV1Application {
	
	



	public static void main(String[] args) throws ParseException, AddressException, MessagingException, IOException {

		
		GetVaccineAvailibilityV1Application getVaccineAvailibilityV1Application = new GetVaccineAvailibilityV1Application();
		try {
			while (true) {
				String formattedDate = getVaccineAvailibilityV1Application.changeDate();
				try {
					getVaccineAvailibilityV1Application.makeAvailibilityCall(formattedDate, PropertiesCache.getInstance().getProperty("spring.cowin.districtidarray").split(","));
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Thread.sleep(Integer.parseInt(PropertiesCache.getInstance().getProperty("execution.interval")) * 60000); // every 2 mins
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		;

	}

	private String changeDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = formatter.format(date);
		System.out.println(formattedDate);
		return formattedDate;
	}

	void makeAvailibilityCall(String date, String[] districtId)
			throws AddressException, MessagingException, IOException {
		

	


		List<Response> responseList = new ArrayList();
		HttpHeaders headers = new HttpHeaders();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(headers);

		boolean responseBool = false;
		for (String str : Arrays.asList(districtId)) {

			String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id="
					+ str + "&date=" + date;
			System.out.print(url);
			ResponseEntity<Root> result = restTemplate.exchange(url, HttpMethod.GET, request, Root.class);

			for (Center center : result.getBody().centers) {
				for (Session session : center.sessions) {
					if (session.min_age_limit < 45 && session.available_capacity > 0) {
						Response response = new Response();
						response.setSessionmin_age_limit(session.min_age_limit);
						response.setCentername("Center Name is::" + center.name + " :District::" + center.district_name
								+ "-" + session.vaccine);
						response.setSessiondate("::Session date is::" + session.date);
						response.setAvailablecapacity(session.available_capacity);
						responseBool = true;
						System.out.println("Center name is ::" + center.name + ":" + center.district_name
								+ "::Available capacity::" + session.available_capacity + "::: Date-->" + session.date
								+ "::" + "Minimum Age Limit::" + session.min_age_limit);
						responseList.add(response);

					}
				}

			}
		}
		
		

		System.out.println("responseBool::" + responseBool + new Date().toGMTString() +"::Size::"+responseList.size());
		if (responseBool) {

			sendEmail(responseList);
		}
	}

	void sendEmail(List<Response> responseList) throws AddressException, MessagingException, IOException {
		MailServiceImpl mailServiceImpl = new MailServiceImpl();
		mailServiceImpl.sendmail(responseList);

	}

}
