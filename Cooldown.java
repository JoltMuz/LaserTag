import java.util.HashMap;
import java.util.Map;

public class Cooldown
{
    public static Map<String, Double> Shoot = new HashMap<>();
    public static Map<String, Double> LongRange = new HashMap<>();
    public static Map<String, Double> Explosive = new HashMap<>();
    public static Map<String, Double> Boost = new HashMap<>();

    public static boolean checkCooldown(String playerName, Map ItemName, double cooldown)
    {
        if (ItemName.containsKey(playerName))
        {
            if ( System.currentTimeMillis()/ (double)1000 - (Double) ItemName.get(playerName) > cooldown)
            {
                ItemName.put(playerName,System.currentTimeMillis()/(double)1000);
                return true;
            }
        }
        else
        {
            ItemName.put(playerName,System.currentTimeMillis()/(double)1000);
            return true;
        }
        return false;
    }
}
