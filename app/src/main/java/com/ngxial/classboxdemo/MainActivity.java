package com.ngxial.classboxdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.ngxial.classboxdemo.common.SharedPrefWrapper;
import com.ngxial.classboxdemo.common.cloud.ClassBoxService;
import com.ngxial.classboxdemo.common.cloud.pojo.BoxGroup;
import com.ngxial.classboxdemo.common.cloud.pojo.BoxGroupInfo;
import com.ngxial.classboxdemo.common.cloud.pojo.Channel;
import com.ngxial.classboxdemo.common.cloud.pojo.ChannelGroup;
import com.ngxial.classboxdemo.common.cloud.pojo.ChannelGroupInfo;
import com.ngxial.classboxdemo.common.cloud.pojo.ChannelInfo;
import com.ngxial.classboxdemo.common.cloud.pojo.LoginInfo;
import com.ngxial.classboxdemo.common.cloud.pojo.Program;
import com.ngxial.classboxdemo.common.cloud.pojo.ProgramInfo;
import com.ngxial.classboxdemo.common.cloud.pojo.User;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity {

    private ClassBoxService mService;
    private SharedPrefWrapper mWrapper;
    private TextView mTextMessage;

    private Subscription mInitSubscription;
    private Subscription mBindChannelSubscription;
    private Subscription munBindChannelSubscription;
    private Subscription mPushGroupProgramSubscription;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = ClassBoxService.getInstance();
        mWrapper = SharedPrefWrapper.getInstance(this);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        init();

        findViewById(R.id.btn_add_channel)
                .setOnClickListener(v -> bindChannel());

        findViewById(R.id.btn_delete_channel)
                .setOnClickListener(v -> unBindChannel());

        findViewById(R.id.btn_push_program)
                .setOnClickListener(v -> pushProgram());
    }

    private void init() {
        mInitSubscription = login("admin", "123456q")
                .flatMap(token -> createBoxGroup(token))
                .flatMap(token -> createChannelGroup(token))
                .flatMap(token -> {
                    if (!mWrapper.getChannelID().equals("")) {
                        return Observable.just(token);
                    } else {
                        return createChannel(token);
                    }
                })
                .flatMap(token -> {
                    if (!mWrapper.getProgramID().equals("")) {
                        return Observable.just(token);
                    } else {
                        return createProgram(token);
                    }
                })
                .flatMap(token -> bindProgram(token))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d("ClassBoxServer", "init completed!");
                        Log.d("ClassBoxServer", "channel id : " + mWrapper.getChannelID());
                        Log.d("ClassBoxServer", "program id : " + mWrapper.getProgramID());
                        Log.d("ClassBoxServer", "channel group id : " + mWrapper.getChanneGrouplID());
                        Log.d("ClassBoxServer", "box group id : " + mWrapper.getBoxGrouplID());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("ClassBoxServer", "init fail!\n" + e);
                    }

                    @Override
                    public void onNext(String token) {

                    }
                });
    }

    private void bindChannel() {
        mBindChannelSubscription =
                mService.bindChannel(mWrapper.getToken(), mWrapper.getChanneGrouplID(), mWrapper.getChannelID())
                        .subscribe();
    }

    private void unBindChannel() {
        munBindChannelSubscription =
                mService.unbindChannel(mWrapper.getToken(), mWrapper.getChanneGrouplID(), mWrapper.getChannelID())
                        .subscribe();
    }

    private void pushProgram() {
        mPushGroupProgramSubscription =
                mService.pushGroupProgram(mWrapper.getBoxGrouplID(), mWrapper.getProgramID())
                        .subscribe();
    }

    private Observable<String> login(String account, String password) {
        return mService.login(account, password)
                .flatMap(login -> {
                    Log.d("login", "" + login);
                    boolean isSuccess = login.getStatus().equals("1");
                    if (isSuccess) {
                        LoginInfo info = login.getContent();
                        User user = info.getUser();
                        String token = user.getAccessToken();
                        String _id = user.get_id();
                        mWrapper.setToken(token);
                        mWrapper.setID(_id);
                        Log.d("ClassBoxServer", "token : " + token);
                        Log.d("ClassBoxServer", "_id : " + _id);
                        return Observable.just(token);
                    } else {
                        return Observable.just("");
                    }
                });
    }

    private Observable<String> createBoxGroup(String token) {
        return mService.createBoxGroup(token, "Common", "Common", "0")
                .flatMap(createBoxGroup -> {
                    Log.d("ClassBoxServer", "" + createBoxGroup);
                    BoxGroupInfo info = createBoxGroup.getContent();
                    BoxGroup boxGroup = info.getBoxGroup();
                    mWrapper.setBoxGroupID(boxGroup.get_id());
                    return Observable.just(token);
                });
    }

    private Observable<String> createChannelGroup(String token) {
        return mService.createChannelGroup(token, "Common", "Common", "0")
                .flatMap(createChannelGroup -> {
                    Log.d("ClassBoxServer", "" + createChannelGroup);
                    ChannelGroupInfo info = createChannelGroup.getContent();
                    ChannelGroup channelGroup = info.getChannelGroup();
                    mWrapper.setChannelGroupID(channelGroup.get_id());
                    return Observable.just(token);
                });
    }

    private Observable<String> createChannel(String token) {
        return mService.createChannel(
                token,
                "https://www.youtube.com/embed/JaZRxsCDHzU?list=PLB53A5B3CCDC14B55",
                "123456q",
                "英文影片",
                "國中英文教學",
                "",
                "google"
        ).flatMap(createChannel -> {
            Log.d("ClassBoxServer", "" + createChannel);
            if (createChannel.getStatus().equals("1")) {
                ChannelInfo info = createChannel.getContent();
                Channel channel = info.getChannel();
                mWrapper.setChannelID(channel.get_id());
                return Observable.just(token);
            } else if (createChannel.getStatus().equals("3")) {
                return getChannel(token, "https://www.youtube.com/embed/JaZRxsCDHzU?list=PLB53A5B3CCDC14B55");
            } else {
                return Observable.error(new Exception());
            }
        });
    }

    private Observable<String> getChannel(String token, String rtsp_url) {
        return mService.getChannel(token, rtsp_url)
                .flatMap(getChannel -> {
                    Log.d("ClassBoxServer", "getChannel : " + getChannel);
                    ChannelInfo info = getChannel.getContent();
                    Channel channel = info.getChannel();
                    mWrapper.setChannelID(channel.get_id());
                    return Observable.just(token);
                });
    }

    private Observable<String> createProgram(String token) {
        return mService.createProgram(
                token,
                "國中英文",
                "關係代名詞的應用",
                "",
                "國中英文教學",
                "https://www.youtube.com/embed/PgooOLhh6Xo?list=PLNYd_7rI788XBZsJNaDkD-h9D7W7_v2ne",
                "",
                "",
                ""
        ).flatMap(createProgram -> {
            Log.d("ClassBoxServer", "" + createProgram);
            if (createProgram.getStatus().equals("1")) {
                ProgramInfo info = createProgram.getContent();
                Program program = info.getProgram();
                mWrapper.setProgramID(program.get_id());
            }
            return Observable.just(token);
        });
    }

    private Observable<String> bindProgram(String token) {
        return mService.bindProgram(
                token,
                mWrapper.getChannelID(),
                mWrapper.getProgramID()
        ).flatMap(common -> {
            Log.d("ClassBoxServer", "" + common);
            return Observable.just(token);
        });
    }

    @Override
    protected void onDestroy() {
        if (mInitSubscription != null) {
            mInitSubscription.unsubscribe();
        }
        if (mBindChannelSubscription != null) {
            mBindChannelSubscription.unsubscribe();
        }
        if (munBindChannelSubscription != null) {
            munBindChannelSubscription.unsubscribe();
        }
        if (mPushGroupProgramSubscription != null) {
            mPushGroupProgramSubscription.unsubscribe();
        }
        super.onDestroy();
    }
}
