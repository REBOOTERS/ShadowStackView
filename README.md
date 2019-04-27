# ShadowStackView

Create something like **Shadow-View** animation when drag the view on screen


<img src="https://raw.githubusercontent.com/REBOOTERS/Images/master/ShadowStackView/shadowview.gif"/>    <img src="https://raw.githubusercontent.com/REBOOTERS/Images/master/ShadowStackView/shadowview_fragment.gif"/>

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

- [ ] support Custom Container
- [ ] support LinearLayout 
- [ ] support Scrollable RecyclerView 


## Thanks
This library is inspired by [devilist/SnakeViewMaker](https://github.com/devilist/SnakeViewMaker) and uses some of its source code.

## License 

---------------------

    Copyright 2019 REBOOTERS

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


