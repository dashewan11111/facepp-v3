# Face++ 人脸关键点SDK 开发集成文档

------
![bg][1]

<br>
## 一、简介
    人脸关键点SDK能离线调用Face++的人脸检测、人脸追踪、关键点、人脸比对的功能。完全离线，无需联网，在本地处理所有数据。
### 关于版本： 0.5.2
| 版本        | 免费 SDK Demo | 在线授权 SDK | 离线授权 SDK |
| :-----      | :-----:       | :-----:      | :-----:      | 
| 授权方式    | 在线          |  在线        |  离线        |
| 获得途径    | 官网下载      | 商务单独打包 | 商务单独打包 |
| 3D POSE            | ![image][success] | ![image][success] | ![image][success] |
| 人脸检测 detect    | ![image][success] | ![image][success] | ![image][success] |
| 人脸关键点 landmark| ![image][success] | ![image][success] | ![image][success] |
| 人脸属性 attribute | ![image][faile]   | ![image][success] | ![image][success] |
| 人脸比对 compare   | ![image][faile]   | ![image][faile]   | ![image][success] |


### 功能列表
* 3D POSE `免费`
  >返回图片或视频流中人脸头部三维向量方向

* 人脸检测和追踪（detect & track）`免费`
  >检测图片或视频流中的人脸并返回人脸框，支持视频流中的人脸追踪。

* 人脸关键点（landmark）`免费`
  >返回图片或视频流中人脸五官与轮廓的关键点位置及对应置信度。
   最多106个点，包括人脸轮廓、眼睛、眉毛、嘴唇以及鼻子。
   支持视频流中关键点的追踪

* 人脸属性（attribute）`付费`
  >返回图片或视频流中人脸的性别、年龄。

* 人脸比对（compare）`付费`
  > 通过detet 接口保存照片到本地设备，然后再通过图片或者视频进行比对。


### 开发组件
下载的SDK包含包含以下文件:

* Android-Demo.zip: 包含一个Android Studio 的demo工程，可以直接用AndroidStudio打开。
* doc 文件夹: 包含SDK 主要类的介绍
* C_SDK: 包含armeabi-v7a & armeabi-v8a 的so文件 和 Native接口头文件(MG_Facepp.h, MG_Common.h)

### 规格参数
| 项目        | Android   | 
| --------    | :-----:   | 
| 系统版本    | 4.0 +     |  
| CPU 平台    | arm armv7 arm64 |  
| 输入图像要求| NV21 BGR  | 

| 包大小      | Android   | 
| --------    | :-----:   | 
| 代码部分    | 32位 **2.0M**; 64位 **3.2M** |  
| 人脸检测 模型 | 4.8M |  
| 人脸关键点track_fast 模型 | 1.8M |  
| 人脸关键点track_robust 模型 | 2.5M |   
| 人脸属性 模型 | 2.3M |  
| 3D POSE 模型 | 145K |  

>性能参数
| 测试设备 |首帧(单位:ms)|追踪Fast(单位:ms)|追踪Robust(单位:ms) | 检出率 | 漏检率|
| --------    | -----:    | -----:    | -----:    | -----:  | -----: | 
|nexus 5x(Android 6.0.1 API:23)| 108.82 | 9.38 | 12.14 | 98.4% | 1.60% |


------
<br>
## 二、Demo 集成说明

