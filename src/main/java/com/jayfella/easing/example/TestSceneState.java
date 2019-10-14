package com.jayfella.easing.example;

import com.jayfella.easing.Easings;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.simsilica.lemur.Label;

public class TestSceneState extends BaseAppState {

    private final Node sceneNode = new Node("Scene");

    private final int count = Easings.Function.values().length;
    private final Node[] nodes = new Node[count];
    private final float extent = 0.75f;

    public TestSceneState() {

    }

    private ColorRGBA createRandomColor() {
        return new ColorRGBA(FastMath.nextRandomFloat(), FastMath.nextRandomFloat(), FastMath.nextRandomFloat(), 1.0F);
    }

    private Geometry createBox() {

        Material material = new Material(getApplication().getAssetManager(), "Common/MatDefs/Light/PBRLighting.j3md");
        material.setColor("BaseColor", createRandomColor());
        material.setFloat("Roughness", FastMath.nextRandomFloat());
        material.setFloat("Metallic", 0.01f);

        Geometry geometry = new Geometry("Box", new Box(extent,extent,extent));
        geometry.setMaterial(material);

        return geometry;
    }

    @Override
    protected void initialize(Application app) {

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
            sceneNode.attachChild(node);

            nodes[i] = node;
        }

        getApplication().getCamera().setLocation(new Vector3f(nodes[nodes.length - 1].getLocalTranslation().x * 0.5f, 0, 20));
    }

    @Override
    protected void cleanup(Application app) {

    }

    @Override
    protected void onEnable() {
        ((SimpleApplication)getApplication()).getRootNode().attachChild(sceneNode);
    }

    @Override
    protected void onDisable() {
        sceneNode.removeFromParent();
    }

    float time = 0;
    float duration = 2;
    boolean flip = false;

    float delay = 1.0f;
    float delayTime = 0.0f;

    @Override
    public void update(float tpf) {

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
