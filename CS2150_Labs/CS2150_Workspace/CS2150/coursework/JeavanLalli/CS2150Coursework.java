/* CS2150Coursework.java
 * TODO: put your university username and full name here
 *
 * Scene Graph:
 *  Scene origin
 *  |
 *
 *  TODO: Provide a scene graph for your submission
 */
package coursework.JeavanLalli;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import java.awt.Graphics;

import javax.swing.JOptionPane;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;
import GraphicsLab.*;
/**
 * TODO: Briefly describe your submission here
 *
 * <p>Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis, respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down cursor keys
 *      to increase or decrease the viewpoint's distance from the scene origin
 * </ul>
 * TODO: Add any additional controls for your sample to the list above
 *
 */
@SuppressWarnings("unused")
public class CS2150Coursework extends GraphicsLab
{  
    private final int planeList = 3;
    /** ids for nearest, linear and mipmapped textures for the ground plane */
    private Texture groundTextures;
	private Texture backTextures;
    private float timeday = 0.0f;
	private Texture ObjectTextures;
	
    /** ids for nearest, linear and mipmapped textures for the Borg cube */
    private Texture cubeTextures;
    /** the borg cube's current angle of rotation */
    private float cubeSpin = 0.0f;
	private Texture cubeTextures2;
    
    //TODO: Feel free to change the window title and default animation scale here
    public static void main(String args[])
    {   new CS2150Coursework().run(WINDOWED,"CS2150 Coursework Submission",0.01f);
    }

