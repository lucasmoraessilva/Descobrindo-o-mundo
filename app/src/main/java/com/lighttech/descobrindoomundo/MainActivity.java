package com.lighttech.descobrindoomundo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.slb_01).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.slb_02).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.slb_03).setOnLongClickListener(new MyOnLongClickListener());
        findViewById(R.id.slb_04).setOnLongClickListener(new MyOnLongClickListener());

        findViewById(R.id.slt_01).setOnDragListener(new MyOnDragListener());
        findViewById(R.id.slt_02).setOnDragListener(new MyOnDragListener());
        findViewById(R.id.slt_03).setOnDragListener(new MyOnDragListener());
        findViewById(R.id.slt_04).setOnDragListener(new MyOnDragListener());
    }

    class MyOnLongClickListener implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View view) {
            ClipData data = ClipData.newPlainText("simple_text", "text");
            View.DragShadowBuilder sb = new View.DragShadowBuilder(view);
            view.startDragAndDrop(data, sb, view, 0);
            view.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    class MyOnDragListener implements View.OnDragListener{

        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            int action = dragEvent.getAction();
            switch(action){
                case DragEvent.ACTION_DRAG_STARTED:
                    if(dragEvent.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
                        return true;
                    }
                return false;
                case DragEvent.ACTION_DRAG_ENTERED:
                    view.setBackgroundColor(Color.YELLOW);
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    view.setBackgroundColor(Color.BLUE);
                    break;
                case DragEvent.ACTION_DROP:
                    View v = (View) dragEvent.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(v);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(v);
                    v.setVisibility(view.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    view.setBackgroundColor(Color.BLUE);
                    break;
            }
            return true;
        }
    }

}