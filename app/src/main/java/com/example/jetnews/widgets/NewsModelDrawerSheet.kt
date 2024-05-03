package com.example.jetnews.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetnews.R
import com.example.jetnews.navigation.NewsScreen

@Composable
fun NewsModelDrawerSheet(
    navController:NavController,


){
    ModalDrawerSheet {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically) {
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

        NavigationDrawerItem(label = { Text(stringResource(id = R.string.home_title)) },
            selected = false,
            icon = { Icon(imageVector = Icons.Default.Home,
                contentDescription = null) },
            onClick = { navController.navigate(NewsScreen.HomeScreen.name) })
        NavigationDrawerItem(label = { Text(stringResource(id =R.string.instrest_title ))},
            selected = false,
            icon = { Icon(imageVector = Icons.Default.List, contentDescription = "interests icon") },
            onClick = { navController.navigate(NewsScreen.InterestScreen.name) })
    }
}