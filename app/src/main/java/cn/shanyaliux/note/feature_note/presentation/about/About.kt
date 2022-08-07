package cn.shanyaliux.note.feature_note.presentation.about

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessible
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavController
import cn.shanyaliux.note.BuildConfig
import cn.shanyaliux.note.R
import cn.shanyaliux.note.feature_note.presentation.about.update.Update
import cn.shanyaliux.note.ui.theme.LightBlue

@Composable
fun About(navController: NavController) {
    AboutComponent(onButtonClicked = {navController.popBackStack()})
}

@Composable
fun AboutComponent(onButtonClicked: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onButtonClicked ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "")
            }

            Text(
                text = "关于",
                style = MaterialTheme.typography.h4
            )

            //占位，为了保持“关于”二字居中显示
            IconButton(onClick = {} ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "", tint = MaterialTheme.colors.background)
            }

        }

        val context = LocalContext.current
        val (w, h) = with(LocalDensity.current) {
            250.dp.roundToPx() to 250.dp.roundToPx()
        }
        val image = remember {
            ContextCompat.getDrawable(context, R.mipmap.ic_launcher_logo)?.toBitmap(w, h)?.asImageBitmap()!!
        }
        Image(
            image,
            contentDescription = "App icon",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = "当前版本 V${BuildConfig.VERSION_NAME}",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
        )

        Spacer(modifier = Modifier.height(40.dp))

        val activity = LocalContext.current as Activity
        val update = Update(activity)

        Card(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(5.dp),
            backgroundColor = Color(0xff4a4a4a),
        ) {

            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            update.check(true)
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "版本更新",
                        modifier = Modifier.padding(10.dp),
                        fontSize = 30.sp,
                        color = LightBlue,
                    )

                    IconButton(onClick = onButtonClicked ) {
                        Icon(Icons.Filled.ArrowForwardIos, contentDescription = "")
                    }

                }
                Divider(
                    //颜色
                    color = MaterialTheme.colors.background,
                    //线的高度
                    thickness = 1.dp,
                    //距离开始的间距
                    startIndent = 10.dp
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                val clipboardManager =
                                    context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                                val clipData = ClipData.newPlainText("myEmail", "shanyaliux@qq.com")
                                clipboardManager.setPrimaryClip(clipData)
                                Toast
                                    .makeText(context, "已复制到剪贴板", Toast.LENGTH_SHORT)
                                    .show()
                            },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "联系我: ",
                            modifier = Modifier.padding(10.dp),
                            fontSize = 30.sp,
                            color = LightBlue,
                        )
                        Text(
                            text = "shanyaliux@qq.com",
                            modifier = Modifier
                                .padding(10.dp),
                            fontSize = 20.sp,
                            color = LightBlue,
                        )
                    }


                    IconButton(onClick = onButtonClicked ) {
                        Icon(Icons.Filled.ArrowForwardIos, contentDescription = "")
                    }

                }
                Divider(
                    //颜色
                    color = MaterialTheme.colors.background,
                    //线的高度
                    thickness = 1.dp,
                    //距离开始的间距
                    startIndent = 10.dp
                )
            }


        }

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "本应用代码已全部开源\nhttps://github.com/Shanyaliux/Note",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
        )



    }


}

