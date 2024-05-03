#include <jni.h>
#include <string>

static const std::string myPkName = "com.sagrawal.newsapp";

static std::string getData(bool debugMode) {
    std::string app_secret = "Null";
    if (debugMode) {
        app_secret = "ba07a65dfd724ffd8481044578ae0cb8"; // Local APi
    } else {
        app_secret = "ba07a65dfd724ffd8481044578ae0cb8"; // Live API
    }
    return app_secret;
}

static std::string getBaseUrl(bool debugMode) {
    std::string base_url = "Null";
    if (debugMode) {
        base_url = "https://newsapi.org/v2/"; // Local APi
    } else {
        base_url = "https://newsapi.org/v2/"; // Live API
    }
    return base_url;
}

static bool validateContext(JNIEnv *env, jobject context) {
    // Do something with the context, for example, obtaining the package name
    jclass contextClass = env->GetObjectClass(context);
    jmethodID getPackageNameMethod = env->GetMethodID(contextClass, "getPackageName",
                                                      "()Ljava/lang/String;");
    jstring packageName = (jstring) env->CallObjectMethod(context, getPackageNameMethod);

    const char *cPackageName = env->GetStringUTFChars(packageName, nullptr);
    return cPackageName == myPkName;
}


extern "C" jstring
Java_com_sagrawal_nativelib_Provider_getApiKey(
        JNIEnv *env,
        jobject,
        jboolean debugMode,
        jobject context) {
    std::string app_secret = "Null";

    const char isValid = validateContext(env, context);
    if (isValid == false) {
        return env->NewStringUTF(app_secret.c_str());
    }

    app_secret = getData(debugMode);
    return env->NewStringUTF(app_secret.c_str());
}

extern "C" jstring
Java_com_sagrawal_nativelib_Provider_getBaseUrl(
        JNIEnv *env,
        jobject,
        jboolean debugMode,
        jobject context) {
    std::string base_url = "Null";

    const char isValid = validateContext(env, context);
    if (isValid == false) {
        return env->NewStringUTF(base_url.c_str());
    }

    base_url = getBaseUrl(debugMode);
    return env->NewStringUTF(base_url.c_str());
}