package com.licenta.aol.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.attributes.PointLightsAttribute;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.licenta.aol.Scenes.HUD;
import com.licenta.aol.Sprites.Mario;
import com.licenta.aol.Tools.B2WorldCreator;
import com.licenta.aol.licentaAOL;

/**
 * Created by claud on 20/10/2017.
 */

public class PlayScreen implements Screen {
    //reference to game, used to set screens
    private licentaAOL game;
    private TextureAtlas atlas;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private HUD hud;
    private Mario player;

    // Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d Variables
    private World world;
    private Box2DDebugRenderer box2dr;

    public PlayScreen(licentaAOL game)
    {
        atlas = new TextureAtlas("D:\\Dropbox\\My Games\\Licenta AOL\\android\\assets\\Skeleton.pack");

        this.game=game;
        //create cam used to follow mario through cam world
        gameCam = new OrthographicCamera();



        //create a FitViewPort to maintain virtual aspect ratio
        gamePort = new FitViewport(licentaAOL.width/licentaAOL.PPM,licentaAOL.height/licentaAOL.PPM,gameCam);

        //create our game HUD
        hud = new HUD(game.batch);

        //load our map and setup our map renderer
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("aol.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/licentaAOL.PPM);

        //initially set pur gameCam to be centered correctly at the start of the game
        gameCam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);

        world = new World(new Vector2(0,-10),true);
        box2dr = new Box2DDebugRenderer();

        new B2WorldCreator(world, map);

        player = new Mario(world, this);
    }


    public TextureAtlas getAtlas()
    {
        return  atlas;
    }





    @Override
    public void show() {

    }

    public void handleInput(float dt)
    {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
        {
            player.b2body.applyLinearImpulse(new Vector2(0, 4f),player.b2body.getWorldCenter(),true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x<=2)
        {
            player.b2body.applyLinearImpulse(new Vector2(0.1f, 0),player.b2body.getWorldCenter(),true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x>=-2)
        {
            player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0),player.b2body.getWorldCenter(),true);
        }
    }

    public void update(float dt)
    {
        //handle user input first
        handleInput(dt);

        //takes 1 step in the physics simulation (60 times per second)
        world.step(1/60f,6,2);

        player.update(dt);

        //attach our gamecam to our players.x coordinates
        gameCam.position.x = player.b2body.getPosition().x;

        //update our gameCam with correct coordinates after changes
        gameCam.update();

        //tell our renderer to draw only what our camera can see in our game world
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta)
    {
        //separate our update logic from render
        update(delta);

        //clear the game screen with black
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our game map
        renderer.render();

        //renderer our Box2DDebugLines
        box2dr.render(world,gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

        //set our batch to now draw what the HUD camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void resize(int width, int height)
    {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose()
    {
        map.dispose();
        renderer.dispose();
        world.dispose();
        box2dr.dispose();
        hud.dispose();
    }
}
