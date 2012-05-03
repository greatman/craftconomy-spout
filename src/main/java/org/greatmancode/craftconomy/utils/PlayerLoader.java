package org.greatmancode.craftconomy.utils;

import java.io.File;

import org.greatmancode.craftconomy.PlayerAccount;
import org.spout.api.util.config.ConfigurationNode;
import org.spout.api.util.config.yaml.YamlConfiguration;

public class PlayerLoader {

	public static File dataFolder = null;
	
	public static PlayerAccount accountLoader(String playerName)
	{
		YamlConfiguration playerFile = new YamlConfiguration(new File(dataFolder, playerName + ".yml"));
		return new PlayerAccount(playerName, playerFile.getNode("player.balance").getDouble());
	}
	
	public static void playerSave(PlayerAccount playerAccount)
	{
		YamlConfiguration playerFile = new YamlConfiguration(new File(dataFolder, playerAccount.getPlayerName() + ".yml"));
		ConfigurationNode confNode = playerFile.getNode("player.balance");
		confNode.setValue(playerAccount.getBalance());
		playerFile.setNode(confNode);
		
	}
}
