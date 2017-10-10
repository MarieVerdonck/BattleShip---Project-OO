package model.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.security.auth.login.Configuration;

import model.DomainException;
import model.Strategy.HitShipStrategy;

/**
 * 
 * @author Dries Janse
 *
 */
public class HitShipFactory {

	public HitShipStrategy getHitShipStrategy() {
		Properties properties = new Properties();
		InputStream input;
		try {
			input = new FileInputStream(new File("src/StrategyProperties.properties"));
			properties.load(input);
		} catch (IOException e) {
			throw new DomainException("Properties file niet gevonden (HitShipFactory)");
		}

		String className = (String) properties.get("hitShipStrategy");

		try {
			Class<?> clazz = Class.forName(className);
			return (HitShipStrategy) clazz.newInstance();
		} catch (Exception e) {
			throw new DomainException("Strategy niet gevonden (PlaceShipFactory)");
		}

	}

}