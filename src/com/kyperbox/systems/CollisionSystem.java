package com.kyperbox.systems;

import com.badlogic.gdx.utils.Array;
import com.kyperbox.objects.GameObject;

public abstract class CollisionSystem extends AbstractSystem {

	private boolean parent_collisions;
	
	
	public void setParentCollisions(boolean parent_collisions) {
		this.parent_collisions = parent_collisions;
	}
	
	public boolean isParentCollisions() {
		return parent_collisions;
	}
	/**
	 * get all the possible collisions for the given object
	 * 
	 * @param object
	 * @return
	 */
	public abstract Array<GameObject> getPossibleCollisions(GameObject object);

	/**
	 * utility function used to check if two objects should collide
	 * @param a
	 * @param b
	 * @return false if both objects are the same or either of them ignore collision, Otherwise a filterBit and group bit are checked. 
	 */
	protected boolean shouldCollide(GameObject a, GameObject b) {
		if (a == b || a.ignoresCollision() || b.ignoresCollision())
			return false;
		if(!isParentCollisions()&&(a.getParent() == b || b.getParent() == a)) 
			return false;
		return (((a.getFilter() & b.getGroup()) != 0) && ((a.getGroup() & b.getFilter()) != 0));
	}
}
