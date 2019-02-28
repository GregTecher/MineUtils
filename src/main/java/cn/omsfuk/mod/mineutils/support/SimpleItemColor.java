package cn.omsfuk.mod.mineutils.support;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class SimpleItemColor implements IItemColor {

    private int color;

    public SimpleItemColor(int color) {
        this.color = color;
    }
    @Override
    public int colorMultiplier(ItemStack stack, int tintIndex) {
        return color;
    }
}
