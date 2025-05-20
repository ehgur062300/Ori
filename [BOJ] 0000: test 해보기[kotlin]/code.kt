data class User(
    val id: Long,
    val type: Type,
    val name: String? = "default",
    val address: Address,
    val phones: List<String>,
    val active: Boolean
) {

    data class Address(
        val zipCode: String,
        val basicAddress: String,
        val detailAddress: String
    )

    enum class Type {
        ADMIN,
        USER
    }
}