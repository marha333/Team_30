package at.team30.setroute.models

class Interest {
    private var title: String? = null
    private var isSelected = false

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun isSelected(): Boolean {
        return isSelected
    }

    fun setSelected(selected: Boolean) {
        this.isSelected = selected
    }
}