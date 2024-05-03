package com.example.jetnews.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetnews.R
import com.example.jetnews.model.Article
import com.example.jetnews.navigation.NewsScreen
import com.example.jetnews.widgets.NewsAppBar
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavController){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val currentRoute = navController.currentBackStackEntry?.destination?.route
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(painterResource(id = R.drawable.ic_jetnews_logo),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.width(8.dp))
                    //Text(text = "Jetnews")
                    Icon(painter = painterResource(id = R.drawable.ic_jetnews_wordmark),
                        contentDescription = stringResource(id = R.string.app_name),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant)
                }

                NavigationDrawerItem(label = { Text(text = "Home") },
                    selected = currentRoute == NewsScreen.HomeScreen.name,
                    icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "home icon") },
                    onClick = { navController.navigate(NewsScreen.HomeScreen.name) })
                NavigationDrawerItem(label = { Text(text = "Interests") },
                    selected = currentRoute == NewsScreen.InterestScreen.name,
                    icon = { Icon(imageVector = Icons.Default.List, contentDescription = "interests icon") },
                    onClick = { navController.navigate(NewsScreen.InterestScreen.name) })
            }
        }) {
        HomeScaffold(navController=navController,drawerState = drawerState)
    }

//    NewsNavigationDrawer(navController = navController, drawerState = drawerState) {
//        HomeScaffold(navController = navController, drawerState = drawerState)
//
//    }

}

@Composable
fun HomeScaffold(navController: NavController, drawerState: DrawerState) {

    val scope = rememberCoroutineScope()

    var selectedNavigationItem by remember {
        mutableStateOf(0)
    }
//    val navigationItems = listOf(stringResource(id = R.string.bottom_bar_title_home),
//        stringResource(id = R.string.bottom_bar_title_video),
//        stringResource(id = R.string.bottom_bar_title_live),
//        stringResource(id = R.string.bottom_bar_title_more))

    Scaffold(
        topBar = {
        NewsAppBar(title =    {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineMedium,
            maxLines = 1,
        )}
        //"jetnews"
        ,
            navController = navController,
            isMainScreen = true,
            onAddActionClicked = {
                Log.d("search icon","search icon clicked, navigate to search screen...")
            }
            ){
            Log.d("side bar icon","open side bar")
            scope.launch {
                drawerState.apply {
                    if (isClosed) open() else close()
                }
            }
        }
    },
        bottomBar = {
            val navigationItems = listOf(stringResource(id = R.string.bottom_bar_title_home),
                stringResource(id = R.string.bottom_bar_title_video),
                stringResource(id = R.string.bottom_bar_title_live),
                stringResource(id = R.string.bottom_bar_title_more))
            NavigationBar(
                modifier = Modifier.fillMaxWidth(),
                containerColor = MaterialTheme.colorScheme.surfaceContainer,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ) {

                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(selected = index == selectedNavigationItem,
                        onClick = {
                            when(index){
                                0 -> navController.navigate(NewsScreen.HomeScreen.name)
                                1 -> navController.navigate(NewsScreen.VideoScreen.name)
                                2 -> navController.navigate(NewsScreen.LiveScreen.name)
                                3 -> navController.navigate(NewsScreen.MoreScreen.name)
                            }
                        },
                        icon = {
                            when(index){
                                0 -> Icon(painter = painterResource(id = R.drawable.ic_home), contentDescription = stringResource(
                                    id = R.string.bottom_bar_cd_home),
                                    modifier = Modifier.size(25.dp))
                                1 -> Icon(painter = painterResource(id = R.drawable.ic_video), contentDescription = stringResource(
                                    id = R.string.bottom_bar_cd_video),
                                    modifier = Modifier.size(25.dp))
                                2 -> Icon(painter = painterResource(id = R.drawable.ic_live), contentDescription = stringResource(
                                    id = R.string.bottom_bar_cd_live),
                                    modifier = Modifier.size(25.dp))
                                3 -> Icon(painter = painterResource(id = R.drawable.ic_more), contentDescription = stringResource(
                                    id = R.string.bottom_bar_cd_more),
                                    modifier = Modifier.size(25.dp))
                            }
                             },
                        label = { Text(text = item)})
                }

            }
        }) {
        HomeMainContent(navController = navController,modifier = Modifier.padding(it))
    }
}


