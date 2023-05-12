class ObjectPool<T>(
    private val maxSize: Int,
    private val createInstance: () -> T,
    private val resetInstance: (T) -> Unit
) {
    private val pool: MutableList<T> = List(maxSize) { _ ->
        createInstance()
    }.toMutableList()

    fun acquire(): Owner<T> {
        if (pool.isEmpty()) {
            return Owner(createInstance())
        }
        return Owner(pool.removeLast())
    }

    fun release(owner: Owner<T>) {
        owner.instance?.let {
            if (pool.size < maxSize) {
                resetInstance(it)
                pool.add(it)
            }
            owner.instance = null
        }
    }

}