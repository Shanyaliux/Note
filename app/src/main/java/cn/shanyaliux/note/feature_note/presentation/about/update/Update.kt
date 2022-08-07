package cn.shanyaliux.note.feature_note.presentation.about.update

import android.app.Activity
import cn.shanyaliux.note.R
import com.azhon.appupdate.manager.DownloadManager
import com.azhon.appupdate.util.ApkUtil
import com.ejlchina.okhttps.HTTP
import com.ejlchina.okhttps.HttpResult
import com.ejlchina.okhttps.gson.GsonMsgConvertor
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class Update (private val activity: Activity) {

    private val jsonUrl =
        "https://gitee.com/Shanya/app-update/raw/master/NoteUpdate.json"

    private val http = HTTP.builder()
        .baseUrl(jsonUrl)
        .addMsgConvertor(GsonMsgConvertor())
        .build()

    fun check(showToast: Boolean=false) {
        http.async("") //  http://api.example.com/users/1
            .setOnResponse { res: HttpResult ->

                val updateBean = res.body.toBean(UpdateBean::class.java)
                updateBean?.let {
                    if (showToast) {
                        MainScope().launch {
                            update(
                                it.apkName,
                                it.downloadUrl,
                                it.versionCode,
                                it.versionName,
                                it.apkSize,
                                it.apkMd5,
                                it.updateDescription,
                                showToast
                            )
                        }
                    }else{
                        update(
                            it.apkName,
                            it.downloadUrl,
                            it.versionCode,
                            it.versionName,
                            it.apkSize,
                            it.apkMd5,
                            it.updateDescription
                        )
                    }

                    ApkUtil.deleteOldApk(
                        activity,
                        activity.externalCacheDir?.path.toString() + "/${it.apkName}"
                    )
                }

            }
            .get()
    }

    private fun update(
        apkName: String,
        downloadUrl: String,
        versionCode: Int,
        versionName: String,
        apkSize: String,
        apkMd5: String,
        updateDescription: String,
        showToast: Boolean = false
    ) {
        val manager: DownloadManager = DownloadManager.Builder(activity).run {
            apkUrl(downloadUrl)
            apkName(apkName)
            smallIcon(R.mipmap.ic_launcher_logo)
            //设置了此参数，那么内部会自动判断是否需要显示更新对话框，否则需要自己判断是否需要更新
            apkVersionCode(versionCode)
            //同时下面三个参数也必须要设置
            apkVersionName(versionName)
            apkSize(apkSize)
            apkDescription(apkDescription = updateDescription)
            apkMD5(apkMd5)
            showNewerToast(showToast)
            //省略一些非必须参数...
            build()
        }
        manager.download()
    }
}

data class UpdateBean(
    var apkName: String,
    var downloadUrl: String,
    var versionCode: Int,
    var versionName: String,
    var apkSize: String,
    var apkMd5: String,
    var updateDescription: String
)