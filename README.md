# ShadowStackView

Create something like **Shadow-View** animation when drag the view on screen .


## Gradle 


```groovy
implementation "com.rebooter:shadowstackview:0.0.1"
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

more detail usage could be found in [example](https://github.com/REBOOTERS/ShadowStackView/blob/master/app/src/main/java/com/engineer/shadowstackview/ui/activity/FakeActivity.kt)

**Attention :The rootView of Activity (aka the layout.xml used to setContentView) can not be LinearLayout**




## todo 

[ ] **support Custom Container**
[ ] support LinearLayout 
[ ] support Scrollable RecyclerView 


## Thanks
This library is inspired by [devilist/SnakeViewMaker](https://github.com/devilist/SnakeViewMaker) and uses some of its source code.


