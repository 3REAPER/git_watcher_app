package ru.pervukhin.gitwatcher.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.pervukhin.gitwatcher.App
import ru.pervukhin.gitwatcher.domain.GitRepository
import ru.pervukhin.gitwatcher.domain.Repository
import javax.inject.Inject

class SearchViewModel : ViewModel() {
    val liveData: MutableLiveData<RepositoryState> = MutableLiveData()
    @Inject
    lateinit var gitRepository: GitRepository

    init {
        App.appComponent.inject(this)
    }

    fun getRepositoryByName(name: String){
        liveData.value = RepositoryState.Loading
        viewModelScope.launch {
            gitRepository.getRepositoryByOwner(name).let {
                if (it == null){
                    liveData.value = RepositoryState.NoInternet
                }
                else if (it.isEmpty()){
                    liveData.value = RepositoryState.Empty
                }
                else{
                    liveData.value = RepositoryState.Success(it)
                }

            }
        }
    }

    sealed class RepositoryState{
        object Loading: RepositoryState()
        data class Success(val list: List<Repository>): RepositoryState()
        object Empty: RepositoryState()
        object NoInternet: RepositoryState()
    }
}