package com.example.jetnews.screens.interests

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
//import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.jetnews.R
import com.example.jetnews.navigation.NewsScreen
import com.example.jetnews.screens.home.HomeScaffold
import com.example.jetnews.ui.interests.SelectTopicButton
import com.example.jetnews.widgets.NewsAppBar
import kotlinx.coroutines.launch
//
//enum class InterestPages(
//    Topics,
//    People,
//    Publications
//)


@Composable
fun InterestScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    if (currentRoute != null) {
        Log.d("current route",currentRoute)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
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
                    selected = currentRoute == NewsScreen.HomeScreen.name,
                    icon = { Icon(imageVector = Icons.Default.Home,
                        contentDescription = null) },
                    onClick = { navController.navigate(NewsScreen.HomeScreen.name) })
                NavigationDrawerItem(label = { Text(stringResource(id =R.string.instrest_title ))},
                    selected = currentRoute == NewsScreen.InterestScreen.name,
                    icon = { Icon(imageVector = Icons.Default.List, contentDescription = "interests icon") },
                    onClick = { navController.navigate(NewsScreen.InterestScreen.name) })
            }
        }) {
        InterestScaffold(navController=navController,drawerState = drawerState)
    }

}

@Composable
fun InterestScaffold(navController: NavController, drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            NewsAppBar(navController = navController,
                title = { Text(text = "Interests",style = MaterialTheme.typography.headlineMedium,
                    maxLines = 1)},
                isMainScreen = true,
                onAddActionClicked = {
                    Log.d("search icon clicked","navigate to search screen")
                }){
                Log.d("side bar icon","open side bar")
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            }
        }
    ) {contentPadding->
        InterestContent(navController = navController,modifier = Modifier.padding(top = contentPadding.calculateTopPadding()))
    }

}

@Composable
fun InterestContent(navController: NavController, modifier: Modifier) {
    InsterestPageNavigation(modifier = modifier)

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsterestPageNavigation(modifier:Modifier) {
    var state by remember{
        mutableStateOf(0)
    }
    val titles = listOf("Topics","People","Publications")
    Column(modifier = modifier) {
        //val coroutineScope = rememberCoroutineScope()
        SecondaryTabRow(selectedTabIndex = state){
            titles.forEachIndexed{index,title ->
                Tab(selected = state == index, onClick = { state = index },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    selectedContentColor = Color.Red,
                   // text = { Text(text=title,maxLines = 1, overflow = TextOverflow.Ellipsis)}
                ) {
                    Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium,
                        color=Color.Black)
                    Log.d("tab row state","$state")
                }
            }
        }

        when(state){
            0 -> TopicsContent()
            1 -> PeopleContent()
            2 -> PublicationsContent()

        }
    }
}

@Composable
fun PublicationsContent() {
    val publicationList = listOf("Kobalt Toral","KKola Uvarek","Kris Vrioc")
    LazyColumn {
        items(publicationList){publication->
            InterestContentRow(selected = false, item = publication )
        }
    }
}

@Composable
fun PeopleContent() {
    val peopleList = listOf("Kobalt Toral","KKola Uvarek","Kris Vrioc")
    LazyColumn {
        items(peopleList){people->
            InterestContentRow(selected = false, item = people )
        }
    }
}

@Preview
@Composable
fun TopicsContent() {
    val topicsListAndroid = listOf("Kotlin","Declarative UIs","Java","Unidirectional Data Flow","C++")
    val topicsListTechnology = listOf("technology","List","Fake","Topics","Technology")
    LazyColumn {
        item {
            Text(text = "Android", style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp))
        }

        items(items = topicsListAndroid){topic->
            InterestContentRow(item = topic, selected = false)

        }
        item {
            Text(text = "Technology", style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp))
        }
        items(items = topicsListTechnology){topic->
            InterestContentRow(item = topic, selected = false)

        }
    }
    
}


@Composable
fun InterestContentRow(modifier:Modifier = Modifier,
    item:String = "Kotlin",
             selected :Boolean,
             onToggle:()->Unit= {}){
    Column {
        Row(modifier= Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .toggleable(
                value = selected,
                onValueChange = { onToggle() }
            ),
            verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.placeholder_1_1), contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .padding(end = 8.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = item, style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 8.dp))

                Spacer(Modifier.width(16.dp))
                SelectTopicButton(selected = selected)
            }

//            Icon(imageVector = Icons.Default.AddCircle, contentDescription = "add icon",
//                tint = Color.Red.copy(0.6f),
//                modifier = Modifier.size(35.dp).clickable {
//                    Log.d("add icon","add topics icon clicked!")
//                    })
//            }

        }
        Box(
            modifier = Modifier.padding(start = 72.dp, end = 16.dp)
        ) {
            HorizontalDivider(
                color = MaterialTheme.colorScheme.onSurface.copy(0.1f),
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }}



