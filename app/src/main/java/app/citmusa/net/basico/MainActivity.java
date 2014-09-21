package app.citmusa.net.basico;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends Activity{

    private boolean favorite = false;

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_fav:
                Drawable icon = null;
                favorite = !favorite;
                if (favorite){
                    icon = getResources().getDrawable(R.drawable.rating_important);
                }
                else{
                    icon = getResources().getDrawable(R.drawable.rating_not_important);
                }
                item.setIcon(icon);
                return true;

            case R.id.action_share:
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                String message = getResources().getString(R.string.message_share);
                ImageView ivImage = (ImageView) findViewById(R.id.img_main);
                Uri img_share = getLocalBitmapUri(ivImage);
                Log.i("uri", img_share.toString());
                share.putExtra(Intent.EXTRA_TEXT, message);
                share.putExtra(Intent.EXTRA_STREAM,img_share);
                share.setType("image/jpeg");
                // create chooser para forzar menú de aplicaciones con las cuales compartir
                startActivity(Intent.createChooser(share,"Compartir con"));
                return true;
            default:
                return super.onMenuItemSelected(featureId, item);
        }

    }

    private Uri getLocalBitmapUri(ImageView imageView) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + System.currentTimeMillis() + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
