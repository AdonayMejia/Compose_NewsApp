package com.example.compose_newsapp.ui.searchview.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.compose_newsapp.R
import com.example.compose_newsapp.model.datamodel.Filter

@Composable
fun FilterDropDown(
    filter:List<Filter>,
    setSelectedFilter: (Filter) -> Unit,
    selectedFilter:Filter
) {
    val filterMenuExpanded = remember { mutableStateOf(false) }

    Box {
        Button(onClick = { filterMenuExpanded.value = !filterMenuExpanded.value }) {
            Text(text = selectedFilter.filterName.ifEmpty { stringResource(R.string.select_a_filter) })
        }
        DropdownMenu(
            expanded = filterMenuExpanded.value,
            onDismissRequest = { filterMenuExpanded.value = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            filter.forEach { filter ->
                DropdownMenuItem(
                    onClick = {
                        setSelectedFilter(filter)
                        filterMenuExpanded.value = false
                    }, text = {
                    Text(text = filter.filterName)
                })
            }
        }
    }
}