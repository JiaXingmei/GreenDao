package com.example.day01_fresco;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_border)
    Button btnBorder;
    @BindView(R.id.btn_circle)
    Button btnCircle;
    @BindView(R.id.btn_bili)
    Button btnBili;
    @BindView(R.id.btn_jianjin)
    Button btnJianjin;
    @BindView(R.id.btn_cipanhuancun)
    Button btnCipanhuancun;
    @BindView(R.id.btn_GIF)
    Button btnGIF;
    @BindView(R.id.btn_listener)
    Button btnListener;
    @BindView(R.id.btn_OKHTTP)
    Button btnOKHTTP;
    @BindView(R.id.icon_rounde)
    SimpleDraweeView iconRounde;
    private SimpleDraweeView icon_rounde;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        uri = Uri.parse("http://img4.imgtn.bdimg.com/it/u=2584422743,1020351689&fm=26&gp=0.jpg");
        icon_rounde = findViewById(R.id.icon_rounde);
        icon_rounde.setImageURI(uri);
    }

    @OnClick({R.id.btn_border, R.id.btn_circle, R.id.btn_bili, R.id.btn_jianjin, R.id.btn_cipanhuancun, R.id.btn_GIF, R.id.btn_listener, R.id.btn_OKHTTP, R.id.icon_rounde})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_border:
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(15f);
                icon_rounde.getHierarchy().setRoundingParams(roundingParams);
                break;
            case R.id.btn_circle:
                RoundingParams roundingParams1 = RoundingParams.fromCornersRadius(2f);
                roundingParams1.setRoundAsCircle(true);
                icon_rounde.getHierarchy().setRoundingParams(roundingParams1);
                break;
            case R.id.btn_bili:
                icon_rounde.setAspectRatio(1.2f);
                break;
            case R.id.btn_jianjin:
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setProgressiveRenderingEnabled(true)
                        .build();
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(icon_rounde.getController())
                        .build();
                icon_rounde.setController(controller);
                break;
            case R.id.btn_cipanhuancun:
                DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                        .setBaseDirectoryName("img_icon")
                        .setBaseDirectoryPath(Environment.getDataDirectory())
                        .build();
                ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                        .setMainDiskCacheConfig(diskCacheConfig)
                        .build();
                Fresco.initialize(this,config);
                break;
            case R.id.btn_GIF:
                AbstractDraweeController build = Fresco.newDraweeControllerBuilder()
                        .setAutoPlayAnimations(true)
                        .setUri(Uri.parse("http://img.soogif.com/wKPS4WyAImkZ692DUvTsodBXfuKJs6er.gif"))
                        .build();
                icon_rounde.setController(build);
                break;
            case R.id.btn_listener:
                break;
            case R.id.btn_OKHTTP:
                break;
            case R.id.icon_rounde:
                break;
        }
    }
}
