package com.licenta.aol.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJoint;
import com.licenta.aol.licentaAOL;

/**
 * Created by claud on 03/11/2017.
 */

public abstract class InteractiveTileObj
{
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle bounds;
    protected Body body;


    public InteractiveTileObj (World world, TiledMap map, Rectangle bounds)
    {
        this.world=world;
        this.map=map;
        this.bounds=bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();


        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX()+bounds.getWidth()/2)/ licentaAOL.PPM,(bounds.getY()+bounds.getHeight()/2)/licentaAOL.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth()/2/licentaAOL.PPM,bounds.getHeight()/2/licentaAOL.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
    }


}
