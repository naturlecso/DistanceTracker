package hu.naturlecso.distancetracker.common.binding

interface Command {
    fun execute()
    fun canExecute(): Boolean
}
