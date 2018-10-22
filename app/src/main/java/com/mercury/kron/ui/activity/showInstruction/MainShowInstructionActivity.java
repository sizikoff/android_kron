package com.mercury.kron.ui.activity.showInstruction;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mercury.kron.R;
import com.mercury.kron.ui.activity.LoginActivity;
import com.mercury.kron.ui.activity.MainActivity;

import java.util.Objects;

public class MainShowInstructionActivity extends AppCompatActivity {
    private ViewPager pager;

    private boolean isPartner = true;
    public static final String EXTRA_BOOLEAN = "boolean" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_show_instruction);
        pager= findViewById(R.id.pager);
       pager.setAdapter(new PageAddapterInstr(getSupportFragmentManager()));




    }

    public void showPages(View view) {

        if ( pager.getCurrentItem()==5) {
            Intent intent = new Intent(MainShowInstructionActivity.this, LoginActivity.class);
            intent.putExtra("boolean", isPartner);
            startActivity(intent);
        }else
        {
            pager.setCurrentItem(pager.getCurrentItem()+1);

        }


    }
}
