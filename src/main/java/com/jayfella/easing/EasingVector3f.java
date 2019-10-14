package com.jayfella.easing;

import com.jme3.math.Vector3f;
import com.simsilica.lemur.anim.AbstractTween;

public abstract class EasingVector3f extends AbstractTween {

    private final Vector3f value = new Vector3f();
    private final Vector3f from, to;
    private final double duration;
    private final Easings.Function function;
    private final Easings.Action action;

    public EasingVector3f(Vector3f from, Vector3f to, double length, Easings.Function function, Easings.Action action) {
        super(length);

        this.from = from;
        this.to = to;
        this.duration = length;
        this.function = function;
        this.action = action;
    }

    @Override
    protected void doInterpolate(double t) {
        float scale = Easings.ease(function, action, t, 0, 1, this.duration);
        value.interpolateLocal(from, to, scale);
        interpolateEase(value, t);
    }

    public abstract void interpolateEase(Vector3f value, double t);

}
