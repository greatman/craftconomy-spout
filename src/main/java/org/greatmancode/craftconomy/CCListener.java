package org.greatmancode.craftconomy;

import org.greatmancode.craftconomy.configuration.CraftconomyConfiguration;
import org.greatmancode.craftconomy.utils.CCLogger;
import org.spout.api.event.EventHandler;
import org.spout.api.event.Listener;
import org.spout.api.event.Order;
import org.spout.api.event.player.PlayerJoinEvent;
import org.spout.api.player.Player;

public class CCListener implements Listener {

	@EventHandler(order = Order.DEFAULT)
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player p = event.getPlayer();
		CCLogger.info(p.getName());
		if (!CraftconomyPlugin.accountList.containsKey(p.getName()))
		{
			PlayerAccount account = new PlayerAccount(p.getName(), CraftconomyConfiguration.NEW_ACCOUNT_HOLDING.getDouble());
			CraftconomyPlugin.accountList.put(p.getName(), account);
			account.setSave(true);
		}
	}
}
