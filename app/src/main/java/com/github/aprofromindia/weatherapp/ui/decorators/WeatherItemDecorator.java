package com.github.aprofromindia.weatherapp.ui.decorators;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class WeatherItemDecorator extends RecyclerView.ItemDecoration {

    private Drawable divider;

    public WeatherItemDecorator(Drawable divider) {
        this.divider = divider;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int divLeft = parent.getPaddingLeft();
        int divRight = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++){
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int divTop = child.getBottom() + params.bottomMargin;
            int divBottom = divTop + divider.getIntrinsicHeight();

            divider.setBounds(divLeft, divTop, divRight, divBottom);
            divider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0) return;

        outRect.top = divider.getIntrinsicHeight();
    }
}
