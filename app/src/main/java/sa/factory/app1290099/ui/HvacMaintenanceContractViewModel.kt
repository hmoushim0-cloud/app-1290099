package sa.factory.app1290099.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1290099.data.HvacMaintenanceContractRepository
import sa.factory.app1290099.model.HvacMaintenanceContract
import sa.factory.app1290099.model.HvacServicePackage
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HvacMaintenanceContractViewModel(
    private val repository: HvacMaintenanceContractRepository
) : ViewModel() {
    val catalog: StateFlow<List<HvacServicePackage>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<HvacMaintenanceContract?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: HvacMaintenanceContract) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
