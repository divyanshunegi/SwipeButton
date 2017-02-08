package divyanshu.ineractive.custom_view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import static android.R.attr.radius;

public class PolygonalDrwable extends Drawable {

    private int numberOfSides = 3;
    private Path polygon = new Path();
    private Path temporal = new Path();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int stroke;
    private int height;
    private int width;

    public PolygonalDrwable(int color, int stroke) {
        this.stroke = stroke;
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(stroke);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        polygon.setFillType(Path.FillType.EVEN_ODD);
    }

    public PolygonalDrwable(int color, int stroke,int height,int width) {
        this.stroke = stroke;
        this.height = height;
        this.width = width;
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(stroke);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        polygon.setFillType(Path.FillType.EVEN_ODD);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(polygon, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return paint.getAlpha();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        computeHex(bounds);
        invalidateSelf();
    }

     void computeHex(Rect bounds) {

        final int width = this.width==0?bounds.width():this.width;
        final int height = this.height==0?bounds.height():this.height;
        final int size = Math.min(width, height);
        final int centerX = bounds.left + (width / 2);
        final int centerY = bounds.top + (height / 2);

        polygon.reset();
        polygon.addPath(createHexagon(height,width, centerX, centerY));
        //polygon.addPath(createHexagon((int) (size * .8f), centerX, centerY));
    }

    private Path createHexagon(int height,int width, int centerX, int centerY) {
//        final float section = (float) (2.0 * Math.PI / numberOfSides);
//        int radius = size / 2;
        Path polygonPath = temporal;
        polygonPath.reset();
        polygonPath.moveTo(centerX, stroke);
        polygonPath.lineTo((width-stroke),centerY);
        polygonPath.lineTo(centerX,(height-stroke));
//        for (int i = 1; i < numberOfSides; i++) {
//            polygonPath.lineTo((centerX + radius * (float)Math.cos(section * i)),
//                    (centerY + radius * (float)Math.sin(section * i)));
//        }
//        polygonPath.close();
        return polygonPath;
    }
}