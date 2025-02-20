package com.beanfeed.regexRails.events;

import org.joml.Vector3i;

public enum Faces {
    NORTH(0, 0, -1),
    EAST(1, 0, 0),
    SOUTH(0, 0, 1),
    WEST(-1, 0, 0),
    DOWN(0, -2, 0);

    public final int modX;
    public final int modY;
    public final int modZ;

    Faces(final int modX, final int modY, final int modZ) {
        this.modX = modX;
        this.modY = modY;
        this.modZ = modZ;
    }
}
