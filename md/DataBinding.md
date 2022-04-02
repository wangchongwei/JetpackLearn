# DataBinding视图绑定

通过视图绑定功能，您可以更轻松地编写可与视图交互的代码。在模块中启用视图绑定之后，系统会为该模块中的每个 XML 布局文件生成一个绑定类。
绑定类的实例包含对在相应布局中具有 ID 的所有视图的直接引用。

在大多数情况下，视图绑定会替代 findViewById。

## 开启DataBinding
在模块的build.gradle中设置
```groovy
    android {
//    ...
    viewBinding {
        enabled = true
    }
}
```

## 使用

为某个模块启用视图绑定功能后，系统会为该模块中包含的每个 XML 布局文件生成一个绑定类。每个绑定类均包含对根视图以及具有 ID 的所有视图的引用。
系统会通过以下方式生成绑定类的名称：将 XML 文件的名称转换为驼峰式大小写，并在末尾添加“Binding”一词。

例如，假设某个布局文件的名称为 result_profile.xml

```xml
<LinearLayout>
        <TextView android:id="@+id/name" />
        <ImageView android:cropToPadding="true" />
        <Button android:id="@+id/button"
            android:background="@drawable/rounded_button" />
    </LinearLayout>
```

所生成的绑定类的名称就为 ResultProfileBinding。此类具有两个字段：一个是名为 name 的 TextView，另一个是名为 button 的 Button。
该布局中的 ImageView 没有 ID，因此绑定类中不存在对它的引用。

每个绑定类还包含一个 getRoot() 方法，用于为相应布局文件的根视图提供直接引用。在此示例中，ResultProfileBinding 类中的 getRoot() 方法会返回 LinearLayout 根视图。

### 在Activity中使用

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
```

您现在即可使用该绑定类的实例来引用任何视图
```kotlin
// livedata 是一个Button，id是livedata
    binding.livedata.setOnClickListener{
        println("JumpLiveDataActivity ===>")
        startActivity(Intent(this, LiveDataActivity::class.java))
    }
```

### 在Fragment中使用