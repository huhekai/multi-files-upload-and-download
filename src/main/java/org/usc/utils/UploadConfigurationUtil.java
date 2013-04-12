package org.usc.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.event.ConfigurationEvent;
import org.apache.commons.configuration.event.ConfigurationListener;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 *
 * @author Shunli
 */
public class UploadConfigurationUtil implements ConfigurationListener {
	private static PropertiesConfiguration config;
	private static Map<String, Object> cache = new HashMap<String, Object>();

	static {
		try {
			config = new PropertiesConfiguration("upload.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		config.setReloadingStrategy(new FileChangedReloadingStrategy());
		// config.addConfigurationListener(new UploadConfigurationUtil());
		config.addConfigurationListener(new UploadConfigurationUtil());

	}

	public static PropertiesConfiguration getConfig() {
		return config;
	}

	public static String getProperty(String key) {
		// dummy op for activing reload configuration
		config.getProperties("dummy");

		if (cache.containsKey(key)) {
			// System.out.println("hide in cache");
			return cache.get(key).toString();
		}

		// System.out.println("get Property");
		String value = config.getString(key);
		cache.put(key, value);
		return value;
	}

	@Override
	public void configurationChanged(ConfigurationEvent configurationevent) {
		// System.out.println("clear cache...");
		cache.clear();
	}

}
