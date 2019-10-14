package com.jayfella.easing;

public class Easings {

    public enum Function {
        Back,
        Bounce,
        Circ,
        Cubic,
        Elastic,
        Expo,
        Linear,
        Quad,
        Quart,
        Quint,
        Sine;

        public float easeIn(float t, float b, float c, float d) {

            switch (this) {

                case Back: return penner.easing.Back.easeIn(t, b, c, d);
                case Bounce: return penner.easing.Bounce.easeIn(t, b, c, d);
                case Circ: return penner.easing.Circ.easeIn(t, b, c, d);
                case Cubic: return penner.easing.Cubic.easeIn(t, b, c, d);
                case Elastic: return penner.easing.Elastic.easeIn(t, b, c, d);
                case Expo: return penner.easing.Expo.easeIn(t, b, c, d);
                case Linear: return penner.easing.Linear.easeIn(t, b, c, d);
                case Quad: return penner.easing.Quad.easeIn(t, b, c, d);
                case Quart: return penner.easing.Quart.easeIn(t, b, c, d);
                case Quint: return penner.easing.Quint.easeIn(t, b, c, d);
                case Sine: return penner.easing.Sine.easeIn(t, b, c, d);

                default: throw new IllegalArgumentException("Unknown Easing: " + this.name());
            }
        }


        public float easeOut(float t, float b, float c, float d) {

            switch (this) {

                case Back: return penner.easing.Back.easeOut(t, b, c, d);
                case Bounce: return penner.easing.Bounce.easeOut(t, b, c, d);
                case Circ: return penner.easing.Circ.easeOut(t, b, c, d);
                case Cubic: return penner.easing.Cubic.easeOut(t, b, c, d);
                case Elastic: return penner.easing.Elastic.easeOut(t, b, c, d);
                case Expo: return penner.easing.Expo.easeOut(t, b, c, d);
                case Linear: return penner.easing.Linear.easeOut(t, b, c, d);
                case Quad: return penner.easing.Quad.easeOut(t, b, c, d);
                case Quart: return penner.easing.Quart.easeOut(t, b, c, d);
                case Quint: return penner.easing.Quint.easeOut(t, b, c, d);
                case Sine: return penner.easing.Sine.easeOut(t, b, c, d);

                default: throw new IllegalArgumentException("Unknown Easing: " + this.name());
            }
        }

        public float easeInOut(float t, float b, float c, float d) {

            switch (this) {

                case Back: return penner.easing.Back.easeInOut(t, b, c, d);
                case Bounce: return penner.easing.Bounce.easeInOut(t, b, c, d);
                case Circ: return penner.easing.Circ.easeInOut(t, b, c, d);
                case Cubic: return penner.easing.Cubic.easeInOut(t, b, c, d);
                case Elastic: return penner.easing.Elastic.easeInOut(t, b, c, d);
                case Expo: return penner.easing.Expo.easeInOut(t, b, c, d);
                case Linear: return penner.easing.Linear.easeInOut(t, b, c, d);
                case Quad: return penner.easing.Quad.easeInOut(t, b, c, d);
                case Quart: return penner.easing.Quart.easeInOut(t, b, c, d);
                case Quint: return penner.easing.Quint.easeInOut(t, b, c, d);
                case Sine: return penner.easing.Sine.easeInOut(t, b, c, d);

                default: throw new IllegalArgumentException("Unknown Easing: " + this.name());
            }
        }
    }

    public enum Action {
        EaseIn,
        EaseOut,
        EaseInOut
    }

    public static float  ease(Function function, Action action, double time, float begin, float change, double duration) {
        return ease(function, action, (float)time, begin, change, (float)duration);
    }

    public static float ease(Function function, Action action, float time, float begin, float change, float duration) {

        switch (action) {
            case EaseIn: return function.easeIn(time, begin, change, duration);
            case EaseOut: return function.easeOut(time, begin, change, duration);
            case EaseInOut: return function.easeInOut(time, begin, change, duration);

            default: throw new IllegalArgumentException("Unknown Easing: " + function.name() + " : " + action.name());
        }

    }


}
