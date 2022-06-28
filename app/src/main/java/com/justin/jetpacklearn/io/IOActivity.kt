package com.justin.jetpacklearn.io

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import com.justin.jetpacklearn.BuildConfig.VERSION_CODE
import com.justin.jetpacklearn.databinding.ActivityIoBinding
import okio.Okio
import okio.Sink
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer

class IOActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPermission()
        initView()
    }

    private fun initPermission() {
        // 这样可能还是会报错：/storage/emulated/0/io/okio.txt: open failed: ENOENT (No such file or directory)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // 当为android 11时，需要更改请求文件管理权限的方式
            if (!Environment.isExternalStorageManager()) {
                var intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 102);
            }
        } else {
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                var permissionArray = arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                )
                requestPermissions(permissionArray, 101)
            }
        }
    }

    private fun initView() {
        var sdcardPath = Environment.getExternalStorageDirectory()
        var path = File(sdcardPath, "io")
        if(!path.exists()) {
            path.mkdirs()
        }
        println("path:" + path.path)
        //  注意权限问题
        binding.okio.setOnClickListener {
            // 使用okio往文件中写入内容
            var okioFile = File(path, "okio.txt")
            println("filePath:" + okioFile.path)
            // 获取 sink （输出流）
            var sink: Sink = Okio.sink(okioFile)
            // 构建缓冲区buffer
            var bufferSink = Okio.buffer(sink)
            // 写入
            bufferSink.writeUtf8("okio,writeToFile, test!")

            bufferSink.close()
            sink.flush()
            sink.close()
        }

        binding.nio.setOnClickListener {
            var nioFile = File(path, "nio.txt")

            var fileOutputStream = FileOutputStream(nioFile)

            var fileChannel = fileOutputStream.channel

            var content: String = "haha, test write file by NIO"

            var sendBuffer = ByteBuffer.wrap(content.toByteArray())

            fileChannel.write(sendBuffer)

            sendBuffer.clear()
            fileChannel.close()
            fileOutputStream.flush()
            fileOutputStream.close()
        }

        binding.bio.setOnClickListener {
            var bioFile = File(path, "bio.txt")
            println("bioFile => " + bioFile.path)

            var fileOutputStream = FileOutputStream(bioFile)

            // 这里调用最终会调用到 /libcore/luni/src/main/native/libcore_io_Linux.cpp 中 Linux_pwriteBytes 函数
            // 也就是说，当我们调用 fileOutputStream.write，本质上是直接调用系统调用pwrite(随机写)
            // 每一次调用系统调用进入内核态都必须存储当前寄存器中所有的状态，当恢复会到用户态的时候，又要还原回去，开销大
            fileOutputStream.write("haha, test write file by BIO".toByteArray())

            fileOutputStream.flush()
            fileOutputStream.close()
        }

        binding.aio.setOnClickListener {

        }
    }


}