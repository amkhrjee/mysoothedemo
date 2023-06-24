@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mysoothe
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mysoothe.ui.theme.MySootheTheme
import java.util.Locale
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MySootheTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MySootheApp()                }
            }
        }
    }
}

private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBar(
    icon: ImageVector,
    text: String,
    modifier: Modifier = Modifier
){
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
            )
        },
        placeholder = { Text(text) },
        modifier = modifier,
        colors = TextFieldDefaults
            .textFieldColors(containerColor = MaterialTheme.colorScheme.surface),
    )
}

@Composable
fun AlignYourBodyElement(
    @DrawableRes banner: Int,
    @StringRes title: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image (
            painterResource(id = banner),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.paddingFromBaseline(
                top = 24.dp,
                bottom = 8.dp
            )
        )
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes banner: Int,
    @StringRes title: Int
){
    Surface(
        shape = MaterialTheme.shapes.small,
//        modifier = modifier.height(min = 56.dp).width(min = 192.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(192.dp)
        ) {
            Image(
                painter = painterResource(id = banner),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )

            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun AlignYourBodyRow() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier
    ){
        items(alignYourBodyData) {item ->
            AlignYourBodyElement(banner = item.drawable, title = item.text)
        }
    }
}

@Composable
fun FavoriteCollectionsGrid() {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier.height(120.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(item.drawable, item.text)
        }
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int,
    content: @Composable () -> Unit
){
    Column {
        Text(
            text = stringResource(title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(Modifier
        .verticalScroll(rememberScrollState())
        .padding(vertical = 16.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        InputBar(
            icon = Icons.Rounded.Search,
            text = "Search",
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .heightIn(min = 56.dp)
        )
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_home))
            },
            selected = true,
            onClick = {}
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_profile))
            },
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun MySootheApp() {
    MySootheTheme {
        Scaffold(
            bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeSectionPreview() {
    MySootheTheme {
        MySootheApp()
    }
}