package play.modules.pdf;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * Usage example:
 * 
 * 
 * {@code
 *   public class MyController extends Controller {
 *     protected static PDFRenderer pdfRenderer = new PDFRenderer();
 *   
 *     public static void report(String depositId) {
 *        pdfRenderer.with("deposit", deposit).with("rate", rate).render();
 *     }
 * }
 */
@Singleton
public class PDFRenderer {
  public Builder with(String name, Object value) {
    return new Builder(name, value);
  }
  
  public void renderPDF(Map<String, Object> arguments) {
    // TODO Use another method that uses `arguments` instead of `LocalvariablesNamesEnhancer`
    PDF.renderPDF(arguments);
  }

  public void renderPDF(String templateName, Map<String, Object> arguments) {
    // TODO Use another method that uses `arguments` instead of `LocalvariablesNamesEnhancer`
    PDF.renderPDF(templateName, arguments);
  }

  public class Builder {
    private final Map<String, Object> arguments = new HashMap<>();

    private Builder(String name, Object value) {
      with(name, value);
    }

    public final Builder with(String name, Object value) {
      arguments.put(name, value);
      return this;
    }

    public void render() {
      PDFRenderer.this.renderPDF(arguments);
    }

    public void render(String templateName) {
      PDFRenderer.this.renderPDF(templateName, arguments);
    }
  }
}
