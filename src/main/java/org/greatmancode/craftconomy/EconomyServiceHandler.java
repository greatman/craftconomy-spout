package org.greatmancode.craftconomy;

import org.spout.api.plugin.services.EconomyService;

public class EconomyServiceHandler extends EconomyService {

	@Override
	public boolean deposit(String playerName, double amount) {
		boolean result = false;
		PlayerAccount account = CraftconomyPlugin.getAccount(playerName);
		if (account != null)
		{
			account.deposit(amount);
			result = true;
		}

		return result;
	}

	@Override
	public double get(String playerName) {
		PlayerAccount account = CraftconomyPlugin.getAccount(playerName);
		double amount = 0.0;
		if (account != null)
		{
			amount = account.getBalance();
		}
		
		return amount;
	}

	@Override
	public boolean has(String playerName, double amount) {
		boolean result = false;
		PlayerAccount account = CraftconomyPlugin.getAccount(playerName);
		if (account != null)
		{
			result = account.has(amount);
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public boolean withdraw(String playerName, double amount) {
		boolean result = false;
		PlayerAccount account = CraftconomyPlugin.getAccount(playerName);
		if (account != null)
		{
			account.withdraw(amount);
			result = true;
		}
		return result;
	}

}
