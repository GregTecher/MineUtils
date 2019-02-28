package cn.omsfuk.mod.mineutils;

import cn.omsfuk.mod.mineutils.block.CreativeFluidBlock;
import cn.omsfuk.mod.mineutils.config.ModConfig;
import cn.omsfuk.mod.mineutils.support.SimpleBlockColor;
import cn.omsfuk.mod.mineutils.support.SimpleItemColor;
import cn.omsfuk.mod.mineutils.tileentity.CreativeFluidTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static cn.omsfuk.mod.mineutils.block.BlockRegistry.creativeFluidBlocks;

@Mod(modid = MineUtils.MODID, name = MineUtils.NAME, version = MineUtils.VERSION)
public class MineUtils
{
    public static final String MODID = "mineutils";
    public static final String NAME = "MineUtils";
    public static final String VERSION = "1.0";

    private static final Logger LOG = Logger.getLogger(MineUtils.class.getName());


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
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

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        for (int i = 0; i < creativeFluidBlocks.length; i++) {
            String registry = ModConfig.fluidRegistry[i];
            int index = registry.lastIndexOf(":");
            String name;
            int color;
            if (index == -1) {
                name = registry;
                color = FluidRegistry.getFluid(name).getColor();
            } else {
                color = Integer.parseInt(registry.substring(registry.lastIndexOf(":") + 1), 16);
            }
            Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(
                    new SimpleBlockColor(color),
                    creativeFluidBlocks[i]);
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(
                    new SimpleItemColor(color),
                    creativeFluidBlocks[i]);
        }

    }
}
