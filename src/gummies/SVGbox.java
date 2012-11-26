package gummies;

import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;

import pbox2d.*;
import processing.core.*;

public class SVGbox {
	PApplet parent;

	// We need to keep track of a Body and a width and height
	Body body;
	float w;
	float h;
	float c;

	// A reference to our box2d world
	PBox2D box2d;

	// Constructor
	SVGbox(PApplet p, PBox2D box2d_, float x, float y, float _w, float _h, float _c) {
		parent = p;
		box2d = box2d_;
		w = _w;
		h = _h;
		c = _c;
		// Add the box to the box2d world
		makeBody(new Vec2(x, y), w, h);
	}

	// This function removes the particle from the box2d world
	void killBody() {
		box2d.destroyBody(body);
	}

	// Is the particle ready for deletion?
	boolean done() {
		// Let's find the screen position of the particle
		Vec2 pos = box2d.getBodyPixelCoord(body);
		// Is it off the bottom of the screen?
		if (pos.y > Gummies.mHeight + w * h) {
			killBody();
			return true;
		}
		return false;
	}

	// Drawing the box
	void display() {
		// We look at each body and get its screen position
		Vec2 pos = box2d.getBodyPixelCoord(body);
		// Get its angle of rotation
		float a = body.getAngle();

		parent.rectMode(parent.CENTER);
		parent.pushMatrix();
		parent.translate(pos.x, pos.y);
		parent.rotate(-a);
		parent.fill(c);
		parent.stroke(0);
		parent.rect(0, 0, w, h);
		parent.popMatrix();
	}

	// This function adds the rectangle to the box2d world
	void makeBody(Vec2 center, float w_, float h_) {

		// Define a polygon (this is what we use for a rectangle)
		PolygonShape sd = new PolygonShape();
		float box2dW = box2d.scalarPixelsToWorld(w_ / 2);
		float box2dH = box2d.scalarPixelsToWorld(h_ / 2);
		sd.setAsBox(box2dW, box2dH);

		// Define a fixture
		FixtureDef fd = new FixtureDef();
		fd.shape = sd;
		// Parameters that affect physics
		fd.density = 1000;
		fd.friction = (float) 0.3;
		fd.restitution = (float) 0.5;

		// Define the body and make it from the shape
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DYNAMIC;
		bd.position.set(box2d.coordPixelsToWorld(center));

		body = box2d.createBody(bd);
		body.createFixture(fd);

		// Give it some initial random velocity
		body.setLinearVelocity(new Vec2(parent.random(-50, 50), parent.random(
				-5000, -10000 )));
		body.setAngularVelocity(parent.random(-50, 50));
	}

}

