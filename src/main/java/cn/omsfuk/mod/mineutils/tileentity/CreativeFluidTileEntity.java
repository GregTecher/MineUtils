package cn.omsfuk.mod.mineutils.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import javax.annotation.Nullable;


public class CreativeFluidTileEntity extends TileEntity implements IFluidHandler {

    private final Fluid fluidType;

    private final FluidStack FILLED_FLUID;

    private final FluidTankProperties[] FLUID_TANK_PROPERTIES;

    public CreativeFluidTileEntity() {
        this(FluidRegistry.WATER);
    }

    public CreativeFluidTileEntity(Fluid fluid) {
        fluidType = fluid;
        FILLED_FLUID = new FluidStack(fluid, Integer.MAX_VALUE);
        FLUID_TANK_PROPERTIES = new FluidTankProperties[]{
                new FluidTankProperties(FILLED_FLUID, Integer.MAX_VALUE, false, true)
        };
    }


    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {

        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return (T) this;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return FLUID_TANK_PROPERTIES;
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return 0;
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        if (resource.getFluid() == fluidType) {
            return new FluidStack(fluidType, resource.amount);
        }
        return null;
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return new FluidStack(fluidType, maxDrain);
    }
}
