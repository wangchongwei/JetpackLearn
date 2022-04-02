# LiveData

* LiveData是什么

* LiveData的优势

* LiveData使用

* LiveData原理


## LiveData概述

LiveData 是一种可观察的数据存储器类。与常规的可观察类不同，LiveData 具有生命周期感知能力，意指它遵循其他应用组件（如 Activity、Fragment 或 Service）的生命周期。这种感知能力可确保 LiveData 仅更新处于活跃生命周期状态的应用组件观察者

如果观察者（由 Observer 类表示）的生命周期处于 STARTED 或 RESUMED 状态，则 LiveData 会认为该观察者处于活跃状态。LiveData 只会将更新通知给活跃的观察者。为观察 LiveData 对象而注册的非活跃观察者不会收到更改通知。

您可以注册与实现 LifecycleOwner 接口的对象配对的观察者。有了这种关系，当相应的 Lifecycle 对象的状态变为 DESTROYED 时，便可移除此观察者。这对于 Activity 和 Fragment 特别有用，因为它们可以放心地观察 LiveData 对象，而不必担心泄露（当 Activity 和 Fragment 的生命周期被销毁时，系统会立即退订它们）。

## 使用LiveData优势

使用 LiveData 具有以下优势：

* 确保界面符合数据状态
  LiveData 遵循观察者模式。当底层数据发生变化时，LiveData 会通知 Observer 对象。您可以整合代码以在这些 Observer 对象中更新界面。这样一来，您无需在每次应用数据发生变化时更新界面，因为观察者会替您完成更新。
  
* 不会发生内存泄漏
    观察者会绑定到Lifecycle对象，并在其关联的生命周期遭到销毁后自我清理
  
* 不会因 Activity 停止而导致崩溃
  如果观察者的生命周期处于非活跃状态（如返回栈中的 Activity），则它不会接收任何 LiveData 事件
  
* 不再需要手动处理生命周期
  界面组件只是观察相关数据，不会停止或恢复观察。LiveData 将自动管理所有这些操作，因为它在观察时可以感知相关的生命周期状态变化。
  
* 数据始终保持最新状态
  如果生命周期变为非活跃状态，它会在再次变为活跃状态时接收最新的数据。例如，曾经在后台的 Activity 会在返回前台后立即接收最新的数据
  
* 适当的配置更改
  如果由于配置更改（如设备旋转）而重新创建了 Activity 或 Fragment，它会立即接收最新的可用数据。

* 共享资源
  您可以使用单例模式扩展 LiveData 对象以封装系统服务，以便在应用中共享它们。LiveData 对象连接到系统服务一次，然后需要相应资源的任何观察者只需观察 LiveData 对象。如需了解详情，请参阅扩展 LiveData。
  
## LiveData使用

* 添加依赖
```groovy
implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.4.0'
```

* 创建 LiveData 的实例以存储某种类型的数据。这通常在 ViewModel 类中完成。

* 创建可定义 onChanged() 方法的 Observer 对象，该方法可以控制当 LiveData 对象存储的数据更改时会发生什么。
  通常情况下，您可以在界面控制器（如 Activity 或 Fragment）中创建 Observer 对象。
  
* 使用 observe() 方法将 Observer 对象附加到 LiveData 对象。observe() 方法会采用 LifecycleOwner 对象。这样会使 Observer 对象订阅 LiveData 对象，以使其收到有关更改的通知。
  通常情况下，您可以在界面控制器（如 Activity 或 Fragment）中附加 Observer 对象。
  
> 注意：您可以使用 observeForever(Observer) 方法在没有关联的 LifecycleOwner 对象的情况下注册一个观察者。
> 在这种情况下，观察者会被视为始终处于活跃状态，因此它始终会收到关于修改的通知。
> 您可以通过调用 removeObserver(Observer) 方法来移除这些观察者。

当您更新存储在 LiveData 对象中的值时，它会触发所有已注册的观察者（只要附加的 LifecycleOwner 处于活跃状态）。

LiveData 允许界面控制器观察者订阅更新。当 LiveData 对象存储的数据发生更改时，界面会自动更新以做出响应


### 创建LiveData对象

LiveData 是一种可用于任何数据的封装容器，其中包括可实现 Collections 的对象，如 List。

```kotlin
private lateinit var binding: ActivityLiveDataBinding
    private lateinit var liveData: MutableLiveData<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLiveDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        liveData = MutableLiveData<String>()
    }
```

最初，LiveData 对象中的数据并未经过设置

> 注意：请确保用于更新界面的 LiveData 对象存储在 ViewModel 对象中，而不是将其存储在 Activity 或 Fragment 中，原因如下：
> 避免 Activity 和 Fragment 过于庞大。现在，这些界面控制器负责显示数据，但不负责存储数据状态。
> 将 LiveData 实例与特定的 Activity 或 Fragment 实例分离开，并使 LiveData 对象在配置更改后继续存在。

我们只是简单示例，所以先简单说明

### 观察 LiveData 对象

在大多数情况下，应用组件的 onCreate() 方法是开始观察 LiveData 对象的正确着手点，原因如下：

* 确保系统不会从 Activity 或 Fragment 的 onResume() 方法进行冗余调用。
  
* 确保 Activity 或 Fragment 变为活跃状态后具有可以立即显示的数据。
  一旦应用组件处于 STARTED 状态，就会从它正在观察的 LiveData 对象接收最新值。
  只有在设置了要观察的 LiveData 对象时，才会发生这种情况。

通常，LiveData 仅在数据发生更改时才发送更新，并且仅发送给活跃观察者。
此行为的一种例外情况是，观察者从非活跃状态更改为活跃状态时也会收到更新。
此外，如果观察者第二次从非活跃状态更改为活跃状态，则只有在自上次变为活跃状态以来值发生了更改时，它才会收到更新。

以下示例代码说明了如何开始观察 LiveData 对象

```kotlin
    var observer: Observer<String>  = Observer<String>{
        binding.textView.text = it
    }
    liveData.observe(this, observer)
```

在传递 observer 参数的情况下调用 observe() 后，系统会立即调用 onChanged()，从而提供 liveData 中存储的最新值。
如果 LiveData 对象尚未在 liveData 中设置值，则不会调用 onChanged()。

### 更新 LiveData 对象

LiveData 没有公开可用的方法来更新存储的数据。MutableLiveData 类将公开 setValue(T) 和 postValue(T) 方法，
如果您需要修改存储在 LiveData 对象中的值，则必须使用这些方法。通常情况下会在 ViewModel 中使用 MutableLiveData，
然后 ViewModel 只会向观察者公开不可变的 LiveData 对象。

设置观察者关系后，您可以更新 LiveData 对象的值（如以下示例中所示），这样会触发所有观察者：

```kotlin
liveData.setValue("Hello World!!")
```

调用 setValue() 或 postValue() 都会触发观察者并更新界面

> 注意：您必须调用 setValue(T) 方法以从主线程更新 LiveData 对象。
> 如果在工作器线程中执行代码，您可以改用 postValue(T) 方法来更新 LiveData 对象。