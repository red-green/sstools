package mod.dogetorhue.slimestonetools;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = SSTools.MODID, version = SSTools.VERSION)
public class SSTools
{
    public static final String MODID = "slimestonetools";
    public static final String VERSION = "0.1";
    
    @Instance(MODID)
    public static SSTools instance;
    
    @SidedProxy(clientSide = "mod.dogetorhue.slimestonetools.ClientProxy", serverSide = "mod.dogetorhue.slimestonetools.CommonProxy")
    public static CommonProxy proxy;
    
    
    @EventHandler
    public void preInit(FMLInitializationEvent event)
    {
        
    		proxy.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	
        proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLInitializationEvent event)
    {
    		MinecraftForge.EVENT_BUS.register(new RenderGuiHandler());
    		proxy.postInit(event);
    }

}
