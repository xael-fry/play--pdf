package play.modules.pdf;

import javax.inject.Singleton;
import java.security.Policy;
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
  public Parameters with(String name, Object value) {
    return new Parameters(name, value);
  }

  public class Parameters {
    private final Map<String, Object> parameters = new HashMap<>();

    private Parameters(String name, Object value) {
      with(name, value);
    }

    public final Parameters with(String name, Object value) {
      parameters.put(name, value);
      return this;
    }

    public void render() {
      // TODO Use another method that uses `parameters` instead of `LocalvariablesNamesEnhancer`
      PDF.renderPDF(parameters);
    }

    public void render(String templateName) {
      // TODO Use another method that uses `parameters` instead of `LocalvariablesNamesEnhancer`
      PDF.renderPDF(templateName, parameters);
    }
  }
}
