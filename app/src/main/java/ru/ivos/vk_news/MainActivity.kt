package ru.ivos.vk_news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import ru.ivos.vk_news.ui.MainScreen
import ru.ivos.vk_news.ui.theme.VKNewsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            VKNewsTheme() {
                MainScreen()
            }
        }
    }
}
