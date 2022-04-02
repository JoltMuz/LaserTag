
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin implements Listener
{
    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new UI(), this);
        getServer().getPluginManager().registerEvents(new LongRange(), this);
        getServer().getPluginManager().registerEvents(new Explosive(), this);
        getServer().getPluginManager().registerEvents(new Boost(), this);
    }
    @Override
    public void onDisable()
    {

    }

}
