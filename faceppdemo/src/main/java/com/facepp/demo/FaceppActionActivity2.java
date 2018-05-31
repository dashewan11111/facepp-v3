package com.facepp.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facepp.demo.adapter.FeaturesAdapter;
import com.facepp.demo.bean.FaceActionInfo;
import com.facepp.demo.bean.FaceActionItem;
import com.facepp.demo.bean.FaceActionList;
import com.facepp.demo.util.ConUtil;
import com.facepp.demo.view.MyDividerItemDecoration;

import java.util.HashMap;

/**
 * @author by licheng on 2018/5/29.
 */
public class FaceppActionActivity2 extends Activity implements FeaturesAdapter.OnItemClickListener, View.OnClickListener {

    private RecyclerView recyclerView;

    private FeaturesAdapter adapter;

    private FaceActionInfo faceActionInfo = new FaceActionInfo();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facepp_action2);

        Button enterBtn = findViewById(R.id.landmark_enterBtn);
        enterBtn.setOnClickListener(this);

        recyclerView = findViewById(R.id.landmark_features);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        FaceActionList.init();
        adapter = new FeaturesAdapter(this, FaceActionList.actionList);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        MyDividerItemDecoration itemDecoration = new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(itemDecoration);

        initDefaultConfig();
    }

    private void initDefaultConfig() {
        faceActionInfo.faceSize = 40;
        faceActionInfo.interval = 30;
        HashMap<String, Integer> map = new HashMap<>();
        map.put("width", 640);
        map.put("height", 480);
        faceActionInfo.resolutionMap = map;
        faceActionInfo.trackModel = "Fast";
    }

    @Override
    public void onClick(View v) {
        startActivityForResult(new Intent(this, OpenglActivity.class).putExtra("FaceAction", faceActionInfo), 101);
    }

    public static final int EXTERNAL_STORAGE_REQ_CAMERA_CODE = 10;

    //oppo vivo 第二次权限进入适配
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101) {
            String error = data.getStringExtra("errorcode");
            ConUtil.showToast(this, "sdk init error, code: " + error);
        }
        // getCameraSizeList();
    }

    @Override
    public void onItemClick(int position) {
        FaceActionItem item = adapter.getItem(position);
        switch (item.getType()) {
            case FaceActionItem.ITEM_TYPE_ENABLE:
            case FaceActionItem.ITEM_TYPE_SWITCH:
            case FaceActionItem.ITEM_TYPE_SWITCH_2:
                if (!TextUtils.isEmpty(item.getErrorMessage())) {
                    Toast.makeText(this, item.getErrorMessage(), Toast.LENGTH_LONG).show();
                } else {
                    item.setEnable(!item.isEnable());
                    adapter.setItem(position, item);
                }
                switch (position) {
                    case 0: // 录像
                        faceActionInfo.isStartRecorder = item.isEnable();
                        break;
                    case 1: // 3D模型
                        faceActionInfo.is3DPose = item.isEnable();
                        break;
                    case 2: // 调试信息
                        faceActionInfo.isdebug = item.isEnable();
                        break;
                    case 3: // 区域选择
                        faceActionInfo.isROIDetect = item.isEnable();
                        break;
                    case 4: // 是否是 106个点
                        faceActionInfo.is106Points = !item.isEnable();
                        break;
                    case 5: // 是否是 后置摄像头
                        faceActionInfo.isBackCamera = !item.isEnable();
                        break;
                    case 9: // 人脸属性
                        faceActionInfo.isFaceProperty = item.isEnable();
                        break;
                    case 12: // 人脸比对
                        faceActionInfo.isFaceCompare = item.isEnable();
                        break;
                    default:
                        break;
                }
                break;

            case FaceActionItem.ITEM_TYPE_INPUT:
                showInputDialog(position, item);
                switch (position) {
                    case 6: // 最小人脸
                        faceActionInfo.faceSize = item.getCurrentValue();
                        break;
                    case 8: // 检测间隔
                        faceActionInfo.interval = item.getCurrentValue();
                        break;
                    default:
                        break;
                }
                break;
            case FaceActionItem.ITEM_TYPE_SELECTION:
                showSingleChoice(position, item);
                break;
            default:

                break;
        }
    }

    public void showSingleChoice(final int position, final FaceActionItem item) {
        final AlertDialog dialog = new AlertDialog.Builder(this).setTitle(item.getBottomTitle()[0])
                .setSingleChoiceItems(item.getSelections(), item.getSelectedId(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        item.setSelectedId(which);
                        adapter.setItem(position, item);
                        dialog.dismiss();
                        switch (position) {
                            case 7: // 相机分辨率
                                HashMap<String, Integer> map = new HashMap<>();
                                String value = item.getSelections()[item.getSelectedId()];
                                String[] widthAndHeight = value.split("\\*");
                                map.put("width", Integer.parseInt(widthAndHeight[0]));
                                map.put("height", Integer.parseInt(widthAndHeight[1]));
                                faceActionInfo.resolutionMap = map;
                                break;
                            case 11: // 追踪模式
                                faceActionInfo.trackModel = item.getSelections()[item.getSelectedId()];
                                break;
                            default:
                                break;
                        }
                    }
                }).create();

        dialog.show();
    }

    public void showInputDialog(final int position, final FaceActionItem item) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(item.getBottomTitle()[0]); // 设置对话框标题
        builder.setIcon(android.R.drawable.btn_star); // 设置对话框标题前的图标
        ViewGroup.LayoutParams tvLp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        final EditText edit = new EditText(this);
        edit.setLayoutParams(tvLp);
        edit.setText(String.valueOf(item.getMinValue()));
        edit.setSelection(String.valueOf(item.getMinValue()).length());

        final InputMethodManager imm = (InputMethodManager) edit.getContext()
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_NOT_ALWAYS);

        builder.setView(edit);
        builder.setPositiveButton(getResources().getString(R.string.complete), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = edit.getText().toString();
                if (isNum(str)) {
                    ConUtil.showToast(FaceppActionActivity2.this, getResources().getString(R.string.number) + "！");
                    return;
                } else {
                    try {
                        item.setCurrentValue(Integer.parseInt(str));
                        adapter.setItem(position, item);
                        dialog.dismiss();
                        switch (position) {
                            case 6: // 最小人脸
                                faceActionInfo.faceSize = item.getCurrentValue();
                                break;
                            case 8: // 检测间隔
                                faceActionInfo.interval = item.getCurrentValue();
                                break;
                            default:
                                break;
                        }
                    } catch (Exception e) {
                        ConUtil.showToast(FaceppActionActivity2.this, getResources().getString(R.string.number) + "！");
                    }
                }
                // 取消重命名时候隐藏软键盘
                imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 取消重命名时候隐藏软键盘
                imm.hideSoftInputFromWindow(edit.getWindowToken(), 0);
            }
        });

        builder.setCancelable(true); // 设置按钮是否可以按返回键取消,false则不可以取消
        AlertDialog dialog = builder.create(); // 创建对话框
        dialog.setCanceledOnTouchOutside(true); // 设置弹出框失去焦点是否隐藏,即点击屏蔽其它地方是否隐藏
        dialog.show();
    }

    public boolean isNum(String str) {
        String reg = "[a-zA-Z]+";
        return str.matches(reg);
    }
}
