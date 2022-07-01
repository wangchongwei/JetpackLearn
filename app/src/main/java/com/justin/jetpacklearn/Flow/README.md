# 协程 & Flow


## 协程

协程是一种并发设计模式，您可以在 Android 平台上使用它来简化异步执行的代码。
协程通过将复杂性放入库来简化异步编程。程序的逻辑可以在协程中顺序地表达，而底层库会为我们解决其异步性。
该库可以将用户代码的相关部分包装为回调、订阅相关事件、在不同线程（甚至不同机器）上调度执行，而代码则保持如同顺序执行一样简单。

协程就像非常轻量级的线程。线程是由系统调度的，线程切换或线程阻塞的开销都比较大。
CPU调度线程是基于时间片轮转算法实现，当线程过多时，每个时间片中切换、挂起线程就占用了接近一半的时间，执行效率非常低。
而协程依赖于线程，但是协程挂起时不需要阻塞线程，几乎是无代价的，协程是由开发者控制的。所以协程也像用户态的线程，非常轻量级，一个线程中可以创建任意个协程。  


协程很重要的一点就是当它挂起的时候，它不会阻塞其他线程。
协程底层库也是异步处理阻塞任务，但是这些复杂的操作被底层库封装起来，协程代码的程序流是顺序的，
不再需要一堆的回调函数，就像同步代码一样，也便于理解、调试和开发。
它是可控的，线程的执行和结束是由操作系统调度的，而协程可以手动控制它的执行和结束。

build.gralde中引入依赖

```groovy
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1"
```

### runBlocking

runBlocking 启动协程，并运行在当前线程

```kotlin
class MainActivity4 : AppCompatActivity() {
    val TAG = "MainActivity4"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        test()
        Log.e(TAG,"线程main ：${Thread.currentThread().id}")
    }
    fun test() = runBlocking {
        repeat(8) {
            Log.e(TAG, "协程执行$it 线程id：${Thread.currentThread().id}")
            delay(1000)
        }
    }
}
```

打印结果如下:  

> 协程执行0 线程id：1
协程执行1 线程id：1
协程执行2 线程id：1
协程执行3 线程id：1
协程执行4 线程id：1
协程执行5 线程id：1
协程执行6 线程id：1
协程执行7 线程id：1
线程main ：1

我们可以得出结论：
runBlocking会阻塞线程，但运行还是在当前线程

### launch:Job

```kotlin
fun testJob() {
        val job = GlobalScope.launch {
            repeat(8) {
                delay(2000)
                Log.e(TAG, "testJob: 线程id:${Thread.currentThread().id}", )
            }
        }
    }
```
打印结果如下：  
> testJob: 线程main：2
testJob: 线程id:6809
testJob: 线程id:6807
testJob: 线程id:6807
testJob: 线程id:6815
testJob: 线程id:6807
testJob: 线程id:6808
testJob: 线程id:6818
testJob: 线程id:6809

说明使用GlobalScope.launch  

* 并不是在单一线程执行的，当多个任务时，系统自动创建线程、分配线程来执行任务
* 是非阻塞、异步执行，相当于开启了子线程

launch 构建器适合执行 "一劳永逸" 的工作，意思就是说它可以启动新协程而不将结果返回给调用方

### async

```kotlin
fun testAsync(){
    GlobalScope.launch {
        var result1 = GlobalScope.async {
            getResult1()
        }
        var result2 = GlobalScope.async {
            getResult2()
        }
        val result = result1.await() + result2.await()
        Log.e(TAG, "testAsync: ThreadId =  ${Thread.currentThread().id} result = ${result}")
    }
}

private suspend fun getResult1():Int {
    delay(3000)
    Log.e(TAG, "getResult1: ThreadId =  ${Thread.currentThread().id}")
    return 4
}

private suspend fun getResult2():Int {
    delay(5000)
    Log.e(TAG, "getResult2:  ThreadId =  ${Thread.currentThread().id}")
    return 5
}

```

输出结果：
> E/MainActivity4: 线程main ：2
E/MainActivity4: getResult1: ThreadId =  7870
E/MainActivity4: getResult2:  ThreadId =  7870
E/MainActivity4: testAsync: ThreadId =  7870 result = 9

其中当打印getResult2后就打印了testAsync: result = 9
阻塞时间为5秒，而不是8秒，说明getResult1、getResult2是并行的。

而且发现 threadId都一样，说明处于同一个线程。
但是其实并不是一定处于同一个线程，如果多尝试几次，肯定也会出现完全不一样的结果。

注意：
* 协程并不等于线程，协程内部会根据任务情况自动调度线程。

