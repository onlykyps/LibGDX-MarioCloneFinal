package com.licenta.aol.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.licenta.aol.Screens.PlayScreen;
import com.licenta.aol.licentaAOL;

/**
 * Created by claud on 03/11/2017.
 */

public class Mario extends Sprite
{
    public enum State {falling, jumping, standing, running}
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;

    private TextureRegion marioStand;
    private Animation marioRun;
    private Animation marioJump;

    private boolean runningRight;
    private float stateTimer;


    public void update(float dt)
    {
        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/2);
    }

    public Mario(World world, PlayScreen screen)
    {
        super(screen.getAtlas().findRegion("marioStand"));
        this.world=world;

        currentState = State.standing;
        previousState = State.falling;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames  = new Array<TextureRegion>();

        defineMario();
        marioStand = new TextureRegion(getTexture(),0,0,24,32);
        setBounds(0,0,24/licentaAOL.PPM,32/licentaAOL.PPM);
        setRegion(marioStand);
    }

    public void defineMario()
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/licentaAOL.PPM,32/licentaAOL.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(17/ licentaAOL.PPM);

        fdef.shape=shape;
        b2body.createFixture(fdef);
    }








}
