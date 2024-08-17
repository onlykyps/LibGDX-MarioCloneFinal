package com.licenta.aol.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.licenta.aol.Sprites.Coin;
import com.licenta.aol.Sprites.Brick;
import com.licenta.aol.Sprites.Mario;
import com.licenta.aol.licentaAOL;

import java.security.PublicKey;

/**
 * Created by claud on 03/11/2017.
 */

public class B2WorldCreator
{
    public  B2WorldCreator(World world, TiledMap map)
    {


        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;


        //create ground bodies.fixtures
        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect=((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ licentaAOL.PPM,(rect.getY()+rect.getHeight()/2)/licentaAOL.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/licentaAOL.PPM,rect.getHeight()/2/licentaAOL.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //create pipe bodies.fixtures
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect=((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/licentaAOL.PPM,(rect.getY()+rect.getHeight()/2)/licentaAOL.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/licentaAOL.PPM,rect.getHeight()/2/licentaAOL.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }


        //create brick bodies.fixtures

        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect=((RectangleMapObject) object).getRectangle();



            new Brick (world,map, rect);
        }

        //create coin bodies.fixtures

        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class))
        {
            Rectangle rect=((RectangleMapObject) object).getRectangle();

            new Coin(world, map, rect);
        }
    }







}
