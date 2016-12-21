package org.xhtmlrenderer.swing;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static play.vfs.VirtualFile.fromRelativePath;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import play.Play;

public class NaiveUserAgentTest {

  private NaiveUserAgent naiveUserAgent = new NaiveUserAgent();

  private String userDir;

  @Before
  public void setUp() {
    Play.roots = asList(fromRelativePath("src"), fromRelativePath("test"), fromRelativePath("conf"));
    this.naiveUserAgent.setBaseURL("http://myserver.com");
    this.userDir = System.getProperty("user.dir").replace('\\', '/');
    if (!this.userDir.startsWith("/")) {
      this.userDir = "/" + this.userDir;
    }
  }

  @Ignore
  @Test
  public void resolveUrlToLocalFile() {
    assertEquals("file:" + this.userDir + "/conf/dependencies.yml", naiveUserAgent.resolveURI("dependencies.yml"));
  }

  @Ignore
  @Test
  public void ignoresUrlParamsWhenResolvingToLocalFile() {
    assertEquals("file:" + this.userDir + "/conf/dependencies.yml",
        naiveUserAgent.resolveURI("dependencies.yml?123213231"));
  }

  @Test
  public void resolvesToExternalUrlIfLocalFileNotFound() {
    assertEquals("http://myserver.com/favicon.ico", naiveUserAgent.resolveURI("/favicon.ico"));
  }
}