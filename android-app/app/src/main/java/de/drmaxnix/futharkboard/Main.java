package de.drmaxnix.futharkboard;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import de.drmaxnix.futharkboard.databinding.ActivityMainBinding;
import de.drmaxnix.futharkboard.ui.main.SectionsPagerAdapter;

public class Main extends AppCompatActivity {
	private ActivityMainBinding binding;
	private TabLayout tabs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		
		
		// SET VERSION TEXT //
		try {
			//get version of the app
			PackageInfo pInfo = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
			String version = pInfo.versionName;
			
			//set text
			TextView version_text = (TextView)findViewById(R.id.version);
			version_text.setText(version);
			
		} catch(PackageManager.NameNotFoundException e){
			e.printStackTrace();
		}
		
		
		// INITIALIZE TAB LAYOUT //
		//get new sections-pager-adapter
		SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
		
		//connect with view-pager
		ViewPager viewPager = binding.viewPager;
		viewPager.setAdapter(sectionsPagerAdapter);
		
		//connect with tab-selector
		tabs = binding.tabs;
		tabs.setupWithViewPager(viewPager);
	}
}