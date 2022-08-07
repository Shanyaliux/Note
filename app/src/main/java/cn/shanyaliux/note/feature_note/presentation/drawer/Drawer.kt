package cn.shanyaliux.note.feature_note.presentation.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import cn.shanyaliux.note.R
import cn.shanyaliux.note.feature_note.presentation.util.Screen


private val screens = listOf(
    Screen.NotesScreen,
    Screen.About
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Surface(
        color = MaterialTheme.colors.background,
    ) {
        Column(
            modifier
                .fillMaxSize()
                .padding(start = 24.dp, top = 48.dp)
        ) {

            val context = LocalContext.current
            val (w, h) = with(LocalDensity.current) {
                100.dp.roundToPx() to 100.dp.roundToPx()
            }
            val image = remember {
                ContextCompat.getDrawable(context, R.mipmap.ic_launcher_logo)?.toBitmap(w, h)?.asImageBitmap()!!
            }
            Image(image, contentDescription = "App icon")

            screens.forEach { screen ->
                Spacer(Modifier.height(24.dp))
                Text(
                    text = screen.title,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.clickable {
                        onDestinationClicked(screen.route)
                    }
                )
            }
        }
    }

}

@Preview
@Composable
fun PreviewDrawer(){
    Drawer(onDestinationClicked = {})
}