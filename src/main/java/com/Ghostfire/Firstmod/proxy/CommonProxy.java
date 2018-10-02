package com.Ghostfire.Firstmod.proxy;

import com.Ghostfire.Firstmod.blocks.TestContainer;
import com.Ghostfire.Firstmod.blocks.tileentity.TestContainerTileEntity;
import com.Ghostfire.Firstmod.init.ModBlocks;
import com.Ghostfire.Firstmod.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new TestContainer());
        GameRegistry.registerTileEntity(TestContainerTileEntity.class, Reference.MOD_ID + "_testcontainer");
        
    }
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(TestContainer).setRegistryName(ModBlocks.testContainer.getRegistryName()));
       
    }
    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(new GuiProxy(), null);
    }
	public void registerItemRenderer(Item item, int meta, String id) {}
}
