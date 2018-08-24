package com.clipservice.eticket.ui.adDetail;


import android.app.Fragment;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.clipservice.eticket.R;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdImgFragment extends Fragment {
    View mView;
    private File resourcePath;
    private File imageCacheFile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_ad_detial_img, container, false);
        setImage();
        // Inflate the layout for this fragment
        return mView;
    }

    private void setImage() {
        Bundle bundle = getArguments();
        String imgUrl = bundle.getString("imgUrl");

        SubsamplingScaleImageView imageView = (SubsamplingScaleImageView)mView.findViewById(R.id.imageView);
        Glide.with(getActivity()).load(imgUrl).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                resourcePath = resource;
                imageView.setImage(ImageSource.uri(resource.getAbsolutePath()),
                        new ImageViewState(1.0f, new PointF(0, 0), 0));
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        imageCacheFile = resourcePath;
        //清除Glide硬盘缓存的图片文件
        if(imageCacheFile != null){
            imageCacheFile.delete();
        }
    }
}
