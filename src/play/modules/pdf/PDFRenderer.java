package play.modules.pdf;

import static play.modules.pdf.PDF.generateTemplateAsPDF;
import static play.modules.pdf.PDF.renderTemplateAsPDF;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import org.allcolor.yahp.converter.IHtmlToPdfTransformer;

/**
 * Usage example:
 * 
 * 
 * {@code public class MyController extends Controller { protected static PDFRenderer pdfRenderer = new PDFRenderer();
 * 
 * public static void report(String depositId) { pdfRenderer.with("deposit", deposit).with("rate", rate).render(); } }
 */
@Singleton
public class PDFRenderer {
  public Builder with(String name, Object value) {
    return new Builder(name, value);
  }

  public void renderPDF(Map<String, Object> arguments, boolean inline, PDF.Options options) {
    renderTemplateAsPDF(PDF.templateNameFromAction("html"), arguments, inline, options);
  }

  public void renderPDF(String templateName, Map<String, Object> arguments, boolean inline, PDF.Options options) {
    renderTemplateAsPDF(templateName, arguments, inline, options);
  }

  public byte[] generatePDF(Map<String, Object> args, boolean inline, PDF.Options options) {
    return generateTemplateAsPDF(PDF.templateNameFromAction("html"), args, inline, options);
  }

  public byte[] generatePDF(String templateName, Map<String, Object> args, boolean inline, PDF.Options options) {
    return generateTemplateAsPDF(templateName, args, inline, options);
  }

  public class Builder {
    private final Map<String, Object> arguments = new HashMap<>();
    private boolean inline = true;
    private String fileName;
    private IHtmlToPdfTransformer.PageSize pageSize;

    private Builder(String name, Object value) {
      with(name, value);
    }

    public final Builder with(String name, Object value) {
      this.arguments.put(name, value);
      return this;
    }

    public void render() {
      PDFRenderer.this.renderPDF(this.arguments, this.inline, options());
    }

    public void render(String templateName) {
      PDFRenderer.this.renderPDF(templateName, this.arguments, this.inline, options());
    }

    public byte[] generate(String templateName) {
      return PDFRenderer.this.generatePDF(templateName, arguments, inline, options());
    }

    public byte[] generate() {
      return PDFRenderer.this.generatePDF(arguments, inline, options());
    }

    public Builder inline(boolean inline) {
      this.inline = inline;
      return this;
    }

    public Builder fileName(String fileName) {
      this.fileName = fileName;
      return this;
    }

    public Builder pageSize(IHtmlToPdfTransformer.PageSize pageSize) {
      this.pageSize = pageSize;
      return this;
    }

    private PDF.Options options() {
      if (this.fileName == null && this.pageSize == null) {
        return null;
      }
      PDF.Options options = new PDF.Options();
      if (this.fileName != null) {
        options.filename = this.fileName;
      }
      if (this.pageSize != null) {
        options.pageSize = this.pageSize;
      }
      return options;
    }
  }
}
