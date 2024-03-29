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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.b3dgs.lionengine.Config;
import com.b3dgs.lionengine.Engine;
import com.b3dgs.lionengine.awt.graphic.EngineAwt;
import com.b3dgs.lionengine.graphic.engine.Loader;
import com.b3dgs.lionengine.graphic.engine.TaskFuture;
import com.b3dgs.sample.Constant;
import com.b3dgs.sample.Scene;

/**
 * Test correct {@link Scene} loading.
 */
final class SampleTest
{
    /**
     * Init engine.
     */
    @BeforeAll
    static void prepareAll()
    {
        EngineAwt.start(Constant.PROGRAM_NAME, Constant.PROGRAM_VERSION, AppSample.class);
    }

    /**
     * Init engine.
     */
    @BeforeEach
    void prepare()
    {
        if (!Engine.isStarted())
        {
            EngineAwt.start(Constant.PROGRAM_NAME, Constant.PROGRAM_VERSION, AppSample.class);
        }
    }

    /**
     * Test scene.
     */
    @Test
    void testScene()
    {
        final TaskFuture task = Loader.start(Config.windowed(Constant.NATIVE), TestScene.class, OptionalInt.of(1));
        task.await();
    }
}
