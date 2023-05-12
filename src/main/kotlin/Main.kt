fun main(args: Array<String>) {
    val pool: ObjectPool<Sample> = ObjectPool(
        maxSize = 1,
        createInstance = ::Sample,
        resetInstance = Sample::reset
    )

    val firstOwner: Owner<Sample> = pool.acquire()
    pool.release(firstOwner)

    val secondOwner: Owner<Sample> = pool.acquire()
    secondOwner.instance?.value = 5
    firstOwner.instance?.value = 7
    println(secondOwner.instance?.value)
}