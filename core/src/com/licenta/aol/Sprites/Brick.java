package com.licenta.aol.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.licenta.aol.licentaAOL;

/**
 * Created by claud on 03/11/2017.
 */

public class Brick extends InteractiveTileObj
{


    public Brick(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);


    }
}
