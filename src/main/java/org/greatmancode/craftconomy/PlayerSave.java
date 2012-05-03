package org.greatmancode.craftconomy;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.greatmancode.craftconomy.utils.PlayerLoader;

public class PlayerSave implements Runnable {

	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Saving accounts...");
		Set<Entry<String, PlayerAccount>> entryset = CraftconomyPlugin.accountList.entrySet();
		Iterator<Entry<String, PlayerAccount>> iterator = entryset.iterator();
		while(iterator.hasNext())
		{
			Entry<String, PlayerAccount> entry = iterator.next();
			PlayerAccount account = entry.getValue();
			if (account.isSave())
			{
				System.out.println("Saving " + account.getPlayerName());
				PlayerLoader.playerSave(account);
				account.setSave(false);
			}
		}
	}

}
