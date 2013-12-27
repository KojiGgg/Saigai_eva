package com.myspace.saigai_eva;
import org.apache.cordova.DroidGap;
import android.os.Bundle;

public class phonegap extends DroidGap {
	@Override
	public void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		super.loadUrl("file:///D:/workspace/Sangai_eva/assets/www/index.php");
	}
}
