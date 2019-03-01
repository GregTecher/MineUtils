package cn.omsfuk.mod.mineutils.proxy;


import cn.omsfuk.mod.mineutils.block.BlockRegistry;
import cn.omsfuk.mod.mineutils.block.CreativeFluidBlock;
import cn.omsfuk.mod.mineutils.config.ModConfig;
import cn.omsfuk.mod.mineutils.tileentity.CreativeFluidTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

import static cn.omsfuk.mod.mineutils.MineUtils.MODID;
import static cn.omsfuk.mod.mineutils.block.BlockRegistry.creativeFluidBlocks;

@Mod.EventBusSubscriber(modid = MODID)
public class CommonProxy {

    @SubscribeEvent
    public static void onRegistryBlock(RegistryEvent.Register<Block> event) {
        BlockRegistry.registerBlock(event);
    }

    @SubscribeEvent
    public static void onRegistryItem(RegistryEvent.Register<Item> event) {
        BlockRegistry.registerItemBlock(event);
    }

    private void loadFluidBlocksFromConfig() {
        GameRegistry.registerTileEntity(CreativeFluidTileEntity.class, new ResourceLocation(MODID, "mineutils"));
        List<CreativeFluidBlock> result = new ArrayList<>();
        for (int i = 0; i < ModConfig.fluidRegistry.length; i++) {
            String registry = ModConfig.fluidRegistry[i];
            int index = registry.lastIndexOf(":");
            String name;
            if (index == -1) {
                name = registry;
            } else {
                name = registry.substring(0, registry.lastIndexOf(":"));
            }
            CreativeFluidBlock block = new CreativeFluidBlock(name);
            name = name.replace(":", "_").replace(".", "_");
            block.setRegistryName(String.format("creative_%s_block", name));
            block.setUnlocalizedName(String.format("creative_%s_block", name));

            result.add(block);
        }
        creativeFluidBlocks = new CreativeFluidBlock[result.size()];
        creativeFluidBlocks = result.toArray(creativeFluidBlocks);

    }

    public void preInit() {
        loadFluidBlocksFromConfig();
    }

    public void onInit() {

    }

    public void postInit() {

    }
}
