package org.greatmancode.craftconomy;

import java.io.File;
import java.util.HashMap;

import org.greatmancode.craftconomy.configuration.CraftconomyConfiguration;
import org.greatmancode.craftconomy.utils.CCLogger;
import org.greatmancode.craftconomy.utils.PlayerLoader;
import org.spout.api.command.CommandRegistrationsFactory;
import org.spout.api.command.annotated.AnnotatedCommandRegistrationFactory;
import org.spout.api.command.annotated.SimpleAnnotatedCommandExecutorFactory;
import org.spout.api.command.annotated.SimpleInjector;
import org.spout.api.exception.ConfigurationException;
import org.spout.api.plugin.CommonPlugin;
import org.spout.api.plugin.ServiceManager.ServicePriority;
import org.spout.api.plugin.services.EconomyService;

public class CraftconomyPlugin extends CommonPlugin {

	public static HashMap<String,PlayerAccount> accountList = new HashMap<String,PlayerAccount>();
	public static CraftconomyConfiguration config;
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnable() {
		new CCLogger(this.getLogger(), this.getName());
		
		CCLogger.info("Starting");
		
		CCLogger.info("Loading configuration");
		config = new CraftconomyConfiguration(this.getDataFolder());
		try {
			config.load();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			CCLogger.severe("Error while loading the configuration" + e.getMessage());
		}
		CCLogger.info("Configuration loaded");
		
		CCLogger.info("Loading accounts");
		if (!getDataFolder().exists())
		{
			getDataFolder().mkdir();
		}
		PlayerLoader.dataFolder = new File(this.getDataFolder(),  "/playerFiles");
		if (!PlayerLoader.dataFolder.exists())
		{
			PlayerLoader.dataFolder.mkdir();
		}
		
		loadAccounts();
		CCLogger.info("Accounts loaded");
		
		CCLogger.info("Starting the save methodsdadasdad");
		//this.getGame().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
		//	
		//	public void run()
		//	{
		//		System.out.println("hello");
		//	}
		//}, 0L, 100L);
		this.getGame().getScheduler().scheduleSyncRepeatingTask(this, new PlayerSave(), CraftconomyConfiguration.SAVE_INTERVAL.getLong(), CraftconomyConfiguration.SAVE_INTERVAL.getLong());
		CCLogger.info("Save method loaded!");
		
		this.getGame().getEventManager().registerEvents(new CCListener(), this);
		
		CCLogger.info("Registering with the server to offer Economy service");
		getGame().getServiceManager().register(EconomyService.class, new EconomyServiceHandler(), this, ServicePriority.Normal);
		CCLogger.info("Registered!");
		
		
		CCLogger.info("Registering commands");
		new CraftconomyCommand(this);
		CommandRegistrationsFactory<Class<?>> commandRegFactory = new AnnotatedCommandRegistrationFactory(new SimpleInjector(this), new SimpleAnnotatedCommandExecutorFactory());
		getGame().getRootCommand().addSubCommands(this, CraftconomyCommand.class, commandRegFactory);
		CCLogger.info("Commands registered!");
		CCLogger.info("Started!");
	}
	
	public void loadAccounts() {
		CCLogger.info(PlayerLoader.dataFolder + "");
		for (File file : PlayerLoader.dataFolder.listFiles()) {
            String name = file.getName().substring(0, file.getName().lastIndexOf("."));
            accountList.put(name, PlayerLoader.accountLoader(name));
		}    
	}
	
	public static PlayerAccount getAccount(String playerName) {
		return accountList.get(playerName);
	}
	
	public static boolean createAccount(String username) {
		boolean result = false;
		if (!CraftconomyPlugin.accountList.containsKey(username))
		{
			CraftconomyPlugin.accountList.put(username, new PlayerAccount(username, CraftconomyConfiguration.NEW_ACCOUNT_HOLDING.getDouble()));
			result = true;
		}
		return result;
	}

}
