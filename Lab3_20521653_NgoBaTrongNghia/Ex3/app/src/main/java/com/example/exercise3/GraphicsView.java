package com.example.exercise3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class GraphicsView extends View {
    Bitmap[] frames = new Bitmap[128];
    int i = 0;
    Paint paint = new Paint();  // Khởi tạo một đối tượng Paint sử dụng chung

    public GraphicsView(Context context) {
        super(context);
        frames[0] = BitmapFactory.decodeResource(getResources(), R.drawable.cat200);
        frames[1] = BitmapFactory.decodeResource(getResources(), R.drawable.cat201);
        frames[2] = BitmapFactory.decodeResource(getResources(), R.drawable.cat202);
        frames[3] = BitmapFactory.decodeResource(getResources(), R.drawable.cat203);
        frames[4] = BitmapFactory.decodeResource(getResources(), R.drawable.cat204);
        frames[5] = BitmapFactory.decodeResource(getResources(), R.drawable.cat205);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (frames[i] != null && !frames[i].isRecycled()) {
            canvas.scale(0.75f, 0.8f);
            canvas.drawBitmap(frames[i], 40, 40, paint);
        } else {
            i = 0;  // Reset chỉ số i về 0 khi phát hiện frame không hợp lệ
        }
        invalidate();  // Gọi lại onDraw liên tục
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        i = (i + 1) % 6;  // Đảm bảo chỉ số không vượt quá 5
        return true;
    }
}