>**该内容针对 有Android 开发基础的人。基于Android Studio。讲解如何跑通Android SDK Demo 代码。**
### 集成准备
* 官网申请的试用版（免费版） **API Key** 和 **API Secret**。还没有注册？[参考这里](https://console.faceplusplus.com.cn/documents/5671787)
>**注意：除非你购买了SDK授权（不是账户充值或者Web Api），否则必须使用试用版。**

* 下载官网最新 Demo
>![image](https://images-1256032145.cos.ap-beijing.myqcloud.com/sdk_location_0.png) 
><br>
>![image](https://images-1256032145.cos.ap-beijing.myqcloud.com/sdk_location_1.png)
><br>
>![image](https://images-1256032145.cos.ap-beijing.myqcloud.com/download.png)

<br>
### 开始集成
#### 1. 解压下载的SDK压缩包，解压Android-Demo.zip。
<br>
#### 2. 打开Android Studio，点击 File --> Open , 选择解压好的 `Android-Demo`文件夹，点击open。（或者在Android Studio启动页选择 Open an exiting Android Studio project）如图；
>![image](https://images-1256032145.cos.ap-beijing.myqcloud.com/new_open.png)

<br>
#### 3. 编译完成,打开 Util 类，填写试用版 api_key和secret
>![image](https://images-1256032145.cos.ap-beijing.myqcloud.com/demo-2.png)
<br>
>![image](https://images-1256032145.cos.ap-beijing.myqcloud.com/demo-1.png)

<br>
#### 4. Run 


## 三、代码流程
>SDK本身只提供核心接口，不涉及上层业务（比如打开摄像头，横竖屏切换等等）。每个开发者的实现方式都不尽相同，所以这些业务模块的代码由客户自己封装完成。SDK demo提供了Face++团队的实现方式。代码仅供参考，只保证接口的调用方式正确和功能演示，不保证满足客户的业务需求。这里只介绍核心接口的调用方式和使用流程。

```flow
st=>start: 开始
op=>operation: 授权检测
cn=>condition: 授权成功？
op1=>operation: 跳转到功能选择页面
op2=>operation: 授权成功
op3=>operation: 授权失败
end=>end: 结束

st->op->cn
cn(yes)->op2->op1->end
cn(no)->op3->end
```
>其中 **在线授权流程**如下（离线授权由商务单独打包，不在此讨论）
```flow
st=>start: 开始授权
cn=>condition: 本地授权成功？
cn1=>condition: 联网授权成功？
cn2=>condition: 授权在有效期内？

op=>operation: 获取本地授权信息
op1=>operation: 联网获取授权信息
op2=>operation: 检验过期时间
op3=>operation: 授权通过
op4=>operation: 授权失败
op5=>operation: //占位

end=>end: 结束

st->op->cn
cn(yes)->op2->cn2
cn(no)->op1->cn1
cn2(no)->op1->cn1
cn2(yes)->op5->op3
cn1(yes)->op3->end
cn1(no)->op4->end
```
> 从以上的授权流程可以看出，购买了次数包的客户**不用担心没次运行程序都会消耗一次授权**。
授权是按照设备来的。比如你购买了一年 10,00 次的授权，那么你**在一台设备上使用一年，不卸载重装应用的前提下，仅消耗一次授权**。

-----
<br>
#### 核心代码说明
<br>
#### 一、LoadingActivity ---- 联网授权授权接口（V3）：takeLicenseFromNetwork
>负责联网授权，只有授权成功之后才能调用SDK的接口。否则，SDK的接口会报错无法调用。

<br>
##### 代码调用示例
```java
LicenseManager licenseManager = new LicenseManager(this);
```
```java
licenseManager.takeLicenseFromNetwork(
            Util.CN_LICENSE_URL, 
            uuid, 
            Util.API_KEY, 
            Util.API_SECRET, 
            apiName, 
            "1", 
            new LicenseManager.TakeLicenseCallback() {
					@Override
					public void onSuccess() {
						authState(true,0,"");
					}

					@Override
					public void onFailed(int i, byte[] bytes) {
                        if(TextUtils.isEmpty(Util.API_KEY)||TextUtils.isEmpty(Util.API_SECRET)) {
                            if (!ConUtil.isReadKey(LoadingActivity.this)) {
                                authState(false,1001,"");
                            }else{
                                authState(false,1001,"");
                            }
                        }else{
                            String msg="";
                            if (bytes!=null && bytes.length>0){
                                msg=  new String(bytes);
                            }
                            authState(false,i,msg);
                        }
					}
				});

```
<br>
##### 参数说明：
>1. **url** : 在线授权Api地址。v2的key 用v2的url;v3的key 用v3的url。
2. **uuid**: 手机唯一序号。(有些设备会无法获取，导致授权失败，请客户随机写一个值)
3. **key & secret**: 注意使用试用版本的，不要用正式的，除非购买了SDK授权。
4. **apiName**: 通过 **NativeFaceppAPI.nativeGetApiName()**获取，客户不用操心;
5. **duration**: 字符串形式的授权时间，测试用户只能写`1`; 正式版用户写自己购买的授权时间。
6. **callback**: 授权结果回调。


##### 返回值说明:
>**void** 

-------
<br>
#### 二、**OpenGLActivity ---- SDK接口初始化接口: init（免费功能）**
>所有SDK Api在使用前都必须要调用此接口进行初始化。
**特别需要注意的是：客户开发必须进行 errorCode 的异常处理！！**
**特别需要注意的是：客户开发必须进行 errorCode 的异常处理！！**
**特别需要注意的是：客户开发必须进行 errorCode 的异常处理！！**

<br>
##### 代码调用示例
```java
Facepp facepp = new Facepp();

String errorCode = facepp.init(this, ConUtil.getFileContent(this, R.raw.megviifacepp_0_5_2_model), isOneFaceTrackig ? 1 : 0);

```
<br>
##### 参数说明：
>1. **context** : 上下文。
2. **model**: 模型文件的二进制数组。**提供了工具方法，客户不用担心**。
3. **isOneFaceTrackig**: 是否只检测一张脸? 是: `1`; 否: `0`。


##### 返回值说明:
>**errorCode** : 初始化成功返回 `null`; 失败返回 具体的错误信息;

-----

<br>
#### 三、**OpenGLActivity ---- 人脸检测接口: detect（detect功能免费，其中人脸属性收费）**
>**SDK最核心接口之一：**负责从一张规定格式（`Facepp.IMAGEMODE_BGR` 和 `Facepp.IMAGEMODE_NV21`）的图片/帧数据中 检测出人脸信息。
**需要额外注意的是：接口返回值里，除了 trackId、index、confidence、feature, 其余全部需要使用对应的接口获取真实数据。**

<br>
##### 代码调用示例

```java
Facepp facepp = new Facepp();
```
```java
 @Override
 public void onPreviewFrame(final byte[] imgData, final Camera camera) {
        //检测操作放到主线程，防止贴点延迟
        int width = mICamera.cameraWidth;
        int height = mICamera.cameraHeight;

        long faceDetectTime_action = System.currentTimeMillis();
        final int orientation = sensorUtil.orientation;
        if (orientation == 0)
            rotation = Angle;
        else if (orientation == 1)
            rotation = 0;
        else if (orientation == 2)
            rotation = 180;
        else if (orientation == 3)
            rotation = 360 - Angle;
        // 图片旋转角度
        setConfig(rotation);
        // 检测图片中的人脸
        final Facepp.Face[] faces = facepp.detect(imgData, width, height, Facepp.IMAGEMODE_NV21);
        
        ....省略....
}
```
##### Facepp.Face类
```java
 public static class Face {
        public int trackID;       // 人脸的跟踪标记。
        public int index;         // 人脸数组下标
        public float confidence;  // 人脸置信度，为一个 0 ~ 1 之间的浮点数。
                                  // 超过 0.5 表示这确实是一个人脸。

        public float pitch;    // 一个弧度，表示物体顺时针饶x轴旋转的弧度。
        public float yaw;      // 一个弧度，表示物体顺时针饶y轴旋转的弧度。
        public float roll;     // 一个弧度，表示物体顺时针饶z轴旋转的弧度。
        public float[] leftEyestatus;  // 人左眼状态，每个数值表示概率，总和为 1
        public float[] rightEyestatus; // 人右眼状态，每个数值表示概率，总和为 1
        public float[] moutstatus;     // 嘴部状态
        public float minority;         // 少数民族置信度（对于汉族而言）
        public float blurness;         // 模糊程度，数值越小表示越清晰，0 ~ 1
        public float age;              // 年龄，为浮点数

        //男女概率之和为 1
        public float female;     // 是女性人脸的概率
        public float male;       // 是男性人脸的概率
        public Rect rect;        // 人脸在图像中的位置，以一个矩形框来刻画。
        public PointF[] points;  // 人脸关键点信息。
        public byte[] feature;   // 人脸特征数据，务必保证其内存大小不低于feature_length
    }
```

<br>
##### 参数说明:
>1. **imgData**: 相机预览(视频流)返回的图片`byte[]`;
2. **width**: 图片的宽度。（这里取的是相机宽度）
3. **height**: 图片的高度。（这里取的是相机高度）
4. **imageMode**: 图片的类型：目前只支持`Facepp.IMAGEMODE_BGR` 和 `Facepp.IMAGEMODE_NV21`;

<br>
##### 返回值说明:
>**faces[]**: Face 对象数组。包含检测图片中的所有人脸。每一个人脸都是Face 对象。 

##### Face 关键字段说明:
* confidence：人脸置信度。为一个 0 ~ 1 之间的浮点数。
* rect: 人脸在图像中的位置，以一个矩形框来刻画。
> 需要先调用 `getRect` 才能得到正确的值。
* feature： 表示人脸特征值。
* points： 里面存储关键点信息。关键点跟人脸位置的匹配看下面的图。
>想要获取该值需要执行下 `getLandmarkRaw` 或者 `getLandmark` 接口。参考下文。

* pitch、yaw、roll： 表示的是头部的各种偏斜的弧度值。
>需要先调用一下 `get3DPose`接口。参考下文。
* minority： 少数民族的置信度。
>需要先调用一下 `getMinorityStatus`接口。参考下文。 
* blurness：人脸模糊度。`收费`
> 需要先调用 `getBlurness` 才能得到正确的值。

*  age、male、female：性别。`收费`
> 想要得到年龄、性别信息，需要先调用下 `getAgeGender` 接口。
* leftEyestatus： 左眼镜状态。`收费`
* rightEyestatus： 右眼镜状态。`收费`
>想要获取眼睛状态，需要先调用下 `getEyeStatus` 接口。
leftEyestatus、rightEyestatus 都这两个属性都包含以下字段。
每个字段的值都是一个浮点数，范围 [0,100]，小数点后 3 位有效数字。字段值的总和等于 100。
**按数组下标排列为：**
1. **occlusion：**眼睛被遮挡的置信度。
2. **no_glass_eye_open：**不戴眼镜且睁眼的置信度
3. **normal_glass_eye_close：**佩戴普通眼镜且闭眼的置信度
4. **normal_glass_eye_open：**佩戴普通眼镜且睁眼的置信度
5. **dark_glasses：**佩戴墨镜的置信度
6. **no_glass_eye_close：**不戴眼镜且闭眼的置信度

* moutstatus: 嘴部状态。`收费`
 > 想要获取嘴部状态信需要调用 `getMouthStatus` 接口。
moutstatus包括以下字段。每个字段的值都是一个浮点数，范围 [0,100]，小数点后 3 位有效数字。字段值的总和等于 100。
**按数组下标排列为：**
1. **surgical_mask_or_respirator：**嘴部被医用口罩或呼吸面罩遮挡的置信度
2. **other_occlusion：**嘴部被其他物体遮挡的置信度
3. **close：**嘴部没有遮挡且闭上的置信度
4. **open：**嘴部没有遮挡且张开的置信度

------

<br>
#### 四、**OpenGLActivity ---- 人脸比对接口: compare （付费功能）**

>**SDK最核心接口之二：**负责比较两个人脸（特征信息）是不是同一人。成功则返回 `人脸特征信息`；失败则返回  `null`；

<br>
##### 功能使用流程
```flow
st=>start: 开始
op0=>operation: 首页点击 "人脸比对"
op1=>operation: 预览界面点击 "注册" 按钮
op2=>operation: 等待注册成功
op3=>operation: 选择注册的图片作为底库图片
op4=>operation: 返回预览页面后自动识别
en=>end: 结束

st->op0->op1->op2->op3->op4->en
```

<br>
##### 代码调用示例
```java
FeatureInfo featureInfo = FaceCompareManager.instance().compare(facepp, face.feature);
```
```java
public synchronized FeatureInfo compare(Facepp facepp, final byte[] target) {
        double likeMax = 0;
        FeatureInfo infoBest = null;
        for (FeatureInfo featureInfo : mFeatureData) {
            if (featureInfo.isSelected) {
                // 真正开始调用人脸比对
                double like = facepp.faceCompare(featureInfo.feature, target);
                if (like > BEST_LIKE_VALUE && like > likeMax) {
                    likeMax = like;
                    infoBest = featureInfo;
                }
            }
        }
        return infoBest;
}
```
<br>
##### 参数说明
> 1. **byte[] feature1：** 本地存储的人脸特征信息（detect 接口获得）；
2. **byte[] feature2：** 动态获取的（视频流/图片）人脸特征信息（也是detect 获得）；

##### 返回值说明
> **like：**相似度，`double` 类型。也就是置信度。默认是`73`。这个用户可以自定义；

------


<br>
#### 五、各种辅助接口
> 出于某些因素的考虑，detect 接口返回的数据本身不是真实数据（除了trackId、index、confidence、feature）。因此真实数据需要先调用各自对应的接口。这些接口会将真实数据赋值给原先不正确的返回值。这个时候，detect 的返回值才能使用。
这些接口包括：
1. 获取关键点真实数据  ----  getLandmark / getLandmarkRaw
2. 获取年龄性别  ----  getAgeGender
3. 获取少数民族  ----  getMinorityStatus
4. 获取人脸3D内的弧度值 ----  get3DPose
5. 获取眼睛状态  -----  getEyeStatus
6. 获取嘴巴状态 ----  getMouthStatus
7. 获取模糊度 ----  getBlurness
8. 获取人脸框 ----  getRect

<br>
##### 1. 获取关键点真实数据  ----  getLandmark / getLandmarkRaw
```java
    /**
     * @brief 获取人脸的Landmark会旋转为竖直方向, jni会旋转, 需要原始数据使用getLandmarkRaw
     * <p>
     * 获取指定人脸的Landmark信息，并改变传入的人脸信息
     * @param[in, out] face 人脸信息
     * @param[in] pointNum 需要的人脸关键点点数
     */
    @Deprecated
    public void getLandmark(Face face, int pointNum) {
        if (!isLoadSuccess()) {
            return;
        }
        if (FaceppHandle == 0) {
            return;
        }
        float[] points = NativeFaceppAPI.nativeLandMark(FaceppHandle, face.index, pointNum);
        loadFacePointsInfo(face, points, pointNum, 0);
    }

    /**
     * @brief 获取人脸的Landmark原始数据
     * <p>
     * 获取指定人脸的Landmark信息，并改变传入的人脸信息
     * @param[in, out] face 人脸信息
     * @param[in] pointNum 需要的人脸关键点点数
     */
    public void getLandmarkRaw(Face face, int pointNum) {
        if (!isLoadSuccess()) {
            return;
        }
        float[] points = NativeFaceppAPI.nativeLandMarkRaw(FaceppHandle, face.index, pointNum);
        loadFacePointsInfo(face, points, pointNum, 0);
    }
```
<br>
##### 2. 获取年龄性别  ----  getAgeGender
```java
    /**
     * @return 调用是否成功
     * @brief 获取指定人脸的年龄性别属性信息，并改变传入的人脸信息
     * @param[in, out] face 人脸信息
     * @return boolean 是否成功
     */
    public boolean getAgeGender(Face face) {
        if (!isLoadSuccess()) {
            return false;
        }
        if (FaceppHandle == 0 || abilities == null || !abilities.contains(Ability.AGEGENDER))
            return false;
        float[] points = NativeFaceppAPI.nativeAgeGender(FaceppHandle, face.index);
        loadFaceAgeGenderInfo(face, points);
        return true;
    }
```

<br>
##### 3. 获取少数民族  ----  getMinorityStatus
```java
    /**
     * @return 调用是否成功
     * @brief 获取指定人脸的民族属性信息，并改变传入的人脸信息
     * @param[in, out] face 人脸信息
     */
    public boolean getMinorityStatus(Face face) {
        if (!isLoadSuccess()) {
            return false;
        }
        if (FaceppHandle == 0 || abilities == null || !abilities.contains(Ability.MINORITY))
            return false;
        float[] points = NativeFaceppAPI.nativeMinority(FaceppHandle, face.index);
        loadFaceMinorityInfo(face, points);
        return true;
    }

```

<br>
##### 4. 获取人脸3D内的弧度值 ----  get3DPose
```java
    /**
     * @return 调用是否成功
     * @brief 获取指定人脸的 3DPose 属性信息，并改变传入的人脸信息
     * @param[in, out] face 人脸信息
     */
    public boolean get3DPose(Face face) {
        if (!isLoadSuccess()) {
            return false;
        }
        if (FaceppHandle == 0 || abilities == null || !abilities.contains(Ability.POSE))
            return false;

        float[] points = NativeFaceppAPI.nativePose3D(FaceppHandle, face.index);
        loadFace3DPoseInfo(face, points);

        return true;
    }

```
<br>
##### 5. 获取眼睛状态  -----  getEyeStatus
```java
   /**
     * @return 调用是否成功
     * @brief 获取指定人脸的眼睛属性信息，并改变传入的人脸信息
     * @param[in, out] face 人脸信息
     */
    public boolean getEyeStatus(Face face) {
        if (!isLoadSuccess()) {
            return false;
        }
        if (FaceppHandle == 0 || abilities == null || !abilities.contains(Ability.EYESTATUS))
            return false;
        float[] points = NativeFaceppAPI.nativeEyeStatus(FaceppHandle, face.index);
        loadFaceEyeStatusInfo(face, points);
        return true;
    }
```
<br>
##### 6. 获取嘴巴状态 ----  getMouthStatus
```java
    /**
     * @return 调用是否成功
     * @brief 获取指定人脸的嘴巴属性信息，并改变传入的人脸信息
     * @param[in, out] face 人脸信息
     */
    public boolean getMouthStatus(Face face) {
        if (!isLoadSuccess()) {
            return false;
        }
        if (FaceppHandle == 0 || abilities == null || !abilities.contains(Ability.MOUTHSTATUS))
            return false;
        float[] points = NativeFaceppAPI.nativeMouthStatus(FaceppHandle, face.index);
        loadFaceMouthStatusInfo(face, points);
        return true;
    }

```
<br>
##### 7. 获取模糊度 ----  getBlurness
```java
    /**
     * @return 调用是否成功
     * @brief 获取指定人脸的模糊程度，并改变传入的人脸信息
     * @param[in, out] face 人脸信息
     */
    public boolean getBlurness(Face face) {
        if (!isLoadSuccess()) {
            return false;
        }
        if (FaceppHandle == 0 || abilities == null || !abilities.contains(Ability.BLURNESS))
            return false;
        float[] points = NativeFaceppAPI.nativeBlurness(FaceppHandle, face.index);
        loadFaceBlurnessInfo(face, points);
        return true;
    }

```
<br>
##### 8. 获取人脸框 ----  getRect
```java
    /**
     * @brief 获取指定人脸的年龄性别属性信息，并改变传入的人脸信息
     * @param[in, out] face 人脸信息
     */
    public void getRect(Face face) {
        if (!isLoadSuccess()) {
            return;
        }
        if (FaceppHandle == 0)
            return;
        float[] rectArray = NativeFaceppAPI.nativeRect(FaceppHandle, face.index);

        face.rect.left = (int) rectArray[0];
        face.rect.top = (int) rectArray[1];
        face.rect.right = (int) rectArray[2];
        face.rect.bottom = (int) rectArray[3];
    }
```

<br>
##### 9. 获取SDK配置信息接口: getConfig（免费功能）
>SDK可以设置一些配置信息（比如每次检测间隔时间，最小能识别的人脸大小），并根据这些配置信息来工作。该接口可以获取这些信息。

<br>
###### 代码调用示例
```java
 Facepp.FaceppConfig faceppConfig = facepp.getFaceppConfig();
```

###### FaceppConfig类

```java
public static class FaceppConfig {
        public static final int DETECTION_MODE_NORMAL = 0;
        public static final int DETECTION_MODE_TRACKING = 1;
        public static final int DETECTION_MODE_TRACKING_FAST = 3;
        public static final int DETECTION_MODE_TRACKING_ROBUST = 4;
        public static final int DETECTION_MODE_TRACKING_RECT = 5;
        public static final int MG_FPP_DETECTIONMODE_TRACK_RECT = 6;
        public int minFaceSize;
        public int rotation;
        public int interval;
        public int detectionMode;
        public int roi_left;
        public int roi_top;
        public int roi_right;
        public int roi_bottom;
        public float face_confidence_filter;
        public int one_face_tracking;

        public FaceppConfig() {}
    }
```

<br>
###### 参数说明:
>1. **context** : 上下文。
2. **model**: 模型文件的二进制数组。**提供了工具方法，客户不用担心**。
3. **isOneFaceTrackig**: 是否只检测一张脸? 是: `1`; 否: `0`。

###### 返回值说明:
>**faceppConfig** : `FaceppConfig`对象实例。

-----
<br>
##### 10. 给SDK设置配置信息接口: setConfig（免费功能）
>SDK可以设置一些配置信息（比如每次检测间隔时间，最小能识别的人脸大小），并根据这些配置信息来工作。该接口可以设置配置信息。

<br>
###### 代码调用示例
```java
Facepp.FaceppConfig faceppConfig = facepp.getFaceppConfig();
faceppConfig.interval = detection_interval;
faceppConfig.minFaceSize = min_face_size;
faceppConfig.roi_left = left;
faceppConfig.roi_top = top;
faceppConfig.roi_right = right;
faceppConfig.roi_bottom = bottom; String[] array = getResources().getStringArray(R.array.trackig_mode_array);
if (trackModel.equals(array[0]))
      faceppConfig.detectionMode = Facepp.FaceppConfig.DETECTION_MODE_TRACKING_FAST;
else if (trackModel.equals(array[1]))
      faceppConfig.detectionMode = Facepp.FaceppConfig.DETECTION_MODE_TRACKING_ROBUST;
else if (trackModel.equals(array[2])) {
      faceppConfig.detectionMode = Facepp.FaceppConfig.MG_FPP_DETECTIONMODE_TRACK_RECT;
      isShowFaceRect = true;
}

facepp.setFaceppConfig(faceppConfig);
```
###### FaceppConfig类
```java
public static class FaceppConfig {
        public static final int DETECTION_MODE_NORMAL = 0;
        public static final int DETECTION_MODE_TRACKING = 1;
        public static final int DETECTION_MODE_TRACKING_FAST = 3;
        public static final int DETECTION_MODE_TRACKING_ROBUST = 4;
        public static final int DETECTION_MODE_TRACKING_RECT = 5;
        public static final int MG_FPP_DETECTIONMODE_TRACK_RECT = 6;
        public int minFaceSize;
        public int rotation;
        public int interval;
        public int detectionMode;
        public int roi_left;
        public int roi_top;
        public int roi_right;
        public int roi_bottom;
        public float face_confidence_filter;
        public int one_face_tracking;

        public FaceppConfig() {}
    }
```

<br>
###### 参数说明:
>**FaceppConfig类的所有可赋值的变量;**

###### 返回值说明:
>**void** 

-----
<br>
## 四、[常见问题][2] 以及 联系方式
* [工单系统](https://www.faceplusplus.com.cn/contact-us/)
* iOS技术支持: QQ 2915925191
* Android技术支持: QQ 3081671610
* 售前咨询1: QQ 3520579216
* 售前咨询2: QQ 1738085687

-----
<br>
### 常见开发类问题

#### 问： 如何修改横屏？
> 这个问题本不属于我们解答范围，但是很多客户是拿demo代码直接copy到自己的项目，然后各种问为什么不能横屏。实在不厌其烦，所以说下代码如何修改横屏。

#### 答：按照以下步骤修改代码。
**1. manifest 文件修改activity为横屏。**
```java
android:screenOrientation="landscape"
```
**2. 将 OpenGLActivity 的 onDrawFrame 方法的** 
```java
Matrix.setLookAtM(mVMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1f, 0f);
```
修改为
```java
Matrix.setLookAtM(mVMatrix, 0, 0, 0, -3, 0f, 0f, 0f, -1f, 0f, 0f);
```
**3. 将 CameraMatrix 的变量**
```java
// 直角坐标系
static float squareCoords[] = { -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f };
```
修改为
```java
// 直角坐标系
static float squareCoords[] = { -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f };
```
**4. 修改预览尺寸: 将ICamera getLayoutParam方法修改为**
```java
// 通过屏幕参数、相机预览尺寸计算布局参数
public RelativeLayout.LayoutParams getLayoutParam() {
		
		int layout_width = Screen.mWidth;
		int layout_height = Screen.mHeight;

		RelativeLayout.LayoutParams layout_params = new RelativeLayout.LayoutParams(
				layout_width, layout_height);
		layout_params.addRule(RelativeLayout.CENTER_HORIZONTAL);// 设置照相机水平居中

		return layout_params;
}
```
<br>
#### 
#### 问：如何检测单张图片？不用视频流的帧数据。
#### 答：加 Android技术支持 QQ 3081671610 获取示例代码
> detect 接口接收的是`Facepp.IMAGEMODE_BGR` 和 `Facepp.IMAGEMODE_NV21` 两种格式的图片数据。因此，单张图片需要转换为这两种格式之一。

<br>
#### 
#### 问：返回的关键点怎么对应到人脸上？
#### 答：如下图
> **---------------------------------81个点的对应图----------------------------------**

> ![81个点](https://images-1256032145.cos.ap-beijing.myqcloud.com/81_points_position.jpg)

<br>
> **---------------------------------106个点的对应图---------------------------------**

> ![106个点](https://images-1256032145.cos.ap-beijing.myqcloud.com/106_points_position.jpg)

-----

  [faile]:https://images-1256032145.cos.ap-beijing.myqcloud.com/shibai_small.png
  [success]:https://images-1256032145.cos.ap-beijing.myqcloud.com/sucess_small.png


  [1]: http://bj-mc-prod-asset.oss-cn-beijing.aliyuncs.com/banner/b608d9c7939cc50a1b5bc18f271c0cee.jpg
  [2]: https://console.faceplusplus.com.cn/documents/4888368