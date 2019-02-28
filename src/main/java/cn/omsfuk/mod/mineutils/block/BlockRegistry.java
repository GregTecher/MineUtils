package cn.omsfuk.mod.mineutils.block;

import cn.omsfuk.mod.mineutils.MineUtils;
import cn.omsfuk.mod.mineutils.support.SimpleBlockStateMapper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = MineUtils.MODID)
public class BlockRegistry {

    public static CreativeFluidBlock[] creativeFluidBlocks;

    @SubscribeEvent
    public static void onRegistryBlock(RegistryEvent.Register<Block> event) {
        for (CreativeFluidBlock block : creativeFluidBlocks) {
            event.getRegistry().register(block);
            if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
                ModelLoader.setCustomStateMapper(block, new SimpleBlockStateMapper("mineutils:creative_fluid_source"));
            }
        }
    }

    @SubscribeEvent
    public static void onRegistryItem(RegistryEvent.Register<Item> event) {
        for (CreativeFluidBlock block : creativeFluidBlocks) {
            ItemBlock item = new ItemBlock(block);
            item.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
            event.getRegistry().register(item);
            if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
                ModelLoader.setCustomModelResourceLocation(
                        Item.getItemFromBlock(block),
                        0,
                        new ModelResourceLocation("mineutils:creative_fluid_source", "inventory"));
            }
        }
    }
}
