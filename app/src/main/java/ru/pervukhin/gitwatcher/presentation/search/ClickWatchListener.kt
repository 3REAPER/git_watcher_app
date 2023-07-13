package ru.pervukhin.gitwatcher.presentation.search

import ru.pervukhin.gitwatcher.domain.Repository

interface ClickWatchListener {
    fun onCLickWatch(repository: Repository)
}