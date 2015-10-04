package moviestreamer.ggg.com.moviestreamer;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by relfenbein on 4/10/2015.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private Picasso picasso;

    public ImageAdapter(Context c){
        mContext = c;

        picasso = new Picasso.Builder(mContext)
                .listener(new Picasso.Listener() {

                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        Log.e("Picasso Error", "Uri: "+uri.toString());
                        exception.printStackTrace();
                    }
                }).build();
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(400,601));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        Picasso.with(mContext)
                .load("http://image.tmdb.org/t/p/w185/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg")
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher2)
                .noFade().resize(185,278)
                .into(imageView);
       // Log.w("Picasso called", "Let's see if it was called");
        return imageView;
    }

    private Integer[] mThumbIds = {
            R.drawable.ic_launcher, R.drawable.ic_launcher1, R.drawable.ic_launcher2,
            R.drawable.ic_launcher, R.drawable.ic_launcher1, R.drawable.ic_launcher2,
            R.drawable.ic_launcher, R.drawable.ic_launcher1, R.drawable.ic_launcher2,
            R.drawable.ic_launcher, R.drawable.ic_launcher1, R.drawable.ic_launcher2,
            R.drawable.ic_launcher, R.drawable.ic_launcher1, R.drawable.ic_launcher2,
            R.drawable.ic_launcher, R.drawable.ic_launcher1, R.drawable.ic_launcher2,
    };

}
