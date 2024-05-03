package com.example.jetnews.widgets

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetnews.R
import com.example.jetnews.navigation.NewsScreen
import com.example.jetnews.screens.home.HomeScaffold
import com.example.jetnews.ui.theme.JetNewsTheme

@Composable
fun NewsNavigationDrawer(
    currentRoute:String,
    navigateToHome:() -> Unit,
    navigateToInterest: () -> Unit,
    closeDrawer:()-> Unit,
    modifier: Modifier = Modifier
){
    ModalDrawerSheet(modifier) {
        JetNewsLogo(modifier = Modifier.padding(horizontal = 28.dp,vertical = 50.dp))
        NavigationDrawerItem(
            label = { Text(stringResource(id = R.string.home_title)) },
            selected = currentRoute == NewsScreen.HomeScreen.name,
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
            onClick = { navigateToHome.invoke(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding))
        NavigationDrawerItem(label = { Text(text = "Interests") }, selected = false,
            icon = { Icon(imageVector = Icons.AutoMirrored.Default.List, contentDescription = null) },
            onClick = { navigateToInterest.invoke() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding))
    }
}

@Composable
fun JetNewsLogo(modifier: Modifier = Modifier) {
    Row(modifier = Modifier) {
        Icon(
            painterResource(id = R.drawable.ic_jetnews_logo),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(8.dp))
        //Text(text = "Jetnews")
        Icon(painter = painterResource(id = R.drawable.ic_jetnews_wordmark),
            contentDescription = stringResource(id = R.string.app_name),
            tint = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode =UI_MODE_NIGHT_YES)
@Composable
fun PreviewNewsNavigationDrawer(){
    JetNewsTheme {
        NewsNavigationDrawer(
            currentRoute = NewsScreen.HomeScreen.name,
            navigateToHome = {},
            navigateToInterest = {  },
            closeDrawer = {  })
    }
}