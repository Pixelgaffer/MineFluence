package net.ocine.minefluence;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.ocine.minefluence.blocks.tileentities.IMachinePart;

import java.util.*;

public class Algorithm {
	static public class Vector {
		public int x, y, z;

		public Vector(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		private boolean check(World w) {
			Object entity = w.getTileEntity(x, y, z);
			if (entity == null) return false;
			return entity instanceof IMachinePart;
		}

		private Collection<Vector> getNeighbors() {
			List<Vector> res = new ArrayList<Vector>();
			res.add(new Vector(x+1, y, z));
			res.add(new Vector(x-1, y, z));
			res.add(new Vector(x, y+1, z));
			res.add(new Vector(x, y-1, z));
			res.add(new Vector(x, y, z+1));
			res.add(new Vector(x, y, z-1));
			return res;
		}

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Vector))return false;
            Vector that = (Vector) obj;
            return that.x == x && that.y == y && that.z == z;
        }

        @Override
        public int hashCode() {
            return x*256 + y + z*2048;
        }
    }

	public static Collection<Vector> doMagic(Vector pos, World w) {
		HashSet<Vector> res = new HashSet<Vector>();
		f(pos, w, res);
		return res;
	}

	private static void f(Vector pos, World w, Set<Vector> s) {
		if (s.contains(pos) || !pos.check(w)) return;
        s.add(pos);
		for (Vector opos : pos.getNeighbors()) f(opos, w, s);
	}

    /**
     * Subtracts the elements from input with toRemove.
     * @param input
     * @param toRemove
     * @return list of remaining items or null if not applicable
     */
    public static List<ItemStack> getRemaining (List<ItemStack> input, Collection<ItemStack> toRemove)
    {
        LinkedList<ItemStack> result = new LinkedList(input);
        
        // clone objects
        for (ItemStack stack : input)
	    result.addLast(new ItemStack(stack));
        
        // subtract lists
        for (ItemStack del : toRemove)
        {
	    ItemStack rm = new ItemStack(del);
	    for (ItemStack in : result)
	    {
		if (areItemsSame(rm, in))
		{
		    int subtract = Math.min(in.stackSize, rm.stackSize);
		    in.stackSize -= subtract;
		    rm.stackSize -
		}
		if (rm.stackSize <= 0)
		    break;
	    }
        }
        
        // finished
        return result;
    }

    public static boolean areItemsSame(ItemStack item1, ItemStack item2){
        return item1.isItemEqual(item2);
    }
}
