package sa.factory.app1290099.data

import sa.factory.app1290099.model.HvacMaintenanceContract
import sa.factory.app1290099.model.HvacServicePackage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface HvacMaintenanceContractRepository {
    fun getAvailableCatalog(): Flow<List<HvacServicePackage>>
    fun getActiveRequest(): Flow<HvacMaintenanceContract?>
    suspend fun submitRequest(request: HvacMaintenanceContract): Result<String>
}

class InMemoryHvacMaintenanceContractRepository : HvacMaintenanceContractRepository {
    private val _catalog = MutableStateFlow<List<HvacServicePackage>>(listOf(
        HvacServicePackage("1", "صيانة وغسيل مكيف اسبليت شامل", "تنظيف الفلاتر والوحدات الداخلية والخارجية وفحص ضغط غاز الفريون وكفاءة التصريف", 5000L, "verified"),
        HvacServicePackage("2", "فحص وشحن غاز الفريون الأمريكي", "كشف التسريبات بالضغط واعادة شحن الفريون الاصلي لرفع كفاءة التبريد وخفض استهلاك الطاقة", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<HvacMaintenanceContract?>(null)

    override fun getAvailableCatalog(): Flow<List<HvacServicePackage>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<HvacMaintenanceContract?> = _active.asStateFlow()

    override suspend fun submitRequest(request: HvacMaintenanceContract): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
