package cn.omsfuk.mod.mineutils.block;

import cn.omsfuk.mod.mineutils.tileentity.CreativeFluidTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import javax.annotation.Nullable;

public class CreativeFluidBlock extends Block {

    private String fluid;

    public CreativeFluidBlock(String fluid) {
        super(Material.IRON);
        setHardness(1);
        setCreativeTab(CreativeTabs.MISC);
        this.fluid = fluid;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new CreativeFluidTileEntity(FluidRegistry.getFluid(fluid));
    }

}
