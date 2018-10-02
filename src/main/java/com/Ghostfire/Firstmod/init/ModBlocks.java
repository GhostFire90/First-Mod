package com.Ghostfire.Firstmod.init;

import java.util.ArrayList;
import java.util.List;

import com.Ghostfire.Firstmod.blocks.BlockBase;
import com.Ghostfire.Firstmod.blocks.TestContainer;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block RUBY_BLOCK = new BlockBase("ruby_block", Material.IRON);
	
	public static TestContainer testContainer;
	@SideOnly(Side.CLIENT)
	public static void initModels() {
		testContainer.initModel();
	}
}
