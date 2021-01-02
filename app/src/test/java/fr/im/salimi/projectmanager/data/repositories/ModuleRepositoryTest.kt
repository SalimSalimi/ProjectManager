package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.entities.Module
import fr.im.salimi.projectmanager.data.repositories.source.FakeModuleDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class ModuleRepositoryTest(private val fakeModuleDataSource: FakeModuleDataSource):BaseRepository<Module>(fakeModuleDataSource) {

    fun initRepository() {
        val module1 = Module(name = "Module1", description = "description1")
        val module2 = Module(name = "Module2", description = "description2")
        val module3 = Module(name = "Module3", description = "description3")

        val list = mutableListOf<Module>()
        list.add(module1)
        list.add(module2)
        list.add(module3)
    }
}