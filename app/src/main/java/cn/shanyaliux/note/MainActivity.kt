package cn.shanyaliux.note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cn.shanyaliux.note.feature_note.presentation.about.About
import cn.shanyaliux.note.feature_note.presentation.add_edit_note.AddEditNoteScreen
import cn.shanyaliux.note.feature_note.presentation.drawer.Drawer
import cn.shanyaliux.note.feature_note.presentation.notes.NotesScreen
import cn.shanyaliux.note.feature_note.presentation.util.Screen
import cn.shanyaliux.note.ui.theme.NoteTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()


                    val drawerState = rememberDrawerState(DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    val openDrawer = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                    ModalDrawer(
                        drawerState = drawerState,
                        gesturesEnabled = drawerState.isOpen,
                        drawerContent = {
                            Drawer(
                                onDestinationClicked = { route ->
                                    scope.launch {
                                        drawerState.close()
                                    }
                                    navController.navigate(route) {
                                        popUpTo = navController.graph.startDestinationId
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.NotesScreen.route
                        ) {
                            composable(route = Screen.NotesScreen.route) {
                                NotesScreen(
                                    navController = navController,
                                    openDrawer = {
                                        openDrawer()
                                    }
                                )
                            }
                            composable(
                                route = Screen.AddEditNoteScreen.route +
                                        "?noteId={noteId}&noteColor={noteColor}",
                                arguments = listOf(
                                    navArgument(
                                        name = "noteId"
                                    ) {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    },
                                    navArgument(
                                        name = "noteColor"
                                    ) {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    },
                                )
                            ) {
                                val color = it.arguments?.getInt("noteColor") ?: -1
                                AddEditNoteScreen(
                                    navController = navController,
                                    noteColor = color
                                )
                            }

                            composable(Screen.About.route) {
                                About(
                                    navController = navController
                                )
                            }
                        }
                    }



                }
            }
        }
    }
}