@Composable
fun HomeMainContent(navController: NavController, modifier: Modifier) {
    val articles = listOf(Article(title= "Locale changes and the AndroidViewModel antipattern",
        imageResource = painterResource(id = R.drawable.post_4),
        writer = "Jose Alcerreca",
        date="April 02",
        readingTimeSpend = 1,
        isMarked = false,
        isTopArticle = true,
        isRead = false,
    ),
        Article(title = "A Little Thing about Android Module Paths",
            imageResource = painterResource(id = R.drawable.post_1_thumb),
            writer = "Pietro Maggi",
            readingTimeSpend = 1,
            isTopArticle = false,
            date = "April 02",
            isMarked = true,
            isRead = false,),
        Article(title = "Dagger in Kotlin:Gotchas and Optimizations",
            imageResource = painterResource(id = R.drawable.post_2_thumb),
            writer = "Manuel Vivo",
            readingTimeSpend = 3,
            isTopArticle = false,
            date = "April 02",
            isMarked = true,
            isRead = true,),
        Article(title = "From Java Programming Language to Kotlin -- the idiomatic way",
            imageResource = painterResource(id = R.drawable.post_3_thumb),
            writer = "Florina Muntenescu",
            readingTimeSpend = 1,
            isTopArticle = false,
            date = "April 02",
            isMarked = false,
            isRead = true,),
        Article(title = "Collections and sequences in Kotlin",
            imageResource = painterResource(id = R.drawable.post_5),
            writer = "Florina Muntenescu",
            readingTimeSpend = 4,
            isTopArticle = false,
            date = "July 24",
            isMarked = false,
            isRead = false,),
        Article(title = "Locale changes and the AndroidViewModel antipattern",
            imageResource = painterResource(id = R.drawable.post_5),
            writer = "Jose Alcerra",
            readingTimeSpend = 1,
            isTopArticle = false,
            date = "July 24",
            isMarked = false,
            isRead =false,),
        )
    val articlesListLength = articles.size
    val verticalColumnArticles = articles.subList(1,4)
    val horizontalRowArticles = articles.subList(4,articlesListLength)

    val histories = articles.filter { it.isRead == true }

    LazyColumn(
        modifier = Modifier
            .padding(top = 80.dp, end = 16.dp)
            .fillMaxSize(),
        //verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(text = "Top stories for you", modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
                style = MaterialTheme.typography.titleSmall)
        }
        item {
            ArticlePostVertical(articles[0], navController = navController)
        }
        items(items = verticalColumnArticles){article->
            ArticlePostVertical(article,navController = navController)
        }
        item {
            Text(text = "Popular on Jetnews", style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 20.dp, bottom = 20.dp))
        }
        item {
                    LazyRow() {
            items(items=horizontalRowArticles){article->
                ArticlePostHorizontalCard(article = article,navController=navController)
            }
        }
        }
        items(items = histories){article->
            ArticlePostVertical(article,historyMark = "BASED ON YOUR HISTORY", icon = Icons.Default.MoreVert,navController = navController)
        }


    }

//    Column(modifier = Modifier
//        .padding(top = 80.dp)
//        .fillMaxSize(),
//        //verticalScroll(rememberScrollState()),
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally) {
//
//        //top article
//        ArticlePostVertical(articles[0])
//
//        //Articles lazy column
//        LazyColumn {
//            items(items = verticalColumnArticles){article->
//                ArticlePostVertical(article)
//            }
//        }
//
//        Text(text = "Popular on Jetnews", style = MaterialTheme.typography.displaySmall,
//            modifier = Modifier
//                .padding(start = 16.dp, top = 20.dp, bottom = 20.dp)
//                .align(Alignment.Start))
//        //Articles Lazy row
//        LazyRow() {
//            items(items=horizontalRowArticles){article->
//                ArticlePostHorizontalCard(article = article)
//            }
//        }
//        PopularOnJetNewsContentRow(articles = horizontalRowArticles)
//
//        //popular on JetNews
//        //PopularOnJetNewsContentRow(articles = articles)
//
//        //test
//        //ArticlePostHorizontalCard(article = articles[0])
//
//    }
}



