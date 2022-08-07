package cn.shanyaliux.note.feature_note.presentation.drawer

import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            Image(
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp),
                painter = painterResource(R.drawable.logo),
                contentDescription = "App icon"
            )
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