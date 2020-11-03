package Vista;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.ssd_tienda.R;

public class AdaptadorNvme extends PagerAdapter {
    private Context ctx;
    private int [] ImageArrayNvme = new int[]{R.drawable.nvme1,R.drawable.nvme2,R.drawable.nvme3};
    public AdaptadorNvme(Context context){ctx =context;};


    @Override
    public int getCount() {
        return ImageArrayNvme.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(ctx);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(ImageArrayNvme[position]);
        container.addView(imageView,0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
