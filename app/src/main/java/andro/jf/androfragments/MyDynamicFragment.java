package andro.jf.androfragments;

/**
 * Created by jf on 30/09/16.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class MyDynamicFragment extends Fragment {

   private OnMyFragmentEvent mListener;

    public MyDynamicFragment() {
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//         mListener = (OnMyFragmentEvent) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
//        }
//    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Toast.makeText(context, "via onAttach(Context)", Toast.LENGTH_SHORT).show();

        try {
         mListener = (OnMyFragmentEvent) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Toast.makeText(activity, "via onAttach(Activity)", Toast.LENGTH_SHORT).show();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            try {
                mListener = (OnMyFragmentEvent) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_dyn, container,
                false);
        Button b = (Button) rootView.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onMyEvent("My URI");
            }
        });
        return rootView;
    }
}