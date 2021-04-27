package fi.tuni.tamk.tiko.bloomorgloom.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.concurrent.TimeUnit;

import fi.tuni.tamk.tiko.bloomorgloom.MyGdxGame;
import fi.tuni.tamk.tiko.bloomorgloom.Navigation;

/**
 * SplashScreen is a Screen class that displays the games splash screen.
 *
 * @author Jere Joensuu
 */

public class SplashScreen implements Screen {
    MyGdxGame game;

    // Textures:
    private Texture bg;
    private Texture head;
    private Texture title;

    // Texture scaling:
    private float scale = 0;
    private float scaler = 0.01f;
    private ScalingDirection scalingDirection = ScalingDirection.SCALING_UP;

    // Head rotating:
    private float rotation;
    private boolean rotatingRight;

    // Background rotation:
    private float bgRotation = 0;

    SpriteBatch batch;
    private OrthographicCamera camera;
    public long splashTimer = System.nanoTime();


    /**
     *  Contains information about the scaling of the textures.
     */
    private enum ScalingDirection {
        SCALING_UP,
        SCALING_DOWN,
        NOT_SCALING
    }

    /**
     * The constructor of the class.
     *
     * @param game Related to the functionality of the Screen interface.
     */
    public SplashScreen(MyGdxGame game) {
        this.game = game;
        batch = game.gameBatch;
        camera = game.camera;

        bg = new Texture("splashScreen/splashBackground.jpg");
        head = new Texture("splashScreen/splashHead.png");
        title = new Texture("splashScreen/splashTitle.png");
    }

    /**
     * The method that draws the textures and calls other supporting methods.
     *
     * @param delta Related to the functionality of the Screen interface.
     */
    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        ScreenUtils.clear(0, 0, 0, 0);

        // Switch to comic screen if screen is touched or after 7.5s has passed.
        if (Gdx.input.justTouched() || TimeUnit.MILLISECONDS.convert(System.nanoTime() - splashTimer, TimeUnit.NANOSECONDS) > 7500) {

            if(!game.settings.getHasSeenComic() && !MyGdxGame.DEBUG) {
                game.setScreen(new ComicScreen(game));
            } else {
                game.navigation.setActive(Navigation.Screen.GAME);
                game.navigation.setScreen(Navigation.Screen.GAME);
            }
        }

        batch.begin();

        batch.draw(bg,
                -660,
                -1174,
                bg.getWidth() / 2f,
                bg.getHeight() / 2f,
                bg.getWidth(),
                bg.getHeight(),
                1,
                1,
                bgRotation,
                0,
                0,
                bg.getWidth(),
                bg.getHeight(),
                false,
                false);

        batch.draw(head,
                0,
                0,
                head.getWidth() / 2f,
                head.getHeight() / 2f,
                head.getWidth(),
                head.getHeight(),
                scale,
                scale,
                rotation,
                0,
                0,
                head.getWidth(),
                head.getHeight(),
                false,
                false);

        batch.draw(title,
                0,
                0,
                title.getWidth() / 2f,
                title.getHeight() / 2f,
                title.getWidth(),
                title.getHeight(),
                scale,
                scale,
                0,
                0,
                0,
                title.getWidth(),
                title.getHeight(),
                false,
                false);

        batch.end();

        animator();
    }

    /**
     * Handles the animating of the textures.
     */
    public void animator() {
        // Set to 120% to leave room for bounce.
        float maxScale = 1.2f;

        // If scaling:
        if (scalingDirection != ScalingDirection.NOT_SCALING) {

            // Scaling up:
            if (scale < maxScale && scalingDirection == ScalingDirection.SCALING_UP) {
                scale += scaler;

                // Speed up scaling until scale reaches 100%, then slow down.
                if (scale <= 1) {
                    // Scaling speeds up faster when scale is small.
                    scaler += (maxScale - scale) * 0.001;
                } else {
                    scaler *= 0.97;
                }

            } else {
                scalingDirection = ScalingDirection.SCALING_DOWN;
            }

            // Scaling down:
            if (scalingDirection == ScalingDirection.SCALING_DOWN) {
                scale -= scaler;

                // Slow scaling down.
                scaler *= 0.90;

                if (scale <= 1){
                    scale = 1;
                    scalingDirection = ScalingDirection.NOT_SCALING;
                }
            }

        // Rotate head. Rotating slows down when when rotation is high.
        } else {
            if (rotatingRight) {
                rotation += 0.5 - ((rotation + 5) * 0.025);
                rotatingRight = rotation <= 7.5;
            } else {
                rotation -= 0.5 + ((rotation - 5) * 0.025);
                rotatingRight = rotation <= -7.5;
            }
        }

        // Rotate background:
        bgRotation += 0.15f;
    }

    /**
     * The rest of the methods are part of the Screen interface.
     */

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
