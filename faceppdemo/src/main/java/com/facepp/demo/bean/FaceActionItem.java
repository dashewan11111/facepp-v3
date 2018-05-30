package com.facepp.demo.bean;

/**
 * @author by licheng on 2018/5/29.
 */

public class FaceActionItem {

    public static final int ITEM_TYPE_ENABLE = 0X01;   // 图文型，只有 enable 和 disable 两种状态
    public static final int ITEM_TYPE_SWITCH = 0X02;   // 切换型，可以在两个值之间切换  (图片 +文字 )
    public static final int ITEM_TYPE_SWITCH_2 = 0X03; // 切换型2，可以在两个值之间切换 （文字 +文字 ）
    public static final int ITEM_TYPE_INPUT = 0X04;    // 输入型
    public static final int ITEM_TYPE_SELECTION = 0X05; // 选择型

    private int[] bottomTitle;

    private int[] contentValues;

    private String[] selections;

    private int minValue;

    private int maxValue;

    private int currentValue;

    private int type;

    private boolean enable;

    private String errorMessage;

    private int selectedId;

    public int[] getBottomTitle() {
        return bottomTitle;
    }

    public void setBottomTitle(int[] bottomTitle) {
        this.bottomTitle = bottomTitle;
    }

    public int[] getContentValues() {
        return contentValues;
    }

    public void setContentValues(int[] contentValues) {
        this.contentValues = contentValues;
    }

    public String[] getSelections() {
        return selections;
    }

    public void setSelections(String[] selections) {
        this.selections = selections;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
}
