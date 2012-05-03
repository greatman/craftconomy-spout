package org.greatmancode.craftconomy.configuration;

import java.io.File;

import org.spout.api.exception.ConfigurationException;
import org.spout.api.util.config.ConfigurationHolder;
import org.spout.api.util.config.ConfigurationHolderConfiguration;
import org.spout.api.util.config.yaml.YamlConfiguration;

public class CraftconomyConfiguration extends ConfigurationHolderConfiguration {

	//General
	/**
	 * Contains the holdings when creating a new account.
	 */
	public static final ConfigurationHolder NEW_ACCOUNT_HOLDING = new ConfigurationHolder(30.0, "general", "defaultBalance");
	
	public static final ConfigurationHolder CURRENCY_NAME = new ConfigurationHolder("Dollar", "general", "currencyName");
	
	public static final ConfigurationHolder SAVE_INTERVAL = new ConfigurationHolder((60 * 20), "general", "save-interval");
	
	public CraftconomyConfiguration(File dataFolder) {
		super(new YamlConfiguration(new File(dataFolder, "config.yml")));

	}
	
	@Override
	public void load() throws ConfigurationException {
		super.load();
		super.save();
	}
	
	@Override
	public void save() throws ConfigurationException {
		super.save();
	}
	
}
