package org.allcolor.yahp.cl.converter;

import org.junit.Test;

import static org.allcolor.yahp.cl.converter.CHtmlToPdfFlyingSaucerTransformer.removeScript;
import static org.junit.Assert.*;

public class CHtmlToPdfFlyingSaucerTransformerTest {
  @Test
  public void removesScriptTagFromHtml() {
    String html = "<!DOCTYPE html>\n" +
        "<html lang=\"ru\" class=\"\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
        "  <head>\n" +
        "<style type=\"text/css\">\n" +
        "/*<![CDATA[*/" +
        "</style>\n" +
        "<script type=\"text/javascript\" src=\"/public/javascripts/jquery.js\">\n" +
        "//<![CDATA[\n" +
        "//]]>\n" +
        "</script>\n" +
        "<script type=\"text/javascript\"\n" +
        "src=\"/public/javascripts/jquery-migrate.min.js\">\n" +
        "//<![CDATA[\n" +
        "//]]>\n" +
        "</script>\n" +
        "</head>\n" +
        "</html>";
    assertEquals("<!DOCTYPE html>\n" +
        "<html lang=\"ru\" class=\"\" xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
        "  <head>\n" +
        "<style type=\"text/css\">\n" +
        "/*<![CDATA[*/" +
        "</style>\n" +
        "\n" +
        "\n" +
        "</head>\n" +
        "</html>", removeScript(html));
  }
}