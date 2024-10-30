package lat.pam.utsproject

data class Food(
    val id: Int,
    val name: String,
    val description: String,
    val imageResId: Int,
    val price: Double
)
{
    val formattedPrice: String
        get() = "Rp ${price.toInt()}.000"
}