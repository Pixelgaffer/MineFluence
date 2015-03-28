package net.ocine.minefluence.blocks;

import net.minecraft.creativetab.CreativeTabs;

public class Blocks {

	public static MachineBlockCore machineBlockCore;
	public static MachineBlockCooler machineBlockCooler;
	public static MachineBlockWorker machineBlockWorker;
	public static MachineBlockOutput machineBlockOutput;
	public static MachineBlockInput machineBlockInput;
	public static MachineBlockDisplay machineBlockDisplay;

	public static void initBlocks(CreativeTabs tab) {
		machineBlockCore = new MachineBlockCore(tab);
		machineBlockCooler = new MachineBlockCooler(tab);
		machineBlockWorker = new MachineBlockWorker(tab);
		machineBlockOutput = new MachineBlockOutput(tab);
		machineBlockInput = new MachineBlockInput(tab);
		machineBlockDisplay = new MachineBlockDisplay(tab);
	}

}
