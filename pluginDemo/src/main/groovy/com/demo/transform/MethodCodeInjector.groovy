package com.demo.transform

import javassist.CtMethod

interface MethodCodeInjector {
    void inject(CtMethod method)
}

class LifeMethodCodeInjectorImpl implements MethodCodeInjector {

    @Override
    void inject(CtMethod method) {
        String methodName = "\"" + method.name + "\""
        String methodCode = "com.yjj.lifemethodlib.LifeMethodRecorder.getInstance().start(\$0," +
            methodName +
            ");"
        method.insertBefore(methodCode)
        method.insertAfter("com.yjj.lifemethodlib.LifeMethodRecorder.getInstance().end(\$0);")
    }
}

class ClickCodeInjectorImpl implements MethodCodeInjector {
    @Override
    void inject(CtMethod method) {
        method.insertBefore("com.yjj.learnDemox.MyClickManager.getInstance().onBeforeClick(\$1);")
        method.insertAfter("com.yjj.learnDemox.MyClickManager.getInstance().onAfterClick(\$1);")
    }
}