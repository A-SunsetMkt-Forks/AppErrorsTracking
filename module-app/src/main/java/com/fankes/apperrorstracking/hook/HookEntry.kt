/*
 * AppErrorsTracking - Added more features to app's crash dialog, fixed custom rom deleted dialog, the best experience to Android developer.
 * Copyright (C) 2017 Fankes Studio(qzmmcn@163.com)
 * https://github.com/KitsunePie/AppErrorsTracking
 *
 * This software is non-free but opensource software: you can redistribute it
 * and/or modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either
 * version 3 of the License, or any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * and eula along with this software.  If not, see
 * <https://www.gnu.org/licenses/>
 *
 * This file is created by fankes on 2022/5/7.
 */
package com.fankes.apperrorstracking.hook

import com.fankes.apperrorstracking.data.ConfigData
import com.fankes.apperrorstracking.generated.locale.ModuleAppLocale
import com.fankes.apperrorstracking.hook.entity.FrameworkHooker
import com.fankes.apperrorstracking.locale.locale
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit

@InjectYukiHookWithXposed(entryClassName = "AppErrorsTracking", isUsingResourcesHook = false)
object HookEntry : IYukiHookXposedInit {

    override fun onInit() = configs {
        debugLog {
            tag = "AppErrorsTracking"
            isRecord = true
        }
        isDebug = false
    }

    override fun onHook() = encase {
        loadSystem {
            locale = ModuleAppLocale.attach { moduleAppResources }
            ConfigData.init(this)
            loadHooker(FrameworkHooker)
        }
    }
}