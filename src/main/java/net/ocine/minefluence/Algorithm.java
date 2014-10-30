package net.ocine.minefluence;

import net.minecraft.World;

import java.util.List;
import java.util.Collection;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

class Algorithm {
	static class Vector {
		int x, y, z;

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
	}

	public static Collection<Vector> doMagic(Vector pos, World w) {
		HashSet<Vector> res = new HashSet<Vector>();
		f(pos, w, res);
		return res;
	}

	private static void f(Vector pos, World w, Set<Vector> s) {
		if (s.contains(pos) || !pos.check(w)) return;
		for (Vector opos : pos.getNeighbors()) f(opos, w, s);
	}
}
