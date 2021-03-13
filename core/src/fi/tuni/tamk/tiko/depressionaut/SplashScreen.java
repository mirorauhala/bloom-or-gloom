package fi.tuni.tamk.tiko.depressionaut;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class SplashScreen implements Screen {
    MyGdxGame host;

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

    SpriteBatch batch;
    private OrthographicCamera camera;

    private enum ScalingDirection {
        SCALING_UP,
        SCALING_DOWN,
        NOT_SCALING
    }

    public SplashScreen(MyGdxGame host) {
        this.host = host;
        batch = host.gameBatch;
        camera = host.camera;

        bg = new Texture("splashScreen/splashBackground.jpg");
        head = new Texture("splashScreen/splashHead.png");
        title = new Texture("splashScreen/splashTitle.png");
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);
        ScreenUtils.clear(0, 0, 0, 0);

        batch.begin();
        batch.draw(bg, 0, 0);
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

        scaler();
    }

    public void scaler() {
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

                if (scalingDirection == ScalingDirection.SCALING_DOWN && scale <= 1){
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
    }

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
