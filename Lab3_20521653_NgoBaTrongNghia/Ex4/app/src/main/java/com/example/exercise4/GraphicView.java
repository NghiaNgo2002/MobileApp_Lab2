package com.example.exercise4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

public class GraphicView extends View {

    private Bitmap[] frames = new Bitmap[6]; // Assuming you have exactly 6 frames
    private int i = 0;
    private long last_tick = 0;
    private long period = 200; // Change as needed
    private Paint paint = new Paint();
    private MediaPlayer mediaPlayer;

    public GraphicView(Context context) {
        super(context);
        frames[0] = BitmapFactory.decodeResource(getResources(), R.drawable.cat200);
        frames[1] = BitmapFactory.decodeResource(getResources(), R.drawable.cat201);
        frames[2] = BitmapFactory.decodeResource(getResources(), R.drawable.cat202);
        frames[3] = BitmapFactory.decodeResource(getResources(), R.drawable.cat203);
        frames[4] = BitmapFactory.decodeResource(getResources(), R.drawable.cat204);
        frames[5] = BitmapFactory.decodeResource(getResources(), R.drawable.cat205);

        // Initialize MediaPlayer with the mp3 file
        mediaPlayer = MediaPlayer.create(context, R.raw.music);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (i < frames.length) {
            long time = System.currentTimeMillis() - last_tick;
            if (time >= period) {
                last_tick = System.currentTimeMillis();
                i = (i + 1) % frames.length; // Loop the frames
            }
            canvas.scale(0.75f, 0.75f); // Adjust scale as needed
            canvas.drawBitmap(frames[i], 40, 40, paint);
            postInvalidateDelayed(period);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            i = (i + 1) % frames.length; // Loop the frames
            postInvalidate();
            mediaPlayer.start(); // Play music on touch
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
