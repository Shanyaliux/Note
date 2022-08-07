package cn.shanyaliux.note.feature_note.presentation.about

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cn.shanyaliux.note.feature_note.presentation.about.update.Update

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

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val activity = LocalContext.current as Activity
            val update = Update(activity)

            Button(
                onClick = { update.check(true) }
            ) {
                Text(text = "更新")
            }

        }
    }


}