package sa.factory.app1290099.model

import kotlinx.serialization.Serializable

@Serializable
data class HvacServicePackage(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class HvacMaintenanceContract(
    val requestId: String,
    val items: List<HvacServicePackage>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: HvacMaintenanceContractStatus = HvacMaintenanceContractStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class HvacMaintenanceContractStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
