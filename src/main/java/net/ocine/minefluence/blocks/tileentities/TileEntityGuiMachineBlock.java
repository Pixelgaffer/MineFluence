package net.ocine.minefluence.blocks.tileentities;


public abstract class TileEntityGuiMachineBlock extends InventoryTileEntity implements IMachineBlock {

	public TileEntityGuiMachineBlock(int inventorySize) {
		super(inventorySize);
	}

	protected Structure structure;

	@Override
	public void addToStructure(Structure structure) {
		this.structure = structure;
	}

	@Override
	public void destroyStructure(boolean callStructure) {
		if (callStructure) {
			structure.destroyStructure();
			return;
		}
		addToStructure(null);
	}

	@Override
	public Structure getStructure() {
		return structure;
	}

}
