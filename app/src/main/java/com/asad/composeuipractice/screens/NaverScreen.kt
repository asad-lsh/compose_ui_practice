package com.asad.composeuipractice.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asad.composeuipractice.ui.theme.ComposeUiPracticeTheme

// 검색창 높이를 기준 단위로 사용
private val SearchBarHeight = 48.dp
private val NaverGreen = Color(0xFF03C75A)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NaverScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Row {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.Menu, contentDescription = "메뉴")
                        }
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.AccountBalanceWallet, contentDescription = "페이")
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Notifications, contentDescription = "알림")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NaverNavItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 12.dp)
        ) {
            // 2. 검색창
            item { SearchBar() }

            // 3. 광고 부분
            item { AdBanner() }

            // 4. 가로 슬라이드 정보 카드
            item { InfoCardRow() }

            // 5. 2xN 세로 슬라이드 뉴스/블로그 카드
            items(NewsItems.chunked(2)) { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowItems.forEach { news ->
                        NewsCard(news = news, modifier = Modifier.weight(1f))
                    }
                    // 홀수 개일 때 마지막 칸 균형 맞춤
                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

// 2. 검색창
@Composable
private fun SearchBar() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(SearchBarHeight),
        shape = RoundedCornerShape(percent = 60),
        color = Color.White,
        shadowElevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // N 로고 (머티리얼에 네이버 N 아이콘이 없어 그린 텍스트로 표현)
            Text(
                text = "N",
                color = NaverGreen,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 22.sp
            )
            Icon(
                imageVector = Icons.Filled.PhotoCamera,
                contentDescription = "카메라 검색",
                tint = NaverGreen
            )
        }
    }
}

// 3. 광고: 왼쪽 이미지 + 오른쪽 텍스트, 높이 검색창의 2배
@Composable
private fun AdBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 20.dp)
            .height(SearchBarHeight * 2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 광고 이미지 플레이스홀더
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.35f)
                .background(Color(0xFFBDBDBD), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("AD", color = Color.White, fontWeight = FontWeight.Bold)
        }
        Column(
            modifier = Modifier
                .weight(0.65f)
                .padding(start = 15.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "이번 주 특가 광고",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "지금 확인하고 혜택을 받아보세요.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// 4. 가로 슬라이드 정보 카드: 날씨/스포츠/주식 등, 높이 검색창의 1.5배
@Composable
private fun InfoCardRow() {
    LazyRow(
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(InfoCards) { info ->
            Surface(
                modifier = Modifier
                    .width(160.dp)
                    .height(SearchBarHeight * 1.5f),
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 1.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(info.emoji, fontSize = 24.sp)
                    Column {
                        Text(
                            text = info.title,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = info.value,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

// 5. 뉴스/블로그 카드: 이미지 + 왼쪽 하단 텍스트, 높이 검색창의 4배
@Composable
private fun NewsCard(news: NewsItem, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(SearchBarHeight * 4)
            .background(news.color, RoundedCornerShape(12.dp))
    ) {
        Text(
            text = news.title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
        )
    }
}

private data class InfoCard(val emoji: String, val title: String, val value: String)

private val InfoCards = listOf(
    InfoCard("☀️", "날씨", "28°C 맑음"),
    InfoCard("⚽", "스포츠", "2 : 1 승"),
    InfoCard("📈", "주식", "+1.24%"),
    InfoCard("💱", "환율", "1,380원")
)

private data class NewsItem(val title: String, val color: Color)

private val NewsItems = listOf(
    NewsItem("오늘의 주요 뉴스", Color(0xFF5C6BC0)),
    NewsItem("인기 블로그 글", Color(0xFF26A69A)),
    NewsItem("연예 소식", Color(0xFFEF5350)),
    NewsItem("IT/과학 트렌드", Color(0xFF7E57C2)),
    NewsItem("경제 브리핑", Color(0xFF66BB6A)),
    NewsItem("여행 추천", Color(0xFFFFA726)),
    NewsItem("맛집 리뷰", Color(0xFFEC407A)),
    NewsItem("스포츠 하이라이트", Color(0xFF42A5F5))
)

private data class NavItem(val label: String, val icon: ImageVector)

private val NaverNavItems = listOf(
    NavItem("홈", Icons.Filled.Home),
    NavItem("쇼핑", Icons.Filled.ShoppingBag),
    NavItem("투데이", Icons.AutoMirrored.Filled.Article),
    NavItem("클립", Icons.Filled.PlayCircle),
    NavItem("마이", Icons.Filled.Person)
)

@Preview(showBackground = true)
@Composable
private fun NaverScreenPreview() {
    ComposeUiPracticeTheme {
        NaverScreen()
    }
}
