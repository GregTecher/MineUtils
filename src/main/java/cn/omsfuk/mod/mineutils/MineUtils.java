package cn.omsfuk.mod.mineutils;

import cn.omsfuk.mod.mineutils.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.logging.Logger;

@Mod(modid = MineUtils.MODID, name = MineUtils.NAME, version = MineUtils.VERSION)
public class MineUtils
{
    public static final String MODID = "mineutils";
    public static final String NAME = "MineUtils";
    public static final String VERSION = "1.0";

    private static final Logger LOG = Logger.getLogger(MineUtils.class.getName());

    @SidedProxy(modId = MineUtils.MODID, clientSide = "cn.omsfuk.mod.mineutils.proxy.ClientProxy", serverSide = "cn.omsfuk.mod.mineutils.proxy.CommonProxy")
    public static CommonProxy proxy;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.onInit();
    }
}
