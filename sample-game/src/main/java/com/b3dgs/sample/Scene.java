/*
 * Copyright (C) 2013-2020 Byron 3D Games Studio (www.b3dgs.com) Pierre-Alexandre (contact@b3dgs.com)
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
package com.b3dgs.sample;

import com.b3dgs.lionengine.Align;
import com.b3dgs.lionengine.Context;
import com.b3dgs.lionengine.game.feature.SequenceGame;
import com.b3dgs.lionengine.graphic.ColorRgba;
import com.b3dgs.lionengine.graphic.Graphic;
import com.b3dgs.lionengine.graphic.Graphics;
import com.b3dgs.lionengine.graphic.Text;

/**
 * Game scene implementation.
 */
public final class Scene extends SequenceGame<World>
{
    private final Text text = Graphics.createText(20);

    /**
     * Create the scene.
     * 
     * @param context The context reference.
     */
    public Scene(Context context)
    {
        super(context, Constant.NATIVE, World::new);
    }

    @Override
    public void load()
    {
        text.setAlign(Align.CENTER);
        text.setColor(ColorRgba.WHITE);
        text.setLocation(getWidth() / 2, getHeight() / 2);
    }

    @Override
    public void update(double extrp)
    {
        super.update(extrp);
        text.setText("FPS = " + String.valueOf(getFps()));
    }

    @Override
    public void render(Graphic g)
    {
        super.render(g);
        g.clear(0, 0, getWidth(), getHeight());
        text.draw(g, getWidth() / 2, 20, Align.CENTER, "Hello World");
        text.render(g);
    }
}
