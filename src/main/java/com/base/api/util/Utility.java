package com.base.api.util;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;


import lombok.extern.slf4j.Slf4j;

/**
 * Clase de métodos utilitarios de la aplicación.
 * @author Aalejo
 *
 */
@Service
@Slf4j
public class Utility {

	
	/**
	 * Metodo utilitario para generar una clave dinamica.
	 * @return String
	 * @throws ParseException
	 */
	public  String generateRandomPassword(int len) throws ParseException{
		// Rango ASCII – alfanumérico (0-9, a-z, A-Z)
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		// cada iteración del bucle elige aleatoriamente un carácter del dado
		// rango ASCII y lo agrega a la instancia `StringBuilder`

		for (int i = 0; i < len; i++)
		{
			int randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}

		return sb.toString();
	}
	
	/**
	 * Metodo utilitario para validar el Regex del email.
	 * @return boolean
	 * @throws ParseException
	 */
	public boolean validateEmailDomain(String email) {
		boolean validated= false;
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = pattern.matcher(email);

		if (matcher.find() == true) {
			log.info("Format Valid");
			validated= true;
		} else {
			log.info("Not Format Invalid");
		}

		return validated;
	}

	/**
	 * Metodo para transformar una fecha tipo Date en una fecha String formato DD-MM-YYYY
	 * @param date
	 * @return DD-MM-YYYY
	 * @throws ParseException
	 */
	public String DateToStringFormatterDash (Date date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Integer day = calendar.get(Calendar.DAY_OF_MONTH);
		Integer month = calendar.get(Calendar.MONTH)+1;
		Integer year = calendar.get(Calendar.YEAR);
		String dayString;
		String monthString;
		if (day < 10) {
			dayString = 0+day.toString();
		}else {
			dayString = day.toString();
		}
		if (month < 10) {
			monthString = 0+month.toString();
		}else {
			monthString = month.toString();
		}			
		String dateFormated = dayString+"-"+monthString+"-"+year.toString();
		return dateFormated;
	}


	/**
	 * Metodo para transformar una fecha tipo Date en una fecha String formato DDMMYYYYHHMMSS
	 * @param date
	 * @return DD-MM-YYYY
	 * @throws ParseException
	 */
	public String DateToStringFormatterNoDash (Date date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Integer day = calendar.get(Calendar.DAY_OF_MONTH);
		Integer month = calendar.get(Calendar.MONTH)+1;
		Integer year = calendar.get(Calendar.YEAR);
		String dayString;
		String monthString;
		if (day < 10) {
			dayString = 0+day.toString();
		}else {
			dayString = day.toString();
		}
		if (month < 10) {
			monthString = 0+month.toString();
		}else {
			monthString = month.toString();
		}			
		String dateFormated = dayString+monthString+year.toString();
		return dateFormated;
	}


	/**
	 * Metodo que convierte una fecha tipo String a una fecha Date.
	 * @param dateString
	 * @return dateFormated
	 * @throws ParseException
	 */
	public Date StringToDateFormatter (String dateString) throws ParseException {
		SimpleDateFormat formatter=new SimpleDateFormat("dd-MM-yyyy");  
		Date dateFormated = formatter.parse(dateString);
		return dateFormated;
	}


	/***
	 * Metodo que genera un codigo unico haciendo uso del momento exacto de su creacion.
	 * 
	 * 
	 * @return code
	 * @throws Exception
	 */
	public String generatedUniqueCode() throws Exception {

		String dateString=DateToStringFormatterNoDash(new Date());
		LocalTime horaActual = LocalTime.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("HHmmss");
		String horaFormateada = horaActual.format(formato);
		log.info("horaFormateada: {}",horaFormateada);

		String code=dateString+horaFormateada;
		return code;
	}
	
}
