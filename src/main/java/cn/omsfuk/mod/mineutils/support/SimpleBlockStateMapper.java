package cn.omsfuk.mod.mineutils.support;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;

public class SimpleBlockStateMapper extends StateMapperBase {

    private String location;

    public SimpleBlockStateMapper(String location) {
        this.location = location;
    }

    @Override
    protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
        return new ModelResourceLocation(location, "");
    }
}

