package engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;
import java.nio.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class TextureLoader {

    public static Texture loadTexture(String path) {

        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        IntBuffer comp = BufferUtils.createIntBuffer(1);

        STBImage.stbi_set_flip_vertically_on_load(true);

        ByteBuffer image = STBImage.stbi_load(path, w, h, comp, 4);

        if (image == null)
            throw new RuntimeException("No se pudo cargar la textura: " + path);

        int texID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texID);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8,
            w.get(0), h.get(0), 0,
            GL_RGBA, GL_UNSIGNED_BYTE, image);

        glGenerateMipmap(GL_TEXTURE_2D);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

        STBImage.stbi_image_free(image);

        return new Texture(texID, w.get(0), h.get(0));
    }
}
