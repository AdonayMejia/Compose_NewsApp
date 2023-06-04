package com.example.compose_newsapp.ui.searchview.model

import com.example.compose_newsapp.model.datamodel.Filter
import com.example.compose_newsapp.ui.searchview.model.FiltersNames.SECTION_CULTURE
import com.example.compose_newsapp.ui.searchview.model.FiltersNames.SECTION_POLITICS
import com.example.compose_newsapp.ui.searchview.model.FiltersNames.SECTION_TECHNOLOGY
import com.example.compose_newsapp.ui.searchview.model.FiltersNames.TAG_ENVIRONMENT_RECYCLING
import com.example.compose_newsapp.ui.searchview.model.FiltersNames.TAG_POLITICS_BLOG
import com.example.compose_newsapp.ui.searchview.model.FiltersNames.TYPE_INTERACTIVE
import com.example.compose_newsapp.ui.searchview.model.FiltersNames.TYPE_LIVE_BLOG

fun filtersGenerator(): List<Filter> {
    val sections = listOf(SECTION_POLITICS, SECTION_TECHNOLOGY, SECTION_CULTURE)
    val types = listOf(TYPE_LIVE_BLOG, TYPE_INTERACTIVE)
    val tags = listOf(TAG_ENVIRONMENT_RECYCLING, TAG_POLITICS_BLOG)

    val sectionFilters = sections.map { section -> Filter("Section: $section", section = section) }
    val typeFilters = types.map { type -> Filter("Type: $type", type = type) }
    val tagFilters = tags.map { tag -> Filter("Tag: $tag", tag = tag) }

    return sectionFilters + typeFilters + tagFilters
}

object FiltersNames{
    const val SECTION_POLITICS = "politics"
    const val SECTION_TECHNOLOGY = "technology"
    const val SECTION_CULTURE = "culture"
    const val TYPE_LIVE_BLOG = "liveblog"
    const val  TYPE_INTERACTIVE = "interactive"
    const val TAG_ENVIRONMENT_RECYCLING = "environment/recycling"
    const val TAG_POLITICS_BLOG = "politics/blog"
}
