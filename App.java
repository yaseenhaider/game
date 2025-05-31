import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

public class App extends SimpleApplication implements ActionListener {

    private Spatial bike;
    private TerrainQuad terrain;
    private boolean isMovingForward = false;
    private boolean isMovingBackward = false;
    private boolean isTurningLeft = false;
    private boolean isTurningRight = false;

    public static void main(String[] args) {
        App app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        // Set up camera
        flyCam.setMoveSpeed(50);
        cam.setLocation(new Vector3f(0, 10, 20));
        cam.lookAt(Vector3f.ZERO, Vector3f.UNIT_Y);

        // Add light
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.5f, -0.5f, -0.5f));
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

        // Load bike model
        bike = assetManager.loadModel("Models/bike.obj");
        bike.scale(0.1f);
        bike.setLocalTranslation(0, 1, 0);
        rootNode.attachChild(bike);

        // Create terrain
        createTerrain();

        // Set up input mappings
        inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Backward", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addListener(this, "Forward", "Backward", "Left", "Right");
    }

    private void createTerrain() {
        // Load height map
        Texture heightMapImage = assetManager.loadTexture("Textures/heightmap.png");
        AbstractHeightMap heightmap = new ImageBasedHeightMap(heightMapImage.getImage());
        heightmap.load();

        // Create terrain
        terrain = new TerrainQuad("terrain", 65, 513, heightmap.getHeightMap());
        Texture grass = assetManager.loadTexture("Textures/grass.jpg");
        grass.setWrap(WrapMode.Repeat);
        terrain.setMaterial(assetManager.loadMaterial("Materials/Terrain.j3m"));
        terrain.setLocalScale(1, 1, 1);
        terrain.setLocalTranslation(0, 0, 0);
        rootNode.attachChild(terrain);
    }

    @Override
    public void onAction(String binding, boolean isPressed, float tpf) {
        if (binding.equals("Forward")) {
            isMovingForward = isPressed;
        } else if (binding.equals("Backward")) {
            isMovingBackward = isPressed;
        } else if (binding.equals("Left")) {
            isTurningLeft = isPressed;
        } else if (binding.equals("Right")) {
            isTurningRight = isPressed;
        }
    }

    @Override
    public void simpleUpdate(float tpf) {
        // Handle bike movement
        if (isMovingForward) {
            bike.move(bike.getLocalRotation().getRotationColumn(2).mult(-0.1f));
        }
        if (isMovingBackward) {
            bike.move(bike.getLocalRotation().getRotationColumn(2).mult(0.1f));
        }
        if (isTurningLeft) {
            bike.rotate(0, 0.05f, 0);
        }
        if (isTurningRight) {
            bike.rotate(0, -0.05f, 0);
        }

        // Ensure bike stays on terrain
        float height = terrain.getHeight(bike.getLocalTranslation());
        bike.setLocalTranslation(bike.getLocalTranslation().x, height + 1, bike.getLocalTranslation().z);
    }
}