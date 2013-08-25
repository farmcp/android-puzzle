/*author: Christopher Farm HUID: 80815298 References: cs76 Intents Lecture*/

package com.appordinance.ImagePuzzle;

import java.lang.reflect.Field;

import net.cs76.projects.nPuzzle80815298.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

	private int[] imageArray;
	private Context imageContext;
	private Bitmap[] bitmapArray;

	ImageAdapter(Context context) {
		imageContext = context;

		// dynamically access the number of images in the draw-able folder that
		// start with puzzle_
		Field[] pictureList = R.drawable.class.getFields();

		int length = pictureList.length;
		int count = 0;
		int index = 0;

		for (int i = 0; i < length; i++) {
			if (pictureList[i].getName().startsWith("puzzle_")) {
				count++;
			}
		}

		imageArray = new int[count];
		bitmapArray = new Bitmap[count];

		try {
			for (int i = 0; i < length; i++) {
				if (pictureList[i].getName().startsWith("puzzle_")) {
					imageArray[index++] = pictureList[i].getInt(null);
				}
			}
		} catch (Exception e) {
		}

	}

	@Override
	public int getCount() {

		return imageArray.length;
	}

	@Override
	public Object getItem(int arg0) {

		return null;
	}

	@Override
	public long getItemId(int position) {

		return imageArray[position];
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ImageView imgView;

		if (convertView == null) {

			// create a new view
			imgView = new ImageView(imageContext);

			imgView.setLayoutParams(new GridView.LayoutParams(300, 200));

		} else {

			// recycle an old view (it might have old thumbs in it!)
			imgView = (ImageView) convertView;

		}

		// see if we've stored a resized thumb in cache
		if (bitmapArray[position] == null) {

			// resize the bitmap
			BitmapFactory.Options option = new BitmapFactory.Options();
			option.inSampleSize = 2;
			Bitmap thumb = BitmapFactory.decodeResource(
					imageContext.getResources(), imageArray[position], option);

			// put the resized image in array
			bitmapArray[position] = thumb;
		}

		// use resized image
		imgView.setImageBitmap(bitmapArray[position]);

		return imgView;
	}

}