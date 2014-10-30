package net.ocine.minefluence.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class Blocks {
	
	public static Block machineBlocks;
	
	public static void initBlocks(CreativeTabs tab) {
		machineBlocks = new MachineBlocks(tab);
	}
	
}
