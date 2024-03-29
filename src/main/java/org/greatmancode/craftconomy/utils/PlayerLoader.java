package org.greatmancode.craftconomy.utils;

import java.io.File;

import org.greatmancode.craftconomy.PlayerAccount;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.util.config.ConfigurationNode;
import org.spout.api.util.config.yaml.YamlConfiguration;

public class PlayerLoader {

	public static File dataFolder = null;
	
	public static PlayerAccount accountLoader(String playerName)
	{
		PlayerAccount account = null;
		YamlConfiguration playerFile = new YamlConfiguration(new File(dataFolder, playerName + ".yml"));
		try {
			playerFile.load();
			account = new PlayerAccount(playerName, playerFile.getNode("player.balance").getDouble());
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return account;
	}
	
	public static void playerSave(PlayerAccount playerAccount)
	{
		YamlConfiguration playerFile = new YamlConfiguration(new File(dataFolder, playerAccount.getPlayerName() + ".yml"));
		ConfigurationNode confNode = playerFile.getNode("player.balance");
		confNode.setValue(playerAccount.getBalance());
		playerFile.setNode(confNode);
		try {
			playerFile.save();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
