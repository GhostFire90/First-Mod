package com.Ghostfire.Firstmod.proxy;

import com.Ghostfire.Firstmod.blocks.TestContainer;
import com.Ghostfire.Firstmod.blocks.tileentity.TestContainerTileEntity;
import com.Ghostfire.Firstmod.util.TestContainerGui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TestContainerTileEntity) {
            return new TestContainer(player.inventory, (TestContainerTileEntity) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TestContainerTileEntity) {
            TestContainerTileEntity containerTileEntity = (TestContainerTileEntity) te;
            return new TestContainerGui(containerTileEntity, new TestContainer(player.inventory, containerTileEntity));
        }
        return null;
    }
}