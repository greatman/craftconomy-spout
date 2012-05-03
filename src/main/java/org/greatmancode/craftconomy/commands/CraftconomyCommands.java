package org.greatmancode.craftconomy.commands;

import org.greatmancode.craftconomy.CraftconomyPlugin;
import org.greatmancode.craftconomy.PlayerAccount;
import org.greatmancode.craftconomy.configuration.CraftconomyConfiguration;
import org.greatmancode.craftconomy.utils.TextUtil;
import org.spout.api.ChatColor;
import org.spout.api.Spout;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.command.annotated.CommandPermissions;
import org.spout.api.exception.CommandException;
import org.spout.api.player.Player;

public class CraftconomyCommands {
	
	private final CraftconomyPlugin plugin;

	public CraftconomyCommands(CraftconomyPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Command(aliases = { "pay" }, desc = "", min = 2, max = 2, usage = "<Player Name> <Amount>")
	@CommandPermissions("craftconomy.money.pay")
	public void payCommand(CommandContext args, CommandSource source) throws CommandException
	{
		if (source instanceof Player)
		{
			Player p = (Player) source;
			if (CraftconomyPlugin.accountList.containsKey(p.getName()))
			{
				if (CraftconomyPlugin.accountList.containsKey(args.getString(0)))
				{
					PlayerAccount senderAccount = CraftconomyPlugin.accountList.get(p.getName());
					PlayerAccount receiverAccount = CraftconomyPlugin.accountList.get(args.getString(0));
					if (senderAccount.has(args.getDouble(1)))
					{
						senderAccount.withdraw(args.getDouble(1));
						receiverAccount.deposit(args.getDouble(1));
						p.sendMessage(TextUtil.generateWithPrefix(args.getDouble(1) + " " + CraftconomyConfiguration.CURRENCY_NAME.getString() + " has been sent to " + args.getString(0)));
						if (Spout.getEngine().getPlayer(args.getString(0), true) != null)
						{
							Spout.getEngine().getPlayer(args.getString(0), true).sendMessage(TextUtil.generateWithPrefix("Received " + args.getDouble(1) + " " + CraftconomyConfiguration.CURRENCY_NAME.getString() + " from " + p.getName()));
						}
					}
					else
					{
						throw new CommandException("You don't have enough money!");
					}
				}
				else
				{
					throw new CommandException("This player doesn't have a account!");
				}		
			}
			else
			{
				throw new CommandException("You have no account!");
			}
			
		}
		else
		{
			throw new CommandException("You need to be a player to use this command!");
		}
	}
	
	@Command(aliases = { "create" }, desc = "Create a new account", min = 1, max = 1, usage = "<Player Name>")
	@CommandPermissions("craftconomy.admin.create")
	public void createCommand(CommandContext args, CommandSource source) throws CommandException
	{
		if (CraftconomyPlugin.createAccount(args.getString(0)))
		{
			source.sendMessage(ChatColor.DARK_GREEN + "Account created!");
		}
		else
		{
			throw new CommandException("This name already exist!");
		}
	}
	
	@Command(aliases = { "give" }, desc = "", min = 2, max = 2,  usage = "<Player Name> <Amount>")
	@CommandPermissions("craftconomy.admin.give")
	public void giveCommand(CommandContext args, CommandSource source) throws CommandException
	{
		if (CraftconomyPlugin.accountList.containsKey(args.getString(0)))
		{
			CraftconomyPlugin.accountList.get(args.getString(0)).deposit(args.getDouble(1));
			source.sendMessage(TextUtil.generateWithPrefix(args.getDouble(1) + " " + CraftconomyConfiguration.CURRENCY_NAME.getString() + " has been sent to " + args.getString(0)));
			if (Spout.getEngine().getPlayer(args.getString(0), true) != null)
			{
				Spout.getEngine().getPlayer(args.getString(0), true).sendMessage(TextUtil.generateWithPrefix("Received " + args.getDouble(1) + " " + CraftconomyConfiguration.CURRENCY_NAME.getString() + " from a administrator"));
			}
		}
		else
		{
			throw new CommandException("Account not found!");
		}
	}
	@Command(aliases = {"balance"}, desc = "")
	@CommandPermissions("craftconomy.balance")
	public void balanceCommand(CommandContext args, CommandSource source) throws CommandException
	{
		//If the guy is the console, check if he provided a parameter
				if (!(source instanceof Player) && args.length() != 1)
				{
					throw new CommandException("You need a name!");
				}
				String name = "";
				if (args.length() == 1)
				{
					if (source.hasPermission("craftconomy.balance.others"))
					{
						name = args.getString(0);
					}
					else
					{
						throw new CommandException("You don't have the permission to check the balance of other players!");
					}
				}
				else
				{
					name = ((Player) source).getName();
				}
				
				if (CraftconomyPlugin.accountList.containsKey(name))
				{
					source.sendMessage(TextUtil.generateWithPrefix("Balance for " + name + ": " + CraftconomyPlugin.accountList.get(name).getBalance() + " " + CraftconomyConfiguration.CURRENCY_NAME.getString()));
					
				}
				else
				{
					throw new CommandException("No accounts under this name has been found.");
				}
	}
	@Command(aliases = { "help" }, desc = "Shows money help")
	public void moneyHelpCommand(CommandContext args, CommandSource source) throws CommandException{
		source.sendMessage(TextUtil.generateWithPrefix("Craftconomy Help"));
		if (source.hasPermission("craftconomy.balance"))
		{
			source.sendMessage(TextUtil.generateWithPrefix("/money - Shows your balance"));
		}
		if (source.hasPermission("craftconomy.balance.others"))
		{
			source.sendMessage(TextUtil.generateWithPrefix("/money <Player Name> - Shows someone else balance"));
		}
		if (source.hasPermission("craftconomy.money.pay"))
		{
			source.sendMessage(TextUtil.generateWithPrefix("/moneypay <Player Name> <Amount>"));
		}
		if (source.hasPermission("craftconomy.admin.create"))
		{
			source.sendMessage(TextUtil.generateWithPrefix("/moneycreate <Player Name> - Create a new account"));
		}
		if (source.hasPermission("craftconomy.admin.delete"))
		{
			source.sendMessage(TextUtil.generateWithPrefix("/moneydelete <Player Name> - Delete a account"));
		}
		if (source.hasPermission("craftconomy.admin.give"))
		{
			source.sendMessage(TextUtil.generateWithPrefix("/moneygive <Player Name> <Amount> - Give a player some money"));
		}
		if (source.hasPermission("craftconomy.admin.take"))
		{
			source.sendMessage(TextUtil.generateWithPrefix("/moneytake <Player Name> <Amount> - Take money from a player"));
		}
		if (source.hasPermission("craftconomy.admin.set"))
		{
			source.sendMessage(TextUtil.generateWithPrefix("/moneyset <Player Name> <Amount> - Set the account of a player to the specified amount"));
		}
	}
	
	@Command(aliases = "take", desc = "Take money from a player", min = 2, max = 2, usage = "<Player Name> <Amount>")
	@CommandPermissions("craftconomy.admin.take")
	public void takeCommand(CommandContext args, CommandSource source) throws CommandException
	{
		if (CraftconomyPlugin.accountList.containsKey(args.getString(0)))
		{
			CraftconomyPlugin.accountList.get(args.getString(0)).withdraw(args.getDouble(1));
			source.sendMessage(TextUtil.generateWithPrefix(args.getDouble(1) + " " + CraftconomyConfiguration.CURRENCY_NAME.getString() + " has been took from " + args.getString(0)));
			if (Spout.getEngine().getPlayer(args.getString(0), true) != null)
			{
				Spout.getEngine().getPlayer(args.getString(0), true).sendMessage(TextUtil.generateWithPrefix("A administrator took " + args.getDouble(1) + " " + CraftconomyConfiguration.CURRENCY_NAME.getString() + " from you"));
			}
		}
		else
		{
			throw new CommandException("Account not found!");
		}
	}
	
	@Command(aliases = "set", desc = "Set a player account balance", min = 2, max = 2, usage = "<Player Name> <Amount>")
	@CommandPermissions("craftconomy.admin.set")
	public void setCommand(CommandContext args, CommandSource source) throws CommandException 
	{
		if (CraftconomyPlugin.accountList.containsKey(args.getString(0)))
		{
			CraftconomyPlugin.accountList.get(args.getString(0)).setBalance(args.getDouble(1));
			source.sendMessage(TextUtil.generateWithPrefix(args.getString(0) + " account has been set to " + args.getDouble(1) + " " + CraftconomyConfiguration.CURRENCY_NAME.getString()));
			if (Spout.getEngine().getPlayer(args.getString(0), true) != null)
			{
				Spout.getEngine().getPlayer(args.getString(0), true).sendMessage(TextUtil.generateWithPrefix("A administrator set your account to " + args.getDouble(1) + " " + CraftconomyConfiguration.CURRENCY_NAME.getString()));
			}
		}
		else
		{
			throw new CommandException("Account not found!");
		}
	}
	
	@Command(aliases = "delete", desc = "Delete a player account", min = 1, max = 1, usage = "<Player Name>")
	@CommandPermissions("craftconomy.admin.delete")
	public void deleteCommand(CommandContext args, CommandSource source) throws CommandException
	{
		throw new CommandException("Not implemented");
	}
}
