data class Sample(
    var value: Int = 0
)

fun Sample.reset() = run { this.value = 0 }