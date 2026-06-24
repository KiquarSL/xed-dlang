package io.kiquar.plugin.dlang

import android.content.res.Resources
import com.rk.file.FileType
import com.rk.icons.Icon

class DLanguage(resources: Resources) : FileType {
    override val extensions = listOf("d")
    override val textmateScope = "source.d"
    override val name = "dlang"
    override val title = "D"
    override val icon = Icon.ExternalResourceIcon(R.drawable.dlang, resources)
}