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
            res.add(new Vector(x + 1, y, z));
            res.add(new Vector(x - 1, y, z));
            res.add(new Vector(x, y + 1, z));
            res.add(new Vector(x, y - 1, z));
            res.add(new Vector(x, y, z + 1));
            res.add(new Vector(x, y, z - 1));
            return res;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Vector)) return false;
            Vector that = (Vector) obj;
            return that.x == x && that.y == y && that.z == z;
        }

        @Override
        public int hashCode() {
            return x * 256 + y + z * 2048;
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
     *
     * @param input
     * @param toRemove
     * @return list of remaining items or null if not applicable
     */
    public static List<ItemStack> getRemaining(List<ItemStack> input, Collection<ItemStack> toRemove) {
        LinkedList<ItemStack> result = new LinkedList<ItemStack>();

        // clone objects
        for (ItemStack stack : input)
            result.addLast(stack == null ? null : stack.copy());

        // subtract lists
        for (ItemStack del : toRemove) {
            if (del == null)
                continue;
            ItemStack rm = del.copy();
            boolean foundStack = false;
            // remove items from inventory
            for (ItemStack in : result) {
                if (in == null)
                    continue;

                if (areItemsSame(rm, in)) {
                    if(rm.stackSize > in.stackSize) {
                    	return null;
                    }
                    foundStack = true;
                    in.stackSize -= rm.stackSize;
                    break;
                }
                if (rm.stackSize <= 0)
                    break;
            }
            
            if(!foundStack) {
            	return null;
            }
        }

        // finished
        return result;
    }

    /**
     * Adds a Collection of items into an inventory represented by a ItemStack array.
     *
     * @param inv     the inventory
     * @param toMerge items to add
     * @return the same array as inv or null if unable to merge
     */
    public static ItemStack[] mergeItems(ItemStack[] inv, Collection<ItemStack> toMerge) {
        for (ItemStack stack : toMerge) {
            ItemStack add = stack.copy();

            boolean couldAdd = false;
            
            // try to add all elements from the stack to existing stacks in the inventory
            for (ItemStack s : inv) {
                if (s == null)
                    continue;

                if (areItemsSame(s, add)) {
                	if(s.stackSize + add.stackSize > s.getMaxStackSize()) {
                		return null;
                	}
                	couldAdd = true;
                    s.stackSize += add.stackSize;
                }
                if(!couldAdd) {
                	return null;
                }
            }

            // if there are still elements in the stack, try to create a new stack in the inventory
            if (!couldAdd) {
                for (int i = 0; i < inv.length; i++) {
                    if (inv[i] == null || inv[i].stackSize == 0) {
                        inv[i] = add;
                        couldAdd = true;
                        break;
                    }
                }
                if(!couldAdd) {
                	return null;
                }
            }
        }

        return inv;
    }

    public static boolean areItemsSame(ItemStack item1, ItemStack item2) {
        return ((item1 != null) && item1.isItemEqual(item2) && item1.getItemDamage() == item2.getItemDamage());
    }
}
