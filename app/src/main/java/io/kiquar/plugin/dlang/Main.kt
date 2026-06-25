package io.kiquar.plugin.dlang

import android.app.Activity
import android.os.Bundle
import androidx.annotation.Keep
import com.rk.activities.main.MainActivity
import com.rk.exec.TerminalCommand
import com.rk.exec.launchTerminal
import com.rk.extension.ExtensionAPI
import com.rk.extension.ExtensionContext
import com.rk.file.FileTypeManager
import com.rk.file.child
import com.rk.file.sandboxHomeDir
import com.rk.utils.getTempDir
import io.github.rosemoe.sora.langs.textmate.registry.FileProviderRegistry
import io.github.rosemoe.sora.langs.textmate.registry.GrammarRegistry
import io.github.rosemoe.sora.langs.textmate.registry.provider.AssetsFileResolver
import java.io.File

@Keep
@Suppress("unused")
class Main(context: ExtensionContext) : ExtensionAPI(context) {
    private var fileResolver: AssetsFileResolver? = null
    private var dLanguage: DLanguage? = null
    private var sdLanguage: SDLanguage? = null

    override fun onExtensionLoaded() {
        val fileProviderRegistry = FileProviderRegistry.getInstance()
        fileResolver = AssetsFileResolver(context.assets)
        fileProviderRegistry.addFileProvider(fileResolver)

        val grammarRegistry = GrammarRegistry.getInstance()
        grammarRegistry.loadGrammars("lang/language.json")

        DLanguage(context.resources).also {
            dLanguage = it
            FileTypeManager.register(it)
        }

        SDLanguage(context.resources).also {
            sdLanguage = it
            FileTypeManager.register(it)
        }
    }

    private fun prepareScript(assetName: String): File {
        val scriptFile = getTempDir().child(assetName)
        context.assets.open(assetName).use { input ->
            scriptFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        scriptFile.setExecutable(true)
        return scriptFile
    }

    private fun runScript(assetName: String, args: Array<String>, title: String) {
        val activity = context.currentActivity!!

        val scriptFile = prepareScript(assetName)
        launchTerminal(
            activity = activity,
            terminalCommand = TerminalCommand(
                exe = scriptFile.absolutePath,
                args = args,
                id = title,
                workingDir = getTempDir().absolutePath,
                terminatePreviousSession = true,
            ),
        )
    }

    override fun onInstalled() {
        runScript("d-cli.sh", arrayOf(), "Installing D")
    }

    override fun onUpdated() {
        dispose()
        runScript("d-cli.sh", arrayOf("--update"), "Updating D")
    }

    override fun onUninstalled() {
        dispose()
        runScript("d-cli.sh", arrayOf("--uninstall"), "Uninstalling D")
    }

    private fun dispose() {
        val fileProviderRegistry = FileProviderRegistry.getInstance()
        fileResolver?.let {
            fileProviderRegistry.removeFileProvider(it)
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
    override fun onActivityDestroyed(activity: Activity) {}
    override fun onActivityPaused(activity: Activity) {}
    override fun onActivityResumed(activity: Activity) {}
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
    override fun onActivityStarted(activity: Activity) {}
    override fun onActivityStopped(activity: Activity) {}
}