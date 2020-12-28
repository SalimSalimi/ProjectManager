package fr.im.salimi.projectmanager.data.repositories

import fr.im.salimi.projectmanager.data.daos.ModuleDao
import fr.im.salimi.projectmanager.data.entities.Module

class ModuleRepository(private val dao: ModuleDao) : BaseRepository<Module>(dao)