    protected void initScene() throws Exception
    {//TODO: Initialise your resources here - might well call other methods you write.	

        groundTextures = loadTexture("coursework/JeavanLalli/textures/grass1.bmp");
        
        // global ambient light level
        float globalAmbient3[]   = {0.2f,  0.2f,  0.2f, 1.0f};
        // set the global ambient lighting
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient3));

        // the first light for the scene is white...
        float diffuse03[]  = { 0.6f,  0.6f, 0.6f, 1.0f};
        // ...with a dim ambient contribution...
        float ambient03[]  = { 0.1f,  0.1f, 0.1f, 1.0f};
        // ...and is positioned above the viewpoint
        float position03[] = { 0.0f, 10.0f, 0.0f, 1.0f}; 

        // supply OpenGL with the properties for the first light
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient03));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse03));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse03));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position03));
        // enable the first light
        GL11.glEnable(GL11.GL_LIGHT0);

        // enable lighting calculations
        GL11.glEnable(GL11.GL_LIGHTING);
        // ensure that all normals are re-normalised after transformations automatically
        GL11.glEnable(GL11.GL_NORMALIZE);
        
        // prepare the display lists for later use

        GL11.glEndList();
        GL11.glNewList(planeList,GL11.GL_COMPILE);
        {   drawUnitPlane();
        }
        GL11.glEndList();
        
        backTextures = loadTexture("coursework/JeavanLalli/textures/Picture2.bmp");
        
        // global ambient light level
        float globalAmbient1[]   = {0.2f,  0.2f,  0.2f, 1.0f};
        // set the global ambient lighting
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient1));

        // the first light for the scene is white...
        float diffuse01[]  = { 0.6f,  0.6f, 0.6f, 1.0f};
        // ...with a dim ambient contribution...
        float ambient01[]  = { 0.1f,  0.1f, 0.1f, 1.0f};
        // ...and is positioned above the viewpoint
        float position01[] = { 0.0f, 10.0f, 0.0f, 1.0f}; 

        // supply OpenGL with the properties for the first light
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient01));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse01));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse01));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position01));
        // enable the first light
        GL11.glEnable(GL11.GL_LIGHT0);

        // enable lighting calculations
        GL11.glEnable(GL11.GL_LIGHTING);
        // ensure that all normals are re-normalised after transformations automatically
        GL11.glEnable(GL11.GL_NORMALIZE);
        
        // prepare the display lists for later use

        GL11.glEndList();
        GL11.glNewList(planeList,GL11.GL_COMPILE);
        {   drawUnitPlane();
        }
        GL11.glEndList();
        
        ObjectTextures = loadTexture("coursework/JeavanLalli/textures/diamond2.bmp");
        
        // global ambient light level
        float globalAmbient2[]   = {0.2f,  0.2f,  0.2f, 1.0f};
        // set the global ambient lighting
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient1));

        // the first light for the scene is white...
        float diffuse02[]  = { 0.6f,  0.6f, 0.6f, 1.0f};
        // ...with a dim ambient contribution...
        float ambient02[]  = { 0.1f,  0.1f, 0.1f, 1.0f};
        // ...and is positioned above the viewpoint
        float position02[] = { 0.0f, 10.0f, 0.0f, 1.0f}; 

        // supply OpenGL with the properties for the first light
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient01));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse01));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse01));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position01));
        // enable the first light
        GL11.glEnable(GL11.GL_LIGHT0);

        // enable lighting calculations
        GL11.glEnable(GL11.GL_LIGHTING);
        // ensure that all normals are re-normalised after transformations automatically
        GL11.glEnable(GL11.GL_NORMALIZE);
        
        // prepare the display lists for later use

        GL11.glEndList();
        GL11.glNewList(planeList,GL11.GL_COMPILE);
        {   drawUnitPlane();
        }
        GL11.glEndList();
        
        cubeTextures = loadTexture("coursework/JeavanLalli/textures/diamond2.bmp");

        // global ambient light level
        float globalAmbient4[]   = {0.2f,  0.2f,  0.2f, 1.0f};
        // set the global ambient lighting
        GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT,FloatBuffer.wrap(globalAmbient2));

        // the first light for the scene is white...
        float diffuse04[]  = { 0.8f,  0.8f, 0.8f, 1.0f};
        // ...with a dim ambient contribution...
        float ambient04[]  = { 0.1f,  0.1f, 0.1f, 1.0f};
        // ...and is positioned above the viewpoint
        float position04[] = { 0.0f, 5.0f, 0.0f, 1.0f};

        // supply OpenGL with the properties for the first light
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient02));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse02));
        GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position02));
        // enable the first light
        GL11.glEnable(GL11.GL_LIGHT0);

        // enable lighting calculations
        GL11.glEnable(GL11.GL_LIGHTING);
        // ensure that all normals are re-normalised after transformations automatically
        GL11.glEnable(GL11.GL_NORMALIZE);

        // use a flat shade model for the cube - the appearance of the cube
        // will be mostly determined by the borg texture anyway
        GL11.glShadeModel(GL11.GL_FLAT);
	}

	protected void checkSceneInput()
    {
    }
    protected void updateScene()
    {
        timeday += + 0.5f * getAnimationScale();   
        
        cubeSpin += 100.0f * getAnimationScale();
        
    }
    protected void renderScene()
    {//TODO: Render your scene here - remember that a scene graph will help you write this method! 
         {
             // how shiny are the front faces of the borg cube (specular exponent)
             float cubeFrontShininess  = 20.0f;
             // specular reflection of the front faces of the borg cube
             float cubeFrontSpecular[] = {1.0f, 1.0f, 1.0f, 1.0f};
             // diffuse reflection of the front faces of the borg cube
             float cubeFrontDiffuse[]  = {0.8f, 0.8f, 0.8f, 1.0f};
             
             // animate the emissive property of the borg cube so that it
             // pulsates as the cube rotates
             double emission = java.lang.Math.sin(cubeSpin / 45.0f);
             // emission must always be positive, and in the range 0.0 - 1.0
             emission = java.lang.Math.abs(emission);
             // scale the emission so that we get values between 0.0 and 0.01
             emission /= 10.0;
             // emissive property of the borg cube's front faces - a pulsating green glow!
             float cubeFrontEmissive[] = {0.0f, (float)emission, 0.0f, 1.0f};

             
             // set the material properties for the borg cube using OpenGL
             GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, cubeFrontShininess);
             GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(cubeFrontSpecular));
             GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(cubeFrontDiffuse));
             GL11.glMaterial(GL11.GL_FRONT, GL11.GL_EMISSION, FloatBuffer.wrap(cubeFrontEmissive));

             // enable texturing and bind an appropriate texture
             GL11.glEnable(GL11.GL_TEXTURE_2D);
             GL11.glBindTexture(GL11.GL_TEXTURE_2D,cubeTextures.getTextureID());

             // rotate, scale, position and draw the borg cube
             GL11.glTranslatef(-0.1f, 4.0f, -15.0f);
             GL11.glScalef(80.0f, 80.0f, 80.0f);
             GL11.glRotatef(cubeSpin, 0.0f, 1.0f, 0.0f);
             drawUnitCube();

             GL11.glDisable(GL11.GL_TEXTURE_2D);
         }
         GL11.glPopMatrix();
    	
    	GL11.glPushMatrix();
        {
            // disable lighting calculations so that they don't affect
            // the appearance of the texture 
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            // change the geometry colour to white so that the texture
            // is bright and details can be seen clearly
            Colour.WHITE.submit();
            // enable texturing and bind an appropriate texture
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,groundTextures.getTextureID());
            
            // position, scale and draw the ground plane using its display list
            GL11.glTranslatef(0.0f,-2.5f,-10.0f);
            GL11.glScaled(25.0f, 1.0f, 10.0f);
            GL11.glCallList(planeList);

            // disable textures and reset any local lighting changes
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        GL11.glPopMatrix();
        
        // draw the back plane
        GL11.glPushMatrix();
        {
            GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
            GL11.glDisable(GL11.GL_LIGHTING);
            // change the geometry colour to white so that the texture
            // is bright and details can be seen clearly
            Colour.WHITE.submit();
            // enable texturing and bind an appropriate texture
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D,backTextures.getTextureID());
            
            GL11.glTranslatef(3.0f,6.0f,-20.0f);
            GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
            GL11.glScaled(30.0f, 1.0f, 18.7f);
            GL11.glCallList(planeList);
            
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glPopAttrib();
        }
        GL11.glPushMatrix();
 
        GL11.glPopMatrix();
        final Colour white = new Colour(0.7f,0.7f,0.7f);
        GL11.glPushMatrix();
        GL11.glPopMatrix();
        {
            GL11.glRotatef((360.0f*timeday*0.3f),0.0f,1.0f,0.0f);
            GL11.glTranslatef(0.3f,0.4f,-8.0f);
            drawBody(white, 0.03f);
            
            GL11.glRotatef((360.0f*timeday*0.4f),0.0f,1.0f,0.0f);
            GL11.glTranslatef(0.4f,0.4f,-8.0f);
            drawBody(white, 0.03f);
            
            GL11.glRotatef((360.0f*timeday*0.4f),0.0f,1.0f,0.0f);
            GL11.glTranslatef(0.4f,0.4f,-8.0f);
            drawBody(white, 0.03f);
            
            GL11.glRotatef((360.0f*timeday*0.2f),0.0f,1.0f,0.0f);
            GL11.glTranslatef(0.2f,0.4f,-8.0f);
            drawBody(white, 0.03f);
            GL11.glRotatef((360.0f*timeday*0.2f),0.0f,1.0f,0.0f);
            GL11.glTranslatef(0.2f,0.4f,-8.0f);
            drawBody(white, 0.03f);
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        {
            GL11.glRotatef((360.0f*timeday*0.3f),1.0f,1.0f,0.0f);
            GL11.glTranslatef(0.3f,0.4f,-8.0f);
            drawBody(white, 0.05f);
            
            GL11.glRotatef((360.0f*timeday*0.4f),1.0f,1.0f,0.0f);
            GL11.glTranslatef(0.4f,0.4f,-8.0f);
            drawBody(white, 0.03f);
            
            GL11.glRotatef((360.0f*timeday*0.4f),1.0f,1.0f,0.0f);
            GL11.glTranslatef(0.4f,0.4f,-8.0f);
            drawBody(white, 0.03f);
            
            GL11.glRotatef((360.0f*timeday*0.2f),1.0f,1.0f,0.0f);
            GL11.glTranslatef(0.2f,0.4f,-8.0f);
            drawBody(white, 0.03f);
            GL11.glRotatef((360.0f*timeday*0.2f),1.0f,1.0f,0.0f);
            GL11.glTranslatef(0.2f,0.4f,-8.0f);
            drawBody(white, 0.03f);
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        {
            GL11.glRotatef((360.0f*timeday*0.2f),2.0f,1.0f,0.0f);
            GL11.glTranslatef(0.2f,0.4f,-8.0f);
            drawBody(white, 0.03f);
            
            GL11.glRotatef((360.0f*timeday*0.3f),2.0f,1.0f,0.0f);
            GL11.glTranslatef(0.3f,0.4f,-8.0f);
            drawBody(white, 0.03f);
            
            GL11.glRotatef((360.0f*timeday*0.4f),2.0f,1.0f,0.0f);
            GL11.glTranslatef(0.4f,0.4f,-8.0f);
            drawBody(white, 0.03f);
            
            GL11.glRotatef((360.0f*timeday*0.2f),2.0f,1.0f,0.0f);
            GL11.glTranslatef(0.2f,0.4f,-8.0f);
            drawBody(white, 0.03f);
            GL11.glRotatef((360.0f*timeday*0.2f),2.0f,1.0f,0.0f);
            GL11.glTranslatef(0.2f,0.4f,-8.0f);
            drawBody(white, 0.03f);
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        {
            GL11.glRotatef((360.0f*timeday*0.2f),2.0f,1.0f,0.0f);
            GL11.glTranslatef(0.2f,0.4f,-8.0f);
            drawBody(white, 0.03f);
            
            GL11.glRotatef((360.0f*timeday*0.2f),2.0f,1.0f,0.0f);
            GL11.glTranslatef(0.2f,0.4f,-8.0f);
            drawBody(white, 0.03f);
            
            GL11.glRotatef((360.0f*timeday*0.2f),2.0f,1.0f,0.0f);
            GL11.glTranslatef(0.2f,0.4f,-8.0f);
            drawBody(white, 0.03f);
           
            GL11.glRotatef((360.0f*timeday*0.2f),2.0f,1.0f,0.0f);
            GL11.glTranslatef(0.2f,0.4f,-8.0f);
            drawBody(white, 0.03f);
            GL11.glRotatef((360.0f*timeday*0.2f),2.0f,1.0f,0.0f);
            GL11.glTranslatef(0.2f,0.4f,-8.0f);
            drawBody(white, 0.03f);
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        {
            GL11.glTranslated(-0.1f,1.9f,-10.0f);
            drawBody1(Colour.WHITE, 0.5f);
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        {
            
            GL11.glTranslated(-0.1f,0.7f,-10.0f);
            drawBody1(Colour.WHITE, 0.8f);
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        {
            
            GL11.glTranslated(-0.1f,-1.0f,-10.0f);
            drawBody1(Colour.WHITE, 1.0f);
        }
        GL11.glPopMatrix();
    }
    protected void setSceneCamera()
    {
        // call the default behaviour defined in GraphicsLab. This will set a default perspective projection
        // and default camera settings ready for some custom camera positioning below...  
        super.setSceneCamera();

        // position the camera up and back a little


        //TODO: If it is appropriate for your scene, modify the camera's position and orientation here
        //        using a call to GL11.gluLookAt(...)
   }

    protected void cleanupScene()
    {//TODO: Clean up your resources here
    }
    private void drawBody(Colour colour, float size)
    {
        colour.submit();
        new Sphere().draw(size, 20, 20);
    }
    private void drawBody1(Colour colour, float size)
    {
        colour.submit();
        new Sphere().draw(size, 20, 20);
    }
    private void drawUnitPlane()
    {
        Vertex v1 = new Vertex(-0.5f, 0.0f,-0.5f); // left,  back
        Vertex v2 = new Vertex( 0.5f, 0.0f,-0.5f); // right, back
        Vertex v3 = new Vertex( 0.5f, 0.0f, 0.5f); // right, front
        Vertex v4 = new Vertex(-0.5f, 0.0f, 0.5f); // left,  front
        
        // draw the plane geometry. order the vertices so that the plane faces up
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v4.toVector(),v3.toVector(),v2.toVector(),v1.toVector()).submit();
            
            GL11.glTexCoord2f(0.0f,0.0f);
            v4.submit();
            
            GL11.glTexCoord2f(1.0f,0.0f);
            v3.submit();
            
            GL11.glTexCoord2f(1.0f,1.0f);
            v2.submit();
            
            GL11.glTexCoord2f(0.0f,1.0f);
            v1.submit();
        }
        GL11.glEnd();
        
        // if the user is viewing an axis, then also draw this plane
        // using lines so that axis aligned planes can still be seen
        if(isViewingAxis())
        {
            // also disable textures when drawing as lines
            // so that the lines can be seen more clearly
            GL11.glPushAttrib(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glBegin(GL11.GL_LINE_LOOP);
            {
                v4.submit();
                v3.submit();
                v2.submit();
                v1.submit();
            }
            GL11.glEnd();
            GL11.glPopAttrib();
        }
    }
   
	private void drawUnitCube() {
		// TODO Auto-generated method stub
		 // the vertices for the cube (note that all sides have a length of 1)
        Vertex v9 = new Vertex(-0.02f, -0.02f,  0.02f);
        Vertex v10 = new Vertex(-0.02f,  0.02f,  0.02f);
        Vertex v11 = new Vertex( 0.02f,  0.02f,  0.02f);
        Vertex v12 = new Vertex( 0.02f, -0.02f,  0.02f);
        Vertex v5 = new Vertex(-0.02f, -0.02f, -0.02f);
        Vertex v6 = new Vertex(-0.02f,  0.02f, -0.02f);
        Vertex v7 = new Vertex( 0.02f,  0.02f, -0.02f);
        Vertex v8 = new Vertex( 0.02f, -0.02f, -0.02f);

        // draw the near face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v11.toVector(),v10.toVector(),v9.toVector(),v12.toVector()).submit();
            
            GL11.glTexCoord2f(1.0f,1.0f);
            v11.submit();
            GL11.glTexCoord2f(0.0f,1.0f);
            v10.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v9.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v12.submit();
        }
        GL11.glEnd();
        // draw the left face:


        // draw the far face:
        GL11.glBegin(GL11.GL_POLYGON);
        {
            new Normal(v6.toVector(),v7.toVector(),v8.toVector(),v5.toVector()).submit();
            
            GL11.glTexCoord2f(1.0f,1.0f);
            v6.submit();
            GL11.glTexCoord2f(0.0f,1.0f);
            v7.submit();
            GL11.glTexCoord2f(0.0f,0.0f);
            v8.submit();
            GL11.glTexCoord2f(1.0f,0.0f);
            v5.submit();
        }
        GL11.glEnd();
	}
}
