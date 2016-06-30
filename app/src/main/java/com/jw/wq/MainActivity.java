package com.jw.wq;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import com.jw.sight.ui.SwipeRefreshBaseActivity;

public class MainActivity extends SwipeRefreshBaseActivity {

    @Override
    protected int provideContentViewId() {
        return R.layout.swipe_recyview;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      final  FloatingActionButton  actionButton = (FloatingActionButton) findViewById(R.id.main_fab);

        actionButton.setOnClickListener(v -> Snackbar.make(actionButton,"666",Snackbar.LENGTH_LONG).show());
    }
}
