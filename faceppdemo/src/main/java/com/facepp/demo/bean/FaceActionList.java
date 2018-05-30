package com.facepp.demo.bean;

import com.facepp.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by licheng on 2018/5/30.
 */

public class FaceActionList {

    public static final List<FaceActionItem> actionList = new ArrayList<>();

    public static void init() {
        // 录像功能
        FaceActionItem itemRecord = new FaceActionItem();
        itemRecord.setContentValues(new int[]{R.drawable.record_blue, R.drawable.record_gray});
        itemRecord.setBottomTitle(new int[]{R.string.record, R.string.record});
        itemRecord.setEnable(false);
        itemRecord.setErrorMessage("功能开发中");
        itemRecord.setType(FaceActionItem.ITEM_TYPE_ENABLE);
        actionList.add(itemRecord);

        // 3D 模型
        FaceActionItem item3DPose = new FaceActionItem();
        item3DPose.setContentValues(new int[]{R.drawable.three_d_blue, R.drawable.three_d_gray});
        item3DPose.setBottomTitle(new int[]{R.string.pose_3d, R.string.pose_3d});
        item3DPose.setEnable(false);
        item3DPose.setType(FaceActionItem.ITEM_TYPE_ENABLE);
        actionList.add(item3DPose);

        // 调试信息
        FaceActionItem itemDebug = new FaceActionItem();
        itemDebug.setContentValues(new int[]{R.drawable.debug_blue, R.drawable.debug_gray});
        itemDebug.setBottomTitle(new int[]{R.string.debug, R.string.debug});
        itemDebug.setEnable(false);
        itemDebug.setType(FaceActionItem.ITEM_TYPE_ENABLE);
        actionList.add(itemDebug);

        // 区域选择
        FaceActionItem itemSelectArea = new FaceActionItem();
        itemSelectArea.setContentValues(new int[]{R.drawable.area_blue, R.drawable.area_gray});
        itemSelectArea.setBottomTitle(new int[]{R.string.roi, R.string.roi});
        itemSelectArea.setEnable(false);
        itemSelectArea.setType(FaceActionItem.ITEM_TYPE_ENABLE);
        actionList.add(itemSelectArea);

        // 关键点个数
        FaceActionItem itemLandmark = new FaceActionItem();
        itemLandmark.setContentValues(new int[]{R.drawable.point81, R.drawable.point106});
        itemLandmark.setBottomTitle(new int[]{R.string.landmarks, R.string.landmarks});
        itemLandmark.setEnable(true);
        itemLandmark.setType(FaceActionItem.ITEM_TYPE_SWITCH);
        actionList.add(itemLandmark);

        // 前/后 置摄像头
        FaceActionItem itemCamera = new FaceActionItem();
        itemCamera.setContentValues(new int[]{R.drawable.frontphone, R.drawable.backphone});
        itemCamera.setBottomTitle(new int[]{R.string.front, R.string.back});
        itemCamera.setEnable(true);
        itemCamera.setType(FaceActionItem.ITEM_TYPE_SWITCH);
        actionList.add(itemCamera);

        // 最小人脸
        FaceActionItem itemFaceMin = new FaceActionItem();
        itemFaceMin.setMinValue(40);
        itemFaceMin.setMaxValue(1000);
        itemFaceMin.setCurrentValue(40);
        itemFaceMin.setBottomTitle(new int[]{R.string.min_face, R.string.min_face});
        itemFaceMin.setType(FaceActionItem.ITEM_TYPE_INPUT);
        actionList.add(itemFaceMin);

        // 相机分辨率
        FaceActionItem itemCameraSize = new FaceActionItem();
        itemCameraSize.setSelectedId(2);
        itemCameraSize.setSelections(new String[]{"1920*1080", "1280*720", "640*480"});
        itemCameraSize.setBottomTitle(new int[]{R.string.resolution, R.string.resolution});
        itemCameraSize.setType(FaceActionItem.ITEM_TYPE_SELECTION);
        actionList.add(itemCameraSize);

        // 检测间隔
        FaceActionItem itemDetectInternal = new FaceActionItem();
        itemDetectInternal.setMinValue(30);
        itemDetectInternal.setMaxValue(10000);
        itemDetectInternal.setCurrentValue(30);
        itemDetectInternal.setBottomTitle(new int[]{R.string.interval, R.string.interval});
        itemDetectInternal.setType(FaceActionItem.ITEM_TYPE_INPUT);
        actionList.add(itemDetectInternal);

        // 人脸属性
        FaceActionItem itemFaceAttribute = new FaceActionItem();
        itemFaceAttribute.setContentValues(new int[]{R.drawable.faceproperty_blue, R.drawable.faceproperty_gray});
        itemFaceAttribute.setBottomTitle(new int[]{R.string.attributes, R.string.attributes});
        itemFaceAttribute.setEnable(false);
        itemFaceAttribute.setType(FaceActionItem.ITEM_TYPE_ENABLE);
        actionList.add(itemFaceAttribute);

        // 单脸检测
        FaceActionItem itemOneFace = new FaceActionItem();
        itemOneFace.setContentValues(new int[]{R.string.one_face_trackig_true, R.string.one_face_trackig_false});
        itemOneFace.setBottomTitle(new int[]{R.string.one_face_trackig, R.string.one_face_trackig});
        itemOneFace.setEnable(false);
        itemOneFace.setType(FaceActionItem.ITEM_TYPE_SWITCH_2);
        actionList.add(itemOneFace);

        // 追踪模式
        FaceActionItem itemTracking = new FaceActionItem();
        itemTracking.setSelectedId(0);
        itemTracking.setSelections(new String[]{"Fast", "Robust", "Tracking_Rect"});
        itemTracking.setBottomTitle(new int[]{R.string.trackig_mode, R.string.trackig_mode});
        itemTracking.setType(FaceActionItem.ITEM_TYPE_SELECTION);
        actionList.add(itemTracking);

        // 人脸比对
        FaceActionItem itemFaceCompare = new FaceActionItem();
        itemFaceCompare.setContentValues(new int[]{R.drawable.facecompare_blue, R.drawable.facecompare_gray});
        itemFaceCompare.setBottomTitle(new int[]{R.string.face_compare, R.string.face_compare});
        itemFaceCompare.setEnable(false);
        itemFaceCompare.setType(FaceActionItem.ITEM_TYPE_ENABLE);
        actionList.add(itemFaceCompare);

    }
}
