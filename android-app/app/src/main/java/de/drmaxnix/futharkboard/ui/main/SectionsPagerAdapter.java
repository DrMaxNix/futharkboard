package de.drmaxnix.futharkboard.ui.main;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import de.drmaxnix.futharkboard.R;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
	private final Context context;
	
	public SectionsPagerAdapter(Context context, FragmentManager fm){
		super(fm);
		this.context = context;
	}
	
	@Override
	public Fragment getItem(int position){
		switch(position){
			case 0:
				return new Start();
		}
		
		return null;
	}
	
	@Nullable
	@Override
	public CharSequence getPageTitle(int position){
		@StringRes
		final int[] TAB_TITLES = new int[]{R.string.tab_name_start};
		
		return context.getResources().getString(TAB_TITLES[position]);
	}
	
	@Override
	public int getCount(){
		return 1;
	}
}