/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_example_OptixRenderer */

#ifndef _Included_com_example_OptixRenderer
#define _Included_com_example_OptixRenderer
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_example_OptixRenderer
 * Method:    startRendering
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_example_OptixRenderer_startRendering
  (JNIEnv *, jobject);

/*
 * Class:     com_example_OptixRenderer
 * Method:    stopRendering
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_example_OptixRenderer_stopRendering
  (JNIEnv *, jobject);

/*
 * Class:     com_example_OptixRenderer
 * Method:    renderFrame
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_example_OptixRenderer_renderFrame
  (JNIEnv *, jobject);

/*
 * Class:     com_example_OptixRenderer
 * Method:    updateData
 * Signature: ([BI)V
 */
JNIEXPORT void JNICALL Java_com_example_OptixRenderer_updateData
  (JNIEnv *, jobject, jbyteArray, jint);

/*
 * Class:     com_example_OptixRenderer
 * Method:    loadChunk
 * Signature: ([BI)V
 */
JNIEXPORT void JNICALL Java_com_example_OptixRenderer_loadChunk
  (JNIEnv *, jobject, jbyteArray, jint);

/*
 * Class:     com_example_OptixRenderer
 * Method:    unloadChunk
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_example_OptixRenderer_unloadChunk
  (JNIEnv *, jobject);

/*
 * Class:     com_example_OptixRenderer
 * Method:    updateChunk
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_example_OptixRenderer_updateChunk
  (JNIEnv *, jobject);

/*
 * Class:     com_example_OptixRenderer
 * Method:    loadEntity
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_example_OptixRenderer_loadEntity
  (JNIEnv *, jobject);

/*
 * Class:     com_example_OptixRenderer
 * Method:    unloadEntity
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_example_OptixRenderer_unloadEntity
  (JNIEnv *, jobject);

/*
 * Class:     com_example_OptixRenderer
 * Method:    updateEntity
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_example_OptixRenderer_updateEntity
  (JNIEnv *, jobject);

/*
 * Class:     com_example_OptixRenderer
 * Method:    isRendering
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_example_OptixRenderer_isRendering
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
