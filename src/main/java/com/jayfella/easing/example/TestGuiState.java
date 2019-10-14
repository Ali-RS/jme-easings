package com.jayfella.easing.example;

import com.jayfella.easing.Easings;
import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.math.Vector3f;
import com.simsilica.lemur.*;
import com.simsilica.lemur.core.VersionedList;
import com.simsilica.lemur.core.VersionedReference;
import com.simsilica.lemur.text.DocumentModel;

import java.util.Arrays;
import java.util.Set;

public class TestGuiState extends BaseAppState {

    private Container controlContainer;
    private Container demoContainer;

    VersionedReference<Set<Integer>> funcRef;
    VersionedReference<Set<Integer>> actionRef;
    VersionedReference<DocumentModel> durationRef;

    private Easings.Function func = Easings.Function.values()[0];
    private Easings.Action action = Easings.Action.values()[0];

    private float time = 0;
    private float duration = 2;

    public TestGuiState() {

    }

    private void createContolContainer() {

        Container container = new Container();

        ListBox<Easings.Function> funcListBox = container.addChild(new ListBox<>(new VersionedList<>(Arrays.asList(Easings.Function.values()))), 0, 0);
        funcListBox.setPreferredSize(new Vector3f(100, funcListBox.getPreferredSize().y, 1));
        funcRef = funcListBox.getSelectionModel().createReference();

        ListBox<Easings.Action> actionListBox = container.addChild(new ListBox<>(new VersionedList<>(Arrays.asList(Easings.Action.values()))), 0, 1);
        actionListBox.setPreferredSize(new Vector3f(100, actionListBox.getPreferredSize().y, 1));
        actionRef = actionListBox.getSelectionModel().createReference();

        container.addChild(new Label("Duration"),1, 0);
        TextField durationTextField = container.addChild(new TextField("" + duration), 1, 1);
        durationRef = durationTextField.getDocumentModel().createReference();

        Button executeButton = container.addChild(new Button("Execute"), 2, 0);
        executeButton.addClickCommands(new Command<Button>() {
            @Override
            public void execute(Button source) {
                time = 0;
            }
        });

        container.setLocalTranslation(10, getApplication().getCamera().getHeight() - 10, 0);

        controlContainer = container;

    }

    private void createDemoContainer() {
        Container container = new Container();

        container.addChild(new Label("This is a demo container."));

        demoContainer = container;
    }

    @Override
    protected void initialize(Application app) {

        createContolContainer();
        createDemoContainer();

    }

    @Override
    protected void cleanup(Application app) {

    }

    @Override
    protected void onEnable() {
        ((SimpleApplication)getApplication()).getGuiNode().attachChild(controlContainer);
        ((SimpleApplication)getApplication()).getGuiNode().attachChild(demoContainer);
    }

    @Override
    protected void onDisable() {
        controlContainer.removeFromParent();
        demoContainer.removeFromParent();
    }

    @Override
    public void update(float tpf) {

        if (funcRef.update()) {
            Set<Integer> indexes = funcRef.get();
            int index = indexes.iterator().next();
            func = Easings.Function.values()[index];
        }

        if (actionRef.update()) {
            Set<Integer> indexes = actionRef.get();
            int index = indexes.iterator().next();
            action = Easings.Action.values()[index];
        }

        if (durationRef.update()) {
            DocumentModel model = durationRef.get();

            try {
                duration = Float.parseFloat(model.getText());
            }
            catch (NumberFormatException ex) {
                // ignored
            }

        }

        if (time < duration) {
            time += tpf;
        }

        // the start position will be where the demoContainer is just off-screen.
        float start = -demoContainer.getPreferredSize().x;

        // the change will be the length from the start to the end position.
        float change = (getApplication().getCamera().getWidth() * 0.5f) + (demoContainer.getPreferredSize().x * 0.5f);

        float x = Easings.ease(func, action, time, 0, change, duration);
        x += start;

        demoContainer.setLocalTranslation(
                x,
                demoContainer.getPreferredSize().x - 10,
                0
        );

    }

}
