package cn.omsfuk.mod.mineutils.block;

import cn.omsfuk.mod.mineutils.config.ModConfig;
import cn.omsfuk.mod.mineutils.support.SimpleBlockColor;
import cn.omsfuk.mod.mineutils.support.SimpleBlockStateMapper;
import cn.omsfuk.mod.mineutils.support.SimpleItemColor;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

public class BlockRegistry {

    public static CreativeFluidBlock[] creativeFluidBlocks;

    public static void registerBlock(RegistryEvent.Register<Block> event) {
        for (CreativeFluidBlock block : creativeFluidBlocks) {
            event.getRegistry().register(block);
        }
    }

    public static void registerItemBlock(RegistryEvent.Register<Item> event) {
        for (CreativeFluidBlock block : creativeFluidBlocks) {
            ItemBlock item = new ItemBlock(block);
            item.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
            event.getRegistry().register(item);
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerBlockModel() {
        for (Block block : creativeFluidBlocks) {
            ModelLoader.setCustomStateMapper(block, new SimpleBlockStateMapper("mineutils:creative_fluid_source"));
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemBlockModel() {
        for (Block block : creativeFluidBlocks) {
            ModelLoader.setCustomModelResourceLocation(
                    Item.getItemFromBlock(block),
                    0,
                    new ModelResourceLocation("mineutils:creative_fluid_source", "inventory"));
        }
    }


    @SideOnly(Side.CLIENT)
    public static void registerColoredBlock() {
        for (int i = 0; i < creativeFluidBlocks.length; i++) {
            String registry = ModConfig.fluidRegistry[i];
            int index = registry.lastIndexOf(":");
            int color;
            if (index == -1) {
                String name = registry;
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
