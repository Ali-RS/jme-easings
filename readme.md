jme-easing
===

A set of easing functions for jMonkeyEngine.
Also included is a Lemur Tween function for easy animation.

![easings](https://media.kasperkamperman.com/blog/easing/easing.gif)


``` java 

    Easings.ease(
        Easings.Function.Elastic,   // the easing function you want to use.
        Easings.Action.EaseIn,      // The direction of the easing function.
        time,                       // the time between 'begin' and 'duration'.
        0,                          // the "begin" time.
        2,                          // the amount of change.
        duration                    // the duration of the effect.
        );

```

Full documentation on these easing functions: http://blog.moagrius.com/actionscript/jsas-understanding-easing/