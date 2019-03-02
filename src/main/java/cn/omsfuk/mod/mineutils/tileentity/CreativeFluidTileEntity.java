package cn.omsfuk.mod.mineutils.tileentity;

import net.minecraft.nbt.NBTTagCompound;
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
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;


public class CreativeFluidTileEntity extends TileEntity implements IFluidHandler {

    private Fluid fluidType;

    private FluidStack filledFluid;

    private FluidTankProperties[] fluidTankProperties;

    public CreativeFluidTileEntity() {
    }

    public CreativeFluidTileEntity(Fluid fluid) {
        init(fluid);
    }

    private void init(Fluid fluid) {
        fluidType = fluid;
        filledFluid = new FluidStack(fluid, Integer.MAX_VALUE);
        fluidTankProperties = new FluidTankProperties[]{
                new FluidTankProperties(filledFluid, Integer.MAX_VALUE, false, true)
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
        return fluidTankProperties;
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

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("fluid")) {
            init(FluidRegistry.getFluid(compound.getString("fluid")));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        if (fluidType != null) {
            compound.setString("fluid", fluidType.getName());
        }
        return super.writeToNBT(compound);
    }
}
