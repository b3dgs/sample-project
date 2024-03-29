/*
 * Copyright (C) 2013-2024 Byron 3D Games Studio (www.b3dgs.com) Pierre-Alexandre (contact@b3dgs.com)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.b3dgs.sample.pc;

import java.util.OptionalInt;

import com.b3dgs.lionengine.Context;
import com.b3dgs.lionengine.LionEngineException;
import com.b3dgs.lionengine.Tick;
import com.b3dgs.sample.Scene;

/**
 * Test scene implementation.
 */
public final class TestScene extends Scene
{
    private final Tick tick = new Tick();

    /**
     * Create the scene.
     * 
     * @param context The context reference (must not be <code>null</code>).
     * @param delay The exit delay.
     * @throws LionEngineException If invalid argument.
     */
    public TestScene(Context context, OptionalInt delay)
    {
        super(context);

        delay.ifPresent(d -> tick.addAction(() -> end(null), d));
        tick.start();
    }

    @Override
    public void update(double extrp)
    {
        tick.update(extrp);

        super.update(extrp);
    }
}
