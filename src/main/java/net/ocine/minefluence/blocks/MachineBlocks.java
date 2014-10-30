package net.ocine.minefluence.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.ocine.minefluence.blocks.tileentities.IMachinePart;
import net.ocine.minefluence.blocks.tileentities.InventoryTileEntity;
import net.ocine.minefluence.blocks.tileentities.TileEntityCore;
import net.ocine.minefluence.blocks.tileentities.TileEntityDisplay;
import net.ocine.minefluence.blocks.tileentities.TileEntityInput;
import net.ocine.minefluence.blocks.tileentities.TileEntityOutput;
import net.ocine.minefluence.blocks.tileentities.TileEntityWorker;
import cpw.mods.fml.common.registry.GameRegistry;

public class MachineBlocks extends BlockContainer {
	
	public static final String UNLOCALIZED_NAME  = "machineBlocks";
	
	public static final String[] names = {"core", "display", "input", "output", "worker"};
	
	public enum Machines {
		CORE, DISPLAY, INPUT, OUTPUT, WORKER;
	}
	
	private IIcon coreFront, coreSide, coreBottom;
	private IIcon displayFront, displaySide, displayBottom;
	private IIcon inputFront, inputSide, inputBottom;
	private IIcon outputFront, outputSide, outputBottom;
	private IIcon workerFront, workerSide, workerBottom;
	
	public MachineBlocks(CreativeTabs tab) {
		super(Material.iron);
		GameRegistry.registerBlock(this, MachineBlocksItemBlock.class, UNLOCALIZED_NAME);
		setCreativeTab(tab);
	}
	
	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int metadata) {
		IMachinePart machineBlock = (IMachinePart)world.getTileEntity(x, y, z);
		if(machineBlock == null) {
			return;
		}
		machineBlock.removeFromMachine();
		if(world.getTileEntity(x, y, z) instanceof InventoryTileEntity) {
			((InventoryTileEntity)world.getTileEntity(x, y, z)).dropItems(world, x, y, z);
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(meta == Machines.CORE.ordinal()) {
			
		}
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		if(metadata == Machines.CORE.ordinal()) {
			return new TileEntityCore();
		}
		if(metadata == Machines.DISPLAY.ordinal()) {
			return new TileEntityDisplay();
		}
		if(metadata == Machines.INPUT.ordinal()) {
			return new TileEntityInput();
		}
		if(metadata == Machines.OUTPUT.ordinal()) {
			return new TileEntityOutput();
		}
		if(metadata == Machines.WORKER.ordinal()) {
			return new TileEntityWorker();
		}
		return null;
	}
	
	public int damagedDropped(int metadata) {
		return metadata;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int i = 0; i < Machines.values().length; i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
	
}
