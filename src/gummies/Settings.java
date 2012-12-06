package gummies;
import processing.core.PApplet;
import processing.core.PShape;

public class Settings {
	PApplet parent;
	String [] title;
	String names;
	float floodStart, floodEnd, floodRate, waveHeight, launchRate, decayRate;
	
	Settings(PApplet p, String[] t, String n, float fS, float fE, float fR, float wH, float lR, float dR) {
		parent = p;
		title = t;
		names = n;
		floodStart = fS;
		floodEnd = fE;
		floodRate = fR;
		waveHeight = wH;
		launchRate = lR;
		decayRate = dR;
	}

}
