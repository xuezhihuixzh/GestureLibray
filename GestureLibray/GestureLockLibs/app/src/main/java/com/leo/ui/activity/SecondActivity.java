package com.leo.ui.activity;

import android.widget.TextView;

import com.andexert.library.RippleView;
import com.leo.Contants;
import com.leo.R;
import com.leo.gesturelibrary.enums.LockMode;
import com.leo.gesturelibrary.view.CustomLockView;
import com.leo.ui.base.BaseActivity;
import com.leo.util.PasswordUtil;

import butterknife.Bind;

import static com.leo.gesturelibrary.enums.LockMode.CLEAR_PASSWORD;
import static com.leo.gesturelibrary.enums.LockMode.SETTING_PASSWORD;

public class SecondActivity extends BaseActivity implements RippleView.OnRippleCompleteListener {
    @Bind(R.id.rv_back)
    RippleView rvBack;
    @Bind(R.id.tv_text)
    TextView tvText;
    @Bind(R.id.lv_lock)
    CustomLockView lvLock;
    @Bind(R.id.tv_hint)
    TextView tvHint;


    @Override
    public void beforeInitView() {
        setContentView(R.layout.activity_second);
    }

    /**
     * 初始化View
     */
    @Override
    public void initView() {
        //显示绘制方向
        lvLock.setShow(true);
        //允许最大输入次数
        lvLock.setErrorNumber(3);
        //密码最少位数
        lvLock.setPasswordMinLength(4);
        //编辑密码或设置密码时，是否将密码保存到本地，配合setSaveLockKey使用
        lvLock.setSavePin(true);
        //保存密码Key
        lvLock.setSaveLockKey(Contants.PASS_KEY);
    }

    /**
     * 设置监听回调
     */
    @Override
    public void initListener() {
        lvLock.setOnCompleteListener(onCompleteListener);
        rvBack.setOnRippleCompleteListener(this);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        //设置模式
        LockMode lockMode = (LockMode) getIntent().getSerializableExtra(Contants.INTENT_SECONDACTIVITY_KEY);
        setLockMode(lockMode);
    }


    /**
     * 密码输入模式
     */
    private void setLockMode(LockMode mode, String password, String msg) {
        lvLock.setMode(mode);
        lvLock.setErrorNumber(3);
        lvLock.setClearPasssword(false);
        if (mode != SETTING_PASSWORD) {
            tvHint.setText("请输入已经设置过的密码");
            lvLock.setOldPassword(password);
        } else {
            tvHint.setText("请输入要设置的密码");
        }
        tvText.setText(msg);
    }


    /**
     * 密码输入监听
     */
    CustomLockView.OnCompleteListener onCompleteListener = new CustomLockView.OnCompleteListener() {
        @Override
        public void onComplete(String password, int[] indexs) {
            tvHint.setText(getPassWordHint());
            finish();
        }

        @Override
        public void onError(String errorTimes) {
            tvHint.setText("密码错误，还可以输入" + errorTimes + "次");
        }

        @Override
        public void onPasswordIsShort(int passwordMinLength) {
            tvHint.setText("密码不能少于" + passwordMinLength + "个点");
        }

        @Override
        public void onAginInputPassword(LockMode mode, String password, int[] indexs) {
            tvHint.setText("请再次输入密码");
        }


        @Override
        public void onInputNewPassword() {
            tvHint.setText("请输入新密码");
        }

        @Override
        public void onEnteredPasswordsDiffer() {
            tvHint.setText("两次输入的密码不一致");
        }

        @Override
        public void onErrorNumberMany() {
            tvHint.setText("密码错误次数超过限制，不能再输入");
        }

    };


    /**
     * 密码相关操作完成回调提示
     */
    private String getPassWordHint() {
        String str = null;
        switch (lvLock.getMode()) {
            case SETTING_PASSWORD:
                str = "密码设置成功";
                break;
            case EDIT_PASSWORD:
                str = "密码修改成功";
                break;
            case VERIFY_PASSWORD:
                str = "密码正确";
                break;
            case CLEAR_PASSWORD:
                str = "密码已经清除";
                break;
        }
        return str;
    }

    /**
     * 设置解锁模式
     */
    private void setLockMode(LockMode mode) {
        String str = "";
        switch (mode) {
            case CLEAR_PASSWORD:
                str = "清除密码";
                setLockMode(CLEAR_PASSWORD, PasswordUtil.getPin(this), str);
                break;
            case EDIT_PASSWORD:
                str = "修改密码";
                setLockMode(LockMode.EDIT_PASSWORD, PasswordUtil.getPin(this), str);
                break;
            case SETTING_PASSWORD:
                str = "设置密码";
                setLockMode(SETTING_PASSWORD, null, str);
                break;
            case VERIFY_PASSWORD:
                str = "验证密码";
                setLockMode(LockMode.VERIFY_PASSWORD, PasswordUtil.getPin(this), str);
                break;
        }
        tvText.setText(str);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onComplete(RippleView rippleView) {
        onBackPressed();
    }

}
