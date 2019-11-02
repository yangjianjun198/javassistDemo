package com.demo.transform

import javassist.CtMethod

interface MethodChecker {
    boolean check(CtMethod method)
}

class OnCreateMethodChecker implements MethodChecker {

    @Override
    boolean check(CtMethod method) {
        return method.getName().endsWith("onCreate") && method.getParameterTypes().length ==
            1 &&
            method.getParameterTypes()[0].getName().equals("android.os.Bundle")
    }
}

class OnVoidMethodChecker implements MethodChecker {
    private final String methodName

    OnVoidMethodChecker(String methodName) {
        this.methodName = methodName
    }

    @Override
    boolean check(CtMethod method) {
        return method.getName().endsWith(methodName) && method.getParameterTypes().length == 0
    }
}

class ViewClickMethodChecker implements MethodChecker {
    static final String VIEW_CLASS = "android.view.View"

    private boolean checkOnClickMethod(CtMethod method) {
        return method.getName().endsWith("onClick") && method.getParameterTypes().length ==
            1 &&
            method.getParameterTypes()[0].getName().equals(VIEW_CLASS)
    }

    @Override
    boolean check(CtMethod ctMethod) {
        return checkOnClickMethod(ctMethod)
    }
}

