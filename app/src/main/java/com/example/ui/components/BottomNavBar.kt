package com.example.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.ScreenTab

sealed class BottomNavItem(
    val tab: ScreenTab,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
) {
    object Tasks : BottomNavItem(
        ScreenTab.TASKS,
        "Tasks",
        Icons.Filled.Checklist,
        Icons.Filled.Checklist,
        "nav_tasks"
    )

    object Progress : BottomNavItem(
        ScreenTab.PROGRESS,
        "Progress",
        Icons.Filled.BarChart,
        Icons.Filled.BarChart,
        "nav_progress"
    )
}

@Composable
fun BottomNavBar(
    currentTab: ScreenTab,
    onTabSelected: (ScreenTab) -> Unit
) {
    val items = listOf(
        BottomNavItem.Tasks,
        BottomNavItem.Progress
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
        )
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
            tonalElevation = 3.dp,
            modifier = Modifier.fillMaxWidth(),
            windowInsets = NavigationBarDefaults.windowInsets
        ) {
            items.forEach { item ->
                val isSelected = currentTab == item.tab
                NavigationBarItem(
                    selected = isSelected,
                    onClick = { onTabSelected(item.tab) },
                    alwaysShowLabel = true,
                    icon = {
                        Icon(
                            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title,
                            modifier = Modifier.size(if (isSelected) 28.dp else 26.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = if (isSelected) 12.5.sp else 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.82f),
                        unselectedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.82f)
                    ),
                    modifier = Modifier.testTag(item.testTag)
                )
            }
        }
    }
}
