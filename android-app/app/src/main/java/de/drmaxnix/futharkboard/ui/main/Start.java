package de.drmaxnix.futharkboard.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.drmaxnix.futharkboard.R;

public class Start extends Fragment {
	private View view;
	
	public Start(){
	
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		// GENERATE VIEW //
		View view = inflater.inflate(R.layout.fragment_start, container, false);
		
		//save
		this.view = view;
		
		
		// OPEN-FUTHARKBOARD-WEBSITE-BUTTON ONCLICK CALLBACK //
		Button open_futharkboard_website = (Button)view.findViewById(R.id.open_futharkboard_website);
		open_futharkboard_website.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://futharkboard.drmaxnix.de")));
			}
		});
		
		
		// OPEN-IME-SETTINGS-BUTTON ONCLICK CALLBACK //
		Button open_ime_settings = (Button)view.findViewById(R.id.open_ime_settings);
		open_ime_settings.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				view.getContext().startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
			}
		});
		
		
		// BUGREPORT-MAIL-BUTTON ONCLICK CALLBACK //
		Button bugreport_mail = (Button)view.findViewById(R.id.bugreport_mail);
		bugreport_mail.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				try {
					// GET APP'S VERSION //
					//get version of the app
					PackageInfo pInfo = view.getContext().getPackageManager().getPackageInfo(view.getContext().getPackageName(), 0);
					String version = pInfo.versionName;
					
					
					// SEND MAIL INTENT //
					Intent intent = new Intent(Intent.ACTION_SENDTO);
					intent.setData(Uri.fromParts("mailto","futharkboard@drmaxnix.de", null));
					
					//set extra data
					intent.putExtra(Intent.EXTRA_SUBJECT, "Bug/suggestion for FutharkBoard v" + version);
					
					//start intent
					view.getContext().startActivity(intent);
					
				} catch(PackageManager.NameNotFoundException e){
					e.printStackTrace();
				}
			}
		});
		
		
		// OPEN-GH-BUTTON ONCLICK CALLBACK //
		Button open_gh = (Button)view.findViewById(R.id.open_gh);
		open_gh.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/DrMaxNix/futharkboard")));
			}
		});
		
		
		// OPEN-GH-LICENSE-BUTTON ONCLICK CALLBACK //
		Button open_gh_license = (Button)view.findViewById(R.id.open_gh_license);
		open_gh_license.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/DrMaxNix/futharkboard/blob/main/LICENSE")));
			}
		});
		
		
		// RETURN GENERATED VIEW //
		return view;
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
	
	}
	
	@Override
	public void onStart(){
		super.onStart();
		
		
		// RELOAD ENABLED-STATE OF IME //
		futhark_board_is_enabled_reload();
	}
	
	
	
	/*
		Reload all view related to the futhark_board_is_enabled-state
	*/
	private void futhark_board_is_enabled_reload(){
		boolean futhark_board_is_enabled = futhark_board_is_enabled();
		
		//get related views
		View status_not_enabled = view.findViewById(R.id.status_not_enabled);
		View status_enabled = view.findViewById(R.id.status_enabled);
		
		//change status banner
		if(!futhark_board_is_enabled){
			//not enabled, show info on how to enable
			status_not_enabled.setVisibility(View.VISIBLE);
			status_enabled.setVisibility(View.GONE);
			
		} else {
			//enabled, show that it's enabled
			status_not_enabled.setVisibility(View.GONE);
			status_enabled.setVisibility(View.VISIBLE);
		}
	}
	
	/*
		Check if the futharkboard is enabled in settings
	*/
	private boolean futhark_board_is_enabled(){
		// GET IMM //
		InputMethodManager imm = (InputMethodManager) this.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		
		
		// SEARCH //
		for(InputMethodInfo imi : imm.getEnabledInputMethodList()){
			if(imi.getServiceName().equals("de.drmaxnix.futharkboard.IME")){
				//found!
				return true;
			}
		}
		
		
		// NOTHING FOUND //
		return false;
	}
}