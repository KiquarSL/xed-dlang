package io.kiquar.plugin.dlang

import android.content.res.Resources
import com.rk.file.FileType
import com.rk.icons.Icon
import com.rk.file.BuiltinFileType

class SDLanguage(resources: Resources) : FileType {
    override val extensions = listOf("sdl")
    override val textmateScope = "source.sdl"
    override val name = "sdl"
    override val title = "SDL"
    override val icon = BuiltinFileType.PROPERTIES.icon
}