async 构建器可启动新协程并允许您使用一个名为 await 的挂起函数返回 result

launch和 async 之间的很大差异是它们对异常的处理方式不同。
如果使用 async 作为最外层协程的开启方式，它期望最终是通过调用 await 来获取结果 (或者异常)，所以默认情况下它不会抛出异常。
这意味着如果使用 async 启动新的最外层协程，而不使用await，它会静默地将异常丢弃。


launch()有三个入参：
1.协程下上文；2.协程启动模式；3.协程体：block是一个带接收者的函数字面量，接收者是CoroutineScope

我们在线程中执行的代码就是第三个参数




## Flow

Flow 库是在 Kotlin Coroutines 1.3.2 发布之后新增的库。

Flow只能运行在协程之中

### Flow基本使用

Flow 能够返回多个异步计算的值，例如下面的 flow builder :

```kotlin
fun flow1() = runBlocking {
    val time = measureTimeMillis {
        flow {
            for (i in 1..5) {
                delay(100)
                emit(i)
            }
        }.collect{
            delay(100)
            println("flow collect => $it")
        }
    }
    println("time => $time")
}
```

注意：flow、collect是同步允许，总共会阻塞1000ms


### 操作符

* map
* flatmap**
* 



### 冷流

冷流： 只有当订阅者发起订阅时，事件的发送者才会开始发送事件。

同步非阻塞
### 热流

热流：不管订阅者是否存在，flow本身可以调用emit(或者tryEmit)发送事件，可以有多个观察者，也可在需要的时候发送事件。
异步非阻塞

常用的热流有：

* ChannelFlow
* StateFlow
* SharedFlow


#### ChannelFlow
```kotlin
fun channelFlow1() = runBlocking {
        val time = measureTimeMillis {
            channelFlow {
                for (i in 1..5) {
                    delay(100)
                    send(i)
                }
            }.collect {
                delay(100)
                println("channelFlow collect => $it")
            }
        }
        println("time => $time")
    }
```

channelFlow 与 collect是异步的，当send只会，collect不会阻塞后续的send，
所以总共会阻塞600ms



#### StateFlow


> StateFlow 是一个状态容器式可观察数据流，可以向其收集器发出当前状态更新和新状态更新。


一般用于MVI开发模式中，使用场景、方式都与 LiveData 类似，

查看示例：https://github.com/wangchongwei/JetpackLearn/tree/master/app/src/main/java/com/justin/jetpacklearn/mvi

用法如下所示
```kotlin
class LoginViewModel @Inject constructor(private var repository: LoginRepository): BaseViewModel() {

    val userIntent = Channel<LoginIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState>
        get() = _state
}

class MVIActivity : AppCompatActivity() {

    private fun observeViewModelState() {
        uiStateJob = lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                // .....
            }
        }
    }
}
```


##### StateFlow 与 LiveData 区别

有两点区别

* StateFlow 必须有初始值，LiveData 不需要。
* 当 View 变为 STOPPED 状态时，LiveData.observe() 会自动取消注册使用方，而从 StateFlow 或任何其他数据流收集数据则不会取消注册使用方。

对于 StateFlow 在界面销毁的时仍处于活跃状态，有两种解决方法：

- 使用 ktx 将 Flow 转换为 LiveData。
- 在界面销毁的时候，手动取消（这很容易被遗忘）。
- 使用 lifecycleScope.launchWhenStarted 开启协程

```kotlin
private var uiStateJob: Job? = null

private fun observeViewModelState() {
  uiStateJob = lifecycleScope.launch {
      viewModel.state.collect {
          ....
      }
  }
}

override fun onStop() {
  uiStateJob?.cancel()
  super.onStop()
}

```

在stop生命周期中，手动取消，但感觉此中方式麻烦且不优雅，
不如直接将 StateFlow 换为 LiveData ，
或者使用 lifecycleScope.launchWhenStarted 开启协程
避免这个问题

#### SharedFlow

> 和 StateFlow 一样，SharedFlow 也是热流，它可以将已发送过的数据发送给新的订阅者，并且具有高的配置性。

##### SharedFlow 使用场景

- 发生订阅时，需要将过去已经更新的n个值，同步给新的订阅者。
- 配置缓存策略。



## Retrofit + Flow + LiveData

在实际使用中，请求远端数据，
在ViewModel请求数据获取到flow，
在flow中对数据流进行处理，将处理结果赋值给一个LiveData
在Activity中订阅LiveData数据，

此时当数据获取到，或者数据发生变更时，会自动触发数据更新。

