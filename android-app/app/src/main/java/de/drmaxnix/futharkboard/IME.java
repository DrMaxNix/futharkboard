package de.drmaxnix.futharkboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class IME extends InputMethodService implements KeyboardView.OnKeyboardActionListener {
	@Override
	public View onCreateInputView(){
		// APPLY KEYBOARD LAYOUT //
		KeyboardView keyboardView = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard_view, null);
		Keyboard keyboard = new Keyboard(this, R.xml.futhark);
		keyboardView.setKeyboard(keyboard);
		keyboardView.setOnKeyboardActionListener(this);
		return keyboardView;
	}
	
	@Override
	public void onKey(int primaryCode, int[] keyCodes){
		// GET INPUT-CONNECTION //
		InputConnection input_connection = getCurrentInputConnection();
		
		//make sure we got something
		if(input_connection == null){
			return;
		}
		
		
		// HANDLE INPUT //
		switch(primaryCode){
			case Keyboard.KEYCODE_DELETE:
				// DELETE KEY //
				//check if some text is selected
				CharSequence selectedText = input_connection.getSelectedText(0);
				if (TextUtils.isEmpty(selectedText)) {
					//no selection, delete previous character
					input_connection.deleteSurroundingText(1, 0);
					
				} else {
					//delete selection
					input_connection.commitText("", 1);
				}
				break;
				
			default:
				// NORMAL CHARACTER INPUT //
				char code = (char)primaryCode;
				input_connection.commitText(String.valueOf(code), 1);
		}
	}
	
	@Override
	public void onPress(int primaryCode) { }
	
	@Override
	public void onRelease(int primaryCode) { }
	
	@Override
	public void onText(CharSequence text) { }
	
	@Override
	public void swipeLeft() { }
	
	@Override
	public void swipeRight() { }
	
	@Override
	public void swipeDown() { }
	
	@Override
	public void swipeUp() { }
}
