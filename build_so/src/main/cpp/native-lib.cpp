#include <jni.h>
#include <string>
#include<iostream>
#include "xskj.h"
#include "pwmcontroller.h"

std::shared_ptr<PwmController> pwmController;
//测试一
extern "C" JNIEXPORT jstring JNICALL
Java_com_soonsolid_so_JniTool_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from Jni";
    return env->NewStringUTF(hello.c_str());
}

//测试二
extern "C" JNIEXPORT jstring JNICALL
Java_com_soonsolid_so_JniTool_helloFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = helloWorld();
    return env->NewStringUTF(hello.c_str());
}

//测试三 java -C  String
extern "C" JNIEXPORT jstring JNICALL
Java_com_soonsolid_so_JniTool_sendToCString(
        JNIEnv *env,
        jobject /* this */,
        jstring data) {
    const char *str;
    str = env->GetStringUTFChars(data, 0);
    if (str == NULL) {
        return NULL;
    }
    std::cout << str << std::endl;
    env->ReleaseStringUTFChars(data, str);
    jstring rtstr = env->NewStringUTF(str);
    return rtstr;
}

//测试三 java -C  Int
extern "C" JNIEXPORT jint JNICALL
Java_com_soonsolid_so_JniTool_sendToCInt(
        JNIEnv *env,
        jobject /* this */,
        jint a,
        jint b) {
    return a + b;
}

//测试三 java -C  Array
extern "C" JNIEXPORT jint  JNICALL
Java_com_soonsolid_so_JniTool_sendToCIntArray(
        JNIEnv *env,
        jobject /* this */,
        jintArray data) {
    int tatol = 0;
    int length = env->GetArrayLength(data);
    int *array = env->GetIntArrayElements(data, 0);
    int i = 0;
    for (; i < length; i++) {
        // array[i] = array[i] * array[i];
        tatol = tatol+array[i];
    }
    return tatol;
}

//测试四 调用头文件
extern "C" JNIEXPORT void  JNICALL
Java_com_soonsolid_so_JniTool_sendNetWork(
        JNIEnv *env,
        jobject /* this */) {
    pwmController->PUL1_OFF();

}
//测试四 调用头文件
extern "C" JNIEXPORT jint JNICALL
Java_com_soonsolid_so_JniTool_sendResultInt(
        JNIEnv *env,
        jobject /* this */,
        jint data) {
    return pwmController->getLiquidSensor(data);
}

//A桶电机1开启
extern "C" JNIEXPORT jint JNICALL
Java_com_soonsolid_so_JniTool_PUL1On(
        JNIEnv *env,
        jobject /* this */) {
    return pwmController->PUL1_On();
}
//A桶电机1关闭
extern "C" JNIEXPORT void JNICALL
Java_com_soonsolid_so_JniTool_PUL1OFF(
        JNIEnv *env,
        jobject /* this */) {
    pwmController->PUL1_OFF();
}
//A桶电机2开启
extern "C" JNIEXPORT void JNICALL
Java_com_soonsolid_so_JniTool_PUL2On(
        JNIEnv *env,
        jobject /* this */) {
    pwmController->PUL2_On();
}
//A桶电机2关闭
extern "C" JNIEXPORT void JNICALL
Java_com_soonsolid_so_JniTool_PUL2OFF(
        JNIEnv *env,
        jobject /* this */) {
    pwmController->PUL2_OFF();
}
//B桶电机1开启
extern "C" JNIEXPORT void JNICALL
Java_com_soonsolid_so_JniTool_PUL3On(
        JNIEnv *env,
        jobject /* this */) {
    pwmController->PUL3_On();
}
//B桶电机1关闭
extern "C" JNIEXPORT void JNICALL
Java_com_soonsolid_so_JniTool_PUL3OFF(
        JNIEnv *env,
        jobject /* this */) {
    pwmController->PUL3_OFF();
}
//B桶电机2打开
extern "C" JNIEXPORT void JNICALL
Java_com_soonsolid_so_JniTool_PUL4On(
        JNIEnv *env,
        jobject /* this */) {
    pwmController->PUL4_On();
}
//B桶电机2关闭
extern "C" JNIEXPORT void JNICALL
Java_com_soonsolid_so_JniTool_PUL4OFF(
        JNIEnv *env,
        jobject /* this */) {
    pwmController->PUL4_OFF();
}
//下风扇风扇开启 单位万
extern "C" JNIEXPORT void JNICALL
Java_com_soonsolid_so_JniTool_PWM1On(
        JNIEnv *env,
        jobject /* this */,
        jint data) {
    pwmController->PWM1_On(data);
}
//下风扇风扇关闭 单位万
extern "C" JNIEXPORT void JNICALL
Java_com_soonsolid_so_JniTool_PWM1OFF(
        JNIEnv *env,
        jobject /* this */) {
    pwmController->PWM1_OFF();
}
//上风扇风扇开启 单位万
extern "C" JNIEXPORT void JNICALL
Java_com_soonsolid_so_JniTool_PWM2On(
        JNIEnv *env,
        jobject /* this */,
        jint data) {
    pwmController->PWM2_On(data);
}
//上风扇风扇关闭 单位万
extern "C" JNIEXPORT void JNICALL
Java_com_soonsolid_so_JniTool_PWM2OFF(
        JNIEnv *env,
        jobject /* this */) {
    pwmController->PWM2_OFF();
}

//获取液体传感器数值   0 - 3 id
extern "C" JNIEXPORT jint JNICALL
Java_com_soonsolid_so_JniTool_getLiquidSensor(
        JNIEnv *env,
        jobject /* this */,
        jint data) {
    return pwmController->getLiquidSensor(data);
}

//获取门阀传感器数值  0 - 1
extern "C" JNIEXPORT jint JNICALL
Java_com_soonsolid_so_JniTool_getDoorSendor(
        JNIEnv *env,
        jobject /* this */,
        jint data) {
    return pwmController->getDoorSendor(data);
}