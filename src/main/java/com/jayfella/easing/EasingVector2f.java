package com.jayfella.easing;

import com.jme3.math.Vector2f;
import com.simsilica.lemur.anim.AbstractTween;

public abstract class EasingVector2f extends AbstractTween {

    private final double duration;
    private final Vector2f value = new Vector2f();
    private final Vector2f from, to;
    private final Easings.Function function;
    private final Easings.Action action;

    public EasingVector2f(Vector2f from, Vector2f to, double duration, Easings.Function function, Easings.Action action) {
        super(duration);

        this.duration = duration;
        this.from = from;
        this.to = to;
        this.function = function;
        this.action = action;
    }

    @Override
    protected void doInterpolate(double t) {
        float scale = Easings.ease(function, action, t, 0, 1, this.duration);
        value.interpolateLocal(from, to, scale);
        interpolateEase(value, t);
    }

    public abstract void interpolateEase(Vector2f value, double t);

}
