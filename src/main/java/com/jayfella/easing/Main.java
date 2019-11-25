package com.jayfella.easing;

import com.jayfella.easing.example.TestGuiState;
import com.jayfella.easing.example.TestSceneState;
import com.jme3.app.SimpleApplication;
import com.jme3.bounding.BoundingSphere;
import com.jme3.light.DirectionalLight;
import com.jme3.light.LightProbe;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.simsilica.lemur.GuiGlobals;
import com.simsilica.lemur.style.BaseStyles;

public class Main extends SimpleApplication {

    public static void main(String... args) {

        Main main = new Main();
        main.start();

    }

    @Override
    public void simpleInitApp() {

        stateManager.attach(new TestSceneState());
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
        Spatial probeModel = assetManager.loadModel("defaultProbe.j3o");
        LightProbe lightProbe = (LightProbe) probeModel.getLocalLightList().get(0);
        lightProbe.setBounds(new BoundingSphere(500, new Vector3f(0, 0, 0)));
        rootNode.addLight(lightProbe);

    }



}
