package cn.omsfuk.mod.mineutils.proxy;

import cn.omsfuk.mod.mineutils.block.BlockRegistry;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {


    @SubscribeEvent
    public static void onRegisterModels(ModelRegistryEvent event) {
        BlockRegistry.registerBlockModel();
        BlockRegistry.registerItemBlockModel();
    }


    @Override
    public void preInit() {
        super.preInit();
    }

    @Override
    public void onInit() {
        super.onInit();
        BlockRegistry.registerColoredBlock();
    }

    @Override
    public void postInit() {
        super.postInit();
    }
}
