package com.mercury.kron.ui.activity.showInstruction;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercury.kron.R;
import com.mercury.kron.ui.activity.LoginActivity;

import static com.mercury.kron.R.drawable.instruction_p_1;
import static com.mercury.kron.R.drawable.instruction_p_2;
import static com.mercury.kron.R.drawable.instruction_p_3;
import static com.mercury.kron.R.drawable.instruction_p_4;
import static com.mercury.kron.R.drawable.instruction_p_5;
import static com.mercury.kron.R.drawable.instruction_p_6;


/**
 * A simple {@link Fragment} subclass.
 */
public class PageFragment extends Fragment {

  public static    int pageNumber;
    private static int[] pages;
    static {
        pages = new int[6];

        pages[0]=instruction_p_1;
        pages[1]=instruction_p_2;
        pages[2]=instruction_p_3;
        pages[3]=instruction_p_4;
        pages[4]=instruction_p_5;
        pages[5]=instruction_p_6;

    }
    public static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public PageFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 0;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_page, container, false);
        ImageView imageView = result.findViewById(R.id.display_image);


        if(pageNumber <7) {
            imageView.setBackgroundResource(pages[pageNumber]);
        }


        return result;
    }
}
