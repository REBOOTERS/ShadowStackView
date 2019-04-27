# ShadowStackView

Create something like **Shadow-View** animation when drag the view on screen .


## Gradle 


```groovy
implementation "com.reoobter:linktextview:0.0.1"
```

## Usage
 

```kotlin 
ShadowStack.with(this).targetView(layout).apply()
```

The function **with(activity:Activity)** need an instance of Activity or subclass of Activity . We use Activity with WeakReference<Activity> Internal.

```kotlin
ShadowStack.with(this)
    .targetView(card)
    .setShadowCount(20)
    .apply()
```

set **Shadow-View** count.

more detail usage could be found in [example]()

**Attention :The rootView of Activity (aka the layout.xml used to setContentView) can not be LinearLayout**




## todo 

[ ] **support Custom Container**
[ ] support LinearLayout
[ ] support Scrollable RecylerView


## Thanks
This library is inspired by [Laevatein](https://github.com/nohana/Laevatein) and uses some of its source code.


