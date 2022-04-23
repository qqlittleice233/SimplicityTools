package com.lt2333.simplicitytools.hook.app.securitycenter

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookBefore
import com.lt2333.simplicitytools.util.hasEnable
import com.lt2333.simplicitytools.util.xposed.base.HookRegister

object SkipWaitingTime : HookRegister() {
    override fun init() {
        findMethod("android.widget.TextView") {
            name == "setText" && parameterCount == 4
        }.hookBefore {
            hasEnable("skip_waiting_time") {
                if (it.args.isNotEmpty() && it.args[0]?.toString()?.startsWith("确定(") == true
                ) {
                    it.args[0] = "确定"
                }
            }
        }

        findMethod("android.widget.TextView") {
            name == "setEnabled" && parameterTypes[0] == Boolean::class.java
        }.hookBefore {
            hasEnable("skip_waiting_time") {
                it.args[0] = true
            }
        }
    }
}