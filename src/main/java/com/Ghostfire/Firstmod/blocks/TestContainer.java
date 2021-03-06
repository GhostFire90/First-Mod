package com.Ghostfire.Firstmod.blocks;

import javax.annotation.Nullable;

import com.Ghostfire.Firstmod.blocks.tileentity.TestContainerTileEntity;
import com.Ghostfire.Firstmod.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class TestContainer extends Block implements ITileEntityProvider{
	private TestContainerTileEntity te;
	public TestContainer() {
		super(Material.IRON);
		setUnlocalizedName(Reference.MOD_ID + ".testcontainer");
		setRegistryName("testcontainer");
	}
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
		
	
	@Override
	public TileEntity createNewTileEntity(World worldin, int meta) {
		return new TestContainerTileEntity();
				
	}
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side,
            float hitX, float hitY, float hitZ) {
		if(world.isRemote) {
			return true;
		}
		TileEntity te = world.getTileEntity(pos);
		if(!(te instanceof TestContainerTileEntity)) {
			return false;
		}
		player.openGui(Reference.MOD_ID, GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
		
	}
	public TestContainer(IInventory playerInventory, TestContainerTileEntity te) {
		this.te = te;
		addOwnSlots();
		addPlayerSlots(playerInventory);
	}
	private void addPlayerSlots(IInventory playerInventory) {
		for(int row = 0; row < 3; ++row) {
			for(int col = 0; col < 9; ++col) {
				int x = 9 + col * 18;
				int y = row * 18 + 70;
				this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 10, x, y));
					
			}
		}
        for (int row = 0; row < 9; ++row) {
            int x = 9 + row * 18;
            int y = 58 + 70;
            this.addSlotToContainer(new Slot(playerInventory, row, x, y));
	}
        private void addSlotToContainer(Slot slot) {
		// TODO Auto-generated method stub
		
	}
		private void addOwnSlots() {
            IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            int x = 9;
            int y = 6;

            // Add our own slots
            int slotIndex = 0;
            for (int i = 0; i < itemHandler.getSlots(); i++) {
                addSlotToContainer(new SlotItemHandler(itemHandler, slotIndex, x, y));
                slotIndex++;
                x += 18;
            }
        }
        private void addSlotToContainer(SlotItemHandler slotItemHandler) {
			// TODO Auto-generated method stub
			
		}
		@Nullable
        public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
            ItemStack itemstack = null;
            Slot slot = this.inventorySlots.get(index);

            if (slot != null && slot.getHasStack()) {
                ItemStack itemstack1 = slot.getStack();
                itemstack = itemstack1.copy();

                if (index < TestContainerTileEntity.SIZE) {
                    if (!this.mergeItemStack(itemstack1, TestContainerTileEntity.SIZE, this.inventorySlots.size(), true)) {
                        return null;
                    }
                } else if (!this.mergeItemStack(itemstack1, 0, TestContainerTileEntity.SIZE, false)) {
                    return null;
                }

                if (itemstack1.isEmpty()) {
                    slot.putStack(ItemStack.EMPTY);
                } else {
                    slot.onSlotChanged();
                }
            }

            return itemstack;
        }

        private boolean mergeItemStack(ItemStack itemstack1, int i, int size, boolean b) {
			// TODO Auto-generated method stub
			return false;
		}
		public boolean canInteractWith(EntityPlayer playerIn) {
            return te.canInteractWith(playerIn);
        }
}
