package com.jayfella.easing;

import com.jayfella.easing.example.TestGuiState;
import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingSphere;
import com.jme3.light.DirectionalLight;
import com.jme3.light.LightProbe;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.simsilica.lemur.Button;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.Label;
import com.simsilica.lemur.style.BaseStyles;

public class Main extends SimpleApplication {

    private final int count = Easings.Function.values().length;
    private final Node[] nodes = new Node[count];
    private final float extent = 0.75f;

    public static void main(String... args) {

        Main main = new Main();
        main.start();

    }

    private ColorRGBA createRandomColor() {
        return new ColorRGBA(FastMath.nextRandomFloat(), FastMath.nextRandomFloat(), FastMath.nextRandomFloat(), 1.0F);
    }

    private Geometry createBox() {

        Material material = new Material(assetManager, "Common/MatDefs/Light/PBRLighting.j3md");
        material.setColor("BaseColor", createRandomColor());
        material.setFloat("Roughness", FastMath.nextRandomFloat());
        material.setFloat("Metallic", 0.01f);

        Geometry geometry = new Geometry("Box", new Box(extent,extent,extent));
        geometry.setMaterial(material);

        return geometry;
    }



    @Override
    public void simpleInitApp() {

        stateManager.attach(new TestGuiState());

        flyCam.setMoveSpeed(10f);
        flyCam.setDragToRotate(true);

        // initialize the GUI
        GuiGlobals.initialize(this);
        BaseStyles.loadGlassStyle();
        GuiGlobals.getInstance().getStyles().setDefaultStyle("glass");

        // set a background color
        viewPort.setBackgroundColor(ColorRGBA.White.mult(0.1f));

        // add some light
        rootNode.addLight(new DirectionalLight(new Vector3f(-1, -1, -1).normalizeLocal(), ColorRGBA.White.mult(0.4f)));

        // add a PBR probe.
        Spatial probeModel = assetManager.loadModel("Scenes/defaultProbe.j3o");
        LightProbe lightProbe = (LightProbe) probeModel.getLocalLightList().get(0);
        lightProbe.setBounds(new BoundingSphere(500, new Vector3f(0, 0, 0)));
        rootNode.addLight(lightProbe);


        for (int i = 0; i < count; i++) {

            Node node = new Node();

            Geometry geometry = createBox();
            node.attachChild(geometry);

            Label label = new Label(Easings.Function.values()[i].name());
            label.setFontSize(0.2f);

            label.setLocalTranslation(
                    -label.getPreferredSize().x * 0.5f,
                    extent + 0.25f,
                    extent);

            node.attachChild(label);

            node.setLocalTranslation(i * 2, 0, 0);
            rootNode.attachChild(node);

            nodes[i] = node;
        }

        cam.setLocation(new Vector3f(nodes[nodes.length - 1].getLocalTranslation().x * 0.5f, 0, 15));


    }

    float time = 0;
    float duration = 2;
    boolean flip = false;

    float delay = 1.0f;
    float delayTime = 0.0f;

    @Override
    public void simpleUpdate(float tpf) {

        if (time >= duration) {
            flip = true;
        }

        else if (time <= 0) {
            flip = false;
        }

        if (flip) {
            time -= tpf;
        }
        else {
            time += tpf;
        }

        for (int i = 0; i < count; i++) {

            Easings.Function func = Easings.Function.values()[i];

            Vector3f position = nodes[i].getLocalTranslation();

            nodes[i].setLocalTranslation(
                    position.x,
                    Easings.ease(func, Easings.Action.EaseIn, time, 0, 2, duration),
                    position.z
                    );
        }

    }

}