@Composable
fun ArticlePostHorizontalCard(article: Article,navController: NavController){
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .padding(start = 16.dp, bottom = 16.dp)
            .width(300.dp)
            .height(260.dp)
//        modifier = Modifier
//            .size(width = 240.dp, height = 100.dp)
    ) {
        Image(painter = article.imageResource, contentDescription = "article picture",
            modifier = Modifier.fillMaxWidth())

            Text(
                text = article.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(top = 4.dp, start = 8.dp)
                    .clickable {
                        navController.navigate(NewsScreen.ArticleScreen.name)
                    },
                maxLines = 2
            )
            Text(text = article.writer, style = MaterialTheme.typography.headlineSmall,
                color = Color.DarkGray.copy(0.6f),
                modifier = Modifier.padding(start = 8.dp))
            Text(text = article.date + " - ${article.readingTimeSpend} min read",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.DarkGray.copy(0.6f),
                modifier = Modifier.padding(start=8.dp, bottom = 16.dp)
            )
        }
    }
    


@Composable
fun ArticlePostVertical(
    article: Article,
    historyMark:String? = null,
    icon: ImageVector? = null,
    navController: NavController ,
//    title:String="article title",
//    imageResource: Painter,
//    writer:String = "writer",
//    readingTimeSpend:Int = 1,
//    date:String? = null,
//    isTopArticle:Boolean = false,
//    isMarked:Boolean = false
){
    if(article.isTopArticle){

        Image(painter = article.imageResource, contentDescription = "top article picture",
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
        Column(
            modifier = Modifier.padding(start=16.dp, end = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = article.title, style = MaterialTheme.typography.headlineSmall,
                color=Color.Black.copy(0.8f),
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clickable {
                        navController.navigate(NewsScreen.ArticleScreen.name)
                    })
            Text(text =  article.writer, fontWeight = FontWeight.Bold,modifier=Modifier.padding(top = 4.dp))

            Text(text = article.date+" - ${article.readingTimeSpend} min read",
                    color = Color.Gray,modifier= Modifier
                        .padding(top = 4.dp))

        }
        
    }else{

        Divider (
            modifier = Modifier
                .height(1.dp)
                //.fillMaxWidth()
                .shadow(elevation = 1.dp)
                .padding(50.dp),
            color=Color.LightGray,

        )


        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)
            ,
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = article.imageResource, contentDescription = "article pictures",
                    modifier = Modifier.padding(4.dp))
                Column(modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(start = 16.dp)) {
                    if (historyMark != null) {
                        Text(text = historyMark, style = MaterialTheme.typography.bodyMedium)
                    }else{Box{}}
                    Text(text = article.title, style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.clickable {
                        navController.navigate(NewsScreen.ArticleScreen.name)
                    })
                    Text(text = article.writer+" - ${article.readingTimeSpend} min read",
                        color = Color.Gray,modifier= Modifier
                            .padding(top = 2.dp))
                }
            }

            if (icon != null){
                IconButton(onClick = {
                    Log.d("history row icon","history row icon clicked") }) {
                    Icon(imageVector = icon, contentDescription = "icon")
                }
            }else{
                if (article.isMarked){
                    Icon(painterResource(id = R.drawable.bookmark_selected), contentDescription = "book mark selected",
                        modifier = Modifier.size(25.dp),
                        tint = Color(0xFF4F5B92)
                    )
                }else{
                    Icon(painterResource(id = R.drawable.bookmark), contentDescription = "book mark unselected",
                        modifier = Modifier.size(25.dp),
                        tint = Color(0xFF4F5B92)
                    )
                }
            }


//            IconButton(onClick = { isMarked.value = !isMarked.value }) {
//
//            }
        }
        
    }
}

//        Text(text = "Top stories for you",modifier = Modifier
//            .align(Alignment.Start)
//            .padding(start = 16.dp))
//        Image(painter = painterResource(id = R.drawable.post_4), contentDescription = "Home post picture",
//            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))
//        Text(text = "Locale changes and the AndroidViewModel antipattern",
//            style = MaterialTheme.typography.titleLarge,
//            modifier=Modifier.padding(start=16.dp))
//        Text(text =  "James", fontWeight = FontWeight.Bold,modifier= Modifier
//            .align(Alignment.Start)
//            .padding(start = 16.dp))
//        Text(text = "April", color = Color.Gray,modifier= Modifier
//            .align(Alignment.Start)
//            .padding(start = 16.dp))
