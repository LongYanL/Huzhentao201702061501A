package com.baway.huzhentao201702061501a;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * 创建日期：2017/2/6 9:25
 * 创建者：huzhentao
 * 创建类的作用：
 */
class GestureView extends View {
    private int widthx;
    private int heightx;

    //创建画笔
    Paint paintX = new Paint();
    Paint paintW = new Paint();
    //  圆的半径
    public  float radiusX;
    public  float radiusW;
    public  int out_circle_color;
    public String text_name;
    public float text_size;



    //重写构造方法
    public GestureView(Context context) {
        super(context);
    }

    public GestureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams(context,attrs);
    }

    public GestureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context,attrs);
    }

    //设置自定义属性
    private void initParams(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GestureView);
        if (typedArray != null) {
            out_circle_color = typedArray.getColor(R.styleable.GestureView_out_circle_color, Color.BLUE);
            radiusX = typedArray.getDimension(R.styleable.GestureView_in_circle_radius, 0);
            radiusW = typedArray.getDimension(R.styleable.GestureView_out_circle_radius, 0);
            text_size = typedArray.getDimension(R.styleable.GestureView_text_size,0);
            text_name=typedArray.getString(R.styleable.GestureView_text_name);

        }
    }



    //画布
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获得屏幕宽高
        widthx =getWidth()/2;
        heightx=getHeight()/2;

        paintX.setStrokeWidth(0);//设置宽度
        paintX.setStyle(Paint.Style.STROKE);//设置空心圆
        paintX.setColor(Color.YELLOW);//设置颜色
        canvas.drawCircle(widthx, heightx, radiusX, paintX);//画圆

        paintW.setStrokeWidth(20);//设置宽度
        paintW.setStyle(Paint.Style.STROKE);//设置空心圆
        paintW.setColor(out_circle_color);//设置颜色
        canvas.drawCircle(widthx, heightx,radiusW, paintW);//画圆

        //设置字体
        paintX.setTextSize(text_size);
        //设置文字
       // String name="圆环";
        //获取文字的宽度
      // float width = paintX.measureText(name, 0, name.length());
       //矩阵
        Rect rect=new Rect();

        paintX.getTextBounds(text_name,0,text_name.length(),rect);//快速获获取
        //获取文字的高度
       // int height = rect.height();
        //文字的位置
        canvas.drawText(text_name,widthx-rect.width()/2,heightx+rect.height()/2,paintX);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                //获得点击位置的坐标
                float x = event.getX();
                float y = event.getY();

                float b = ((x-getWidth()/2)*(x-getWidth()/2)+ (y-getHeight()/2)*(y-getHeight()/2));

                //double numberMath = Math.sqrt(b);

                //判断在圆环外
                if (b>radiusW*radiusW){

                    Toast.makeText(getContext(),"在圆环外",Toast.LENGTH_SHORT).show();

                }

            //判断再小圆内
                if(b<radiusX*radiusX){
                    Toast.makeText(getContext(),"在小圆内",Toast.LENGTH_SHORT).show();
                }
                //判断再小圆外同时在大圆内
                if (b>radiusX*radiusX&&b<radiusW*radiusW){
                    Toast.makeText(getContext(),"在大圆内",Toast.LENGTH_SHORT).show();
                }
                break;

        }

        return true;
    }
}








