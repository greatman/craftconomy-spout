package org.greatmancode.craftconomy;

import org.greatmancode.craftconomy.commands.CraftconomyCommands;
import org.spout.api.command.CommandContext;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.command.annotated.NestedCommand;
import org.spout.api.exception.CommandException;

public class CraftconomyCommand {

	private final CraftconomyPlugin plugin;

	public CraftconomyCommand(CraftconomyPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Command(aliases = { "money" }, desc = "Shows your balance or someone else balance", min = 1)
	@NestedCommand(CraftconomyCommands.class)
	public void moneyCommand(CommandContext args, CommandSource source) throws CommandException {
		
		
		
	}
}
