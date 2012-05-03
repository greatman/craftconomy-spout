package org.greatmancode.craftconomy;

public class PlayerAccount {

	private String playerName;
	private double balance;
	private boolean save = false;
	
	public PlayerAccount(String playerName, double balance)
	{
		setPlayerName(playerName);
		this.balance = balance;
	}
	
	public double setBalance(double amount)
	{
		this.balance = amount;
		return this.balance;
	}
	/**
	 * Deposit money in a player account
	 * @param amount The amount to deposit
	 * @return The balance after the change.
	 */
	public double deposit(double amount)
	{
		balance += amount;
		setSave(true);
		return balance;
	}
	
	/**
	 * Withdraw money from a player account
	 * @param amount The amount to withdraw
	 * @return The balance after the change
	 */
	public double withdraw(double amount)
	{
		balance -= amount;
		setSave(true);
		return balance;
	}
	
	/**
	 * Checks if the player has enough money
	 * @param amount The amount of money to check
	 * @return True if the player have enough money else false
	 */
	public boolean has(double amount)
	{
		boolean result = false;
		if (balance >= amount)
		{
			result = true;
		}
		return result;
	}
	
	public double getBalance()
	{
		return balance;
	}

	public String getPlayerName() {
		return playerName;
	}

	private void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public boolean isSave() {
		return save;
	}
	public void setSave(boolean save) {
		this.save = save;
	}
}
