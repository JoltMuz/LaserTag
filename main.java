package io.github.JoltMuz.LaserTag;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin implements Listener
{
	public static Plugin plugin;
	@Override
    public void onEnable()
    {
		plugin = this;
        getServer().getPluginManager().registerEvents(new UI(), this);
        getServer().getPluginManager().registerEvents(new LongRange(), this);
        getServer().getPluginManager().registerEvents(new Explosive(), this);
        getServer().getPluginManager().registerEvents(new Boost(), this);
        this.getCommand("lasergun").setExecutor(new UI());
    }
    @Override
    public void onDisable()
    {

    }
}
