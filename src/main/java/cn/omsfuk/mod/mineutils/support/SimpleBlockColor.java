package cn.omsfuk.mod.mineutils.support;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

import javax.annotation.Nullable;

public class SimpleBlockColor implements IBlockColor {

    private int color;

    public SimpleBlockColor(int color) {
        this.color = color;
    }
    @Override
    public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
        return color;
    }
}
