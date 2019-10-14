package com.jayfella.easing;

import com.simsilica.lemur.anim.AbstractTween;

public abstract class EasingTween extends AbstractTween {

    private final double duration;
    private final float from, to;
    private final Easings.Function function;
    private final Easings.Action action;

    public EasingTween(float from, float to, double length, Easings.Function function, Easings.Action action) {
        super(length);

        this.from = from;
        this.to = to;
        this.duration = length;
        this.function = function;
        this.action = action;
    }

    @Override
    protected void doInterpolate(double t) {
        float ease = Easings.ease(function, action, t, 0, 1, this.duration);

        float diff = to - from;

        interpolateEase(from + (diff * ease), t);
    }

    public abstract void interpolateEase(float x, double t);

}
