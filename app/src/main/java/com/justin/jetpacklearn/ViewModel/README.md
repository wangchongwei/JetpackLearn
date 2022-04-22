# ViewModel

ViewModel 类旨在以注重生命周期的方式存储和管理界面相关的数据。ViewModel 类让数据可在发生屏幕旋转等配置更改后继续留存

Android 框架可以管理界面控制器（如 Activity 和 Fragment）的生命周期。Android 框架可能会决定销毁或重新创建界面控制器，以响应完全不受您控制的某些用户操作或设备事件。

如果系统销毁或重新创建界面控制器，则存储在其中的任何瞬态界面相关数据都会丢失。例如，应用可能会在它的某个 Activity 中包含用户列表。
为配置更改重新创建 Activity 后，新 Activity 必须重新提取用户列表。
对于简单的数据，Activity 可以使用 onSaveInstanceState() 方法从 onCreate() 中的捆绑包恢复其数据，但此方法仅适合可以序列化再反序列化的少量数据，
而不适合数量可能较大的数据，如用户列表或位图。

另一个问题是，界面控制器经常需要进行可能需要一些时间才能返回的异步调用。界面控制器需要管理这些调用，并确保系统在其销毁后清理这些调用以避免潜在的内存泄漏。
此项管理需要大量的维护工作，并且在为配置更改重新创建对象的情况下，会造成资源的浪费，因为对象可能需要重新发出已经发出过的调用。

诸如 Activity 和 Fragment 之类的界面控制器主要用于显示界面数据、对用户操作做出响应或处理操作系统通信（如权限请求）。
如果要求界面控制器也负责从数据库或网络加载数据，那么会使类越发膨胀。
为界面控制器分配过多的责任可能会导致单个类尝试自己处理应用的所有工作，而不是将工作委托给其他类。以这种方式为界面控制器分配过多的责任也会大大增加测试的难度。

从界面控制器逻辑中分离出视图数据所有权的操作更容易且更高效。


## 实现 ViewModel

### 添加依赖
```groovy
// ViewModel依赖
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation "androidx.activity:activity-ktx:1.2.2"
```

架构组件为界面控制器提供了 ViewModel 辅助程序类，该类负责为界面准备数据。
在配置更改期间会自动保留 ViewModel 对象，以便它们存储的数据立即可供下一个 activity 或 fragment 实例使用。
例如，如果您需要在应用中显示用户列表，请确保将获取和保留该用户列表的责任分配给 ViewModel，而不是 activity 或 fragment，如以下示例代码所示：

```kotlin
class MyViewModel : ViewModel() {

    private val users: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>().also {
            loadUsers()
        }
    }

    fun getUsers(): LiveData<List<User>> {
        return users
    }

    private fun loadUsers() {
        // TODO: 2022/4/7  
    }
}
```

然后，您可以从 Activity 访问该列表，如下所示：

```kotlin
class ViewModelActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityViewModelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)

        val model: MyViewModel by viewModels()

        var observer: Observer<List<User>> = Observer {
            println("it => " + it)
        }

        model.getUsers().observe(this, observer)
    }
}

```


如果重新创建了该 Activity，它接收的 MyViewModel 实例与第一个 Activity 创建的实例相同。
当所有者 Activity 完成（finish）时，框架会调用 ViewModel 对象的 onCleared() 方法，以便它可以清理资源。  


> 注意：ViewModel 绝不能引用视图、Lifecycle 或可能存储对 Activity 上下文的引用的任何类。


ViewModel 对象存在的时间比视图或 LifecycleOwners 的特定实例存在的时间更长。
这还意味着，您可以更轻松地编写涵盖 ViewModel 的测试，
因为它不了解视图和 Lifecycle 对象。ViewModel 对象可以包含 LifecycleObservers，如 LiveData 对象。
但是，ViewModel 对象绝不能观察对生命周期感知型可观察对象（如 LiveData 对象）的更改。
如果 ViewModel 需要 Application 上下文（例如，为了查找系统服务），
它可以扩展 AndroidViewModel 类并设置用于接收 Application 的构造函数， 因为 Application 类会扩展 Context