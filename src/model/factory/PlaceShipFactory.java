package model.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.security.auth.login.Configuration;

import model.DomainException;
import model.Strategy.PlaceShipStrategy;
/**
 * 
 * @author Dries Janse
 *
 */
public class PlaceShipFactory {

	public PlaceShipStrategy getPlaceShipStrategy() {
		Properties properties = new Properties();
		InputStream input;
		try {
			input = new FileInputStream(new File("src/StrategyProperties.properties"));
			properties.load(input);
		} catch (IOException e) {
			throw new DomainException("Properties file niet gevonden");
		}

		String className = (String) properties.get("placeShipStrategy");

		try {
			Class<?> clazz = Class.forName(className);
			return (PlaceShipStrategy) clazz.newInstance();
		} catch (Exception e) {
			throw new DomainException("Strategy niet gevonden");
		}

	}

}
