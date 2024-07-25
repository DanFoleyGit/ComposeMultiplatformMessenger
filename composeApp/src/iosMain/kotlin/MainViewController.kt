import androidx.compose.ui.window.ComposeUIViewController
import di.initKoin

/*
ComposeUIViewController has parameter called configure which is a lambda and we can call initKoin() there
 */
fun MainViewController() = ComposeUIViewController(
    configure =  {
        initKoin()
    }
) { App() }